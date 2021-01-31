package main.java.gameobj.staticobj;

import main.java.gameobj.MovingObj;
import main.java.gameobj.StaticObj;
import java.util.List;
import java.util.ArrayList;
import processing.core.PImage;

public abstract class Spawn extends StaticObj {
    /**
     * List to track what the spawner has spawned, so can use for things like object deletion.
     */
    protected ArrayList<MovingObj> spawned;

    public Spawn(float posX, float posY, float width, float height, PImage sprite) {
        super(posX, posY, width, height, sprite);

        this.spawned = new ArrayList<MovingObj>();
    }

    // Getter Methods
    public List<MovingObj> getSpawned() {
        return this.spawned;
    }

    // Other Methods
    public void addSpawned(MovingObj newObj) {
        spawned.add(newObj);
    }
}