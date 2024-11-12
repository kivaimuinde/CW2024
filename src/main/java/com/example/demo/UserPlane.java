package com.example.demo;

/**
 * The UserPlane class represents the player's fighter plane in the game.
 * It extends the FighterPlane class and handles user-controlled movements,
 * firing projectiles, and tracking the number of kills.
 */
public class UserPlane extends FighterPlane {

	// Constants for the user's plane image and initial settings
	private static final String IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = -40;  // Upper Y-bound to limit movement
	private static final double Y_LOWER_BOUND = 600.0;  // Lower Y-bound to limit movement
	private static final double INITIAL_X_POSITION = 5.0;  // Initial X position
	private static final double INITIAL_Y_POSITION = 300.0;  // Initial Y position
	private static final int IMAGE_HEIGHT = 150;  // Image height of the plane
	private static final int VERTICAL_VELOCITY = 8;  // Vertical movement speed
	private static final int PROJECTILE_X_POSITION = 110;  // X position of the projectile
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;  // Y offset for projectile spawn

	private int velocityMultiplier;  // Controls the direction and speed of the plane's movement
	private int numberOfKills;  // Tracks the number of kills the player has made

	/**
	 * Constructs a UserPlane object with an initial health value.
	 *
	 * @param initialHealth the initial health of the user plane
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		velocityMultiplier = 0;  // Initially not moving
	}

	/**
	 * Updates the position of the user plane based on user input.
	 * The plane can move up or down within defined vertical bounds.
	 */
	@Override
	public void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);  // Prevent movement beyond bounds
			}
		}
	}

	/**
	 * Updates the state of the plane.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Fires a projectile from the user plane.
	 *
	 * @return the projectile fired by the user plane
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	/**
	 * Checks if the user plane is currently moving.
	 *
	 * @return true if the user plane is moving, false otherwise
	 */
	private boolean isMoving() {
		return velocityMultiplier != 0;
	}

	/**
	 * Makes the plane move upwards.
	 */
	public void moveUp() {
		velocityMultiplier = -1;
	}

	/**
	 * Makes the plane move downwards.
	 */
	public void moveDown() {
		velocityMultiplier = 1;
	}

	/**
	 * Stops the movement of the user plane.
	 */
	public void stop() {
		velocityMultiplier = 0;
	}

	/**
	 * Returns the number of kills the user plane has made.
	 *
	 * @return the number of kills
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the kill count of the user plane by 1.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}
}
