package main.java;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Class for all GameObjs. Assuming box-shaped objects, 
 * X and Y positions are taken as the top-left of the object.
 * Note that if the sprite array is to be used, it needs 
 * to be instantiated with the setSpriteArray() method.
 */
public abstract class GameObj {
    protected double posX;
    protected double posY;
    protected int width;
    protected int height;
    protected PImage sprite;
    protected PImage[] spriteArray;

    /**
     * Constructor for GameObjs. Bad widths and heights (negative) are converted to zero,
     * and bad sprites (null) are converted to empty PImages.
     * @param posX X Position of the object.
     * @param posY Y Position of the object.
     * @param width Width of the object.
     * @param height Height of the object.
     * @param sprite Sprite of the object.
     */
    public GameObj(double posX, double posY, int width, int height, PImage sprite) {
        this.posX = posX;
        this.posY = posY;
        
        if (width < 0) {
            this.width = 0;
        } else {
            this.width = width;
        }

        if (height < 0) {
            this.height = 0;
        } else {
            this.height = height;
        }

        if (sprite == null) {
            this.sprite = new PImage();
        } else {
            this.sprite = sprite;
        }
    }

    // Getter Methods
    public double getPosX() {
        return this.posX;
    }

    public double getPosY() {
        return this.posY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public PImage getSprite() {
        return this.sprite;
    }

    public PImage[] getSpriteArray() {
        return this.spriteArray;
    }

    // Setter Methods
    public void setPos(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public boolean setSize(int width, int height) {
        if (width < 0 || height < 0) {
            return false;
        }

        this.width = width;
        this.height = height;

        return true;
    }

    public boolean setSprite(PImage sprite) {
        if (sprite == null) {
            return false;
        }

        this.sprite = sprite;

        return true;
    }

    public void setSpriteArray(PImage[] sprites) {
        // Check that theres no nulls; if there are, just make them into empty PImages
        for (int i = 0; i < sprites.length; i++) {
            if (sprites[i] == null) {
                sprites[i] = new PImage();
            }
        }

        this.spriteArray = sprites;
    }

    // Other
    public boolean isCollision(double objPosX, double objPosY, int objWidth, int objHeight) {
        if (objWidth < 0 || objHeight < 0) {
            return false;
        }

        // collision if (aLeft < bRight && 
        //                  aRight > bLeft &&
        //                  aTop < bBottom &&
        //                  aBottom > bTop)

        return (this.posX < objPosX + objWidth &&
                    this.posX + this.width > objPosX &&
                    this.posY < objPosY + objHeight &&
                    this.posY + this.height > objPosY);
    }

    public boolean equals(GameObj obj) { //!!! need to check if with doubles this still works
        return (this.getPosX() == obj.getPosX() &&
                    this.getPosY() == obj.getPosY() &&
                    this.getWidth() == obj.getWidth() &&
                    this.getHeight() == obj.getHeight() &&
                    this.getSprite().equals(obj.getSprite()));
    }

    public void draw(PApplet app) {
        //!!! need to refactor everything to use floats now :(
        app.image(this.sprite, (float) this.posX, (float) this.posY);
    }
}