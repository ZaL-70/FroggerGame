package uk.ac.nott.cs.comp2013.froggerApp.actors.level;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.actors.Actor;

public class WetTurtle extends Actor {
	public static final String TURTLE_WET_ANIMATION1 = "file:src/main/resources/imgs/obstacle/TurtleAnimation2Wet.png";
	public static final String TURTLE_WET_ANIMATION2 = "file:src/main/resources/imgs/obstacle/TurtleAnimation3Wet.png";
	public static final String TURTLE_WET_ANIMATION3 = "file:src/main/resources/imgs/obstacle/TurtleAnimation4Wet.png";

	Image turtle1;
	Image turtle2;
	Image turtle3;
	Image turtle4;

	int speed;
	boolean sunk = false;

	@Override
	public void act(long now) {
		if (now/900000000 % 4 == 0) {
			setImage(turtle2);
			sunk = false;
		}
		else if (now/900000000 % 4 == 1) {
			setImage(turtle1);
			sunk = false;
		}
		else if (now/900000000 % 4 == 2) {
			setImage(turtle3);
			sunk = false;
		}
		else if (now/900000000 % 4 == 3) {
			setImage(turtle4);
			sunk = true;
		}
		move(speed,0);
		if (getX() > 600 && speed > 0)
			setX(-200);
		if (getX() < -75 && speed < 0)
			setX(600);
	}

	public boolean isSunk() {
		return sunk;
	}

	public void setTurtleImages(Image turtle1, Image turtle2, Image turtle3, Image turtle4) {
		this.turtle1 = turtle1;
		this.turtle2 = turtle2;
		this.turtle3 = turtle3;
		this.turtle4 = turtle4;
		setImage(turtle2);
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
