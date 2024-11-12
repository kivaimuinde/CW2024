package com.example.demo;

/**
 * The EnemyPlane class represents an enemy plane in the game. It is a type of
 * FighterPlane that moves horizontally across the screen, fires projectiles at a
 * certain rate, and can be damaged and destroyed.
 */
public class EnemyPlane extends FighterPlane {

	/** The name of the image file used for the EnemyPlane. */
	private static final String IMAGE_NAME = "enemyplane.png";
	/** The height of the EnemyPlane image. */
	private static final int IMAGE_HEIGHT = 150;
	/** The horizontal velocity of the EnemyPlane, moving leftward. */
	private static final int HORIZONTAL_VELOCITY = -6;
	/** The x-offset for the position of the projectile relative to the EnemyPlane. */
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	/** The y-offset for the position of the projectile relative to the EnemyPlane. */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	/** The initial health of the EnemyPlane. */
	private static final int INITIAL_HEALTH = 1;
	/** The fire rate at which the EnemyPlane fires projectiles. */
	private static final double FIRE_RATE = 0.01;

	/**
	 * Constructs an EnemyPlane object with a specified initial position.
	 *
	 * @param initialXPos the initial x-coordinate of the EnemyPlane
	 * @param initialYPos the initial y-coordinate of the EnemyPlane
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the position of the EnemyPlane by moving it horizontally at a
	 * constant velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fires a projectile from the EnemyPlane if the fire rate condition is met.
	 * The projectile's position is calculated relative to the EnemyPlane's current
	 * position.
	 *
	 * @return a new EnemyProjectile if the fire rate condition is met, otherwise null
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}

	/**
	 * Updates the state of the EnemyPlane, including its position and firing behavior.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
