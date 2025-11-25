package entity;

import main.GameLoop;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *  This class is for create a Fruit.
 *  Updating and setting the fruit values as the process if its ate by a snake.
 */
public class Fruit extends Entity implements Serializable {
    private GameLoop gl;
    private Random random = new Random();
    private Snake snakeHead;
    private ArrayList<Snake> snakeBody;
    private Snake secondSnakeHead;
    private ArrayList<Snake> secondSnakeBody;

    /**
     * The default constructor for the Fruit class.
     */
    public Fruit()
    {

    }

    /**
     * Constructor for create the fruit.
     *
     * @param x The X coordinate of the fruit position.
     * @param y The Y coordinate of the fruit position.
     * @param gl The Game loop for the get the data's.
     * @param snakeHead The green snakes head.
     * @param snakeBody The green snakes body.
     * @param secondSnakeHead The orange snakes head.
     * @param secondSnakeBody The orange snakes body.
     */
    public Fruit(int x, int y, GameLoop gl, Snake snakeHead, ArrayList<Snake> snakeBody, Snake secondSnakeHead,  ArrayList<Snake> secondSnakeBody)
    {
        this.x = x;
        this.y = y;
        this.gl = gl;
        this.snakeHead = snakeHead;
        this.snakeBody = snakeBody;
        this.secondSnakeHead = secondSnakeHead;
        this.secondSnakeBody = secondSnakeBody;
    }

    /**
     * Retrieves the X coordinate of the fruit
     *
     * @return The fruit X coordinate.
     */
    public int getX()
    {
        return x;
    }

    /**
     * Retrieves the Y coordinate of the fruit
     *
     * @return The fruit Y coordinate.
     */
    public int getY()
    {
        return y;
    }

    /**
     * Sets the X coordinate for the fruit.
     *
     * @param x The snakes y coordinate.
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Sets the Y coordinate for the fruit.
     *
     * @param y The snakes y coordinate.
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * This method find out which snake ate the fruit.
     * Checking which snake head is equals with fruit x and y coordinate.
     *
     * @return The string value of which snake ate the fruit.
     */
    public String fruitIsEaten() {
        if (snakeHead.getX() == x && snakeHead.getY() == y) {
            return "eatenbySnake";
        }
        if (secondSnakeHead.getX() == x && secondSnakeHead.getY() == y)
        {
            return  "eatenbySecondSnake";
        }
        return "nobodyeatitsofar";
    }

    /**
     * This method is for updating the fruit position on the screen after calculated the right X and Y coordinate.
     */
    public void update()
    {
        if(fruitIsEaten() == "eatenbySnake" || fruitIsEaten() == "eatenbySecondSnake")
        {
            forUpdate(snakeHead, snakeBody);
            forUpdate(secondSnakeHead, secondSnakeBody);
        }
    }

    /**
     * This method is calculating the right position for the next fruit.
     * That means its try to generate an accessible place for both of them.
     */
    public void forUpdate(Snake snakeHead, ArrayList<Snake> snakeBody)
    {
        int genX = (random.nextInt(0,1080)/gl.getData().getTileSize())*gl.getData().getTileSize();
        int genY = (random.nextInt(0,720)/gl.getData().getTileSize())*gl.getData().getTileSize();
        int[][] xy = new int[snakeBody.size()][snakeBody.size()];
        for(int i = 1; i < snakeBody.size(); i++)
        {
            Snake snakePart = snakeBody.get(i);
            xy[i][0] = snakePart.getX();
            xy[i][1] = snakePart.getY();
        }
        for(int i = 1; i < snakeBody.size(); i++)
        {
            if(xy[i][0] == genX && xy[i][1] == genY || snakeHead.getX() == genX && snakeHead.getY() == genY)
            {
                genX = (random.nextInt(0,1080)/gl.getData().getTileSize())* gl.getData().getTileSize();
                genY = (random.nextInt(0,720)/gl.getData().getTileSize())*gl.getData().getTileSize();
            }
        }
        x = genX;
        y = genY;
    }

    /**
     *  This method drawing the fruit on the screen.
     *
     * @param g2 Represents the main render object.
     */
    public void draw(Graphics2D g2)
    {
        g2.setColor(Color.decode("#FF6961"));
        g2.fillRect(x, y, gl.getData().getTileSize(), gl.getData().getTileSize());
    }
}
