package uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.level;

import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.Actor;

public class Log extends Actor {
	private double speed;

	@Override
	public void act(long now) {
		move(speed,0);
		if (getX() > BoardConfig.WIDTH && speed > 0)
			setX(0 - getWidth());
		if (getX() < (0 - getWidth()) && speed < 0)
			setX(BoardConfig.WIDTH);
	}

	public boolean getLeft() {
		return speed < 0;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return speed;
	}

}
