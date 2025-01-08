package uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.level;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.Actor;

public class WetTurtle extends Actor {
	Image turtle1, turtle2, turtle3, turtle4;

	double speed;
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
		if (getX() > BoardConfig.WIDTH && speed > 0)
			setX(0 - getWidth());
		if (getX() < (0 - getWidth()) && speed < 0)
			setX(BoardConfig.WIDTH);
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

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return speed;
	}

}
