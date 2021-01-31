package main.java.gameobj.staticobj.spawn;

import main.java.gameobj.staticobj.Spawn;
import main.java.gameobj.movingobj.Cat;
import main.java.gameobj.movingobj.cat.Runny;
import main.java.gameobj.movingobj.cat.Jumpy;
import main.java.gameobj.movingobj.cat.Stumbly;
import main.java.CatMethod;
import java.lang.RuntimeException;
import java.util.Random;
import java.util.ArrayList;
import java.util.Map;
import processing.core.PImage;

public class CatSpawn extends Spawn {
    // Cats here are ordered by name as key (capitalisation, same as class name), and sprite
    // as the value.
    private Map<String, PImage> catSprLibrary;

    /**
     * Constructs a Cat Spawner.
     * @param posX X Position of spawner.
     * @param posY Y Position of spawner.
     * @param w Width of cats spawned.
     * @param h Height of cats spawned.
     * @param spr Sprite for the spawner.
     * @param catSprs Cat sprites, with name (capitalised, same as class name) as key and sprite 
     *                  as value.
     */
    public CatSpawn(float posX, float posY, float w, float h, PImage spr, 
                        Map<String, PImage> catSprs) {
        super(posX, posY, w, h, spr);

        // Check the cat sprite library is good; should have all the correct entries
        ArrayList<String> required = new ArrayList<String>(); //!!! surely there is a better way of doing this :(
        required.add("Runny");
        required.add("Jumpy");
        required.add("Stumbly");

        for (String name : catSprs.keySet()) { //!!! i dont remember if there would be issues with this, be careful!
            if (required.contains(name)) {
                required.remove(name);
            }
        }

        if (required.size() != 0) {
            throw new RuntimeException("One or more required sprites not present!");
        }

        this.catSprLibrary = catSprs;
    }

    // Getter Methods
    public Map<String, PImage> getCatSprLibrary() {
        return this.catSprLibrary;
    }

    // Individual Spawns
    public Runny spawnRunny(float speedMax) {
        Runny newObj = new Runny(this.getPosX(), this.getPosY(), this.getWidth(), 
                                    this.getHeight(), speedMax, this.catSprLibrary.get("Runny"));
        
        return newObj;
    }

    public Jumpy spawnJumpy(float speedMax) {
        Jumpy newObj = new Jumpy(this.getPosX(), this.getPosY(), this.getWidth(), 
                                    this.getHeight(), speedMax, this.catSprLibrary.get("Jumpy"));
        
        return newObj;
    }

    public Stumbly spawnStumbly(float speedMax) {
        Stumbly newObj = new Stumbly(this.getPosX(), this.getPosY(), this.getWidth(), 
                                    this.getHeight(), speedMax, this.catSprLibrary.get("Stumbly"));
        
        return newObj;
    }

    // Other Methods
    /**
     * Randomly selects a cat type to spawn, utilising individual spawn method for required cat.
     * @param speedMax Max speed of spawned cat.
     * @return A Cat of a random type.
     */
    public Cat spawnRandom(float speedMax) {
        ArrayList<CatMethod> spawnMethods = new ArrayList<CatMethod>();

        spawnMethods.add(() -> this.spawnRunny(speedMax)); //!!! Probably a better way to do this :(
        spawnMethods.add(() -> this.spawnJumpy(speedMax));
        spawnMethods.add(() -> this.spawnStumbly(speedMax));

        Random randomiser = new Random();

        return spawnMethods.get(randomiser.nextInt(spawnMethods.size())).spawnCatReference();
    }
}