package uk.ac.nott.cs.comp2013.froggerApp.controller;

import javafx.animation.AnimationTimer;
import uk.ac.nott.cs.comp2013.froggerApp.actors.Animal;
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
                if (animal.changeScore()) {
                    logicHandler.setNumber(animal.getPoints());
                }
                if(logicHandler.handleGameEnd(animal.getStop())) {
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