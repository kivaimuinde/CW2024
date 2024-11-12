package com.example.demo;

/**
 * The Projectile class represents a projectile (such as a bullet or fireball) that can be fired by a fighter plane or enemy.
 * It extends ActiveActorDestructible, indicating it is an active object that can be destroyed when it takes damage.
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Constructs a new projectile with the specified properties.
	 *
	 * @param imageName the name of the image file representing the projectile
	 * @param imageHeight the height of the projectile image
	 * @param initialXPos the initial x-position of the projectile
	 * @param initialYPos the initial y-position of the projectile
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * Destroys the projectile when it takes damage.
	 */
	@Override
	public void takeDamage() {
		destroy();
	}

	/**
	 * Updates the position of the projectile. This method must be implemented by subclasses to define the specific movement behavior.
	 */
	@Override
	public abstract void updatePosition();
}
