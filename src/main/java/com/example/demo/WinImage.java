package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The WinImage class represents the "You Win" image shown when the player wins the game.
 * It extends the ImageView class and is used to display a victory image when triggered.
 */
public class WinImage extends ImageView {

	// Constants for the image's file path and dimensions
	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";  // Image file path
	private static final int HEIGHT = 500;  // Height of the image
	private static final int WIDTH = 600;  // Width of the image

	/**
	 * Constructs a WinImage object at a specified position.
	 *
	 * @param xPosition the X position of the image on the screen
	 * @param yPosition the Y position of the image on the screen
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);  // Initially set the image to be invisible
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
	}

	/**
	 * Displays the "You Win" image by making it visible.
	 */
	public void showWinImage() {
		this.setVisible(true);
	}
}
