package uk.ac.nott.cs.comp2013.froggerApp.view.level;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameObject;

public class BackgroundImage extends GameObject {
	public BackgroundImage(String imageLink) {
		setImage(new Image(imageLink, BoardConfig.WIDTH, BoardConfig.HEIGHT, true, true));
	}
}
