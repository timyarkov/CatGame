package main.java.gameobj.movingobj.cat;

import main.java.Game;
import main.java.gameobj.movingobj.Cat;
import java.util.Random;
import java.lang.Math;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Stumbly periodically goes to a random position.
 */
public class Stumbly extends Cat {
    private int ticker; // Frame counter for when to change pointing
    private final int cycleDuration = 300; // Every 300 frames, i.e. 5 seconds

    private Random randomiser;

    // X and Y Coords for random position
    private float randomX;
    private float randomY;
    
    // Since randomiser is between 0 and 1, magnify the result 
    private final float randomiserMult = 1000.0f; 

    public Stumbly(float posX, float posY, float w, float h, float speedMax, PImage spr) {
        super(posX, posY, w, h, speedMax, spr);
    }

    // Other
    public void behaviour(Game gameInst) {
        //!!! test performance for this one, four randomises a tick might be a bit crazy
        if (this.ticker == 0) {
            // Randomise X and Y
            this.randomX = this.randomiser.nextFloat() * this.randomiserMult;
            this.randomY = this.randomiser.nextFloat() * this.randomiserMult;

            // Randomise polarity on X and Y !!! do actually need this????
            if (Math.pow(-1, randomiser.nextInt(2)) < 0) { // Negative case
                this.randomX *= -1;
            }

            if (Math.pow(-1, randomiser.nextInt(2)) < 0) { // Negative case
                this.randomY *= -1;
            }

            this.setPointing(randomX, randomY);
        }

        // Run the ticker
        this.ticker++;

        if (this.ticker >= cycleDuration) {
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
        app.text('S', this.getPosX(), this.getPosY());
    }
}