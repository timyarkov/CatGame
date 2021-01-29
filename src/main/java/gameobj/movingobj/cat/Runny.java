package main.java.gameobj.movingobj.cat;

import main.java.Game;
import main.java.gameobj.movingobj.Cat;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Runny follows the vector pointing directly to the player's X and Y position.
 */
public class Runny extends Cat {
    public Runny(float posX, float posY, float w, float h, float speedMax, PImage spr) {
        super(posX, posY, w, h, speedMax, spr);
    }

    // Other
    public void behaviour(Game gameInst) {
        this.setPointing(gameInst.getPlayer().getPosX(), gameInst.getPlayer().getPosY());
    }

    @Override
    public void tick(Game gameInst) {
        this.behaviour(gameInst); // Set the vector direction
        super.tick(gameInst); // Run collision checks and move
    }

    @Override // While there's no art, have letters distinguish cats
    public void draw(PApplet app) {
        super.draw(app);
        app.text('R', this.getPosX(), this.getPosY());
    }
}