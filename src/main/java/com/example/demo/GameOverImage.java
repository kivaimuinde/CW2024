package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The GameOverImage class represents the "Game Over" image displayed at the end of the game.
 * It extends ImageView to display an image at a specified position on the screen.
 */
public class GameOverImage extends ImageView {

	/** The path to the "Game Over" image file. */
	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

	/**
	 * Constructs a GameOverImage with a specified position.
	 * This constructor loads the "Game Over" image and sets its position on the screen.
	 *
	 * @param xPosition the x-coordinate for the image
	 * @param yPosition the y-coordinate for the image
	 */
	public GameOverImage(double xPosition, double yPosition) {
		// Load the "Game Over" image from the resources folder
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));

		// Set the position of the image
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}
}
