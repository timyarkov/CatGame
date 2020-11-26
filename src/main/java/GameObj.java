package main;

import processing.core.PImage;

/**
 * Class for all GameObjs.
 */
public abstract class GameObj {
    protected int posX;
    protected int posY;
    protected int width;
    protected int height;
    protected PImage sprite;

    /**
     * Constructor for GameObjs. Bad widths and heights (negative) are converted to zero,
     * and bad sprites (null) are converted to empty PImages.
     * i need to do the documentation on a machine that has java support working :(
     */
    public GameObj(int posX, int posY, int width, int height, PImage sprite) {
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
    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
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

    // Setter Methods
    public void setPos(int posX, int posY) {
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

    // Other
    public boolean isCollision(int objPosX, int objPosY, int objWidth, int objHeight) {
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

    public boolean equals(GameObj obj) {
        return (this.getPosX() == obj.getPosX() &&
                    this.getPosY() == obj.getPosY() &&
                    this.getWidth() == obj.getWidth() &&
                    this.getHeight() == obj.getHeight() &&
                    this.getSprite().equals(obj.getSprite()));
    }
}