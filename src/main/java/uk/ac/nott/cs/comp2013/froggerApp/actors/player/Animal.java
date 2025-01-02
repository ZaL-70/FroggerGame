package uk.ac.nott.cs.comp2013.froggerApp.actors.player;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import uk.ac.nott.cs.comp2013.froggerApp.actors.*;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.AnimalController;

public class Animal extends Actor {
	public enum State {
		alive,
		waterDeath,
		carDeath,
	}

	int points = 0;
	int end = 0;
	boolean scoreChanged = false;
	State deathState;
	AnimalController animalController;

	// Animal (actor/entity) constructor adds behavior via a controller
	public Animal(String imageLink) {
		animalController = new AnimalController(this);
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
		// Handle death
		animalController.handleDeath(now);
		// Set death state
		animalController.updateDeathState();
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
}
