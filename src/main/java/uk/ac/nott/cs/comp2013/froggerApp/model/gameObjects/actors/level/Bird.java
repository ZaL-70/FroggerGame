package uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.level;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.Actor;

public class Bird extends Actor {
    Image bird1, bird2, bird3;

    double speed_x;
    double speed_y;
    private double initialX;
    private double initialY;

    @Override
    public void act(long now) {
        if (now/900000000 % 4 == 0) {
            setImage(bird2);
        }
        else if (now/900000000 % 4 == 1) {
            setImage(bird1);
        }
        else if (now/900000000 % 4 == 2) {
            setImage(bird3);
        }
        move(speed_x, speed_y);
        if (getX() > BoardConfig.WIDTH && speed_x > 0) {
            resetToInitialPosition();
        }
        if (getX() < (0 - getWidth()) && speed_x < 0) {
            resetToInitialPosition();
        }
    }

    public void setInitialPosition(double x, double y) {
        this.initialX = x;
        this.initialY = y;
    }

    // Add method to reset to initial position
    private void resetToInitialPosition() {
        setX(initialX);
        setY(initialY);
    }

    public void setBirdImages(Image bird1, Image bird2, Image bird3) {
        this.bird1 = bird1;
        this.bird2 = bird2;
        this.bird3 = bird3;
        setImage(bird2);
    }

    public void setSpeedX(double speed) {
        this.speed_x = speed;
    }

    public double getSpeedX() {
        return speed_x;
    }

    public void setSpeedY(double speed) {
        this.speed_y = speed;
    }

    public double getSpeedY() {
        return speed_y;
    }
}
