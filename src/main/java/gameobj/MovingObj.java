package main.java.gameobj;

import main.java.GameObj;
import main.java.Tickable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;
import processing.core.PImage;

//!!! need to do javadocs

public abstract class MovingObj extends GameObj implements Tickable {
    protected int speed;
    protected int speedMax;

    protected boolean upAvailable;
    protected boolean downAvailable;
    protected boolean leftAvailable;
    protected boolean rightAvailable;
    
    protected int pointingX;
    protected int pointingY;

    public MovingObj(int posX, int posY, int w, int h, int speedMax, PImage spr) {
        super(posX, posY, w, h, spr);

        this.speed = 0;
        this.speedMax = speedMax;

        this.upAvailable = true;
        this.downAvailable = true;
        this.leftAvailable = true;
        this.rightAvailable = true;

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
        if ((this.speed + 1) <= speedMax) {
            this.speed++;
        }
    }

    public void decelerate() {
        if ((this.speed - 1) >= 0) {
            this.speed--;
        }
    }

    //!!! double check for this one! might run into a case of clipping into walls when going kinda parallel to them
    public void moveToPoint() {
        // Have buffer of one pixel or so each direction to avoid jittering back and forth

        // X Movements
        if (this.posX - this.pointingX > 1 && this.leftAvailable) {
            this.posX -= this.speed; // Left
        } else if (this.posX - this.pointingX < 1 && this.rightAvailable) {
            this.posX += this.speed; // Right
        }

        // Y Movements
        if (this.posY - this.pointingY > 1 && this.upAvailable) {
            this.posY -= this.speed; // Up
        } else if (this.posY - this.pointingY < 1 && this.downAvailable) {
            this.posY -= this.speed; // Down
        }
    }

    // Other
    public abstract void behaviour();

    public void tick(Game gameInst) {
        //!!! checks surroundings, if can move (no collisions), will move, otherwise dont move,
        //      also need to figure out the accelerate and decelerate
        for (Wall elem : gameInst.getWalls()) {
            // For performance, skip far away walls 
            if (Math.abs(this.getPosX() - elem.getPosX()) > 16) { //!!! will probably need to be larger
                continue;
            }

            // Check whether there will be a collision in each direction,
            // 2 units ahead (!!! need to experiment whether this will be enough or need more)
            // 2 units
            // Above
            if (this.isCollision(elem.getPosX(), elem.getPosY() + 2, 
                                    elem.getWidth(), elem.getHeight())) {
                this.upAvailable = false;
            } else {
                this.upAvailable = true;
            }

            // Below
            if (this.isCollision(elem.getPosX(), elem.getPosY() - 2, 
                                    elem.getWidth(), elem.getHeight())) {
                this.downAvailable = false;
            } else {
                this.downAvailable = true;
            }

            // To the left
            if (this.isCollision(elem.getPosX() + 2, elem.getPosY(), 
                                    elem.getWidth(), elem.getHeight())) {
                this.leftAvailable = false;
            } else {
                this.leftAvailable = true;
            }

            // To the right
            if (this.isCollision(elem.getPosX() - 2, elem.getPosY(), 
                                    elem.getWidth(), elem.getHeight())) {
                this.rightAvailable = false;
            } else {
                this.rightAvailable = true;
            }
        }
    }
}