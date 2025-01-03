package uk.ac.nott.cs.comp2013.froggerApp.controller.player;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uk.ac.nott.cs.comp2013.froggerApp.actors.level.*;
import uk.ac.nott.cs.comp2013.froggerApp.actors.player.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.view.LevelSetup;

import java.util.ArrayList;
import java.util.List;

import static uk.ac.nott.cs.comp2013.froggerApp.actors.player.Animal.FROG_SIZE;
import static uk.ac.nott.cs.comp2013.froggerApp.actors.player.Animal.State;

/**
 * This class separates the behaviors & animations for the Animal Actor into a controller class
 */
public class AnimalController {
    Animal animal;
    double movementY = LevelSetup.ROW_HEIGHT / 2, movementX = 10.666666*2, maxHeight = LevelSetup.BOARD_HEIGHT;
    int deathTime = 0;
    List<End> inter = new ArrayList<End>();

    public AnimalController(Animal animal) {
        this.animal = animal;
    }

    public void onKeyPress(KeyEvent event) {
        if(!(animal.getState() == State.alive)) {}
        else {
            switch (event.getCode()) {
                case KeyCode.W:
                    animal.move(0, -movementY);
                    animal.setImage(Animal.imgW2);
                    break;
                case KeyCode.A:
                    animal.move(-movementX, 0);
                    animal.setImage(Animal.imgA2);
                    break;
                case KeyCode.S:
                    animal.move(0, movementY);
                    animal.setImage(Animal.imgS2);
                    break;
                case KeyCode.D:
                    animal.move(movementX, 0);
                    animal.setImage(Animal.imgD2);
                    break;
            }
        }
    }

    public void onKeyRelease(KeyEvent event) {
        if(!(animal.getState() == State.alive)) {}
        else {
            switch (event.getCode()) {
                case KeyCode.W:
                    if (animal.getY() < maxHeight) {
                        animal.changeScore(10, true);
                        maxHeight = animal.getY();
                    }
                    animal.move(0, -movementY);
                    animal.setImage(Animal.imgW1);
                    break;
                case KeyCode.A:
                    animal.move(-movementX, 0);
                    animal.setImage(Animal.imgA1);
                    break;
                case KeyCode.S:
                    animal.move(0, movementY);
                    animal.setImage(Animal.imgS1);
                    break;
                case KeyCode.D:
                    animal.move(movementX, 0);
                    animal.setImage(Animal.imgD1);
                    break;
            }
        }
    }

    public void respawn() {
        animal.setState(State.alive);
        animal.setImage(new Image(Animal.FROG_UP, FROG_SIZE, FROG_SIZE, true, true));
        animal.setX(300);
        animal.setY(LevelSetup.rowToY(2));
    }

    public void handleBoundary() {
        if (animal.getY()<0 || animal.getY()>734) {
            respawn();
        }
        if (animal.getX()<0) {
            animal.move(movementX, 0);
        }
        if (animal.getX()>575) {
            animal.move(-movementX, 0);
        }
    }

    public void updateDeathState() {
        // Check car death
        if (!(animal.getIntersectingObjects(Obstacle.class).isEmpty())) {
            animal.setState(State.carDeath);
        }
        // Check water by turtle death
        if (!(animal.getIntersectingObjects(WetTurtle.class).isEmpty())) {
            if (animal.getIntersectingObjects(WetTurtle.class).getFirst().isSunk()) {
                animal.setState(State.waterDeath);
            }
        }
        // Check water death
        if (animal.getY() < 413) {
            if (!animal.getOnObstacle()) {
                animal.setState(State.waterDeath);
            }
        }
        // Check end point taken death
        if (!(animal.getIntersectingObjects(End.class).isEmpty()) && !animal.getOnObstacle()) {
            inter = animal.getIntersectingObjects(End.class);
            if (animal.getIntersectingObjects(End.class).getFirst().isActivated()) {
                animal.setState(State.endDeath);
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
        if (!(animal.getIntersectingObjects(Turtle.class).isEmpty()) && animal.getState() == State.alive) {
            animal.move(-1,0);
        }
        if (!(animal.getIntersectingObjects(WetTurtle.class).isEmpty())) {
            if (!(animal.getIntersectingObjects(WetTurtle.class).getFirst().isSunk())) {
                animal.move(-1,0);
            }
        }
        // Check end point interaction
        if (!(animal.getIntersectingObjects(End.class).isEmpty()) && !animal.getOnObstacle()) {
           inter = animal.getIntersectingObjects(End.class);
            if (!animal.getIntersectingObjects(End.class).getFirst().isActivated()) {
                animal.changeScore(50, true);
                animal.incrementStop();
                maxHeight = LevelSetup.BOARD_HEIGHT;
                animal.getIntersectingObjects(End.class).getFirst().setEnd();
                respawn();
            }
        }
    }

    // Animation, animate in a view class by updating actor image
    public void handleDeath(long now) {
        if (animal.getState() == State.carDeath) {
            if (now % 11 == 0) {
                deathTime++;
            }
            if (deathTime == 1) {
                animal.setImage(new Image("file:src/main/resources/imgs/player/death/cardeath1.png", FROG_SIZE, FROG_SIZE, true, true));
            }
            if (deathTime == 2) {
                animal.setImage(new Image("file:src/main/resources/imgs/player/death/cardeath2.png", FROG_SIZE, FROG_SIZE, true, true));
            }
            if (deathTime == 3) {
                animal.setImage(new Image("file:src/main/resources/imgs/player/death/cardeath3.png", FROG_SIZE, FROG_SIZE, true, true));
            }
            if (deathTime == 4) {
                respawn();
                deathTime = 0;
                if (animal.getPoints() > 50) {
                    animal.changeScore(-50, true);
                }
            }
        }
        if (animal.getState() == State.waterDeath || animal.getState() == State.endDeath) {
            if (now % 11 == 0) {
                deathTime++;
            }
            if (deathTime == 1) {
                animal.setImage(new Image("file:src/main/resources/imgs/player/death/waterdeath1.png", FROG_SIZE, FROG_SIZE, true, true));
            }
            if (deathTime == 2) {
                animal.setImage(new Image("file:src/main/resources/imgs/player/death/waterdeath2.png", FROG_SIZE, FROG_SIZE, true, true));
            }
            if (deathTime == 3) {
                animal.setImage(new Image("file:src/main/resources/player/death/waterdeath3.png", FROG_SIZE, FROG_SIZE, true, true));
            }
            if (deathTime == 4) {
                animal.setImage(new Image("file:src/main/resources/imgs/player/death/waterdeath4.png", FROG_SIZE, FROG_SIZE, true, true));
            }
            if (deathTime == 5) {
                respawn();
                deathTime = 0;
                if (animal.getPoints() > 50) {
                    animal.changeScore(-50, true);
                }
            }
        }
    }
}
