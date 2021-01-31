package main.java.gameobj.staticobj;

import main.java.gameobj.StaticObj;
import processing.core.PApplet;
import processing.core.PImage;

//!!! do javadoc!!!

public class Wall extends StaticObj {
    /**
     * Constructor for a wall; utilises StaticObj's constructor.
     */
    public Wall(float posX, float posY, float width, float height, PImage sprite) {
        super(posX, posY, width, height, sprite);
    }

    @Override // Likely will be final art, unless wall texture?
    public void draw(PApplet app) {
        app.fill(100, 100, 100);
        app.rect(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }
}