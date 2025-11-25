package main;

import java.io.Serializable;

/**
 * This class is for store the defined screen values and the pre- and after values for loading and finishing the game.
 */
public class Data implements Serializable {
    private static final int tileSize = 20;
    private final int screenCol = 54;
    private final int screenRow = 36;
    private final int screenWidth = tileSize * screenCol; // 1080;
    private final int screenHeight = tileSize * screenRow; // 720;
    private final int FPS = 100;
    private boolean goToSettings, goToGameMode = false;
    private int snakeScore;
    private int secondSnakeScore;
    private int whoIsWon;

    /**
     * No-args constructor for the class
     */
    public Data()
    {

    }

    /**
     * Retrieves the tile size.
     *
     * @return The int value of tile size.
     */
    public int getTileSize()
    {
        return tileSize;
    }

    /**
     * Retrieves the screen width.
     *
     * @return The int value of screen width.
     */
    public int getScreenWidth()
    {
        return screenWidth;
    }

    /**
     * Retrieves the screen height.
     *
     * @return The int value of screen height.
     */
    public int getScreenHeight()
    {
        return screenHeight;
    }

    /**
     * Retrieves the FPS, which is the value of the updated frames.
     *
     * @return The int value of FPS.
     */
    public int getFPS()
    {
        return FPS;
    }

    /**
     * Retrieves true if the user navigate to the settings, else its false.
     *
     * @return True if the user went to the settings, false otherwise.
     */
    public boolean isGoToSettings()
    {
        return goToSettings;
    }

    /**
     * Sets the value whether the user went tho the settings.
     *
     * @param goToSettings True if the user went to settings and false if didn't.
     */
    public void setGoToSettings(boolean goToSettings)
    {
        this.goToSettings = goToSettings;
    }

    /**
     * Retrieves true if the user navigate to the game mode screen, else its false.
     *
     * @return True if the user went to the game mode screen, false otherwise.
     */
    public boolean isGoToGameMode()
    {
        return goToGameMode;
    }

    /**
     * Sets the value whether the user went tho the game mode.
     *
     * @param goToGameMode True if the user went to the game mode and false if didn't.
     */
    public void setGoToGameMode(boolean goToGameMode)
    {
        this.goToGameMode = goToGameMode;
    }

    /**
     * Retrieves how many fruits ate the green snake.
     *
     * @return The green snake's score.
     */
    public int getSnakeScore()
    {
        return snakeScore;
    }

    /**
     * Sets how many fruits ate the green snake.
     *
     * @param snakeScore The int number of the score (fruits eaten).
     */
    public void setSnakeScore(int snakeScore)
    {
        this.snakeScore = snakeScore;
    }

    /**
     * Retrieves how many fruits ate the orange snake.
     *
     * @return The orange snake's score.
     */
    public int getSecondSnakeScore()
    {
        return secondSnakeScore;
    }

    /**
     * Sets how many fruits ate the orange snake.
     *
     * @param secondSnakeScore The int number of the score (fruits eaten).
     */
    public void setSecondSnakeScore(int secondSnakeScore)
    {
        this.secondSnakeScore = secondSnakeScore;
    }

    /**
     * Retrieves who is the winner of the game.
     *
     * @return The int number of the winner, if the green snake its equals 1, if the orange snake its equals 2, else 0 (none of them).
     */
    public int getWhoIsWon()
    {
        return whoIsWon;
    }

    /**
     * Sets who is the winner of the game.
     *
     * @param whoIsWon The int number of the winner, if the green snake its equals 1, if the orange snake its equals 2, else 0 (none of them).
     */
    public void setWhoIsWon(int whoIsWon)
    {
        this.whoIsWon = whoIsWon;
    }

    /**
     * Adds a specified value to the score of the orange snake.
     *
     * @param value The value to be added to the orange snake's score.
     */
    public void addSecondSnakeScore(int value)
    {
        secondSnakeScore += value;
    }

    /**
     * Adds a specified value to the score of the green snake.
     *
     * @param value The value to be added to the green snake's score.
     */
    public void addSnakeScore(int value)
    {
        snakeScore += value;
    }
}
