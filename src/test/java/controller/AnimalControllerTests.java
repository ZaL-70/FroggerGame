package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import uk.ac.nott.cs.comp2013.froggerApp.actors.level.End;
import uk.ac.nott.cs.comp2013.froggerApp.actors.level.Log;
import uk.ac.nott.cs.comp2013.froggerApp.actors.level.Obstacle;
import uk.ac.nott.cs.comp2013.froggerApp.actors.level.WetTurtle;
import uk.ac.nott.cs.comp2013.froggerApp.actors.player.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.AnimalController;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AnimalControllerTests extends ApplicationTest {
    private Animal mockAnimal;
    private AnimalController controller;

    @BeforeEach
    void setUp() {
        mockAnimal = mock(Animal.class);
        controller = new AnimalController(mockAnimal);
    }

    @Test
    void testRespawn() {
        controller.respawn();

        verify(mockAnimal).setState(Animal.State.alive);
        verify(mockAnimal).setImage(any()); // Verify an image is set
        verify(mockAnimal).setX(300);
        verify(mockAnimal).setY(679.8 + 13.333333 * 2);
    }

    @Test
    void testHandleBoundaryTop() {
        when(mockAnimal.getY()).thenReturn(-1.0);

        controller.handleBoundary();

        verify(mockAnimal).setY(679.8 + 13.333333 * 2);
    }

    @Test
    void testHandleBoundaryBottom() {
        when(mockAnimal.getY()).thenReturn(735.0);

        controller.handleBoundary();

        verify(mockAnimal).setY(679.8 + 13.333333 * 2);
    }

    @Test
    void testHandleBoundaryLeft() {
        when(mockAnimal.getX()).thenReturn(-1.0);

        controller.handleBoundary();

        verify(mockAnimal).move(10.666666 * 2, 0); // Moved back into bounds
    }

    @Test
    void testHandleBoundaryRight() {
        when(mockAnimal.getX()).thenReturn(601.0);

        controller.handleBoundary();

        verify(mockAnimal).move(-10.666666 * 2, 0); // Moved back into bounds
    }

    @Test
    void testOnKeyPressMoveUp() {
        when(mockAnimal.getState()).thenReturn(Animal.State.alive); // Mock alive state

        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.W, false, false, false, false);
        controller.onKeyPress(keyEvent);

        verify(mockAnimal).move(0, -13.333333 * 2);
        verify(mockAnimal).setImage(any()); // Image for jumping upward
    }

    @Test
    void testOnKeyPressMoveLeft() {
        when(mockAnimal.getState()).thenReturn(Animal.State.alive); // Mock alive state

        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.A, false, false, false, false);
        controller.onKeyPress(keyEvent);

        verify(mockAnimal).getState();
        verify(mockAnimal).move(-10.666666 * 2, 0);
        verify(mockAnimal).setImage(any()); // Image for jumping left
    }

    @Test
    void testNoMoveOnCarDeath() {
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.A, false, false, false, false);
        controller.onKeyPress(keyEvent);

        when(mockAnimal.getState()).thenReturn(Animal.State.carDeath); // Mock non-alive state

        verify(mockAnimal).getState();
        verifyNoMoreInteractions(mockAnimal);
    }

    @Test
    void testUpdateStateCarDeath() {
        Obstacle mockObstacle = mock(Obstacle.class);
        when(mockAnimal.getIntersectingObjects(Obstacle.class)).thenReturn(List.of(mockObstacle));

        controller.updateDeathState();

        verify(mockAnimal).setState(Animal.State.carDeath);
    }

    @Test
    void testUpdateStateWaterDeath() {
        // Simulate interaction with WetTurtle
        WetTurtle mockWetTurtle = mock(WetTurtle.class);
        when(mockAnimal.getIntersectingObjects(WetTurtle.class)).thenReturn(List.of(mockWetTurtle));
        when(mockWetTurtle.isSunk()).thenReturn(true);

        controller.updateDeathState();

        verify(mockAnimal, atLeastOnce()).setState(Animal.State.waterDeath);
    }

    @Test
    void testLogInteraction() {
        Log mockLog = mock(Log.class);
        when(mockAnimal.getIntersectingObjects(Log.class)).thenReturn(List.of(mockLog));
        when(mockAnimal.getState()).thenReturn(Animal.State.alive); // Mock alive state
        when(mockLog.getLeft()).thenReturn(true);

        controller.handleActorInteraction();

        verify(mockAnimal).move(-2, 0); // Move left with the log
    }

    @Test
    void testEndInteraction() {
        End mockEnd = mock(End.class);
        when(mockAnimal.getIntersectingObjects(End.class)).thenReturn(List.of(mockEnd));
        when(mockEnd.isActivated()).thenReturn(false);

        controller.handleActorInteraction();

        verify(mockAnimal).changeScore(50, true);
        verify(mockAnimal).incrementStop();
        verify(mockEnd).setEnd();
    }
}
