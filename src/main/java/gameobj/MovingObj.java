package main.java.gameobj;

import main.java.Game;
import main.java.GameObj;
import main.java.gameobj.staticobj.Wall;
import main.java.MathVector;
import main.java.Tickable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;
import processing.core.PImage;

//!!! need to do javadocs

public abstract class MovingObj extends GameObj implements Tickable {
    protected float speed; //!!! need to consider, since float is floating point and inaccurate
    protected float speedMax;
    protected static final float accelerateMultiplier = 0.2f; //!!! to tweak

    protected boolean upAvailable;
    protected boolean downAvailable;
    protected boolean leftAvailable;
    protected boolean rightAvailable;

    protected MathVector moveVector; // For movement
    
    protected float diffX;
    protected float diffY;

    protected float pointingX;
    protected float pointingY;

    public MovingObj(float posX, float posY, float w, float h, float speedMax, PImage spr) {
        super(posX, posY, w, h, spr);

        this.speed = 0;
        this.speedMax = speedMax; // !!!might need conversion for different resolutions?

        this.upAvailable = true;
        this.downAvailable = true;
        this.leftAvailable = true;
        this.rightAvailable = true;

        this.moveVector = new MathVector(0, 0);

        this.pointingX = 0;
        this.pointingY = 0;
    }

    // Getter Methods
    public float getSpeed() {
        return this.speed;
    }

    public float getSpeedMax() {
        return this.speedMax;
    }

    public float getPointingX() {
        return this.pointingX;
    }

    public float getPointingY() {
        return this.pointingY;
    }

    public MathVector getVector() {
        return this.moveVector;
    }

    // Setter methods
    public boolean setSpeedMax(float speedMax) {
        if (speedMax < 0) {
            return false;
        }

        this.speedMax = speedMax;

        return true;
    }

    public void setPointing(float pointingX, float pointingY) {
        this.pointingX = pointingX;
        this.pointingY = pointingY;
    }


    // Movement
    public void accelerate() {
        if ((this.speed + MovingObj.accelerateMultiplier) <= speedMax) {
            this.speed += MovingObj.accelerateMultiplier;
        }
    }

    public void decelerate() {
        if ((this.speed - MovingObj.accelerateMultiplier) >= 0) {
            this.speed -= MovingObj.accelerateMultiplier;
        }
    }

    //!!! double check for this one! might run into a case of clipping into walls when going kinda parallel to them
    @Deprecated
    public void moveToPointOld() {
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

    //!!! trying vectors with this one
    public void moveToPoint() {
        // Determine components (!!! double check directions on this one!)
        this.diffX = this.pointingX - this.posX;
        this.diffY = this.pointingY - this.posY;

        this.moveVector.setComponents(this.diffX, this.diffY);

        // Act upon it
        // X axis moves
        if (diffX < 0 && this.leftAvailable) {
            this.posX -= this.moveVector.getUnitXComponent() * this.speed; // Left
        } else if (diffX > 0 && this.rightAvailable) {
            this.posX += this.moveVector.getUnitXComponent() * this.speed; // Right
        }

        // Y axis moves
        if (diffY < 0 && this.upAvailable) {
            this.posY -= this.moveVector.getUnitYComponent() * this.speed; // Up
        } else if (diffY > 0 && this.downAvailable) {
            this.posY += this.moveVector.getUnitYComponent() * this.speed; // Down
        }
    }

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

        // After collision checks, perform movements
        this.moveToPoint();
    }
}