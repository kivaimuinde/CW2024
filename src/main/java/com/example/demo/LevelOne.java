package com.example.demo;

/**
 * The LevelOne class represents the first level of the game. It extends {@link LevelParent} and defines
 * the specific behavior and conditions for the level, including enemy spawning, player health, and level transition.
 */
public class LevelOne extends LevelParent {

	/** The background image file for the level. */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";

	/** The class name for the next level to be loaded. */
	private static final String NEXT_LEVEL = "com.example.demo.LevelTwo";

	/** The total number of enemies in the level. */
	private static final int TOTAL_ENEMIES = 5;

	/** The number of kills required to advance to the next level. */
	private static final int KILLS_TO_ADVANCE = 10;

	/** The probability that an enemy will spawn in the current cycle. */
	private static final double ENEMY_SPAWN_PROBABILITY = .20;

	/** The initial health of the player. */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * Constructs a LevelOne instance with the specified screen height and width, and initializes the level
	 * with the background image and player health.
	 *
	 * @param screenHeight the height of the screen
	 * @param screenWidth the width of the screen
	 */
	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the game is over based on the player's health or kill count. If the player is destroyed,
	 * the game ends. If the player reaches the required kill count, the next level is triggered.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (userHasReachedKillTarget()) {
			goToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Initializes the friendly units for this level, specifically adding the player unit to the game scene.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Spawns enemy units for this level, adding them to the game scene based on a defined probability.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	/**
	 * Instantiates the level view that is responsible for rendering the level on the screen.
	 *
	 * @return the {@link LevelView} instance for this level
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Determines if the player has reached the required number of kills to advance to the next level.
	 *
	 * @return true if the player has reached the kill target, false otherwise
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
}
