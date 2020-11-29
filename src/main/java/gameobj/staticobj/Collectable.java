package main.java.gameobj.staticobj;

import main.java.gameobj.StaticObj;

public abstract class Collectable extends StaticObj {
    protected boolean collected;

    /**
     * Constructor for a collectable; utilises StaticObj's constructor.
     */
    public Wall(int posX, int posY, int width, int height, PImage sprite) {
        super(posX, posY, width, height, sprite);

        this.collected = false;
    }

    // Getter methods
    public boolean isCollected() {
        return this.collected;
    }

    // Setter methods
    public void setCollected(boolean isCollected) {
        this.collected = isCollected;
    }

    // Other
    public boolean collect() {
        if (this.collected == false) {
            this.collected = true;
            this.setSprite(new PImage());

            return true;
        }

        return false;
    }
}