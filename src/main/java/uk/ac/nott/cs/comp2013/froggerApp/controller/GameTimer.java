package uk.ac.nott.cs.comp2013.froggerApp.controller;

import javafx.animation.AnimationTimer;
import uk.ac.nott.cs.comp2013.froggerApp.actors.player.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.MyStage;

public class GameTimer {
    Animal animal;
    MyStage background;
    GameLogic logicHandler;
    public AnimationTimer timer;

    public GameTimer(MyStage background, Animal animal, GameLogic logicHandler) {
        this.background = background;
        this.animal = animal;
        this.logicHandler = logicHandler;
    }

    public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (animal.getScoreChanged()) {
                    logicHandler.setNumber(animal.getPoints());
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

    public void stopTimer() {
        timer.stop();
    }
}