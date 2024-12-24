package controller;

import javafx.animation.AnimationTimer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.nott.cs.comp2013.froggerApp.actors.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.controller.GameLogic;
import uk.ac.nott.cs.comp2013.froggerApp.controller.GameTimer;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.MyStage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameTimerTests {
    private Animal mockAnimal;
    private MyStage mockBackground;
    private GameLogic mockLogicHandler;
    private GameTimer gameTimer;

    @BeforeEach
    public void setUp() {
        mockAnimal = mock(Animal.class);
        mockBackground = mock(MyStage.class);
        mockLogicHandler = mock(GameLogic.class);
        gameTimer = new GameTimer(mockBackground, mockAnimal, mockLogicHandler);
    }

    @Test
    public void testCreateTimerScoreChange() {
        when(mockAnimal.changeScore()).thenReturn(true);
        when(mockAnimal.getPoints()).thenReturn(100);

        gameTimer.createTimer();
        gameTimer.timer.handle(0);

        verify(mockLogicHandler).setNumber(100);
        assertEquals(mockAnimal.getPoints(), 100);
    }

    @Test
    public void testCreateTimerGameEnd() {
        when(mockAnimal.getStop()).thenReturn(true);

        gameTimer.createTimer();
        gameTimer.timer.handle(0);

        verify(mockLogicHandler).handleGameEnd(true);
        assertEquals(mockAnimal.getPoints(), 0);
    }

    @Test
    public void testStartTimer() {
        AnimationTimer mockTimer = mock(AnimationTimer.class);
        gameTimer.timer = mockTimer;

        gameTimer.startTimer();

        verify(mockTimer).start();
    }

    @Test
    public void testStopTimer() {
        AnimationTimer mockTimer = mock(AnimationTimer.class);
        gameTimer.timer = mockTimer;

        gameTimer.stopTimer();

        verify(mockTimer).stop();
    }
}

