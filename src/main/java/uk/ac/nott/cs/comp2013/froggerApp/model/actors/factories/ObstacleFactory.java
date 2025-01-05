package uk.ac.nott.cs.comp2013.froggerApp.model.actors.factories;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.Obstacle;

public class ObstacleFactory {
    public static Obstacle createObstacle(String imageLink, int size, int xpos, int row, double speed) {
        Obstacle obstacle = new Obstacle();
        obstacle.setImage(new Image(imageLink, size, size, true, true));
        obstacle.setX(xpos);
        obstacle.setY(BoardConfig.rowToY(row));
        obstacle.setSpeed(speed);
        return obstacle;
    }
}
