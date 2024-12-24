package uk.ac.nott.cs.comp2013.froggerApp;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import uk.ac.nott.cs.comp2013.froggerApp.actors.*;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.BackgroundImage;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.MyStage;
import uk.ac.nott.cs.comp2013.froggerApp.view.world.setupLevel;
import uk.ac.nott.cs.comp2013.froggerApp.actors.digit;

public class FroggerApp extends Application {
	AnimationTimer timer;
	MyStage background;
	Animal animal;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	    background = new MyStage();
	    Scene scene  = new Scene(background,600,800);
		setupLevel.createLevel1(background);
		animal = new Animal("file:src/main/resources/imgs/player/action/froggerUp.png");
		background.add(animal);
		background.add(new digit(0, 30, 360, 25));
		background.start();
		primaryStage.setScene(scene);
		primaryStage.show();
		start();  
	}
	public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	if (animal.changeScore()) {
            		setNumber(animal.getPoints());
            	}
            	if (animal.getStop()) {
            		System.out.print("STOPP:");
            		background.stopMusic();
            		stop();
            		background.stop();
            		Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("You Have Won The Game!");
            		alert.setHeaderText("Your High Score: "+animal.getPoints()+"!");
            		alert.setContentText("Highest Possible Score: 800");
            		alert.show();
            	}
            }
        };
    }
	public void start() {
		background.playMusic();
    	createTimer();
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
    
    public void setNumber(int n) {
    	int shift = 0;
    	while (n < 0) {
    		  int d = n / 10;
    		  int k = n - d * 10;
    		  n = d;
    		  background.add(new digit(k, 30, 360 - shift, 25));
    		  shift+=30;
    		}
    }
}
