package com.example.demo;

import javafx.scene.Group;

/**
 * Represents the view for Level 4 of the game.
 * This view includes more complex graphical elements like moving obstacles and environmental hazards.
 */
public class LevelViewLevelFour extends LevelView {

    private final Group root;
    private final ShieldImage shieldImage;
    private final MovingObstacle movingObstacle;

    /**
     * Constructor for LevelViewLevelFour.
     * Initializes the graphical elements for Level 4, such as the shield and moving obstacles.
     *
     * @param root          The root group where UI elements are added.
     * @param heartsToDisplay The number of hearts to display for the player.
     */
    public LevelViewLevelFour(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        this.root = root;
        this.shieldImage = new ShieldImage(1150, 500);
        this.movingObstacle = new MovingObstacle(600, 400);
        root.getChildren().addAll(shieldImage, movingObstacle);
    }

    /**
     * Shows the shield on the screen.
     */
    public void showShield() {
        shieldImage.showShield();
    }

    /**
     * Hides the shield from the screen.
     */
    public void hideShield() {
        shieldImage.hideShield();
    }

    /**
     * Updates the movement of obstacles for Level 4.
     */
    public void updateObstacles() {
        movingObstacle.updatePosition();
    }

    @Override
    public void showWinImage() {
        // Level-specific win image logic (if any)
    }

    @Override
    public void showGameOverImage() {
        // Level-specific game over image logic (if any)
    }
}
