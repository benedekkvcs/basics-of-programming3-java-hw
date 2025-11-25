package menu;

import main.GameLoop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 * This class stores the Java Swing objects that appears on the screen in the menu.
 */
public class Menu implements Serializable
{
    private JButton play = new JButton();
    private JButton settings = new JButton();
    private JButton exitGame = new JButton();
    private JButton backButton = new JButton();
    private JButton sPlayerButton = new JButton();
    private JButton mPlayerButton = new JButton();
    private JSlider slider = new JSlider(-80, 3);
    private JLabel volumeText = new JLabel("Music volume");
    private JLabel scoreText = new JLabel();
    private JLabel secondScoreText = new JLabel();
    private JLabel winningText = new JLabel();

    /**
     * No-args constructor for the Menu class.
     */
    public Menu()
    {

    }

    /**
     * Retrieves the play game button.
     *
     * @return The JButton of the play.
     */
    public JButton getPlay()
    {
        return play;
    }

    /**
     * Retrieves the settings button.
     *
     * @return The JButton of the settings.
     */
    public JButton getSettings()
    {
        return settings;
    }

    /**
     * Retrieves the exit button.
     *
     * @return The JButton of the exit.
     */
    public JButton getExitGame()
    {
        return exitGame;
    }

    /**
     * Retrieves the back button.
     *
     * @return The JButton of the back.
     */
    public JButton getBackButton()
    {
        return backButton;
    }

    /**
     * Retrieves the single-player button.
     *
     * @return The JButton of the single-player.
     */
    public JButton getsPlayerButton()
    {
        return sPlayerButton;
    }

    /**
     * Retrieves the multiplayer button.
     *
     * @return The JButton of the multiplayer.
     */
    public JButton getmPlayerButton()
    {
        return mPlayerButton;
    }

    /**
     * Retrieves the music volume slider.
     *
     * @return The JSlider of the music volume.
     */
    public JSlider getSlider()
    {
        return slider;
    }

    /**
     * Retrieves the volume text area.
     *
     * @return The JLabel of the volume text.
     */
    public JLabel getVolumeText()
    {
        return volumeText;
    }

    /**
     * Retrieves the green snakes score text area.
     *
     * @return The JLabel of the green snakes score text.
     */
    public JLabel getScoreText()
    {
        return scoreText;
    }

    /**
     * Retrieves the orange snakes score text area.
     *
     * @return The JLabel of the orange snakes score text.
     */
    public JLabel getSecondScoreText()
    {
        return secondScoreText;
    }

    /**
     * Retrieves the winning states text area.
     *
     * @return The JLabel of the winning text.
     */
    public JLabel getWinningText()
    {
        return winningText;
    }
}
