package uk.ac.nott.cs.comp2013.froggerApp.controller;

import javafx.animation.AnimationTimer;
import uk.ac.nott.cs.comp2013.froggerApp.actors.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.MyStage;

public class GameTimer {
    Animal animal;
    MyStage background;
    GameLogic logicHandler;
    public AnimationTimer timer;

    public GameTimer(Animal animal, MyStage background, GameLogic logicHandler) {
        this.animal = animal;
        this.background = background;
        this.logicHandler = logicHandler;
    }

    public void createTimer() {
    }

    public void startTimer() {
    }

    public void stopTimer() {
    }
}
