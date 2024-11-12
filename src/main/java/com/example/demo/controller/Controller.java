package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.example.demo.LevelParent;

/**
 * The Controller class manages the game flow by initializing levels and handling transitions
 * between them. It observes changes in the game state and updates accordingly.
 */
public class Controller implements Observer {

	/** The class name for the first level of the game. */
	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";

	/** The primary stage for the application window. */
	private final Stage stage;

	/**
	 * Constructs a Controller for managing the game stages and levels.
	 *
	 * @param stage the primary Stage object for the application window
	 */
	public Controller(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Launches the game, displaying the stage and starting from the first level.
	 *
	 * @throws ClassNotFoundException if the level class cannot be found
	 * @throws NoSuchMethodException if the level constructor is not found
	 * @throws SecurityException if access to the level class or constructor is restricted
	 * @throws InstantiationException if the level class cannot be instantiated
	 * @throws IllegalAccessException if the level class or constructor is inaccessible
	 * @throws IllegalArgumentException if invalid arguments are passed to the constructor
	 * @throws InvocationTargetException if the constructor throws an exception
	 */
	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	/**
	 * Transitions to the specified level.
	 *
	 * @param className the name of the level class to load
	 * @throws ClassNotFoundException if the level class cannot be found
	 * @throws NoSuchMethodException if the level constructor is not found
	 * @throws SecurityException if access to the level class or constructor is restricted
	 * @throws InstantiationException if the level class cannot be instantiated
	 * @throws IllegalAccessException if the level class or constructor is inaccessible
	 * @throws IllegalArgumentException if invalid arguments are passed to the constructor
	 * @throws InvocationTargetException if the constructor throws an exception
	 */
	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Class<?> myClass = Class.forName(className);
		Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
		LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
		myLevel.addObserver(this);
		Scene scene = myLevel.initializeScene();
		stage.setScene(scene);
		myLevel.startGame();
	}

	/**
	 * Responds to changes in observed objects, transitioning to a specified level.
	 *
	 * @param arg0 the observable object triggering the update
	 * @param arg1 the level class name to transition to, provided as a String
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			goToLevel((String) arg1);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				 | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}
}
