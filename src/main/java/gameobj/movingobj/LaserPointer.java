package main.java.gameobj.movingobj;

import main.java.gameobj.MovingObj;

public class LaserPointer extends MovingObj {
    public LaserPointer(double posX, double posY, int w, int h, double speedMax, PImage spr) {
        super(posX, posY, w, h, speedMax, spr);
    }

    // Getter Methods
    public boolean isAlive() {
        return this.alive;
    }

    // Other Methods

    @Override // No art, so just draw squares for now
    public boolean draw(PApplet app) {
        app.fill(0, 255, 0);
        app.rect(this.getPosX(), this.getPostY(), this.getWidth(), this.getHeight());
    }
}