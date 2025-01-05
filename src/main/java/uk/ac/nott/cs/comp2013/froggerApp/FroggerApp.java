package uk.ac.nott.cs.comp2013.froggerApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;
import uk.ac.nott.cs.comp2013.froggerApp.model.actors.player.Animal;
import uk.ac.nott.cs.comp2013.froggerApp.controller.*;
import uk.ac.nott.cs.comp2013.froggerApp.controller.player.AnimalController;
import uk.ac.nott.cs.comp2013.froggerApp.view.level.LevelSetup;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.*;

public class FroggerApp extends Application {
	MyStage world;
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
		animal = new Animal(PlayerConfig.IMAGE_PATHS.get("up"));
		animalController = new AnimalController(animal);
		// Create main game level & add to scene
		world = setupLevel.createLevel1(animal);
		scene = new Scene(world, BoardConfig.WIDTH,BoardConfig.HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();
		// Instantiate & activate game controller's & loop & logic handlers
		startGame();
	}

	public void startGame() {
		animal.initialise(animalController);
		world.playMusic();
		logicHandler = new GameLogic(world, animal);
		gameTimer = new GameTimer(world, animal, logicHandler);
		gameTimer.createTimer();
		gameTimer.startTimer();
	}

    public void stop() {
		gameTimer.stopTimer();
    }
}
