package uk.ac.nott.cs.comp2013.froggerApp.view.world;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import uk.ac.nott.cs.comp2013.froggerApp.model.GameConfig.*;

import java.io.File;

/**
 * Singleton class for the game world inheriting {@link World}. Any custom behaviour
 * for managing {@link World} contents, game UI, sound effects etc should be handled here
 */
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

	/**
	 * @return singleton instance of game world
	 */
	public static MyStage getInstance() {
		if (instance == null) {
			instance = new MyStage();
		}
		return instance;
	}

	public void createGameOverScreen(int score) {
		Text gameOverText = new Text("GAME OVER!\n Your High Score: " + score + "\nPress SPACE to restart...");
		gameOverText.setTextAlignment(TextAlignment.CENTER);
		gameOverText.setFont(Font.font("Impact", FontWeight.BOLD, 60));
		// Create the StackPane and set its size
		gameOverPane.setPrefWidth(BoardConfig.WIDTH);
		gameOverPane.setPrefHeight(BoardConfig.HEIGHT);
		// Center the text in the StackPane
		StackPane.setAlignment(gameOverText, Pos.CENTER);
		gameOverPane.getChildren().add(gameOverText);
		gameOverPane.setStyle("-fx-background-color: rgba(255, 102, 129, 0.73);");
		getChildren().add(gameOverPane);
	}

	public void createWinScreen(int score) {
		Text gameOverText = new Text("YOU WIN!\n Your High Score: " + score + "\nPress SPACE to replay...");
		gameOverText.setTextAlignment(TextAlignment.CENTER);
		gameOverText.setFont(Font.font("Impact", FontWeight.BOLD, 60));
		// Create the StackPane and set its size
		gameOverPane.setPrefWidth(BoardConfig.WIDTH);
		gameOverPane.setPrefHeight(BoardConfig.HEIGHT);
		// Center the text in the StackPane
		StackPane.setAlignment(gameOverText, Pos.CENTER);
		gameOverPane.getChildren().add(gameOverText);
		gameOverPane.setStyle("-fx-background-color: rgba(52, 235, 58, 0.73);");
		getChildren().add(gameOverPane);
	}

	public void removeGameEndScreen() {
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
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}
	}

}
