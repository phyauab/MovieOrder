package com.example.clement.movieorder;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;

//a class to fade the images edge
public class FadingImageView extends android.support.v7.widget.AppCompatImageView {
    private FadeSide mFadeSide;

    private Context c;

    public enum FadeSide {
        RIGHT_SIDE, LEFT_SIDE, TOP_SIDE, BOTTOM_SIDE
    }

    public FadingImageView(Context c, AttributeSet attrs, int defStyle) {
        super(c, attrs, defStyle);

        this.c = c;

        init();
    }

    public FadingImageView(Context c, AttributeSet attrs) {
        super(c, attrs);

        this.c = c;

        init();
    }

    public FadingImageView(Context c) {
        super(c);

        this.c = c;

        init();
    }

    private void init() {
        // Enable horizontal fading
        this.setHorizontalFadingEdgeEnabled(true);
        // Enable vertical fading
        this.setVerticalFadingEdgeEnabled(true);
        // Apply default fading length
        this.setEdgeLength(70);
        // Apply default side
        this.setFadeDirection(FadeSide.BOTTOM_SIDE);

    }

    public void setFadeDirection(FadeSide side) {
        this.mFadeSide = side;
    }

    public void setEdgeLength(int length) {
        this.setFadingEdgeLength(getPixels(length));
    }

    @Override
    protected float getLeftFadingEdgeStrength() {
        return mFadeSide.equals(FadeSide.LEFT_SIDE) ? 1.0f : 0.0f;
    }

    @Override
    protected float getRightFadingEdgeStrength() {
        return mFadeSide.equals(FadeSide.RIGHT_SIDE) ? 1.0f : 0.0f;
    }

    @Override
    protected float getTopFadingEdgeStrength() {
        return mFadeSide.equals(FadeSide.TOP_SIDE) ? 1.0f : 0.0f;
    }

    @Override
    protected float getBottomFadingEdgeStrength() {
        return mFadeSide.equals(FadeSide.BOTTOM_SIDE) ? 1.0f : 0.0f;
    }

    @Override
    public boolean hasOverlappingRendering() {
        return true;
    }

    @Override
    public boolean onSetAlpha(int alpha) {
        return false;
    }

    private int getPixels(int dipValue) {
        Resources r = c.getResources();

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dipValue, r.getDisplayMetrics());
    }
}
