package com.oguzhan.mordred.blackdot;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;

/**
 * Created by mordred on 28.02.2017.
 */

public class Circle {
    private float centerX;
    private float centerY;
    private float radius;
    private Paint mPaint;

    public Circle(Float centerX,Float centerY, Float radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
    }

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float r) {
        this.radius = r;
    }

    public void setColor(int i) {
        mPaint.setColor(i);
    }

    public Paint getPaint() {
        return mPaint;
    }
}
