package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;

/**
 * The LevelParent class serves as the base class for creating different levels in the game.
 * It manages game mechanics such as player and enemy interactions, projectile handling, and level progression.
 */
public abstract class LevelParent extends Observable {

	/** The adjustment to screen height for the level's display area. */
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;

	/** The delay between each game loop iteration in milliseconds. */
	private static final int MILLISECOND_DELAY = 50;

	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;

	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;

	private int currentNumberOfEnemies;
	private LevelView levelView;

	/**
	 * Constructs a LevelParent instance, initializing game elements including the scene, timeline, player,
	 * and lists for friendly units, enemy units, and projectiles.
	 *
	 * @param backgroundImageName the background image file for the level
	 * @param screenHeight the height of the screen
	 * @param screenWidth the width of the screen
	 * @param playerInitialHealth the initial health of the player
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();
		friendlyUnits.add(user);
	}

	/**
	 * Initializes the friendly units for the level. This method must be implemented by subclasses.
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Checks whether the game is over, either due to the player's destruction or reaching a kill target.
	 * This method must be implemented by subclasses.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Spawns enemy units in the level. This method must be implemented by subclasses.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Instantiates the level view that is responsible for displaying the level's visuals.
	 * This method must be implemented by subclasses.
	 *
	 * @return the LevelView instance for this level
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * Initializes the scene with the background image, friendly units, and other visual elements.
	 *
	 * @return the initialized Scene object
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	/**
	 * Starts the game by beginning the game loop (timeline).
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	/**
	 * Moves the game to the next level by notifying observers with the next level's class name.
	 *
	 * @param levelName the class name of the next level
	 */
	public void goToNextLevel(String levelName) {
		setChanged();
		notifyObservers(levelName);
	}

	/**
	 * Updates the scene during each iteration of the game loop, handling the spawning of enemies,
	 * movement of actors, collision detection, and removal of destroyed actors.
	 */
	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	/**
	 * Initializes the game loop's timeline and sets up the periodic update cycle.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Initializes the background of the level, including keyboard input handling for player movement and shooting.
	 */
	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP) user.moveUp();
				if (kc == KeyCode.DOWN) user.moveDown();
				if (kc == KeyCode.SPACE) fireProjectile();
			}
		});
		background.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
			}
		});
		root.getChildren().add(background);
	}

	/**
	 * Fires a projectile from the player's plane.
	 */
	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	/**
	 * Generates enemy fire (projectiles) by iterating over all enemy units.
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	/**
	 * Spawns an enemy projectile and adds it to the root and projectile list if it exists.
	 */
	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	/**
	 * Updates the position and state of all actors in the game (friendly units, enemy units, projectiles).
	 */
	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	/**
	 * Removes all destroyed actors from the scene and their respective lists.
	 */
	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	/**
	 * Removes destroyed actors from the specified list and the scene.
	 */
	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Handles collisions between planes and projectiles (friendly and enemy units).
	 */
	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	/**
	 * Handles collisions between user projectiles and enemy units.
	 */
	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	/**
	 * Handles collisions between enemy projectiles and friendly units.
	 */
	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	/**
	 * General collision handler that damages actors if they intersect.
	 */
	private void handleCollisions(List<ActiveActorDestructible> actors1,
								  List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					actor.takeDamage();
					otherActor.takeDamage();
				}
			}
		}
	}

	/**
	 * Checks if any enemy units have penetrated the defenses and damages the player accordingly.
	 */
	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	/**
	 * Updates the level view by removing hearts based on the player's health.
	 */
	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	/**
	 * Updates the player's kill count based on the number of destroyed enemies.
	 */
	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	/**
	 * Checks if an enemy has crossed the screen boundary, signaling penetration.
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	/**
	 * Stops the game and displays the win screen.
	 */
	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
	}

	/**
	 * Stops the game and displays the game over screen.
	 */
	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
	}

	/**
	 * Returns the user (player) plane.
	 *
	 * @return the user plane
	 */
	protected UserPlane getUser() {
		return user;
	}

	/**
	 * Returns the root group for the scene.
	 *
	 * @return the root group
	 */
	protected Group getRoot() {
		return root;
	}

	/**
	 * Returns the current number of enemy units in the level.
	 *
	 * @return the current number of enemies
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	/**
	 * Adds an enemy unit to the level and the root scene.
	 *
	 * @param enemy the enemy unit to add
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	/**
	 * Returns the maximum Y position for enemies to spawn.
	 *
	 * @return the maximum Y position for enemy spawning
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Returns the width of the screen.
	 *
	 * @return the screen width
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Checks if the player is destroyed.
	 *
	 * @return true if the player is destroyed, false otherwise
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	/**
	 * Updates the number of enemies in the level.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

}
