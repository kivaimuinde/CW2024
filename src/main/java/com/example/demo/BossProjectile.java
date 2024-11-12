package com.example.demo;

/**
 * The BossProjectile class represents a projectile fired by the Boss in the game.
 * It has a specific image, size, and horizontal movement speed.
 */
public class BossProjectile extends Projectile {

	/** The name of the image file used for the BossProjectile. */
	private static final String IMAGE_NAME = "fireball.png";
	/** The height of the BossProjectile image. */
	private static final int IMAGE_HEIGHT = 75;
	/** The horizontal velocity of the BossProjectile, moving leftward. */
	private static final int HORIZONTAL_VELOCITY = -15;
	/** The initial x-position of the BossProjectile on the screen. */
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructs a BossProjectile object at a specified initial y-position.
	 *
	 * @param initialYPos the initial y-coordinate of the BossProjectile
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	/**
	 * Updates the position of the BossProjectile by moving it horizontally at
	 * a constant velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the BossProjectile, including its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
