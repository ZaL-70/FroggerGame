package uk.ac.nott.cs.comp2013.froggerApp.controller;

import javafx.scene.control.Alert;
import uk.ac.nott.cs.comp2013.froggerApp.actors.Actor;
import uk.ac.nott.cs.comp2013.froggerApp.actors.player.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.actors.level.Digit;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.MyStage;

public class GameLogic {
    MyStage background;
    Animal animal;

    public GameLogic(MyStage background, Animal animal) {
        this.background = background;
        this.animal = animal;
    }

    public void setNumber(int n) {
        if(background.getChildren() != null) {
            background.getChildren().removeIf(node -> node instanceof Digit);
        }
        int shift = 0;
        while (n > 0) {
            int d = n / 10;
            int k = n - d * 10;
            n = d;
            background.add(new Digit(k,30, 360-shift, 25));
            shift += 30;
        }
    }

    public boolean handleGameEnd() {
        boolean stop = animal.getStop();
        if (stop) {
            System.out.print("STOPP:");
            background.stopMusic();
            background.stop();
            //showAlert();  // refactor to alternative method (add onscreen feature)
        }
        return stop;
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("You Have Won The Game!");
        alert.setHeaderText("Your High Score: " + animal.getPoints() + "!");
        alert.setContentText("Highest Possible Score: 800");
        alert.show();
    }
}