package uk.ac.nott.cs.comp2013.froggerApp.actors;

import javafx.scene.image.Image;

public class End extends Actor {
	boolean activated = false;
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
	}
	
	public End(int x, int y) {
		setX(x);
		setY(y);
		setImage(new Image("file:src/main/resources/imgs/world/End.png", 60, 60, true, true));
	}
	
	public void setEnd() {
		setImage(new Image("file:src/main/resources/imgs/player/FrogEnd.png", 70, 70, true, true));
		activated = true;
	}
	
	public boolean isActivated() {
		return activated;
	}
	

}
