# SKY GAME

## GitHub Repository
[Link to GitHub Repository](https://github.com/yourusername/1942-retro-game)

## Compilation Instructions
1. Ensure that Java Development Kit (JDK) 17 or higher is installed on your system.
2. Download or clone this repository:
   ```bash
   git clone https://github.com/kivaimuinde/CW2024.git

3. Navigate to the project directory
   ```bash
   cd CW2024

4. Use a Java IDE like IntelliJ IDEA or Eclipse, or compile the code directly using the command line
   ```bash
   javac -d bin src/com/example/demo/*.java
   

5. To run game
   ```bash
   java -cp bin com.example.demo.Main
   
Alternatively, one can use the run button on various IDEs menu


## Dependencies
* Ensure you have JavaFX installed and properly configured, as it is required for UI components.
* If using an IDE, configure the JavaFX library path in your project settings.

## Implemented and working Properly
* Player Movement: Smooth vertical movement of the player’s plane, constrained within game boundaries.
* Enemy Movement and Spawning: Randomized enemy spawning and movement with bounds checking.
* Projectile Firing: Player and enemy planes fire projectiles.
* Collision Detection: Detects collisions between projectiles and planes, reducing health or destroying objects.
* Level Progression: Transitions between Levels 1, 2, 3, and 4, each with unique enemies, speeds, and boss levels.
* Game Over and Win Screens: Displayed based on conditions for player victory or loss.
* Health and Shields: Heart displays track player health, and shield visuals are activated in specific levels.

## Implemented but Not Working Properly
* Enemy AI: While basic movement and firing functions, certain complex patterns are not fully implemented. Attempts to introduce more dynamic enemy patterns affected performance and were partially rolled back.
* Boss Behavior in Levels 3 and 4: The boss entity occasionally shows inconsistent behavior under rapid player movement or heavy projectile activity.

## Features Not Implemented
* **_Power-Ups_**: Power-up items were intended but were not fully implemented due to time constraints.
* **Score Tracking**: A feature to track and display player score dynamically was planned but could not be completed in this iteration.

## New Classes
* **LevelThree**: Extends LevelParent, adding specific enemies and boss behavior for increased difficulty in Level 3.
* **LevelFour**: Further extends LevelParent, with advanced enemies and a final boss for the game's ultimate challenge.
* **ShieldImage**: Manages the shield image’s position, size, and visibility within levels.
* **HeartDisplay**: Controls the heart UI to visually track player health.

## Modified Java Classes
* **_FighterPlane_**: Refactored for modular movement and health management; improvements in the projectile firing method.
* _**EnemyPlane**_: Adjusted movement patterns, added bounds checking, and improved projectile firing to match the player’s firing system.
* **_LevelParent_**: Restructured to accommodate Level 3 and Level 4 extensions, allowing each level to customize boss and enemy types.
* **_LevelViewLevelTwo_**: Added shield display control specific to Level 2.

## Unexpected Problems
* **_JavaFX Performance_**: High numbers of sprites on-screen caused lag; resolved by optimizing collision checks and adjusting frame rates.
* **_Inconsistent Boss Behavior_**: Boss entities in higher levels occasionally act unpredictably; troubleshooting this was partially successful but requires further attention.
* **_JavaFX Resource Pathing_**: Image files had pathing issues when packaged in a .jar file; this was addressed by standardizing path references in the codebase.
## Known Issues
* Hitbox Precision: The hitbox detection for collisions is sometimes imprecise under heavy activity. Improvements in hitbox granularity are planned.
* Level-Specific Background Scaling: Some background images do not scale correctly across different screen sizes.