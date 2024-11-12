package com.example.demo;

import javafx.scene.Group;

/**
 * The LevelViewLevelTwo class is an extension of the LevelView class, specifically designed for Level Two.
 * It manages additional graphical elements specific to this level, such as a shield image.
 */
public class LevelViewLevelTwo extends LevelView {

	/** The x position for the shield image on the screen. */
	private static final int SHIELD_X_POSITION = 1150;

	/** The y position for the shield image on the screen. */
	private static final int SHIELD_Y_POSITION = 500;

	/** The root group of the scene, which holds all graphical elements for this level. */
	private final Group root;

	/** The shield image to be displayed during the game. */
	private final ShieldImage shieldImage;

	/**
	 * Constructs the LevelViewLevelTwo instance, initializing the necessary display elements for this level.
	 * This includes the heart display (from the superclass) and a shield image.
	 *
	 * @param root the root group to which UI elements will be added
	 * @param heartsToDisplay the initial number of hearts (player health) to display
	 */
	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		addImagesToRoot();
	}

	/**
	 * Adds all relevant images (like the shield) to the root of the scene.
	 */
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}

	/**
	 * Displays the shield image on the screen.
	 */
	public void showShield() {
		shieldImage.showShield();
	}

	/**
	 * Hides the shield image from the screen.
	 */
	public void hideShield() {
		shieldImage.hideShield();
	}
}
