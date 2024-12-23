package uk.ac.nott.cs.comp2013.froggerApp.sprites;

/**
 * This abstract class holds the necessary abstractions for a Sprite in a game.
 * Each Sprite will have their own definition of rendering and handling collisions.
 */
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Sprite {
	protected double x; // Top left x
	protected double y; // Top left y
	private double vX;
	private double vY;
	private double width;
	private double height;

	// For collision
	private double centerX;
	private double centerY;
	private double halfWidth;
	private double halfHeight;

	private Image image;

	/**
	 * Creates a Sprite and renders it using the passed in GraphicsContext.
	 * 
	 * @param x
	 *            The upper left x coordinate
	 * @param y
	 *            The upper left y coordinate
	 * @param width
	 *            The desired width
	 * @param height
	 *            The desired height
	 * @param gc
	 *            The GraphicsContext that will do the drawing
	 */
	public Sprite(double x, double y, double width, double height, GraphicsContext gc) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);

		this.setHalfWidth(this.getWidth() / 2);
		this.setHalfHeight(this.getHeight() / 2);

		this.setCenterX(this.getX() + this.getHalfWidth());
		this.setCenterY(this.getY() + this.getHalfHeight());

		this.render(gc);
	}

	public Sprite(String imageFile, double x, double y, double width, double height, GraphicsContext gc) {
		setX(x);
		setY(y);

		try {
			this.image = new Image(imageFile);

			// Recalculating Sprite x and y positions
			setWidth(image.getWidth());
			setHeight(image.getHeight());

			setHalfWidth(getWidth() / 2);
			setHalfHeight(getHeight() / 2);

			setCenterX(getX() + getHalfWidth());
			setCenterY(getY() + getHalfHeight());

		} catch (Exception e) {
			System.out.println(imageFile + " does not exist.");
		}

		render(gc);
	}

	/**
	 * Determines if this Sprite collided with another. Will be overridden in child
	 * classes.
	 * 
	 * @param other
	 *            The other Sprite
	 * @return Whether or not this collided with the other Sprite
	 */
	public abstract boolean didCollideWith(Sprite other);

	/**
	 * Determines if this Sprite collided with the top wall. Will be overridden in
	 * child classes.
	 * 
	 * @param canvas
	 *            The canvas to check boundaries on.
	 * @return Whether or not this collided with the scene's top wall.
	 */
	public abstract boolean didCollideWithTopWall(Canvas canvas);

	/**
	 * Determines if this Sprite collided with the bottom wall. Will be overridden
	 * in child classes.
	 * 
	 * @param canvas
	 *            The canvas to check boundaries on.
	 * @return Whether or not this collided with the scene's bottom wall.
	 */
	public abstract boolean didCollideWithBotWall(Canvas canvas);

	/**
	 * Determines if this Sprite collided with the left wall. Will be overridden in
	 * child classes.
	 * 
	 * @param canvas
	 *            The canvas to check boundaries on.
	 * @return Whether or not this collided with the scene's left wall.
	 */
	public abstract boolean didCollideWithLeftWall(Canvas canvas);

	/**
	 * Determines if this Sprite collided with the right wall. Will be overridden in
	 * child classes.
	 * 
	 * @param canvas
	 *            The canvas to check boundaries on.
	 * @return Whether or not this collided with the scene's right wall.
	 */
	public abstract boolean didCollideWithRightWall(Canvas canvas);

	/**
	 * Draws the Sprite using the GraphicsContext specified.
	 * 
	 * @param gc
	 *            The graphicsContext of the Canvas.
	 */
	public abstract void render(GraphicsContext gc);

	/**
	 * Updates the Sprite's new position with regards to time.
	 * 
	 * @param time
	 *            The time pertaining to the Sprite's new position.
	 */
	public void update(double time) {
		this.x += this.getvX() * time;
		this.y += this.getvY() * time;

		this.setCenterX(this.getX() + this.getHalfWidth());
		this.setCenterY(this.getY() + this.getHalfHeight());
	}

	/**
	 * Scales the image
	 * 
	 * @param source
	 *            The image to scale
	 * @param targetWidth
	 *            The desired width of the image
	 * @param targetHeight
	 *            The desired height of the image
	 * @param preserveRatio
	 *            ?
	 * @return The scaled Image
	 */
	public Image scaleImage(Image source, double targetWidth, double targetHeight, boolean preserveRatio) {
		ImageView imageView = new ImageView(source);
		imageView.setPreserveRatio(preserveRatio);
		imageView.setFitWidth(targetWidth);
		imageView.setFitHeight(targetHeight);
		return imageView.snapshot(null, null);
	}

	public Image getImage() {
		return image;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getvX() {
		return vX;
	}

	public double getvY() {
		return vY;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public double getCenterX() {
		return centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public double getHalfWidth() {
		return halfWidth;
	}

	public double getHalfHeight() {
		return halfHeight;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setvX(double vX) {
		this.vX = vX;
	}

	public void setvY(double vY) {
		this.vY = vY;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	public void setHalfWidth(double halfWidth) {
		this.halfWidth = halfWidth;
	}

	public void setHalfHeight(double halfHeight) {
		this.halfHeight = halfHeight;
	}

}
