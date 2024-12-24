package uk.ac.nott.cs.comp2013.froggerApp.controller;

import javafx.scene.control.Alert;
import uk.ac.nott.cs.comp2013.froggerApp.actors.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.actors.Digit;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.MyStage;

public class GameLogic {
    MyStage background;
    Animal animal;

    public GameLogic(MyStage background, Animal animal) {
        this.background = background;
        this.animal = animal;
    }

    public void setNumber(int n) {
    }

    public void handleGameEnd(boolean stop) {
    }

    public void showAlert() {
    }
}
