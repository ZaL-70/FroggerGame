package uk.ac.nott.cs.comp2013.froggerApp.controller.player;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uk.ac.nott.cs.comp2013.froggerApp.actors.level.*;
import uk.ac.nott.cs.comp2013.froggerApp.actors.player.Animal;

import java.util.ArrayList;
import java.util.List;

import static uk.ac.nott.cs.comp2013.froggerApp.actors.player.Animal.State;

/**
 * This class separates the behaviors & animations for the Animal Actor into a controller class
 */
public class AnimalController {
    Animal animal;
    double movementY = 13.333333*2, movementX = 10.666666*2, w = 800;
    int imgSize = 40, carD = 0;
    List<End> inter = new ArrayList<End>();

    private static final String FROG_UP = "file:src/main/resources/imgs/player/action/froggerUp.png";
    private static final String FROG_UP_JUMP = "file:src/main/resources/imgs/player/action/froggerUpJump.png";
    private static final String FROG_DOWN = "file:src/main/resources/imgs/player/action/froggerDown.png" ;
    private static final String FROG_DOWN_JUMP = "file:src/main/resources/imgs/player/action/froggerDownJump.png";
    private static final String FROG_LEFT = "file:src/main/resources/imgs/player/action/froggerLeft.png";
    private static final String FROG_LEFT_JUMP = "file:src/main/resources/imgs/player/action/froggerLeftJump.png";
    private static final String FROG_RIGHT = "file:src/main/resources/imgs/player/action/froggerRight.png";
    private static final String FROG_RIGHT_JUMP = "file:src/main/resources/imgs/player/action/froggerRightJump.png";

    Image imgW1, imgA1, imgS1, imgD1, imgW2, imgA2, imgS2, imgD2;

    public AnimalController(Animal animal) {
        this.animal = animal;
        imgW1 = new Image(FROG_UP, imgSize, imgSize, true, true);
        imgA1 = new Image(FROG_LEFT, imgSize, imgSize, true, true);
        imgS1 = new Image(FROG_DOWN, imgSize, imgSize, true, true);
        imgD1 = new Image(FROG_RIGHT, imgSize, imgSize, true, true);
        imgW2 = new Image(FROG_UP_JUMP, imgSize, imgSize, true, true);
        imgA2 = new Image(FROG_LEFT_JUMP, imgSize, imgSize, true, true);
        imgS2 = new Image(FROG_DOWN_JUMP, imgSize, imgSize, true, true);
        imgD2 = new Image(FROG_RIGHT_JUMP, imgSize, imgSize, true, true);
    }

    // AnimalController
    public void onKeyPress(KeyEvent event) {
        if(!(animal.getState() == State.alive)) {}
        else {
            switch (event.getCode()) {
                case KeyCode.W:
                    animal.move(0, -movementY);
                    animal.setImage(imgW2);
                    break;
                case KeyCode.A:
                    animal.move(-movementX, 0);
                    animal.setImage(imgA2);
                    break;
                case KeyCode.S:
                    animal.move(0, movementY);
                    animal.setImage(imgS2);
                    break;
                case KeyCode.D:
                    animal.move(movementX, 0);
                    animal.setImage(imgD2);
                    break;
            }
        }
    }

    // AnimalController
    public void onKeyRelease(KeyEvent event) {
        if(!(animal.getState() == State.alive)) {}
        else {
            switch (event.getCode()) {
                case KeyCode.W:
                    if (animal.getY() < w) {
                        animal.changeScore(10, true);
                        w = animal.getY();
                    }
                    animal.move(0, -movementY);
                    animal.setImage(imgW1);
                    break;
                case KeyCode.A:
                    animal.move(-movementX, 0);
                    animal.setImage(imgA1);
                    break;
                case KeyCode.S:
                    animal.move(0, movementY);
                    animal.setImage(imgS1);
                    break;
                case KeyCode.D:
                    animal.move(movementX, 0);
                    animal.setImage(imgD1);
                    break;
            }
        }
    }

    // Controller updating AnimalModel
    public void respawn() {
        animal.setState(State.alive);
        animal.setImage(new Image("file:src/main/resources/imgs/player/action/froggerUp.png", imgSize, imgSize, true, true));
        animal.setX(300);
        animal.setY(679.8+movementY);
    }

    // AnimalController
    public void handleBoundary() {
        if (animal.getY()<0 || animal.getY()>734) {
            respawn();
        }
        if (animal.getX()<0) {
            animal.move(movementX, 0);
        }
        if (animal.getX()>600) {
            animal.move(-movementX, 0);
        }
    }

    // AnimalController
    public void updateDeathState() {
        // Check death
        if (!(animal.getIntersectingObjects(Obstacle.class).isEmpty())) {
            animal.setState(State.carDeath);
        }
        if (!(animal.getIntersectingObjects(WetTurtle.class).isEmpty())) {
            if (animal.getIntersectingObjects(WetTurtle.class).getFirst().isSunk()) {
                animal.setState(State.waterDeath);
            }
        }
        if (animal.getY() < 413) {
            boolean onObstacle = !animal.getIntersectingObjects(Log.class).isEmpty() ||
                    !animal.getIntersectingObjects(Turtle.class).isEmpty() ||
                    (!animal.getIntersectingObjects(WetTurtle.class).isEmpty() &&
                            !animal.getIntersectingObjects(WetTurtle.class).get(0).isSunk());
            if (!onObstacle) {
                animal.setState(State.waterDeath);
            }
        }
    }

    public void handleActorInteraction() {
        if (!(animal.getIntersectingObjects(Log.class).isEmpty()) && animal.getState() == State.alive) {
            if(animal.getIntersectingObjects(Log.class).getFirst().getLeft())
                animal.move(-2,0);
            else
                animal.move(.75,0);
        }
        else if (!(animal.getIntersectingObjects(Turtle.class).isEmpty()) && animal.getState() == State.alive) {
            animal.move(-1,0);
        }
        else if (!(animal.getIntersectingObjects(WetTurtle.class).isEmpty())) {
            if (!(animal.getIntersectingObjects(WetTurtle.class).getFirst().isSunk())) {
                animal.move(-1,0);
            }
        }
        // Check end point interaction
        else if (!(animal.getIntersectingObjects(End.class).isEmpty())) {
           inter = animal.getIntersectingObjects(End.class);
            if (animal.getIntersectingObjects(End.class).getFirst().isActivated()) {
                animal.changeScore(-50,true);
            } else {
                animal.changeScore(50, true);
                animal.incrementStop();
            }
            w = 800;
            animal.getIntersectingObjects(End.class).getFirst().setEnd();
            respawn();
        }
    }

    // Animation, animate in a view class by updating actor image
    public void handleDeath(long now) {
        if (animal.getState() == State.carDeath) {
            if ((now)% 11 == 0) {
                carD++;
            }
            if (carD==1) {
                animal.setImage(new Image("file:src/main/resources/imgs/player/death/cardeath1.png", imgSize, imgSize, true, true));
            }
            if (carD==2) {
                animal.setImage(new Image("file:src/main/resources/imgs/player/death/cardeath2.png", imgSize, imgSize, true, true));
            }
            if (carD==3) {
                animal.setImage(new Image("file:src/main/resources/imgs/player/death/cardeath3.png", imgSize, imgSize, true, true));
            }
            if (carD == 4) {
                respawn();
                carD = 0;
                if (animal.getPoints() > 50) {
                    animal.changeScore(-50, true);
                }
            }
        }
        if (animal.getState() == State.waterDeath) {
            if ((now)% 11 ==0) {
                carD++;
            }
            if (carD==1) {
                animal.setImage(new Image("file:src/main/resources/imgs/player/death/waterdeath1.png", imgSize,imgSize , true, true));
            }
            if (carD==2) {
                animal.setImage(new Image("file:src/main/resources/imgs/player/death/waterdeath2.png", imgSize,imgSize , true, true));
            }
            if (carD==3) {
                animal.setImage(new Image("file:src/main/resources/player/death/waterdeath3.png", imgSize,imgSize , true, true));
            }
            if (carD == 4) {
                animal.setImage(new Image("file:src/main/resources/imgs/player/death/waterdeath4.png", imgSize,imgSize , true, true));
            }
            if (carD == 5) {
                respawn();
                carD = 0;
                if (animal.getPoints() > 50) {
                    animal.changeScore(-50, true);
                }
            }
        }
    }
}
