package com.example.demo;

/**
 * The EnemyProjectile class represents a projectile fired by the EnemyPlane.
 * It moves horizontally across the screen with a specified velocity.
 */
public class EnemyProjectile extends Projectile {

	/** The name of the image file used for the EnemyProjectile. */
	private static final String IMAGE_NAME = "enemyFire.png";
	/** The height of the EnemyProjectile image. */
	private static final int IMAGE_HEIGHT = 50;
	/** The horizontal velocity of the EnemyProjectile, moving leftward. */
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructs an EnemyProjectile object with a specified initial position.
	 *
	 * @param initialXPos the initial x-coordinate of the EnemyProjectile
	 * @param initialYPos the initial y-coordinate of the EnemyProjectile
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the EnemyProjectile by moving it horizontally at a
	 * constant velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the EnemyProjectile, including its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
