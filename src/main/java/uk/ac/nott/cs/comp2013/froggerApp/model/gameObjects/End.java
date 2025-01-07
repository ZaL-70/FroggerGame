package uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;

public class End extends GameObject {
	boolean activated = false;
	
	public End(int x, int y) {
		setX(x);
		setY(y);
		setImage(new Image(EndPointConfig.IMAGE_PATHS.get("endPoint"), 60, 60, true, true));
	}
	
	public void setEnd() {
		setImage(new Image(EndPointConfig.IMAGE_PATHS.get("frogEnd"), 70, 70, true, true));
		activated = true;
	}
	
	public boolean isActivated() {
		return activated;
	}

}
