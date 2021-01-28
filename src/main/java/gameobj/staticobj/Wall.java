package main.java.gameobj.staticobj;

import main.java.gameobj.StaticObj;
import processing.core.PImage;

//!!! do javadoc!!!

public class Wall extends StaticObj {
    /**
     * Constructor for a wall; utilises StaticObj's constructor.
     */
    public Wall(double posX, double posY, int width, int height, PImage sprite) {
        super(posX, posY, width, height, sprite);
    }
}