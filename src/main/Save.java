package main;

import entity.Fruit;
import entity.Snake;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class is for Saving the game objects, loading the game objects and refresh the game objects with the loaded information.
 */
public class Save implements Serializable
{
    private Fruit fruit;
    private Snake snakeHead;
    private ArrayList<Snake> snakeBody;

    /**
     *  No-args constructor for the class
     */
    public Save()
    {

    }

    /**
     * Saves game state to a serialized file, including information about the snake, fruit, and game form.
     *
     * @param isMultiplayer True if the game is in multiplayer and false if tha game is single-player mode.
     * @param snakeHead     The head of the snake.
     * @param snakeBody     The body of the snake.
     * @param fruit         The fruit.
     * @param form          The save type.
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void savefunction(boolean isMultiplayer, Snake snakeHead, ArrayList<Snake> snakeBody, Fruit fruit, String form) throws IOException {
        String filename = System.getProperty("file.txt");
        File f =  new File(filename, form);

        if (!f.exists()) {
            if (f.createNewFile()) {
                System.out.println("A new file is created: " + f.getAbsolutePath());
            } else {
                System.out.println("Failed to create the file.");
                return;
            }
        }
        try {

            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            if(!isMultiplayer)
            {
                oos.writeObject(fruit);
                oos.writeObject(snakeHead);
                oos.writeObject(snakeBody);
            }

            oos.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    /**
     * Loads game state from a serialized file based on the specified form, updating the game's fruit, snake head, and body.
     *
     * @param form The type of the saved file.
     */
    public void load(String form)
    {
        String filename = System.getProperty("file.txt");
        File f = new File(filename, form);

        if(f.exists())
        {
            try {

                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);

                fruit = (Fruit)ois.readObject();
                snakeHead = (Snake)ois.readObject();
                snakeBody = (ArrayList<Snake>) ois.readObject();

                ois.close();
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Initiates the loading process and updates the current game state with loaded information.
     *
     * @param fruit      The fruit object to be updated.
     * @param snakeHead  The snake head object to be updated.
     * @param snakeBody  The snake body as a list of Snake objects to be updated.
     */
    public void loadingProcess(Fruit fruit, Snake snakeHead, ArrayList<Snake> snakeBody)
    {
        load("Serialized");
        fruit.setX(getFruit().getX());
        fruit.setY(getFruit().getY());
        snakeHead.setX(getSnakeHead().getX());
        snakeHead.setY(getSnakeHead().getY());

        while (snakeBody.size() < getSnakeBody().size())
        {
            snakeBody.add(new Snake(snakeHead.getX(), snakeHead.getY()));
        }
        while (snakeBody.size() > getSnakeBody().size()) {
            snakeBody.remove(snakeBody.size() - 1);
        }
        for (int i = snakeBody.size() - 1; i >= 0; i--)
        {
            Snake snakePart = snakeBody.get(i);
            if (i == 0) {
                snakePart.setX(snakeHead.getX());
                snakePart.setY(snakeHead.getY());
            } else {
                snakePart.setX(getSnakeBody().get(i).getX());
                snakePart.setY(getSnakeBody().get(i).getY());
            }
        }
        snakeHead.setDirection(getSnakeHead().getDirection());
    }

    /**
     * Retrieves the loaded fruit.
     *
     * @return The Fruit object representing the current state of the game's fruit.
     */
    public Fruit getFruit()
    {
        return fruit;
    }

    /**
     * Retrieves the loaded snake head.
     *
     * @return The Snake object representing the current state of the game's snake head.
     */
    public Snake getSnakeHead()
    {
        return snakeHead;
    }

    /**
     * Retrieves the loaded snake body.
     *
     * @return The list of Snake objects representing the current state of the game's snake body.
     */
    public ArrayList<Snake> getSnakeBody()
    {
        return snakeBody;
    }
}


