package uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.factories;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.level.Turtle;

import static uk.ac.nott.cs.comp2013.froggerApp.view.level.LevelSetup.rowToY;

public class TurtleFactory {
    public static Turtle createTurtle(int size, int xpos, int row, int speed) {
        Turtle turtle = new Turtle();
        turtle.setTurtleImages(
                new Image(Turtle.TURTLE_ANIMATION1, size, size,true,true),
                new Image(Turtle.TURTLE_ANIMATION2, size, size,true,true),
                new Image(Turtle.TURTLE_ANIMATION3, size, size,true,true)
        );
        turtle.setX(xpos);
        turtle.setY(rowToY(row) - 5);
        turtle.setSpeed(speed);
        return turtle;
    }
}
