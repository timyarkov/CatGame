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
    protected float posX;
    protected float posY;
    protected float width;
    protected float height;
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
    public GameObj(float posX, float posY, float width, float height, PImage sprite) {
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
    public float getPosX() {
        return this.posX;
    }

    public float getPosY() {
        return this.posY;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public PImage getSprite() {
        return this.sprite;
    }

    public PImage[] getSpriteArray() {
        return this.spriteArray;
    }

    // Setter Methods
    public void setPos(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public boolean setSize(float width, float height) {
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
    public boolean isCollision(float objPosX, float objPosY, float objWidth, float objHeight) {
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

    public boolean equals(GameObj obj) { //!!! need to check if with floats this still works
        return (this.getPosX() == obj.getPosX() &&
                    this.getPosY() == obj.getPosY() &&
                    this.getWidth() == obj.getWidth() &&
                    this.getHeight() == obj.getHeight() &&
                    this.getSprite().equals(obj.getSprite()));
    }

    public void draw(PApplet app) {
        app.image(this.sprite, this.posX, this.posY);
    }
}