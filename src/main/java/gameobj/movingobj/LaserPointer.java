package main.java.gameobj.movingobj;

import main.java.gameobj.MovingObj;
import processing.core.PApplet;
import processing.core.PImage;

public class LaserPointer extends MovingObj {
    public LaserPointer(float posX, float posY, float w, float h, float speedMax, PImage spr) {
        super(posX, posY, w, h, speedMax, spr);
    }

    // Other Methods
    @Override // No art, so just draw green squares for now
    public void draw(PApplet app) {
        app.fill(0, 255, 0);
        app.rect(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }
}