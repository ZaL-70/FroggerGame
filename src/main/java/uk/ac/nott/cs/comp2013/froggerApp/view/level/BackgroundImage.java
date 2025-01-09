package uk.ac.nott.cs.comp2013.froggerApp.view.level;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.GameObject;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.MyStage;

public class BackgroundImage extends GameObject {

	/**
	 * Create a base image on the {@link MyStage} world pane
	 * @param imageLink Background image filepath
	 */
	public BackgroundImage(String imageLink) {
		setImage(new Image(imageLink, BoardConfig.WIDTH, BoardConfig.HEIGHT, true, true));
	}
}
