package main;

import entity.Entity;
import entity.Fruit;
import entity.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * The GameLoop class manages the main game loop responsible for continuous game
 * updates and rendering.
 * It also handles the initialization of the game window and related components.
 */
public class GameLoop extends JPanel implements Serializable {
    private Data data = new Data();
    private Snake snakeHead;
    private ArrayList<Snake> snakeBody;
    private Fruit fruit;

    private Snake secondSnakeHead;
    private ArrayList<Snake> secondSnakeBody;

    private JFrame window;
    private KeyHandler keyHandler = new KeyHandler();
    private Control control;

    public boolean running = true;
    private JLabel szovegLabel;
    private Timer timer;
    private int elapsedSeconds;
    private Save save = new Save();

    /**
     * Constructs a GameLoop object with the specified JFrame and Control instances.
     * Initializes the dimensions, background color, and focus settings for the game
     * loop.
     *
     * @param wind    The JFrame used for displaying the game loop.
     * @param control The Control instance managing the game's control logic.
     */
    public GameLoop(JFrame wind, Control control) {
        window = wind;
        this.control = control;
        this.setPreferredSize(new Dimension(data.getScreenWidth(), data.getScreenHeight()));
        this.setBackground(Color.decode("#1d1c1a"));
        this.setDoubleBuffered(true);
        window.addKeyListener(keyHandler);
        window.setFocusable(true);
        szovegLabel = new JLabel();
        add(szovegLabel);

        savedTextDisplay();
    }

    /**
     * This method displays the text for 1 seconds, which is appears on the screen,
     * when the user is saving.
     */
    public void savedTextDisplay() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedSeconds++;
                if (elapsedSeconds >= 1) {
                    stopAndResetTimer();
                    szovegLabel.setVisible(false);
                }
            }
        });
    }

    /**
     * Set the game running and called the function for setting the entities.
     */
    public void initializeGame() {
        setEntities();
        running = true;
    }

    /**
     * This method is an initializing function, create the snakes and fruit, before
     * the game start.
     */
    public void setEntities() {
        Random random = new Random();

        int snakeX = (random.nextInt(0, 1080) / data.getTileSize()) * data.getTileSize();
        int snakeY = (random.nextInt(0, 720) / data.getTileSize()) * data.getTileSize();

        int fruitX = (random.nextInt(0, 1080) / data.getTileSize()) * data.getTileSize();
        int fruitY = (random.nextInt(0, 720) / data.getTileSize()) * data.getTileSize();

        snakeHead = new Snake(snakeX, snakeY, this, keyHandler);
        snakeBody = new ArrayList<Snake>();
        snakeBody.add(new Snake(snakeHead.getX(), snakeHead.getY()));

        int secondSnakeX = (random.nextInt(0, 1080) / data.getTileSize()) * data.getTileSize();
        int secondSnakeY = (random.nextInt(0, 720) / data.getTileSize()) * data.getTileSize();

        secondSnakeHead = new Snake(secondSnakeX, secondSnakeY, this, keyHandler);

        secondSnakeBody = new ArrayList<Snake>();
        secondSnakeBody.add(new Snake(secondSnakeHead.getX(), secondSnakeHead.getY()));

        fruit = new Fruit(fruitX, fruitY, this, snakeHead, snakeBody, secondSnakeHead, secondSnakeBody);

        getData().setSnakeScore(0);
        getData().setSecondSnakeScore(0);
    }

    /**
     * Retrieves the Key handler of the program.
     *
     * @return The Data object representing game data.
     */
    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    /**
     * Retrieves the Data object containing game-related information.
     *
     * @return The Data object representing game data.
     */
    public Data getData() {
        return data;
    }

    /**
     * The main game loop responsible for updating and rendering the game
     * continuously.
     * Throws exceptions for I/O errors, font format errors, and thread
     * interruptions.
     *
     * @throws IOException          If an I/O error occurs during game loop
     *                              execution.
     * @throws FontFormatException  If a font format error occurs during game loop
     *                              execution.
     * @throws InterruptedException If the thread is interrupted during the sleep
     *                              period.
     */
    public void loop() throws IOException, FontFormatException, InterruptedException {
        double drawInterval = 1000000000 / data.getFPS();
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (running) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                // UPDATE
                update();

                // DRAW
                repaint();

                delta--;
            }
            try {
                Thread.sleep(10000 / data.getFPS()); // FPS alapján számolva
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (keyHandler.getEscpressed()) {
                running = false;
            }
            if (!running) {
                control.scoreScreen();
            }
            if (!control.getIsMultiplayer()) {
                if (keyHandler.getPause()) {
                    save.savefunction(control.getIsMultiplayer(), snakeHead, snakeBody, fruit, "Serialized");
                    savedText("Saved!");
                    timer.start();
                }
                if (keyHandler.getLoad()) {
                    save.loadingProcess(fruit, snakeHead, snakeBody);
                    getData().setSnakeScore(snakeBody.size() - 1);
                }
            }
        }
    }

    /**
     * This method set visible the text which is the text for when the user is
     * saving the game.
     * 
     * @param text The text what is appears on the screen when this function called.
     * @throws IOException         If an I/O error occurs while initializing the
     *                             game.
     * @throws FontFormatException If a font format error occurs while initializing
     *                             the game.
     */
    public void savedText(String text) throws IOException, FontFormatException {
        szovegLabel.setText(text);
        szovegLabel.setFont(control.menuFont());
        szovegLabel.setForeground(Color.decode("#6CBB3C"));
        szovegLabel.setVisible(true);
    }

    /**
     * Stop and reset method for the timer.
     */
    private void stopAndResetTimer() {
        timer.stop();
        elapsedSeconds = 0;
        timer.start();
    }

    /**
     * This method is for updating the snakes body parts and make sure the body
     * follows the head.
     * Also, it checks collision and colliding snakes.
     * Calls the fruit and the snake update methods.
     */
    public void update() {
        snakeHead.update();

        secondSnakeHead.updateforSecondSnake(control.getIsMultiplayer());

        if (fruit.fruitIsEaten() == "eatenbySnake") {
            snakeBody.add(new Snake(snakeHead.getX(), snakeHead.getY()));
            getData().addSnakeScore(1);
        }
        if (fruit.fruitIsEaten() == "eatenbySecondSnake") {
            secondSnakeBody.add(new Snake(secondSnakeHead.getX(), secondSnakeHead.getY()));
            getData().addSecondSnakeScore(1);
        }

        for (int i = snakeBody.size() - 1; i >= 0; i--) {
            Snake snakePart = snakeBody.get(i);
            if (i == 0) {
                snakePart.setX(snakeHead.getX());
                snakePart.setY(snakeHead.getY());
            } else {
                Snake prevSnakePart = snakeBody.get(i - 1);
                snakePart.setX(prevSnakePart.getX());
                snakePart.setY(prevSnakePart.getY());
            }
        }

        for (int i = secondSnakeBody.size() - 1; i >= 0; i--) {
            Snake snakePart = secondSnakeBody.get(i);
            if (i == 0) {
                snakePart.setX(secondSnakeHead.getX());
                snakePart.setY(secondSnakeHead.getY());
            } else {
                Snake prevSnakePart = secondSnakeBody.get(i - 1);
                snakePart.setX(prevSnakePart.getX());
                snakePart.setY(prevSnakePart.getY());
            }
        }

        checkCollision(snakeHead, snakeBody);
        checkCollision(secondSnakeHead, secondSnakeBody);
        if (control.getIsMultiplayer()) {
            colliding(snakeHead, snakeBody, secondSnakeHead, secondSnakeBody);
        }

        fruit.update();
    }

    /**
     * This method is checking if the snakes went into its own body or went out of
     * the screen.
     * If any collision made the game stops, then sets the right winner.
     *
     * @param snakeHead The snakes head.
     * @param snakeBody The snakes body.
     */
    public void checkCollision(Snake snakeHead, ArrayList<Snake> snakeBody) {
        for (int i = 1; i < snakeBody.size(); i++) {
            Snake snakePart = snakeBody.get(i);
            if (snakePart.getX() == snakeHead.getX() && snakePart.getY() == snakeHead.getY()) {
                running = false;
                if (Objects.equals(snakeHead.getColor(), "#6CBB3C")) {
                    getData().setWhoIsWon(2);
                } else {
                    getData().setWhoIsWon(1);
                }
            }
        }
        if (snakeHead.getX() < 0 || snakeHead.getY() < 0 || snakeHead.getX() > data.getScreenWidth()
                || snakeHead.getY() > data.getScreenHeight()) {
            running = false;
            if (Objects.equals(snakeHead.getColor(), "#6CBB3C")) {
                getData().setWhoIsWon(2);
            } else {
                getData().setWhoIsWon(1);
            }
        }
    }

    /**
     * This method checks if the two snakes in the game are colliding,
     * then decide which snakes head goes to the other snakes body.
     *
     * @param snakeHead       The green snakes head.
     * @param snakeBody       The green snakes body.
     * @param secondSnakeHead The orange snakes head.
     * @param secondSnakeBody The orange snakes body.
     */
    public void colliding(Snake snakeHead, ArrayList<Snake> snakeBody, Snake secondSnakeHead,
            ArrayList<Snake> secondSnakeBody) {
        for (int i = 1; i < snakeBody.size(); i++) {
            Snake snakePart = snakeBody.get(i);
            if (snakePart.getX() == secondSnakeHead.getX() && snakePart.getY() == secondSnakeHead.getY()) {
                running = false;
                getData().setWhoIsWon(1);
            }
        }

        for (int j = 1; j < secondSnakeBody.size(); j++) {
            Snake snakePart = secondSnakeBody.get(j);
            if (snakePart.getX() == snakeHead.getX() && snakePart.getY() == snakeHead.getY()) {
                running = false;
                getData().setWhoIsWon(2);
            }
        }
    }

    /**
     * The render method of all the entity's.
     * Sets their colors and their sizes.
     *
     * @param g the <code>Graphics</code> object to protect.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        snakeHead.draw(g2, "#6CBB3C");
        g2.setColor(Color.decode("#6CBB3C"));

        for (int i = 1; i < snakeBody.size(); i++) {
            Snake snakePart = snakeBody.get(i);
            g2.fillRect(snakePart.getX(), snakePart.getY(), data.getTileSize(), data.getTileSize());
        }

        if (control.getIsMultiplayer()) {
            secondSnakeHead.draw(g2, "#FF964F");
            g2.setColor(Color.decode("#FF964F"));

            for (int i = 1; i < secondSnakeBody.size(); i++) {
                Snake snakePart = secondSnakeBody.get(i);
                g2.fillRect(snakePart.getX(), snakePart.getY(), data.getTileSize(), data.getTileSize());
            }
        }
        fruit.draw(g2);
    }
}
