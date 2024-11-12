package com.example.demo;

/**
 * The ActiveActorDestructible class represents an active, destructible game object.
 * This abstract class extends ActiveActor and implements the Destructible interface,
 * allowing subclasses to define specific behaviors for objects that can take damage
 * and be destroyed.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	/** A flag indicating whether the actor has been destroyed. */
	private boolean isDestroyed;

	/**
	 * Constructs an ActiveActorDestructible with the specified image, size, and initial position.
	 *
	 * @param imageName the name of the image file for the actor
	 * @param imageHeight the height to set for the image, preserving its aspect ratio
	 * @param initialXPos the initial x-coordinate of the actor on the screen
	 * @param initialYPos the initial y-coordinate of the actor on the screen
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
	 * Updates the position of the actor. Each subclass must implement this method
	 * to define how the actor's position is updated.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Updates the actor's state. Each subclass must implement this method
	 * to define how the actor's state is updated.
	 */
	public abstract void updateActor();

	/**
	 * Applies damage to the actor. Each subclass must implement this method to
	 * define how the actor reacts to damage.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Destroys the actor by setting its destroyed status to true.
	 */
	@Override
	public void destroy() {
		setDestroyed(true);
	}

	/**
	 * Sets the destroyed status of the actor.
	 *
	 * @param isDestroyed true if the actor is destroyed, false otherwise
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * Checks if the actor is destroyed.
	 *
	 * @return true if the actor is destroyed, false otherwise
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
}
