package com.example.demo;

/**
 * Represents a projectile fired by the Boss in the game, with specific image, size, and horizontal movement speed.
 */
public class BossProjectile extends Projectile {

	/** Image file for the BossProjectile. */
	private static final String IMAGE_NAME = "fireball.png";
	/** Image height for the BossProjectile. */
	private static final int IMAGE_HEIGHT = 75;
	/** Leftward horizontal velocity of the BossProjectile. */
	private static final int HORIZONTAL_VELOCITY = -15;
	/** Initial x-position of the BossProjectile on the screen. */
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructs a BossProjectile object with a specified initial y-position.
	 *
	 * @param initialYPos Initial y-coordinate of the BossProjectile.
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	/**
	 * Updates the BossProjectile's horizontal position based on its velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the BossProjectile's state.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
