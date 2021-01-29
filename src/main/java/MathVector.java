package main.java;

import java.lang.Math;

/**
 * Implementation of a 2D maths type of vector, used for movement.
 * Theres probably a better library for this, and definitely inaccurate because 
 * floating point error and that but shhhhhhh
 * Also my math is probably wrong :(
 */
public class MathVector {
    private double xComponent;
    private double yComponent;

    //!!! see if need global vars for lengths and unit stuff, in case performance will be bad

    /**
     * Constructs a 2D math vector, note at this point no calculations are done yet.
     * @param xComponent
     * @param yComponent
     */
    public MathVector(float xComponent, double yComponent) {
        this.xComponent = xComponent;
        this.yComponent = yComponent;
    }

    // Getter Methods
    public double getXComponent() {
        return this.xComponent;
    }

    public double getYComponent() {
        return this.yComponent;
    }

    public double getLength() {
        return Math.sqrt(Math.pow(this.xComponent, 2) + Math.pow(this.yComponent, 2));
    }

    public double getUnitMultiplier() {
        if (this.getLength() > 0) {
            return 1 / this.getLength();
        }

        return 0; // If length zero, just let the unit vector multiplier be zero
    }

    public double getUnitXComponent() {
        return this.getUnitMultiplier() * this.xComponent;
    }

    public double getUnitYComponent() {
        return this.getUnitMultiplier() * this.yComponent;
    }

    // Setter Methods
    public void setComponents(double xComponent, double yComponent) {
        this.xComponent = xComponent;
        this.yComponent = yComponent;
    }
}