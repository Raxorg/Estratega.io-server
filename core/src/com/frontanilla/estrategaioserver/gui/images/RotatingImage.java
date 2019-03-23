package com.frontanilla.estrategaioserver.gui.images;

import com.frontanilla.estrategaioserver.gui.images.RectangleImage;

public class RotatingImage extends RectangleImage {

    private float speed;

    public RotatingImage(float x, float y, float w, float h, float revolutionsPerSecond, boolean clockwise) {
        super(x, y, w, h);
        this.speed = 360 * revolutionsPerSecond;
        speed *= clockwise ? -1 : 1;
    }

    public void update(float delta) {
        rotation = rotation + speed * delta;
    }
}
