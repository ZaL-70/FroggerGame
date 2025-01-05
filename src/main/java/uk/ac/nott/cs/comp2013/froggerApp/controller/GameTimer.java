package uk.ac.nott.cs.comp2013.froggerApp.controller;

import javafx.animation.AnimationTimer;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.player.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.MyStage;

public class GameTimer {
    Animal animal;
    MyStage world;
    GameLogic logicHandler;
    public AnimationTimer timer;

    public GameTimer(MyStage world, Animal animal, GameLogic logicHandler) {
        this.world = world;
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