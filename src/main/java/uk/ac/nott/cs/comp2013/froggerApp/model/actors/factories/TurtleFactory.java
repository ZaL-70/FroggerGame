package uk.ac.nott.cs.comp2013.froggerApp.model.actors.factories;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.Turtle;

public class TurtleFactory {
    public static Turtle createTurtle(int size, int xpos, int row, double speed) {
        Turtle turtle = new Turtle();
        turtle.setTurtleImages(
                new Image(TurtleConfig.IMAGE_PATHS.get("animation1"), size, size,true,true),
                new Image(TurtleConfig.IMAGE_PATHS.get("animation2"), size, size,true,true),
                new Image(TurtleConfig.IMAGE_PATHS.get("animation3"), size, size,true,true)
        );
        turtle.setX(xpos);
        turtle.setY(BoardConfig.rowToY(row) - 5);
        turtle.setSpeed(speed);
        return turtle;
    }
}
