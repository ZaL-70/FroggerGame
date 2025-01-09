package uk.ac.nott.cs.comp2013.froggerApp.controller.player;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.animationHandler.CarDeathAnimator;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.animationHandler.DeathAnimator;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.animationHandler.HomeTakenAnimator;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.animationHandler.WaterDeathAnimator;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.deathChecker.CollisionChecker;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.deathChecker.DeathChecker;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.deathChecker.DrowningChecker;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.deathChecker.TakenHomeChecker;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.interactionHandler.ObjectInteractionHandler;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.interactionHandler.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.GameObject;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player.Animal;

import java.util.List;

import static uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player.Animal.State;

/**
 * Class managing {@link Animal} logics: State, movements & animations to
 * perform based on its interactions with other {@link GameObject}
 */
public class AnimalController {
    Animal animal;
    List<DeathChecker> deathCheckers;
    List<ObjectInteractionHandler> interactionHandlers;
    List<DeathAnimator> deathAnimators;

    public AnimalController(Animal animal) {
        deathCheckers = List.of(new CollisionChecker(), new DrowningChecker(), new TakenHomeChecker());
        interactionHandlers = List.of(new LogInteraction(), new TurtleInteraction(), new WetTurtleInteraction(), new EndInteraction(), new BirdInteraction());
        deathAnimators = List.of(new HomeTakenAnimator(), new WaterDeathAnimator(), new CarDeathAnimator());
        this.animal = animal;
    }

    public void onKeyPress(KeyEvent event) {
        if(!(animal.getState() == State.alive)) {}
        else {
            switch (event.getCode()) {
                case KeyCode.W:
                    animal.move(0, -PlayerConfig.MOVEMENT_Y);
                    animal.setImage(Animal.imgW2);
                    break;
                case KeyCode.A:
                    animal.move(-PlayerConfig.MOVEMENT_X, 0);
                    animal.setImage(Animal.imgA2);
                    break;
                case KeyCode.S:
                    animal.move(0, PlayerConfig.MOVEMENT_Y);
                    animal.setImage(Animal.imgS2);
                    break;
                case KeyCode.D:
                    animal.move(PlayerConfig.MOVEMENT_X, 0);
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
                    if (animal.getY() < animal.getMaxHeight()) {
                        animal.changeScore(10, true);
                        animal.setMaxHeight(animal.getY());
                    }
                    animal.move(0, -PlayerConfig.MOVEMENT_Y);
                    animal.setImage(Animal.imgW1);
                    break;
                case KeyCode.A:
                    animal.move(-PlayerConfig.MOVEMENT_X, 0);
                    animal.setImage(Animal.imgA1);
                    break;
                case KeyCode.S:
                    animal.move(0, PlayerConfig.MOVEMENT_Y);
                    animal.setImage(Animal.imgS1);
                    break;
                case KeyCode.D:
                    animal.move(PlayerConfig.MOVEMENT_X, 0);
                    animal.setImage(Animal.imgD1);
                    break;
            }
        }
    }

    public void handleBoundary() {
        if (animal.getY() < BoardConfig.UPPER_BOUNDARY || animal.getY() > BoardConfig.LOWER_BOUNDARY) {
            animal.respawn();
        }
        if (animal.getX() < BoardConfig.LEFT_BOUNDARY) {
            animal.move(PlayerConfig.MOVEMENT_X, 0);
        }
        if (animal.getX() > BoardConfig.RIGHT_BOUNDARY) {
            animal.move(-PlayerConfig.MOVEMENT_X, 0);
        }
    }

    public void updateDeathState() {
        for (DeathChecker obj : deathCheckers) {
            obj.check(animal);
        }
    }

    public void handleActorInteraction() {
        for (ObjectInteractionHandler obj : interactionHandlers) {
            obj.interact(animal);
        }
    }

    public void handleDeath(long now) {
        for (DeathAnimator obj : deathAnimators) {
            obj.animate(animal, now);
        }
    }
}
