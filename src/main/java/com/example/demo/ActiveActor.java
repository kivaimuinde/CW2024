package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The ActiveActor class represents an interactive, movable game object
 * that is displayed as an image on the screen. This abstract class provides
 * basic functionalities for image setup and movement, allowing subclasses to
 * define specific behaviors for different types of active actors.
 */
public abstract class ActiveActor extends ImageView {

	/** The location path for images used by active actors. */
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructs an ActiveActor with the specified image, size, and initial position.
	 *
	 * @param imageName the name of the image file for the actor
	 * @param imageHeight the height to set for the image, preserving its aspect ratio
	 * @param initialXPos the initial x-coordinate of the actor on the screen
	 * @param initialYPos the initial y-coordinate of the actor on the screen
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Updates the position of the actor. Each subclass must implement this
	 * method to define how the actor's position is updated.
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally by the specified distance.
	 *
	 * @param horizontalMove the distance to move horizontally
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the actor vertically by the specified distance.
	 *
	 * @param verticalMove the distance to move vertically
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}
}
