package uk.ac.nott.cs.comp2013.froggerApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.ac.nott.cs.comp2013.froggerApp.actors.*;
import uk.ac.nott.cs.comp2013.froggerApp.controller.GameLogic;
import uk.ac.nott.cs.comp2013.froggerApp.controller.GameTimer;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.*;

public class FroggerApp extends Application {
	MyStage background;
	Scene scene;
	Animal animal;
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
		animal = new Animal("file:src/main/resources/imgs/player/action/froggerUp.png");
		// Create main game level & add to scene
		background = setupLevel.createLevel1(animal);
		scene = new Scene(background,600,800);
		primaryStage.setScene(scene);
		primaryStage.show();
		// Instantiate & activate game loop's handlers
		startGame();
	}

	public void startGame() {
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
