package uk.ac.nott.cs.comp2013.froggerApp.controller;

import javafx.animation.AnimationTimer;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player.Animal;

/**
 * Class monitoring the world & animal state via the
 * {@link GameLogic} logic handler
 */
public class GameTimer {
    Animal animal;
    GameLogic logicHandler;
    public AnimationTimer timer;

    public GameTimer(Animal animal, GameLogic logicHandler) {
        this.animal = animal;
        this.logicHandler = logicHandler;
    }

    public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                logicHandler.handleLevelEnd();
                if(animal.getScoreChanged()) {
                    logicHandler.setNumber(animal.getPoints());
                }
                if(animal.getLivesChanged()) {
                    logicHandler.setLives();
                }
                if(logicHandler.handleGameEnd()) {
                    stopTimer();
                }
            }
        };
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() { timer.stop(); }
}