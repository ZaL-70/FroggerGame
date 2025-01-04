package uk.ac.nott.cs.comp2013.froggerApp.view.level;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameObject;

public class Digit extends GameObject {
	Image imgDigit;
	
	public Digit(int n, int size, int xpos, int ypos) {
		imgDigit = new Image("file:src/main/resources/imgs/info/"+n+".png", size, size, true, true);
		setImage(imgDigit);
		setX(xpos);
		setY(ypos);
	}
	
}
