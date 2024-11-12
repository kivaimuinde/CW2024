package com.example.demo;

import javafx.scene.Group;

/**
 * Represents the view for Level 3 of the game.
 * This view includes a boss and some additional visual elements specific to Level 3.
 */
public class LevelViewLevelThree extends LevelView {

    private final Group root;
    private final ShieldImage shieldImage;

    /**
     * Constructor for LevelViewLevelThree.
     * Initializes the level's graphical elements, including the shield.
     *
     * @param root          The root group where UI elements are added.
     * @param heartsToDisplay The number of hearts to display for the player.
     */
    public LevelViewLevelThree(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        this.root = root;
        this.shieldImage = new ShieldImage(1150, 500);
        root.getChildren().add(shieldImage);
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

    @Override
    public void showWinImage() {
        // Level-specific win image logic (if any)
    }

    @Override
    public void showGameOverImage() {
        // Level-specific game over image logic (if any)
    }
}
