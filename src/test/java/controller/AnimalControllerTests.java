package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import uk.ac.nott.cs.comp2013.froggerApp.model.End;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.Log;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.Obstacle;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.Turtle;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.WetTurtle;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.player.Animal;
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
    void testHandleBoundaryTop() {
        when(mockAnimal.getY()).thenReturn(BoardConfig.UPPER_BOUNDARY - 1);
        controller.handleBoundary();
        verify(mockAnimal).respawn();
    }

    @Test
    void testHandleBoundaryBottom() {
        when(mockAnimal.getY()).thenReturn(BoardConfig.LOWER_BOUNDARY + 1);
        controller.handleBoundary();
        verify(mockAnimal).respawn();
    }

    @Test
    void testHandleBoundaryLeft() {
        when(mockAnimal.getX()).thenReturn(BoardConfig.LEFT_BOUNDARY - 1);
        controller.handleBoundary();
        verify(mockAnimal).move(PlayerConfig.MOVEMENT_X, 0); // Moved back into bounds
    }

    @Test
    void testHandleBoundaryRight() {
        when(mockAnimal.getX()).thenReturn(BoardConfig.RIGHT_BOUNDARY + 1);
        controller.handleBoundary();
        verify(mockAnimal).move(-PlayerConfig.MOVEMENT_X, 0);
    }

    /* Helper function to make given key event */
    private KeyEvent createKeyEvent(KeyCode keyCode) {
        return new KeyEvent(KeyEvent.KEY_PRESSED, "", "", keyCode, false, false, false, false);
    }

    @Test
    void testOnKeyPressMoveUp() {
        when(mockAnimal.getState()).thenReturn(Animal.State.alive); // Mock alive state
        KeyEvent keyEvent = createKeyEvent(KeyCode.W);
        controller.onKeyPress(keyEvent);
        verify(mockAnimal).move(0, -PlayerConfig.MOVEMENT_Y);
        verify(mockAnimal).setImage(any());
    }

    @Test
    void testOnKeyPressMoveLeft() {
        when(mockAnimal.getState())
                .thenReturn(Animal.State.alive); // Mock alive state
        KeyEvent keyEvent = createKeyEvent(KeyCode.A);
        controller.onKeyPress(keyEvent);
        verify(mockAnimal).move(
                -PlayerConfig.MOVEMENT_X, 0);
        verify(mockAnimal).setImage(any());
    }

    @Test
    void testNoMoveOnCarDeath() {
        when(mockAnimal.getState()).thenReturn(Animal.State.carDeath); // Mock non-alive state
        KeyEvent keyEvent = createKeyEvent(KeyCode.S);
        controller.onKeyPress(keyEvent);
        verify(mockAnimal).getState();
        verify(mockAnimal, never()).move(any(Double.class), any(Double.class));
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
        when(mockLog.getSpeed()).thenReturn(1.0);  // Use actual speed from Log
        controller.handleActorInteraction();
        verify(mockAnimal).move(mockLog.getSpeed(), 0); // Move left with the log
    }

    @Test
    void testTurtleInteraction() {
        Turtle mockTurtle = mock(Turtle.class);
        when(mockAnimal.getIntersectingObjects(Turtle.class)).thenReturn(List.of(mockTurtle));
        when(mockAnimal.getState()).thenReturn(Animal.State.alive); // Mock alive state
        controller.handleActorInteraction();
        verify(mockAnimal).move(mockTurtle.getSpeed(), 0); // Move left with the turtle
    }

    @Test
    void testWetTurtleInteraction() {
        WetTurtle mockWetTurtle = mock(WetTurtle.class);
        when(mockAnimal.getIntersectingObjects(WetTurtle.class)).thenReturn(List.of(mockWetTurtle));
        // Mock returning un sunk turtle
        when(mockAnimal.getIntersectingObjects(WetTurtle.class).getFirst().isSunk()).thenReturn(false);
        controller.handleActorInteraction();
        verify(mockAnimal).move(mockWetTurtle.getSpeed(), 0); // Move left with the turtle
    }

    @Test
    void testWetTurtleDeath() {
        WetTurtle mockWetTurtle = mock(WetTurtle.class);
        when(mockAnimal.getIntersectingObjects(WetTurtle.class)).thenReturn(List.of(mockWetTurtle));
        // Mock returning sunk turtle
        when(mockAnimal.getIntersectingObjects(WetTurtle.class).getFirst().isSunk()).thenReturn(true);
        controller.handleActorInteraction();
        verify(mockAnimal, never()).move(any(Double.class), any(Double.class));
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
