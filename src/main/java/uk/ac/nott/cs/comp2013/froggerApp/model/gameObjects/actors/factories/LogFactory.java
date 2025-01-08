package uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.factories;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.level.Log;;

public class LogFactory {
    public static Log createLog(String imageLink, int size, double xpos, int row, double speed) {
        Log log = new Log();
        log.setImage(new Image(imageLink, size, size, true, true));
        log.setX(xpos);
        log.setY(BoardConfig.rowToY(row));
        log.setSpeed(speed);
        return log;
    }
}
