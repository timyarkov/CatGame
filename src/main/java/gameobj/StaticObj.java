package main.java.gameobj;

import main.java.GameObj;
import processing.core.PImage;

/**
 * Abstract class for static, non-moving objects.
 */
public abstract class StaticObj extends GameObj {
    /**
     * Constructor for a static object; utilises GameObj's constructor.
     */
    public StaticObj(float posX, float posY, float width, float height, PImage sprite) {
        super(posX, posY, width, height, sprite);
    }
}