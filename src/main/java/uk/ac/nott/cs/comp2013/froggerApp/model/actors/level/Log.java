package uk.ac.nott.cs.comp2013.froggerApp.model.actors.level;

import uk.ac.nott.cs.comp2013.froggerApp.model.actors.Actor;

public class Log extends Actor {
	private double speed;

	@Override
	public void act(long now) {
		move(speed , 0);
		if (getX()>600 && speed>0)
			setX(-180);
		if (getX()<-300 && speed<0)
			setX(700);
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
