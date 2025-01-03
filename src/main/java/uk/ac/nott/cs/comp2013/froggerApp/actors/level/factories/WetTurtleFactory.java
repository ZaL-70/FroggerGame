package uk.ac.nott.cs.comp2013.froggerApp.actors.level.factories;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.actors.level.Turtle;
import uk.ac.nott.cs.comp2013.froggerApp.actors.level.WetTurtle;

import static uk.ac.nott.cs.comp2013.froggerApp.view.LevelSetup.rowToY;

public class WetTurtleFactory {
    public static WetTurtle createWetTurtle(int size, int xpos, int row, int speed) {
        WetTurtle wetTurtle = new WetTurtle();
        wetTurtle.setTurtleImages(
                new Image(Turtle.TURTLE_ANIMATION1, size, size,true,true),
                new Image(WetTurtle.TURTLE_WET_ANIMATION1, size, size,true,true),
                new Image(WetTurtle.TURTLE_WET_ANIMATION2, size, size,true,true),
                new Image(WetTurtle.TURTLE_WET_ANIMATION3, size, size,true,true)
        );
        wetTurtle.setX(xpos);
        wetTurtle.setY(rowToY(row) - 5);
        wetTurtle.setSpeed(speed);
        return wetTurtle;
    }
}
