package uk.ac.nott.cs.comp2013.froggerApp.view.level;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.GameObject;

public class Life extends GameObject {

    public Life(int size, int x, int y) {
        setX(x);
        setY(GameConfig.BoardConfig.rowToY(y));
        setImage(new Image("file:src/main/resources/imgs/info/life-heart.png", size, size, true, true));
    }

}
