package main.java.gameobj.staticobj.spawn;

import main.java.gameobj.staticobj.Spawn;
import main.java.gameobj.movingobj.LaserPointer;
import processing.core.PImage;

/**
 * Handles spawning of player (LaserPointer) objects.
 */
public class PlayerSpawn extends Spawn {
    /**
     * Constructs a PlayerSpawn object.
     * @param posX X Position of the spawn.
     * @param posY Y Position of the spawn.
     * @param width Width of the player objects spawned.
     * @param height Height of the player objects spawned.
     */
    public PlayerSpawn(float posX, float posY, float width, float height) {
        super(posX, posY, width, height, new PImage()); // add sprite arg if decide to display
    }

    /**
     * Returns a new player object at the spawn's position.
     * @return New player object.
     */
    public LaserPointer spawnPlayer(float speedMax, PImage spr) {
        LaserPointer newPlayer = new LaserPointer(this.posX, this.posY, 
                                                        this.width, this.height, speedMax, spr);

        this.addSpawned(newPlayer);
        
        return newPlayer;
    }
}