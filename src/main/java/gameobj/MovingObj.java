package main.java.gameobj;

import main.java.GameObj;
import main.java.Tickable;
import processing.core.PImage;

//!!! need to do javadocs

public abstract class MovingObj extends GameObj implements Tickable {
    protected int speed;
    protected int speedMax;
    
    protected int pointingX;
    protected int pointingY;

    public MovingObj(int posX, int posY, int w, int h, int speedMax, PImage spr) {
        super(posX, posY, w, h, spr);

        this.speed = 0;
        this.speedMax = speedMax;

        this.pointingX = 0;
        this.pointingY = 0;
    }

    // Getter Methods
    public int getSpeed() {
        return this.speed;
    }

    public int getSpeedMax() {
        return this.speedMax;
    }

    public int getPointingX() {
        return this.pointingX;
    }

    public int getPointingY() {
        return this.pointingY;
    }

    // Setter methods
    public boolean setSpeedMax(int speedMax) {
        if (speedMax < 0) {
            return false;
        }

        this.speedMax = speedMax;

        return true;
    }

    public void setPointing(int pointingX, int pointingY) {
        this.pointingX = pointingX;
        this.pointingY = pointingY;
    }


    // Movement
    public void accelerate() {
        if ((this.speed += 1) <= speedMax) {
            this.speed++;
        }
    }

    public void decelerate() {
        if ((this.speed -= 1) >= 0) {
            this.speed--;
        }
    }

    //!!! walls collisions to be dealt with in tickable
    public void moveToPoint() {
        // Have buffer of one pixel or so to avoid jittering back and forth

        // X Movements
        if (this.posX - this.pointingX > 1) {
            this.posX -= this.speed; // Left
        } else if (this.posX - this.pointingX < 1) {
            this.posX += this.speed; // Right
        }

        // Y Movements
        if (this.posY - this.pointingY > 1) {
            this.posY -= this.speed; // Up
        } else if (this.posY - this.pointingY < 1) {
            this.posY -= this.speed; // Down
        }
    }

    public void tick(Game gameInst) {
        //!!! checks surroundings, if can move (no collisions), will move, otherwise dont move,
        //      also need to figure out the accelerate and decelerate
        
    }
}