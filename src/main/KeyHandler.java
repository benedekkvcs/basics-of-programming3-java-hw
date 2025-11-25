package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

/**
 * This class is for handle the pressed, typed and released keys.
 */

public class KeyHandler implements KeyListener, Serializable {
    private boolean snakeUp, snakeDown, snakeLeft, snakeRight, escPressed, pause, load;
    private boolean secondSnakeUp, secondSnakeDown, secondSnakeLeft, secondSnakeRight;
    private boolean canType = true;

    /**
     * Override method to the typed keys based on the happened event.
     *
     * @param e the event to be processed.
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Override method to the pressed keys based on the happened event.
     * Sets the boolean value for the snakes correct direction.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W && canType) {
            snakeUp = true;
        }
        if (code == KeyEvent.VK_S && canType) {
            snakeDown = true;
        }
        if (code == KeyEvent.VK_A && canType) {
            snakeLeft = true;
        }
        if (code == KeyEvent.VK_D && canType) {
            snakeRight = true;
        }
        if(code == KeyEvent.VK_ESCAPE && canType)
        {
            escPressed = true;
        }


        if(code == KeyEvent.VK_UP && canType)
        {
            secondSnakeUp = true;
        }
        if(code == KeyEvent.VK_DOWN && canType)
        {
            secondSnakeDown = true;
        }
        if(code == KeyEvent.VK_LEFT && canType)
        {
            secondSnakeLeft = true;
        }
        if(code == KeyEvent.VK_RIGHT && canType)
        {
            secondSnakeRight = true;
        }

        if(code == KeyEvent.VK_SPACE && canType)
        {
            pause = true;
        }

        if(code == KeyEvent.VK_O && canType)
        {
            load = true;
        }
    }

    /**
     * Override method to the released keys based on the happened event.
     * Sets the boolean value for the snakes correct direction.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
             snakeUp = false;
        }
        if (code == KeyEvent.VK_S) {
            snakeDown = false;
        }
        if (code == KeyEvent.VK_A) {
            snakeLeft = false;
        }
        if (code == KeyEvent.VK_D) {
            snakeRight = false;
        }
        if(code == KeyEvent.VK_ESCAPE)
        {
            escPressed = false;
        }


        if(code == KeyEvent.VK_UP)
        {
            secondSnakeUp = false;
        }
        if(code == KeyEvent.VK_DOWN)
        {
            secondSnakeDown = false;
        }
        if(code == KeyEvent.VK_LEFT)
        {
            secondSnakeLeft = false;
        }
        if(code == KeyEvent.VK_RIGHT)
        {
            secondSnakeRight = false;
        }

        if(code == KeyEvent.VK_SPACE)
        {
            pause = false;
        }

        if(code == KeyEvent.VK_O)
        {
            load = false;
        }
    }

    /**
     * Retrieves the current pause status of the game.
     *
     * @return True if the game is paused, false otherwise.
     */
    public boolean getPause()
    {
        return pause;
    }

    /**
     * Retrieves the status of the Esc key.
     *
     * @return True if the Esc key is pressed, else its false.
     */
    public boolean getEscpressed()
    {
        return escPressed;
    }

    /**
     * Retrieves the status of the load operation.
     *
     * @return True if a load operation is requested, else its false.
     */
    public boolean getLoad()
    {
        return load;
    }

    /**
     * Retrieves the boolean value of the green snakes up movement.
     *
     * @return True if the snake is going up, else its false.
     */
    public boolean getSnakeUp()
    {
        return snakeUp;
    }

    /**
     * Retrieves the boolean value of the green snakes down movement.
     *
     * @return True if the snake is going down, else its false.
     */
    public boolean getSnakeDown()
    {
        return snakeDown;
    }

    /**
     * Retrieves the boolean value of the green snakes right movement.
     *
     * @return True if the snake is going right, else its false.
     */
    public boolean getSnakeRight()
    {
        return snakeRight;
    }

    /**
     * Retrieves the boolean value of the green snakes left movement.
     *
     * @return True if the snake is going left, else its false.
     */
    public boolean getSnakeLeft()
    {
        return snakeLeft;
    }

    /**
     * Retrieves the boolean value of the orange snakes up movement.
     *
     * @return True if the snake is going up, else its false.
     */
    public boolean getSecondSnakeUp()
    {
        return secondSnakeUp;
    }

    /**
     * Retrieves the boolean value of the orange snakes down movement.
     *
     * @return True if the snake is going down, else its false.
     */
    public boolean getSecondSnakeDown()
    {
        return secondSnakeDown;
    }

    /**
     * Retrieves the boolean value of the orange snakes right movement.
     *
     * @return True if the snake is going right, else its false.
     */
    public boolean getSecondSnakeRight()
    {
        return secondSnakeRight;
    }

    /**
     * Retrieves the boolean value of the orange snakes left movement.
     *
     * @return True if the snake is going left, else its false.
     */
    public boolean getSecondSnakeLeft()
    {
        return secondSnakeLeft;
    }

    /**
     * Sets true as the user can type, false if the user can't.
     *
     * @param value The boolean value if the user could type or not.
     */
    public void setCanType(boolean value)
    {
        canType = value;
    }

    /**
     * Retrieves the boolean value about the user can type or not.
     *
     * @return True if the user can type, else its false.
     */
    public boolean getCanType()
    {
        return canType;
    }
}
