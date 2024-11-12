package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The ShieldImage class represents a shield image in the game.
 * It is used to display a shield that can be shown or hidden on the screen.
 * This shield image can be toggled on and off during gameplay.
 */
public class ShieldImage extends ImageView {

	// Path to the shield image file
	private static final String IMAGE_NAME = "/images/shield.png";
	// The size of the shield image
	private static final int SHIELD_SIZE = 200;

	/**
	 * Constructs a ShieldImage instance at the specified position.
	 * Initializes the image, sets its size, and makes it initially invisible.
	 *
	 * @param xPosition the x position of the shield image
	 * @param yPosition the y position of the shield image
	 */
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		// Set the shield image from the class path
		this.setImage(new Image(getClass().getResource("/com/example/demo/images/shield.jpg").toExternalForm()));
		this.setVisible(false); // Initially, the shield is hidden
		this.setFitHeight(SHIELD_SIZE); // Set the height of the shield
		this.setFitWidth(SHIELD_SIZE); // Set the width of the shield
	}

	/**
	 * Makes the shield visible on the screen.
	 */
	public void showShield() {
		this.setVisible(true);
	}

	/**
	 * Hides the shield from the screen.
	 */
	public void hideShield() {
		this.setVisible(false);
	}
}
