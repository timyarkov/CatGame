package main.java.gameobj.movingobj;

import main.java.gameobj.MovingObj;
import main.java.Game;
import processing.core.PApplet;
import processing.core.PImage;

public abstract class Cat extends MovingObj {
    public Cat(float posX, float posY, float w, float h, float speedMax, PImage spr) {
        super(posX, posY, w, h, speedMax, spr);
    }

    // Other
    public abstract void behaviour(Game gameInst);

    @Override // No art yet, so red squares
    public void draw(PApplet app) {
        app.fill(255, 0, 0);
        app.rect(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
    }
}