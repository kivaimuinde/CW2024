package com.example.demo;

/**
 * The FighterPlane class represents a type of active, destructible actor in the game.
 * It extends the ActiveActorDestructible class, adding functionality for managing health and firing projectiles.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	/** The health of the FighterPlane. */
	private int health;

	/**
	 * Constructs a FighterPlane object with specified attributes such as image, size, position, and health.
	 *
	 * @param imageName the name of the image file used for the FighterPlane
	 * @param imageHeight the height of the image
	 * @param initialXPos the initial x-coordinate of the FighterPlane
	 * @param initialYPos the initial y-coordinate of the FighterPlane
	 * @param health the initial health of the FighterPlane
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Fires a projectile from the FighterPlane.
	 * This method must be implemented by subclasses to specify the projectile behavior.
	 *
	 * @return the fired projectile or null if no projectile is fired
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Reduces the health of the FighterPlane when it takes damage.
	 * If the health reaches zero, the FighterPlane is destroyed.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Calculates the x-coordinate of the projectile's initial position, accounting for offsets.
	 *
	 * @param xPositionOffset the horizontal offset to apply
	 * @return the calculated x-coordinate for the projectile
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the y-coordinate of the projectile's initial position, accounting for offsets.
	 *
	 * @param yPositionOffset the vertical offset to apply
	 * @return the calculated y-coordinate for the projectile
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks whether the FighterPlane's health has reached zero.
	 *
	 * @return true if the health is zero, false otherwise
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Gets the current health of the FighterPlane.
	 *
	 * @return the current health of the FighterPlane
	 */
	public int getHealth() {
		return health;
	}
}
