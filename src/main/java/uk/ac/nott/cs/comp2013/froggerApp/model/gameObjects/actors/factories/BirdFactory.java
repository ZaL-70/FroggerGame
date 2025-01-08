package uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.factories;

import javafx.scene.image.Image;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.level.Bird;

public class BirdFactory {
    public static Bird createBirdTopLeft(int size, double speed) {
        Bird birdLeft = new Bird();
        birdLeft.setBirdImages(
                new Image(BirdConfig.IMAGE_PATHS.get("animationRight3"), size, size,true,true),
                new Image(BirdConfig.IMAGE_PATHS.get("animationRight2"), size, size,true,true),
                new Image(BirdConfig.IMAGE_PATHS.get("animationRight1"), size, size,true,true)
        );
        birdLeft.setX(0);
        birdLeft.setY(0);
        birdLeft.setSpeedX(SpeedConfig.XSCALE * speed);
        birdLeft.setSpeedY(speed);
        birdLeft.setInitialPosition(0, 0);
        return birdLeft;
    }

    public static Bird createBirdTopRight(int size, double speed) {
        Bird birdRight = new Bird();
        birdRight.setBirdImages(
                new Image(BirdConfig.IMAGE_PATHS.get("animationLeft3"), size, size,true,true),
                new Image(BirdConfig.IMAGE_PATHS.get("animationLeft2"), size, size,true,true),
                new Image(BirdConfig.IMAGE_PATHS.get("animationLeft1"), size, size,true,true)
        );
        birdRight.setX(BoardConfig.WIDTH);
        birdRight.setY(0);
        birdRight.setSpeedX(-SpeedConfig.XSCALE * speed);
        birdRight.setSpeedY(speed);
        birdRight.setInitialPosition(BoardConfig.WIDTH, 0);
        return birdRight;
    }

    public static Bird createBirdLeft(int size, double xpos, double ypos, double speed_x, double speed_y) {
        Bird bird = new Bird();
        bird.setBirdImages(
                new Image(BirdConfig.IMAGE_PATHS.get("animationLeft3"), size, size,true,true),
                new Image(BirdConfig.IMAGE_PATHS.get("animationLeft2"), size, size,true,true),
                new Image(BirdConfig.IMAGE_PATHS.get("animationLeft1"), size, size,true,true)
        );
        bird.setX(xpos);
        bird.setY(ypos);
        bird.setSpeedX(speed_x);
        bird.setSpeedY(speed_y);
        bird.setInitialPosition(xpos, ypos);
        return bird;
    }

    public static Bird createBirdRight(int size, double xpos, double ypos, double speed_x, double speed_y) {
        Bird bird = new Bird();
        bird.setBirdImages(
                new Image(BirdConfig.IMAGE_PATHS.get("animationRight3"), size, size,true,true),
                new Image(BirdConfig.IMAGE_PATHS.get("animationRight2"), size, size,true,true),
                new Image(BirdConfig.IMAGE_PATHS.get("animationRight1"), size, size,true,true)
        );
        bird.setX(xpos);
        bird.setY(ypos);
        bird.setSpeedX(speed_x);
        bird.setSpeedY(speed_y);
        bird.setInitialPosition(xpos, ypos);
        return bird;
    }
}
