package uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.factories;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.Actor;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.level.WetTurtle;

/**
 * Factory class for statically generating different types of
 * {@link WetTurtle} objects
 */
public class WetTurtleFactory {
    public static WetTurtle createWetTurtle(int size, double xpos, int row, double speed) {
        WetTurtle wetTurtle = new WetTurtle();
        wetTurtle.setTurtleImages(
                new Image(TurtleConfig.IMAGE_PATHS.get("animation1"), size, size,true,true),
                new Image(TurtleConfig.IMAGE_PATHS.get("wetAnimation1"), size, size,true,true),
                new Image(TurtleConfig.IMAGE_PATHS.get("wetAnimation2"), size, size,true,true),
                new Image(TurtleConfig.IMAGE_PATHS.get("wetAnimation3"), size, size,true,true)
        );
        wetTurtle.setX(xpos);
        wetTurtle.setY(BoardConfig.rowToY(row) - 5);
        wetTurtle.setSpeed(speed);
        return wetTurtle;
    }
}
