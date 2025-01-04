package uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.level;

import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.Actor;

public class Obstacle extends Actor {
	private double speed;

	@Override
	public void act(long now) {
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -50 && speed<0)
			setX(600);
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

}
