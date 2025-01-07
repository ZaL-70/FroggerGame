package uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.level;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.Actor;

public class Turtle extends Actor {
	Image turtle1, turtle2, turtle3;
	private double speed;

	@Override
	public void act(long now) {
		if (now/900000000 % 3 == 0) {
			setImage(turtle2);
		}
		else if (now/900000000 % 3 == 1) {
			setImage(turtle1);
		}
		else if (now/900000000 % 3 == 2) {
			setImage(turtle3);
		}
		move(speed,0);
		if (getX() > BoardConfig.WIDTH && speed > 0)
			setX(0 - getWidth());
		if (getX() < (0 - getWidth()) && speed < 0)
			setX(BoardConfig.WIDTH);
	}

	public void setTurtleImages(Image turtle1, Image turtle2, Image turtle3) {
		this.turtle1 = turtle1;
		this.turtle2 = turtle2;
		this.turtle3 = turtle3;
		setImage(turtle2);
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return speed;
	}

}
