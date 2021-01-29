package main.java.gameobj.movingobj.cat;

import main.java.Game;
import main.java.gameobj.movingobj.Cat;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Jumpy periodically moves on the vector towards ahead where the player is pointing.
 */
public class Jumpy extends Cat {
    private int ticker; // Frame counter for when to change pointing
    private final int cycleDuration = 120; // Every 120 frames, i.e. 2 seconds
    
    private final float distanceMult = 3.0f; // Multiplier for distance ahead to point to

    public Jumpy(float posX, float posY, float w, float h, float speedMax, PImage spr) {
        super(posX, posY, w, h, speedMax, spr);

        this.ticker = 0;
    }

    // Other
    public void behaviour(Game gameInst) {
        // When ticker is zero, set behviour
        if (this.ticker == 0) {
            //!!! need to check if this spaghetti works :(
            this.setPointing((float) gameInst.
                                        getPlayer().
                                        getVector().
                                        getUnitXComponent() * distanceMult, 
                                (float) gameInst.
                                            getPlayer().
                                            getVector().
                                            getUnitXComponent() * distanceMult);
        }

        // Run the ticker
        this.ticker++;
        
        if (this.ticker >= this.cycleDuration) {
            this.ticker = 0;
        }
    }

    @Override
    public void tick(Game gameInst) {
        this.behaviour(gameInst); // Set the vector direction
        super.tick(gameInst); // Run collision checks and move
    }

    @Override // While there's no art, have letters distinguish cats
    public void draw(PApplet app) {
        super.draw(app);
        app.text('J', this.getPosX(), this.getPosY());
    }
}