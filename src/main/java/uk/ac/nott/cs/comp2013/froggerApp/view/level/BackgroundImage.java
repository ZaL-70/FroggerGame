package uk.ac.nott.cs.comp2013.froggerApp.view.level;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.Actor;

public class BackgroundImage extends Actor {

	@Override
	public void act(long now) {
	}

	public BackgroundImage(String imageLink) {
		setImage(new Image(imageLink, 600, 800, true, true));
	}

}
