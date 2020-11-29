package main.java.gameobj.staticobj;

import main.java.gameobj.StaticObj;

//!!! do javadoc!!!

public class Wall extends StaticObj {
    /**
     * Constructor for a wall; utilises StaticObj's constructor.
     */
    public Wall(int posX, int posY, int width, int height, PImage sprite) {
        super(posX, posY, width, height, sprite);
    }
}