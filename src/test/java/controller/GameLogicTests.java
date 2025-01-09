package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.testfx.framework.junit5.ApplicationTest;
import uk.ac.nott.cs.comp2013.froggerApp.view.level.Digit;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.controller.GameLogic;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.MyStage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameLogicTests extends ApplicationTest {
    private MyStage mockWorld;
    private Animal mockAnimal;
    private GameLogic gameLogic;

    @BeforeEach
    public void setUp() {
        mockWorld = mock(MyStage.class);
        mockAnimal = mock(Animal.class);
        gameLogic = new GameLogic(mockWorld, mockAnimal);
    }

    @Test
    public void testSetNumber() {
        gameLogic.setNumber(123);
        ArgumentCaptor<Digit> digitCaptor = ArgumentCaptor.forClass(Digit.class);
        verify(mockWorld, times(3)).add(digitCaptor.capture());
        // Assert correct digits were added
        assertEquals(3, digitCaptor.getAllValues().size());
    }

    @Test
    public void testHandleGameEndTrue() {
        when(mockAnimal.getStop()).thenReturn(true);
        when(mockAnimal.getPoints()).thenReturn(800);
        boolean ended = gameLogic.handleGameEnd();
        verify(mockWorld).stopMusic();
        verify(mockWorld).stop();
        assertTrue(ended);
    }

    @Test
    public void testHandleGameEndFalse() {
        when(mockAnimal.getStop()).thenReturn(false);
        when(mockAnimal.getPoints()).thenReturn(150);
        boolean ended = gameLogic.handleGameEnd();
        assertFalse(ended);
    }

    @Test
    public void testHandleLevelEndTrue() {
        when(mockAnimal.getEnd()).thenReturn(true);
        gameLogic.handleLevelEnd();
        verify(mockAnimal).changeLives(1, true);
    }
}
