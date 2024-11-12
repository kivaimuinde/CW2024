package com.example.demo;

/**
 * The UserProjectile class represents a projectile fired by the user plane in the game.
 * It extends the Projectile class and handles the movement and behavior of the user's projectiles.
 */
public class UserProjectile extends Projectile {

	// Constants for the projectile's image and movement settings
	private static final String IMAGE_NAME = "userfire.png";  // Image name for the projectile
	private static final int IMAGE_HEIGHT = 125;  // Height of the projectile image
	private static final int HORIZONTAL_VELOCITY = 15;  // Speed of the projectile's horizontal movement

	/**
	 * Constructs a UserProjectile object with an initial position.
	 *
	 * @param initialXPos the initial X position of the projectile
	 * @param initialYPos the initial Y position of the projectile
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the user projectile by moving it horizontally.
	 * The projectile moves at a fixed velocity to the right.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the user projectile.
	 * Calls updatePosition to move the projectile.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
