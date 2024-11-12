package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a moving obstacle in Level 4. Obstacles will move along a specific path and can collide with the player.
 */
public class MovingObstacle extends ImageView {

    private static final String IMAGE_NAME = "/com/example/demo/images/moving_obstacle.png";
    private static final int OBSTACLE_SIZE = 100;
    private double xVelocity = 2;

    /**
     * Constructor for the moving obstacle.
     *
     * @param xPosition The initial X position of the obstacle.
     * @param yPosition The initial Y position of the obstacle.
     */
    public MovingObstacle(double xPosition, double yPosition) {
        this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        this.setFitWidth(OBSTACLE_SIZE);
        this.setFitHeight(OBSTACLE_SIZE);
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
    }

    /**
     * Updates the position of the obstacle, moving it horizontally across the screen.
     */
    public void updatePosition() {
        this.setLayoutX(getLayoutX() + xVelocity);
        if (getLayoutX() > 1200 || getLayoutX() < 0) {
            xVelocity *= -1; // Reverse direction if obstacle hits the edge of the screen.
        }
    }
}
