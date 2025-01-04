package uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.player;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.Actor;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.level.Log;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.level.Turtle;
import uk.ac.nott.cs.comp2013.froggerApp.model.gameObjects.actors.level.WetTurtle;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.AnimalController;
import uk.ac.nott.cs.comp2013.froggerApp.view.level.LevelSetup;

public class Animal extends Actor {
	public enum State {
		alive,
		waterDeath,
		carDeath,
		endDeath
	}

	int points = 0, end = 0;
	public static final double MOVEMENT_Y = LevelSetup.ROW_HEIGHT / 2, MOVEMENT_X = 10.666666*2;
	public static double MAX_HEIGHT = LevelSetup.BOARD_HEIGHT;
	boolean scoreChanged = false;
	State deathState;
	AnimalController animalController;

	public static final String FROG_UP = "file:src/main/resources/imgs/player/action/froggerUp.png";
	public static final String FROG_UP_JUMP = "file:src/main/resources/imgs/player/action/froggerUpJump.png";
	public static final String FROG_DOWN = "file:src/main/resources/imgs/player/action/froggerDown.png" ;
	public static final String FROG_DOWN_JUMP = "file:src/main/resources/imgs/player/action/froggerDownJump.png";
	public static final String FROG_LEFT = "file:src/main/resources/imgs/player/action/froggerLeft.png";
	public static final String FROG_LEFT_JUMP = "file:src/main/resources/imgs/player/action/froggerLeftJump.png";
	public static final String FROG_RIGHT = "file:src/main/resources/imgs/player/action/froggerRight.png";
	public static final String FROG_RIGHT_JUMP = "file:src/main/resources/imgs/player/action/froggerRightJump.png";
	public static final int FROG_SIZE = 40;
	public static Image imgW1, imgA1, imgS1, imgD1, imgW2, imgA2, imgS2, imgD2;

	// Animal (actor/entity) constructor adds behavior via a controller
	public Animal(String imageLink) {
		setImage(new Image(imageLink));
		imgW1 = new Image(Animal.FROG_UP, FROG_SIZE, FROG_SIZE, true, true);
		imgA1 = new Image(Animal.FROG_LEFT, FROG_SIZE, FROG_SIZE, true, true);
		imgS1 = new Image(Animal.FROG_DOWN, FROG_SIZE, FROG_SIZE, true, true);
		imgD1 = new Image(Animal.FROG_RIGHT, FROG_SIZE, FROG_SIZE, true, true);
		imgW2 = new Image(Animal.FROG_UP_JUMP, FROG_SIZE, FROG_SIZE, true, true);
		imgA2 = new Image(Animal.FROG_LEFT_JUMP, FROG_SIZE, FROG_SIZE, true, true);
		imgS2 = new Image(Animal.FROG_DOWN_JUMP, FROG_SIZE, FROG_SIZE, true, true);
		imgD2 = new Image(Animal.FROG_RIGHT_JUMP, FROG_SIZE, FROG_SIZE, true, true);
	}

	public void initialise(AnimalController animalController) {
		this.animalController = animalController;
		animalController.respawn();

		setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				animalController.onKeyPress(event);
			}
		});
		setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				animalController.onKeyRelease(event);
			}
		});
	}

	// AnimalModel is Actor (will act via controller)
	@Override
	public void act(long now) {
		// Handle boundary component
		animalController.handleBoundary();
		// Set death state
		animalController.updateDeathState();
		// Handle death
		animalController.handleDeath(now);
		// Handle interactions with other Actors
		animalController.handleActorInteraction();
	}

	public void setState(State state) {
		this.deathState = state;
	}

	public State getState() {
		return deathState;
	}

	public void incrementStop() {
		this.end++;
	}

	public boolean getStop() {
		return end==5;
	}

	public int getPoints() {
		return points;
	}

	public void changeScore(int points, boolean scoreChanged) {
		this.points = this.points + points;
		this.scoreChanged = scoreChanged;
	}

	public boolean getScoreChanged() {
		if (scoreChanged) {
			scoreChanged = false;
			return true;
		}
		return false;
	}

	public boolean getOnObstacle() {
        return !getIntersectingObjects(Log.class).isEmpty() ||
                !getIntersectingObjects(Turtle.class).isEmpty() ||
                (!getIntersectingObjects(WetTurtle.class).isEmpty() &&
                        !getIntersectingObjects(WetTurtle.class).get(0).isSunk());
	}
}
