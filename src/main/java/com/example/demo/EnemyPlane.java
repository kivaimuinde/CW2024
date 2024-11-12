package com.example.demo;

/**
 * Represents an enemy plane in the game. It is a type of FighterPlane that moves
 * horizontally across the screen, fires projectiles at a defined rate, and can be damaged and destroyed.
 */
public class EnemyPlane extends FighterPlane {

	/** Image file name for the EnemyPlane. */
	private static final String IMAGE_NAME = "enemyplane.png";
	/** Image height for the EnemyPlane. */
	private static final int IMAGE_HEIGHT = 150;
	/** Leftward horizontal velocity of the EnemyPlane. */
	private static final int HORIZONTAL_VELOCITY = -6;
	/** X-offset for projectile position relative to the EnemyPlane. */
	private static final double PROJECTILE_X_OFFSET = -100.0;
	/** Y-offset for projectile position relative to the EnemyPlane. */
	private static final double PROJECTILE_Y_OFFSET = 50.0;
	/** Initial health for the EnemyPlane. */
	private static final int INITIAL_HEALTH = 1;
	/** Fire rate at which the EnemyPlane fires projectiles. */
	private static final double FIRE_RATE = 0.01;

	/**
	 * Constructs an EnemyPlane at the specified initial position.
	 *
	 * @param initialXPos Initial x-coordinate of the EnemyPlane.
	 * @param initialYPos Initial y-coordinate of the EnemyPlane.
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the horizontal position of the EnemyPlane based on its velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fires a projectile if the fire rate condition is met.
	 * The projectile's position is calculated based on the EnemyPlane's current location.
	 *
	 * @return A new EnemyProjectile if the fire rate condition is met; otherwise, null.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileX = getProjectileXPosition(PROJECTILE_X_OFFSET);
			double projectileY = getProjectileYPosition(PROJECTILE_Y_OFFSET);
			return new EnemyProjectile(projectileX, projectileY);
		}
		return null;
	}

	/**
	 * Updates the state of the EnemyPlane, including position and firing behavior.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
