package entity;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SnakeAndFruitTest {

    @Test
    public void testSnakeTwoArgsCons()
    {
        Snake snake = new Snake(100, 200);
        assertEquals(100, snake.getX());
        assertEquals(200, snake.getY());
    }

    @Test
    public void testSnakeFourArgsCons()
    {
        Snake snake = new Snake(20, 40, null, null);
        assertEquals(40, snake.getY());
        assertEquals(20, snake.getX());
    }

    @Test
    public void testFruitCons()
    {
        Fruit fruit = new Fruit(10, 20, null, null, null, null, null);
        assertEquals(10, fruit.getX());
    }

    @Test
    public void testFruitDefaultCons()
    {
        Fruit fruit = new Fruit();
        assertNotEquals(20, fruit.getY());
    }

    @Test
    public void testEntityDirection()
    {
        Snake snake = new Snake(10, 10);
        snake.setDirection("up");
        assertEquals("up", snake.getDirection());
    }
}