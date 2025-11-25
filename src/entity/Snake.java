package entity;

import main.Data;
import main.GameLoop;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

/**
 * This class is for create a Snake head or body with the right constructor.
 * Updating and setting the snake values as the process of moving it within the
 * game.
 */
public class Snake extends Entity implements Serializable {
    private GameLoop gl;
    private KeyHandler keyH;
    private String color;

    /**
     * Four-args constructor for the snake heads.
     *
     * @param x    The X coordinate of the snakes position.
     * @param y    The Y coordinate of the snakes position.
     * @param gl   The Game loop for the get the data's.
     * @param keyH The Key handler which control the key events.
     */
    public Snake(int x, int y, GameLoop gl, KeyHandler keyH) {
        this.x = x;
        this.y = y;
        this.gl = gl;
        this.keyH = keyH;
        setDefaultValues();
    }

    /**
     * Two-args constructor for the snake body parts
     *
     * @param x The X coordinate of the snakes position.
     * @param y The Y coordinate of the snakes position.
     */
    public Snake(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieves the X coordinate of the snake
     *
     * @return The snakes X coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Retrieves the Y coordinate of the snake
     *
     * @return The snakes Y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x coordinate for the snake.
     *
     * @param x The snakes x coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y coordinate for the snake.
     *
     * @param y The snakes y coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Retrieves the snakes direction.
     *
     * @return The direction of the snake.
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets the direction's way.
     *
     * @param direction The direction where the snake will go.
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Retrieves the snakes color.
     *
     * @return The color of the snake.
     */
    public String getColor() {
        return color;
    }

    /**
     * Initializing the speed and the direction values, before the game starts.
     */
    public void setDefaultValues() {
        speed = 20;
        direction = "";
    }

    /**
     * This method is for updating the green snake movement based on the pressed
     * keys and switch the direction according to that information.
     */
    public void update() {
        if (keyH.getSnakeUp() == true || keyH.getSnakeDown() == true || keyH.getSnakeLeft() == true
                || keyH.getSnakeRight() == true) {

            if (keyH.getSnakeUp() == true && direction != "down") {
                direction = "up";
            } else if (keyH.getSnakeDown() == true && direction != "up") {
                direction = "down";
            } else if (keyH.getSnakeRight() == true && direction != "left") {
                direction = "right";
            } else if (keyH.getSnakeLeft() == true && direction != "right") {
                direction = "left";
            }
        }
        switchDirection();
    }

    /**
     * This method is for updating the orange snake movement based on the pressed
     * keys and switch the direction according to that information.
     */
    public void updateforSecondSnake(boolean canMove) {
        if ((keyH.getSecondSnakeUp() == true || keyH.getSecondSnakeDown() == true || keyH.getSecondSnakeLeft() == true
                || keyH.getSecondSnakeRight() == true) && canMove) {

            if (keyH.getSecondSnakeUp() == true && direction != "down") {
                direction = "up";
            } else if (keyH.getSecondSnakeDown() == true && direction != "up") {
                direction = "down";
            } else if (keyH.getSecondSnakeRight() == true && direction != "left") {
                direction = "right";
            } else if (keyH.getSecondSnakeLeft() == true && direction != "right") {
                direction = "left";
            }
        }
        switchDirection();
    }

    /**
     * This method chosen the turn based on the right direction.
     */
    public void switchDirection() {
        switch (direction) {
            case "up":
                y -= speed;
                break;
            case "down":
                y += speed;
                break;
            case "right":
                x += speed;
                break;
            case "left":
                x -= speed;
                break;
        }
    }

    /**
     * This method drawing the snake on the screen.
     *
     * @param g2    Represents the main render object.
     * @param color The color of the snake.
     */
    public void draw(Graphics2D g2, String color) {
        g2.setColor(Color.decode(color));
        g2.fillRect(x, y, gl.getData().getTileSize(), gl.getData().getTileSize());
        this.color = color;
    }
}
