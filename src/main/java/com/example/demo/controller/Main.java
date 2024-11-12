package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Main class is the entry point of the application. It sets up the main game window
 * and initiates the Controller to start the game.
 */
public class Main extends Application {

	/** The width of the game window in pixels. */
	private static final int SCREEN_WIDTH = 1300;

	/** The height of the game window in pixels. */
	private static final int SCREEN_HEIGHT = 750;

	/** The title of the game window. */
	private static final String TITLE = "Sky Battle";

	/** The game controller responsible for managing levels and game state. */
	private Controller myController;

	/**
	 * Sets up the main game window with the specified dimensions and title.
	 * Initializes the Controller and launches the game.
	 *
	 * @param stage the primary Stage object for the application window
	 * @throws ClassNotFoundException if the Controller cannot load the initial level class
	 * @throws NoSuchMethodException if the initial level constructor is not found
	 * @throws SecurityException if access to the level class or constructor is restricted
	 * @throws InstantiationException if the initial level class cannot be instantiated
	 * @throws IllegalAccessException if the level class or constructor is inaccessible
	 * @throws IllegalArgumentException if invalid arguments are passed to the constructor
	 * @throws InvocationTargetException if the constructor throws an exception
	 */
	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);
		myController = new Controller(stage);
		myController.launchGame();
	}

	/**
	 * The main method launches the JavaFX application.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		launch();
	}
}
