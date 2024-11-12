package com.example.demo;

/**
 * The LevelTwo class extends the LevelParent class to create the second level in the game.
 * This level introduces a boss enemy and features game mechanics specific to this level.
 */
public class LevelTwo extends LevelParent {

	/** The background image for the second level. */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";

	/** The initial health of the player for level two. */
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/** The boss enemy for level two. */
	private final Boss boss;

	/** The level view specific to LevelTwo. */
	private LevelViewLevelTwo levelView;

	/**
	 * Constructs the LevelTwo instance, initializing the boss and setting up the level's background,
	 * player health, and other necessary game elements.
	 *
	 * @param screenHeight the height of the screen for the level
	 * @param screenWidth the width of the screen for the level
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();
	}

	/**
	 * Initializes the friendly units (in this case, only the user plane) for level two.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks if the game is over based on the player's status or the boss's destruction.
	 * If the user is destroyed, the game ends with a loss. If the boss is destroyed, the game ends with a win.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

	/**
	 * Spawns enemy units for level two. In this case, the boss is spawned if no enemies are currently active.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	/**
	 * Instantiates the level view specific to LevelTwo, which includes unique features and displays for this level.
	 *
	 * @return the LevelViewLevelTwo instance for this level
	 */
	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}

}
