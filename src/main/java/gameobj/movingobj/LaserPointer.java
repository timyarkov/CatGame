package main.java.gameobj.movingobj;

import main.java.gameobj.MovingObj;
import processing.core.PApplet;
import processing.core.PImage;

public class LaserPointer extends MovingObj {
    public LaserPointer(double posX, double posY, int w, int h, double speedMax, PImage spr) {
        super(posX, posY, w, h, speedMax, spr);
    }

    // Other Methods
    @Override // No art, so just draw squares for now
    public void draw(PApplet app) {
        app.fill(0, 255, 0);
        app.rect((float) this.getPosX(), (float) this.getPosY(), 
                    this.getWidth(), this.getHeight());
    }
}