package uk.ac.nott.cs.comp2013.froggerApp.view.world;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MyStage extends World {
	private static MyStage instance;
	MediaPlayer mediaPlayer;

	private MyStage() {
        super();
        if (instance != null) {
			throw new RuntimeException("An instance of class: MyStage already exists");
		}
	}

	public static MyStage getInstance() {
		if (instance == null) {
			instance = new MyStage();
		}
		return instance;
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
