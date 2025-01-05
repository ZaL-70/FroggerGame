package uk.ac.nott.cs.comp2013.froggerApp.model.actors.factories;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.Log;

import static uk.ac.nott.cs.comp2013.froggerApp.view.level.LevelSetup.rowToY;

public class LogFactory {
    public static Log createLog(String imageLink, int size, int xpos, int row, double speed) {
        Log log = new Log();
        log.setImage(new Image(imageLink, size, size, true, true));
        log.setX(xpos);
        log.setY(rowToY(row));
        log.setSpeed(speed);
        return log;
    }
}
