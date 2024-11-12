package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The HeartDisplay class manages the display of hearts representing the player's health in the game.
 * It uses an HBox container to display multiple heart images in a horizontal layout.
 */
public class HeartDisplay {

	/** The path to the heart image file. */
	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";

	/** The height to which each heart image will be resized. */
	private static final int HEART_HEIGHT = 50;

	/** The index of the first heart image in the container for removal purposes. */
	private static final int INDEX_OF_FIRST_ITEM = 0;

	/** The container for displaying hearts. */
	private HBox container;

	/** The x-coordinate for positioning the heart display container. */
	private double containerXPosition;

	/** The y-coordinate for positioning the heart display container. */
	private double containerYPosition;

	/** The number of hearts to display initially. */
	private int numberOfHeartsToDisplay;

	/**
	 * Constructs a HeartDisplay with a specified position and number of hearts to display.
	 *
	 * @param xPosition the x-coordinate for the heart display container
	 * @param yPosition the y-coordinate for the heart display container
	 * @param heartsToDisplay the number of hearts to initially display
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the HBox container that will hold the heart images.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);
	}

	/**
	 * Initializes the heart images based on the number of hearts to display.
	 * The heart images are added to the container in a horizontal layout.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));

			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}

	/**
	 * Removes the first heart from the heart display.
	 * This method simulates the player losing a heart.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}

	/**
	 * Returns the HBox container that holds the heart images.
	 *
	 * @return the container for the heart images
	 */
	public HBox getContainer() {
		return container;
	}
}
