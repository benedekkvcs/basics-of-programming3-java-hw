package main;

import menu.Menu;
import menu.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 *  This class is for display the screen and control all the components of th program.
 */
public class Control extends JPanel implements Runnable, Serializable {
    private GameLoop gameLoop;
    private Menu menu = new Menu();
    private Sound sound;
    private JFrame window = new JFrame();
    private JPanel gui = new JPanel(new GridBagLayout());
    private boolean isMultiplayer = false;

    Action game = new AbstractAction("Play Game")
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                gameMode();
                gameLoop.getData().setGoToGameMode(true);
            } catch (IOException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        }
    };

    Action singleplayer = new AbstractAction("Singleplayer")
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            isMultiplayer = false;
            playGame();
        }
    };

    Action multiplayer = new AbstractAction("Multiplayer")
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            isMultiplayer = true;
            playGame();
        }
    };


    Action exit = new AbstractAction("Exit")
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };

    Action setting = new AbstractAction("Settings") {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                settingsMenu();
                gameLoop.getData().setGoToSettings(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        }
    };

    Action back = new AbstractAction("Back") {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                mainMenu();
            } catch (IOException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        }
    };

    /**
     * Load the font, which will be used in the menu.
     * Retrieves a sized font.
     *
     * @return The right sized font.
     * @throws IOException If an I/O error occurs while initializing the game.
     * @throws FontFormatException If a font format error occurs while initializing the game.
     */
    public Font menuFont() throws IOException, FontFormatException {
        String fname = "/QuartzoBold-W9lv.ttf";
        InputStream is = Control.class.getResourceAsStream(fname);
        Font font = Font.createFont(Font.TRUETYPE_FONT, is);
        Font sizedFont = font.deriveFont(50.00F);
        return sizedFont;
    }

    /**
     * The title screen for the score, sets the texts and font.
     * Appears the score and the winner of the game.
     *
     * @throws IOException If an I/O error occurs while initializing the game.
     * @throws FontFormatException If a font format error occurs while initializing the game.
     */
    public void scoreScreen() throws IOException, FontFormatException {
        gameLoop.getKeyHandler().setCanType(false);
        setSize(600, 600);

        Font font = new Font("Segoe UI Black", Font.BOLD, 50);

        menu.getScoreText().setFont(font);
        menu.getScoreText().setBounds(100, 100, 440, 100);
        menu.getScoreText().setForeground(Color.decode("#6CBB3C"));
        menu.getScoreText().setText("Green's score: " + gameLoop.getData().getSnakeScore());

        if(isMultiplayer)
        {
            menu.getSecondScoreText().setFont(font);
            menu.getSecondScoreText().setBounds(90, 200, 480, 100);
            menu.getSecondScoreText().setForeground(Color.decode("#FF964F"));
            menu.getSecondScoreText().setText("Orange's score: " + gameLoop.getData().getSecondSnakeScore());

            menu.getWinningText().setFont(menuFont());
            menu.getWinningText().setBounds(150, 0, 400, 100);
            if(gameLoop.getData().getWhoIsWon() == 1)
            {
                menu.getWinningText().setForeground(Color.decode("#6CBB3C"));
                menu.getWinningText().setText("Green won");
            }
            if(gameLoop.getData().getWhoIsWon() == 2)
            {
                menu.getWinningText().setForeground(Color.decode("#FF964F"));
                menu.getWinningText().setText("Orange won");
            }
        }

        gui.setBackground(Color.decode("#1d1c1a"));
        gui.setLayout(null);

        gui.add(menu.getBackButton());
        gui.add(menu.getScoreText());
        gui.add(menu.getSecondScoreText());
        gui.add(menu.getWinningText());

        gameLoop.initializeGame();
    }


    /**
     * This method sets the main menu screen, adds the right buttons and font.
     * Repaint and revalidate the screen if its need to be.
     *
     * @throws IOException If an I/O error occurs while initializing the game.
     * @throws FontFormatException If a font format error occurs while initializing the game.
     */
    public void mainMenu() throws IOException, FontFormatException
    {
        if(gameLoop.getData().isGoToSettings())
        {
            gui.remove(menu.getBackButton());
            gui.remove(menu.getSlider());
            gui.remove(menu.getVolumeText());
            gui.revalidate();
            gui.repaint();
        }
        if(gameLoop.getData().isGoToGameMode())
        {
            gui.remove(menu.getBackButton());
            gui.remove(menu.getsPlayerButton());
            gui.remove(menu.getmPlayerButton());
            gui.remove(menu.getScoreText());
            gui.remove(menu.getSecondScoreText());
            gui.remove(menu.getWinningText());
            gui.revalidate();
            gui.repaint();
        }

        gameLoop.getKeyHandler().setCanType(false);

        setSize(600, 600);

        Font font = menuFont();

        gui.setBackground(Color.decode("#1d1c1a"));
        gui.setLayout(null);

        menu.getPlay().setAction(game);
        setMenuButtons(menu.getPlay(), font,1);

        menu.getSettings().setAction(setting);
        setMenuButtons(menu.getSettings(), font, 2);

        menu.getExitGame().setAction(exit);
        setMenuButtons(menu.getExitGame(), font, 3);

        gui.add(menu.getPlay());
        gui.add(menu.getSettings());
        gui.add(menu.getExitGame());
    }

    /**
     * This method sets the settings menu screen, adds the right buttons and font, also the slider for the music volume.
     *
     * @throws IOException If an I/O error occurs while initializing the game.
     * @throws FontFormatException If a font format error occurs while initializing the game.
     */
    public void settingsMenu() throws IOException, FontFormatException {
        gui.remove(menu.getPlay());
        gui.remove(menu.getSettings());
        gui.remove(menu.getExitGame());
        gui.revalidate();
        gui.repaint();

        Font font = menuFont();

        menu.getBackButton().setAction(back);
        setMenuButtons(menu.getBackButton(), font, 4);

        menu.getSlider().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Float asd = (float) menu.getSlider().getValue();
                sound.setVolume(asd);
            }
        });


        menu.getSlider().setVisible(true);
        menu.getSlider().setBounds(150, 100, 300, 100);
        menu.getSlider().setBackground(Color.decode("#1d1c1a"));
        menu.getSlider().setOpaque(true);
        menu.getSlider().setPaintLabels(true);

        menu.getVolumeText().setFont(menuFont());
        menu.getVolumeText().setBounds(150, 50, 400, 50);
        menu.getVolumeText().setForeground(Color.decode("#6CBB3C"));


        gui.add(menu.getVolumeText());
        gui.add(menu.getSlider());
        gui.add(menu.getBackButton());
    }

    /**
     * This method sets the game mode selection screen, adds the right buttons and font.
     *
     * @throws IOException If an I/O error occurs while initializing the game.
     * @throws FontFormatException If a font format error occurs while initializing the game.
     */
    public void gameMode() throws IOException, FontFormatException {
        gui.remove(menu.getPlay());
        gui.remove(menu.getSettings());
        gui.remove(menu.getExitGame());
        gui.revalidate();
        gui.repaint();

        Font font = menuFont();

        menu.getsPlayerButton().setAction(singleplayer);
        setMenuButtons(menu.getsPlayerButton(), font, 1);

        menu.getmPlayerButton().setAction(multiplayer);
        setMenuButtons(menu.getmPlayerButton(), font, 2);

        menu.getBackButton().setAction(back);
        setMenuButtons(menu.getBackButton(), font, 4);
        gui.add(menu.getBackButton());

        gui.add(menu.getsPlayerButton());
        gui.add(menu.getmPlayerButton());
    }

    /**
     * Sets the parameters of the menu buttons.
     *
     * @param jButton The button which will be modified.
     * @param font The font for the button text.
     * @param ord The order of the buttons.
     */
    public void setMenuButtons(JButton jButton, Font font, int ord)
    {
        jButton.setBounds(150, ord * 100, 320, 100);
        jButton.setBackground(Color.decode("#FAF8F6"));
        jButton.setForeground(Color.decode("#6CBB3C"));
        jButton.setFont(font);
        jButton.setOpaque(false);
        jButton.setFocusPainted(false);
        jButton.setBorderPainted(false);
        jButton.setContentAreaFilled(false);
    }

    /**
     * This method initialize the window and the java swing components before the game starts.
     */
    public void playGame()
    {
        gui.removeAll();
        gui.revalidate();
        gui.repaint();
        gui.setLayout(new GridBagLayout());
        gui.add(gameLoop);
        setSize(1080, 720);
        gameLoop.setBackground(Color.decode("#1d1c1a"));
        gameLoop.getKeyHandler().setCanType(true);
    }

    /**
     * This method for setting the right window size.
     *
     * @param w the new width of this component in pixels.
     * @param h the new height of this component in pixels.
     */
    public final void setSize(int w, int h)
    {
        gui.setPreferredSize(new Dimension(w, h));
        gui.setBounds(0,0, w, h);
        Container c = gui.getTopLevelAncestor();
        if (c instanceof JFrame) {
            JFrame f = (JFrame) c;
            f.pack();
        }
    }

    /**
     * Retrieves the multiplayer mode.
     *
     * @return The boolean value of multiplayer mode.
     */
    public boolean getIsMultiplayer()
    {
        return isMultiplayer;
    }

    /**
     * Retrieves the program window.
     *
     * @return The JPanel of the window.
     */
    public JPanel getwindow()
    {
        return gui;
    }

    /**
     * One-args constructor for the Control class.
     *
     * @param sound The music what goes in the background.
     * @throws IOException If an I/O error occurs while initializing the game.
     * @throws FontFormatException If a font format error occurs while initializing the game.
     */
    public Control(Sound sound) throws IOException, FontFormatException
    {
        this.sound = sound;
        gameLoop = new GameLoop(window, this);
        gameLoop.initializeGame();
        mainMenu();
        window.setTitle("SNAKE");
        window.setResizable(false);
    }

    /**
     * Runs the game control, displaying the game window and starting the game loop in a separate thread.
     * The game loop is responsible for continuously updating and rendering the game.
     */
    @Override
    public void run()
    {
        window.add(this.getwindow());
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLocationByPlatform(true);

        window.pack();
        window.setVisible(true);

        Thread gameLoopThread = new Thread(() -> {
            try {
                gameLoop.loop();
            } catch (IOException | InterruptedException | FontFormatException e) {
                throw new RuntimeException(e);
            }
        });
        gameLoopThread.start();
    }
}
