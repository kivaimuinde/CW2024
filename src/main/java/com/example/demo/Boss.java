package com.example.demo;

import java.util.*;

/**
 * The Boss class represents a powerful enemy in the game that has a unique
 * movement pattern, can fire projectiles, and has a shield that occasionally activates
 * to block damage.
 */
public class Boss extends FighterPlane {

	/** Constants for the boss's attributes and behavior. */
	private static final String IMAGE_NAME = "bossplane.png";
	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
	private static final double BOSS_FIRE_RATE = .04;
	private static final double BOSS_SHIELD_PROBABILITY = .002;
	private static final int IMAGE_HEIGHT = 300;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HEALTH = 100;
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	private static final int ZERO = 0;
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = -100;
	private static final int Y_POSITION_LOWER_BOUND = 475;
	private static final int MAX_FRAMES_WITH_SHIELD = 500;

	/** List storing the boss's movement pattern. */
	private final List<Integer> movePattern;
	private boolean isShielded;
	private int consecutiveMovesInSameDirection;
	private int indexOfCurrentMove;
	private int framesWithShieldActivated;

	/**
	 * Constructs a Boss object with default attributes, initializes movement
	 * pattern, and sets shield and move tracking variables.
	 */
	public Boss() {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		isShielded = false;
		initializeMovePattern();
	}

	/**
	 * Updates the position of the boss based on its movement pattern. Prevents
	 * movement beyond the screen's upper and lower bounds.
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	/**
	 * Updates the boss's state, including position and shield status.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
	}

	/**
	 * Fires a projectile if the boss's firing rate condition is met.
	 *
	 * @return a new BossProjectile if firing occurs, or null if not
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}

	/**
	 * Takes damage if the boss's shield is not active.
	 */
	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
		}
	}

	/**
	 * Initializes the boss's movement pattern with a series of vertical moves
	 * and shuffles them to create variation.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Updates the shield's activation status based on probability and duration.
	 * Activates the shield if the condition is met, or deactivates it if the shield's
	 * duration is exhausted.
	 */
	private void updateShield() {
		if (isShielded) framesWithShieldActivated++;
		else if (shieldShouldBeActivated()) activateShield();
		if (shieldExhausted()) deactivateShield();
	}

	/**
	 * Retrieves the next move from the movement pattern, resetting or reshuffling
	 * if necessary.
	 *
	 * @return the next vertical movement step for the boss
	 */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	/**
	 * Determines if the boss should fire a projectile in the current frame based on a probability.
	 *
	 * @return true if the boss should fire, false otherwise
	 */
	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Gets the initial y-position for the boss's projectile.
	 *
	 * @return the y-coordinate from which the boss's projectile should be fired
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Determines if the boss's shield should activate based on a probability.
	 *
	 * @return true if the shield should activate, false otherwise
	 */
	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	/**
	 * Checks if the shield has been active for its maximum allowed duration.
	 *
	 * @return true if the shield duration has been exhausted, false otherwise
	 */
	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	/**
	 * Activates the boss's shield.
	 */
	private void activateShield() {
		isShielded = true;
	}

	/**
	 * Deactivates the boss's shield and resets the activation duration.
	 */
	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
	}
}
