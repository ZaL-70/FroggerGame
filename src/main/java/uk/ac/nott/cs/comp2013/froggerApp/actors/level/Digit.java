package uk.ac.nott.cs.comp2013.froggerApp.actors.level;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.actors.Actor;

public class Digit extends Actor {
	Image imgDigit;

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
	}
	
	public Digit(int n, int size, int xpos, int ypos) {
		imgDigit = new Image("file:src/main/resources/imgs/info/"+n+".png", size, size, true, true);
		setImage(imgDigit);
		setX(xpos);
		setY(ypos);
	}
	
}
