package com.example.demo;

import javafx.scene.Group;

/**
 * The LevelView class is responsible for managing and displaying the graphical elements of the game
 * related to a specific level, such as the heart display, win image, and game over image.
 */
public class LevelView {

	/** The x position for the heart display on the screen. */
	private static final double HEART_DISPLAY_X_POSITION = 5;

	/** The y position for the heart display on the screen. */
	private static final double HEART_DISPLAY_Y_POSITION = 25;

	/** The x position for the win image on the screen. */
	private static final int WIN_IMAGE_X_POSITION = 355;

	/** The y position for the win image on the screen. */
	private static final int WIN_IMAGE_Y_POSITION = 175;

	/** The x position for the loss (game over) screen image. */
	private static final int LOSS_SCREEN_X_POSITION = -160;

	/** The y position for the loss (game over) screen image. */
	private static final int LOSS_SCREEN_Y_POSISITION = -375;

	/** The root group of the scene, which holds all the graphical elements. */
	private final Group root;

	/** The win image to display when the user wins the game. */
	private final WinImage winImage;

	/** The game over image to display when the user loses the game. */
	private final GameOverImage gameOverImage;

	/** The heart display that shows the player's remaining health. */
	private final HeartDisplay heartDisplay;

	/**
	 * Constructs the LevelView instance, initializing the necessary display elements for the level.
	 *
	 * @param root the root group to which UI elements will be added
	 * @param heartsToDisplay the initial number of hearts (player health) to display
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSISITION);
	}

	/**
	 * Displays the heart display on the screen, showing the player's current health.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Displays the win image on the screen when the player wins the level.
	 */
	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}

	/**
	 * Displays the game over image on the screen when the player loses the level.
	 */
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
	}

	/**
	 * Removes hearts from the display when the player loses health.
	 *
	 * @param heartsRemaining the number of hearts to display after removal
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}
}
