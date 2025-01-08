package uk.ac.nott.cs.comp2013.froggerApp.view.world;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;

public class MyStage extends World {
	private static MyStage instance;
	MediaPlayer mediaPlayer;
	StackPane gameOverPane;

	private MyStage() {
		super();
		if (instance != null) {
			throw new RuntimeException("An instance of class: MyStage already exists");
		}
		gameOverPane = new StackPane(); // Initialize the game over pane
		gameOverPane.setStyle("-fx-background-color: transparent;");
	}

	public static MyStage getInstance() {
		if (instance == null) {
			instance = new MyStage();
		}
		return instance;
	}

	public void createGameOverScreen(int score) {
		Text gameOverText = new Text("YOU WIN!\n Your High Score: " + score + "\nPress SPACE to restart...");
		gameOverText.setTextAlignment(TextAlignment.CENTER);
		gameOverText.setFont(Font.font("Impact", FontWeight.BOLD, 60));
		// Create the StackPane and set its size (width and height)
		gameOverPane.setPrefWidth(600);  // Set a fixed width for the game over screen
		gameOverPane.setPrefHeight(800); // Set a fixed height for the game over screen
		// Center the text in the StackPane
		StackPane.setAlignment(gameOverText, Pos.CENTER);
		gameOverPane.getChildren().add(gameOverText);
		gameOverPane.setStyle("-fx-background-color: rgba(255, 102, 129, 0.73);");
		getChildren().add(gameOverPane);
	}

	public void createWinScreen(int score) {
		Text gameOverText = new Text("YOU WIN!\n Your High Score: " + score + "\nPress SPACE to restart...");
		gameOverText.setTextAlignment(TextAlignment.CENTER);
		gameOverText.setFont(Font.font("Impact", FontWeight.BOLD, 60));
		// Create the StackPane and set its size (width and height)
		gameOverPane.setPrefWidth(600);  // Set a fixed width for the game over screen
		gameOverPane.setPrefHeight(800); // Set a fixed height for the game over screen
		// Center the text in the StackPane
		StackPane.setAlignment(gameOverText, Pos.CENTER);
		gameOverPane.getChildren().add(gameOverText);
		gameOverPane.setStyle("-fx-background-color: rgba(52, 235, 58, 0.73);");
		getChildren().add(gameOverPane);
	}

	public void removeGameOver() {
		gameOverPane.getChildren().clear(); // Clear game over text
		gameOverPane.setStyle("-fx-background-color: transparent;");
	}

	public void removeWin() {
		gameOverPane.getChildren().clear(); // Clear game over text
		gameOverPane.setStyle("-fx-background-color: transparent;");
	}
	
	public void playMusic() {
		String musicFile = "src/main/resources/audio/Snake_Charmer.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	    mediaPlayer.play();
	}
	
	public void stopMusic() {
		mediaPlayer.stop();
	}

}
