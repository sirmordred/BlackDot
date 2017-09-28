package com.oguzhan.mordred.blackdot;

/**
 * Created by mordred on 10.03.2017.
 */

public class PointerPairs {
    private float x = 0f;
    private float y = 0f;
    public PointerPairs(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getXCoordinate() {
        return this.x;
    }

    public float getYCoordinate() {
        return this.y;
    }
}
