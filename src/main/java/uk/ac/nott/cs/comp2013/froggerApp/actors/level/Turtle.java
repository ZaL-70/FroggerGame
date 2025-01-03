package uk.ac.nott.cs.comp2013.froggerApp.actors.level;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.actors.Actor;

public class Turtle extends Actor {
	public static final String TURTLE_ANIMATION1 = "file:src/main/resources/imgs/obstacle/TurtleAnimation1.png";
	public static final String TURTLE_ANIMATION2 = "file:src/main/resources/imgs/obstacle/TurtleAnimation2.png";
	public static final String TURTLE_ANIMATION3 = "file:src/main/resources/imgs/obstacle/TurtleAnimation3.png";

	Image turtle1;
	Image turtle2;
	Image turtle3;
	public int speed;

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
		if (getX() > 600 && speed > 0)
			setX(-200);
		if (getX() < -75 && speed < 0)
			setX(600);
	}

	public void setTurtleImages(Image turtle1, Image turtle2, Image turtle3) {
		this.turtle1 = turtle1;
		this.turtle2 = turtle2;
		this.turtle3 = turtle3;
		setImage(turtle2);
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
