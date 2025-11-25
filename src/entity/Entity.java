package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * The Entity class represents a serializable object with basic properties for game entities.
 * Entities have position (x, y), speed, and direction attributes.
 */
public class Entity implements Serializable {
    protected int x;
    protected int y;
    protected int speed;
    protected String direction;
}
