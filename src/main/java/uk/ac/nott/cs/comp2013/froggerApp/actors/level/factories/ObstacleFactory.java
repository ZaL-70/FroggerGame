package uk.ac.nott.cs.comp2013.froggerApp.actors.level.factories;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.actors.level.Obstacle;

import static uk.ac.nott.cs.comp2013.froggerApp.view.LevelSetup.rowToY;

public class ObstacleFactory {
    public static Obstacle createObstacle(String imageLink, int size, int xpos, int row, int speed) {
        Obstacle obstacle = new Obstacle();
        obstacle.setImage(new Image(imageLink, size, size, true, true));
        obstacle.setX(xpos);
        obstacle.setY(rowToY(row));
        obstacle.setSpeed(speed);
        return obstacle;
    }
}
