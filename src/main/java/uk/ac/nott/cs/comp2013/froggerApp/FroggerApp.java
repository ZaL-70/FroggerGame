package uk.ac.nott.cs.comp2013.froggerApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.ac.nott.cs.comp2013.froggerApp.actors.player.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.controller.*;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.AnimalController;
import uk.ac.nott.cs.comp2013.froggerApp.view.*;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.*;

public class FroggerApp extends Application {
	MyStage background;
	Scene scene;
	Animal animal;
	AnimalController animalController;
	LevelSetup setupLevel;
	GameTimer gameTimer;
	GameLogic logicHandler;

	public static void main(String[] args) {
		launch(args);
	}

	// Application start method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Instantiate Level creator & player
		setupLevel = new LevelSetup();
		animal = new Animal(Animal.FROG_UP);
		animalController = new AnimalController(animal);
		// Create main game level & add to scene
		background = setupLevel.createLevel1(animal);
		scene = new Scene(background,LevelSetup.BOARD_WIDTH,LevelSetup.BOARD_HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();
		// Instantiate & activate game loop's handlers
		startGame();
	}

	public void startGame() {
		animal.initialise(animalController);
		background.playMusic();
		logicHandler = new GameLogic(background, animal);
		gameTimer = new GameTimer(background, animal, logicHandler);
		gameTimer.createTimer();
		gameTimer.startTimer();
	}

    public void stop() {
		gameTimer.stopTimer();
    }
}
