package com.example.demo;

/**
 * Represents a projectile fired by the EnemyPlane.
 * Moves horizontally across the screen with a set velocity.
 */
public class EnemyProjectile extends Projectile {

	/** Image file name for the EnemyProjectile. */
	private static final String IMAGE_NAME = "enemyFire.png";
	/** Image height for the EnemyProjectile. */
	private static final int IMAGE_HEIGHT = 50;
	/** Horizontal velocity moving leftward for the EnemyProjectile. */
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructs an EnemyProjectile with specified initial coordinates.
	 *
	 * @param initialXPos Initial x-coordinate of the EnemyProjectile.
	 * @param initialYPos Initial y-coordinate of the EnemyProjectile.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Moves the EnemyProjectile horizontally at a constant velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the EnemyProjectile's state, including position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
