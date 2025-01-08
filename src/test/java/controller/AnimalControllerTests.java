package controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.testfx.framework.junit5.ApplicationTest;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.animationHandler.CarDeathAnimator;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.animationHandler.DeathAnimator;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.animationHandler.HomeTakenAnimator;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.animationHandler.WaterDeathAnimator;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.End;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.level.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.AnimalController;

import java.util.List;
import java.util.stream.Stream;

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
        when(mockAnimal.getState()).thenReturn(Animal.State.alive); // Mock alive state
        KeyEvent keyEvent = createKeyEvent(KeyCode.A);
        controller.onKeyPress(keyEvent);
        verify(mockAnimal).move(-PlayerConfig.MOVEMENT_X, 0);
        verify(mockAnimal).setImage(any());
    }

    @ParameterizedTest
    @EnumSource(value = Animal.State.class,
            names = { "carDeath", "waterDeath", "endDeath", "captured"})
    void testNoMoveOnState(Animal.State state) {
        when(mockAnimal.getState()).thenReturn(state); // Mock non-alive state
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
    void testUpdateStateWetTurtleDeath() {
        // Simulate interaction with WetTurtle
        WetTurtle mockWetTurtle = mock(WetTurtle.class);
        when(mockAnimal.getIntersectingObjects(WetTurtle.class)).thenReturn(List.of(mockWetTurtle));
        when(mockWetTurtle.isSunk()).thenReturn(true);
        controller.updateDeathState();
        verify(mockAnimal, atLeastOnce()).setState(Animal.State.waterDeath);
    }

    @Test
    void testUpdateStateWaterDeath() {
        when(mockAnimal.getY()).thenReturn(BoardConfig.WATER_BOUND-1);
        when(mockAnimal.getOnObstacle()).thenReturn(false);
        controller.updateDeathState();
        verify(mockAnimal, atLeastOnce()).setState(Animal.State.waterDeath);
    }

    static Stream<Arguments> animatorProvider() {
        return Stream.of(
                Arguments.of(new WaterDeathAnimator(), Animal.State.waterDeath, -50, -1),
                Arguments.of(new HomeTakenAnimator(), Animal.State.endDeath, -50, -1),
                Arguments.of(new CarDeathAnimator(), Animal.State.carDeath, -50, -1)
        );
    }
    @ParameterizedTest(name = "{1}")
    @MethodSource("animatorProvider")
    void testUpdateLivesAndScoreOnDeath(DeathAnimator animator, Animal.State state, int scoreChange, int lifeChange) {
        // Arrange
        when(mockAnimal.getState()).thenReturn(state);
        when(mockAnimal.getPoints()).thenReturn(100);
        // Act: Simulate death animation
        for (long now = 0; now <= 55; now++) { // Iterate to trigger death_time == 5
            animator.animate(mockAnimal, now);
        }
        // Assert lives & score adjusted
        verify(mockAnimal, times(1)).changeScore(scoreChange, true);
        verify(mockAnimal, times(1)).changeLives(lifeChange, true);
    }

    @Test
    void testLogInteraction() {
        Log mockLog = mock(Log.class);
        when(mockAnimal.getIntersectingObjects(Log.class)).thenReturn(List.of(mockLog));
        when(mockLog.getSpeed()).thenReturn(1.0);  // Use actual speed from Log
        controller.handleActorInteraction();
        verify(mockAnimal).move(mockLog.getSpeed(), 0); // Move left with the log
    }

    @Test
    void testTurtleInteraction() {
        Turtle mockTurtle = mock(Turtle.class);
        when(mockAnimal.getIntersectingObjects(Turtle.class)).thenReturn(List.of(mockTurtle));
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
    void testBirdInteraction() {
        Bird mockBird = mock(Bird.class);
        when(mockAnimal.getIntersectingObjects(Bird.class)).thenReturn(List.of(mockBird));
        controller.handleActorInteraction();
        verify(mockAnimal).move(mockBird.getSpeedX(), mockBird.getSpeedY()); // Move left with the turtle
    }

    @Test
    void testEndInteraction() {
        End mockEnd = mock(End.class);
        when(mockAnimal.getIntersectingObjects(End.class)).thenReturn(List.of(mockEnd));
        when(mockEnd.isActivated()).thenReturn(false);
        controller.handleActorInteraction();
        verify(mockAnimal).changeScore(50, true);
        verify(mockAnimal).incrementEnd();
        verify(mockEnd).setEnd();
    }
}
