package uk.ac.nott.cs.comp2013.froggerApp.model.actors.player;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.Actor;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.Log;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.Turtle;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.level.WetTurtle;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.AnimalController;

public class Animal extends Actor {
	public enum State {
		alive,
		waterDeath,
		carDeath,
		endDeath
	}

	int points = 0, end = 0;
	double max_height = PlayerConfig.MAX_HEIGHT;
	boolean scoreChanged = false;
	State deathState;
	AnimalController animalController;
	public static Image imgW1, imgA1, imgS1, imgD1, imgW2, imgA2, imgS2, imgD2;

	// Animal (actor/entity) constructor adds behavior via a controller
	public Animal(String imageLink) {
		setImage(new Image(imageLink));
		imgW1 = new Image(PlayerConfig.IMAGE_PATHS.get("up"), PlayerConfig.SIZE, PlayerConfig.SIZE, true, true);
		imgA1 = new Image(PlayerConfig.IMAGE_PATHS.get("left"), PlayerConfig.SIZE, PlayerConfig.SIZE, true, true);
		imgS1 = new Image(PlayerConfig.IMAGE_PATHS.get("down"), PlayerConfig.SIZE, PlayerConfig.SIZE, true, true);
		imgD1 = new Image(PlayerConfig.IMAGE_PATHS.get("right"), PlayerConfig.SIZE, PlayerConfig.SIZE, true, true);
		imgW2 = new Image(PlayerConfig.IMAGE_PATHS.get("upJump"), PlayerConfig.SIZE, PlayerConfig.SIZE, true, true);
		imgA2 = new Image(PlayerConfig.IMAGE_PATHS.get("leftJump"), PlayerConfig.SIZE, PlayerConfig.SIZE, true, true);
		imgS2 = new Image(PlayerConfig.IMAGE_PATHS.get("downJump"), PlayerConfig.SIZE, PlayerConfig.SIZE, true, true);
		imgD2 = new Image(PlayerConfig.IMAGE_PATHS.get("rightJump"), PlayerConfig.SIZE, PlayerConfig.SIZE, true, true);
	}

	public void respawn() {
		setState(State.alive);
		setImage(new Image(PlayerConfig.IMAGE_PATHS.get("up"), PlayerConfig.SIZE, PlayerConfig.SIZE, true, true));
		setX(PlayerConfig.RESPAWN_X);
		setY(BoardConfig.rowToY(PlayerConfig.RESPAWN_ROW));
	}

	public void initialise(AnimalController animalController) {
		this.animalController = animalController;
		respawn();

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
		animalController.handleBoundary();
		animalController.updateDeathState();
		animalController.handleDeath(now);
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

	public void setMaxHeight(double height) {
		this.max_height = height;
	}

	public double getMaxHeight() {
		return max_height;
	}
}
