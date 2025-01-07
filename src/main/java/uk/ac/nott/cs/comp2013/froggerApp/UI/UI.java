package uk.ac.nott.cs.comp2013.froggerApp.UI;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

/**
 * This abstract class builds the necessary UI components for a game.
 * 
 * @author Phillip
 *
 */
public abstract class UI extends Pane {
	private Canvas canvas;
	
	public UI(Canvas canvas) {
		this.canvas = canvas;
	}

	/**
	 * Creates the UI for the game.
	 */
	public abstract void create();

	/**
	 * Places the canvas in the designated root.
	 * The canvas is where the Sprites and such are drawn onto.
	 */
	public abstract void placeCanvas(Group root);
	
	/**
	 * Initializes the statistics used to debug the game.
	 */
	public abstract void initStats(); 
	
	public Canvas getCanvas() {
		return canvas;
	}
}
