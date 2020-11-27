package main.java.gameobj;

import processing.core.PImage;

/**
 * Abstract class for static, non-moving objects.
 */
public abstract class StaticObj {
    /**
     * Constructor for a static object; utilises GameObj's constructor.
     */
    public StaticObj(int posX, int posY, int width, int height, PImage sprite) {
        super(posX, posY, sprite);
    }
}