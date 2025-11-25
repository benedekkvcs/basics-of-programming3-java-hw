package main;

import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

public class KeyHandlerTest
{
    private KeyEvent createKeyEvent(int keyCode)
    {
        return new KeyEvent(new Component() {}, 0, 0, 0, keyCode, ' ');
    }

    @Test
    public void testKeyHandlerSnakeUp()
    {
        KeyHandler keyHandler = new KeyHandler();
        keyHandler.keyPressed(createKeyEvent(KeyEvent.VK_W));
        assertTrue(keyHandler.getSnakeUp());
    }

    @Test
    public void testKeyHandlerSnakeDown()
    {
        KeyHandler keyHandler = new KeyHandler();
        keyHandler.keyPressed(createKeyEvent(KeyEvent.VK_S));
        assertTrue(keyHandler.getSnakeDown());
    }

    @Test
    public void testKeyHandlerSnakeLeft()
    {
        KeyHandler keyHandler = new KeyHandler();
        keyHandler.keyPressed(createKeyEvent(KeyEvent.VK_A));
        assertTrue(keyHandler.getSnakeLeft());
    }

    @Test
    public void testKeyHandlerSnakeRight()
    {
        KeyHandler keyHandler = new KeyHandler();
        keyHandler.keyPressed(createKeyEvent(KeyEvent.VK_D));
        assertTrue(keyHandler.getSnakeRight());
    }

    @Test
    public void testKeyHandlerCanType()
    {
        KeyHandler keyHandler = new KeyHandler();
        keyHandler.setCanType(false);
        assertFalse(keyHandler.getCanType());
    }
}
