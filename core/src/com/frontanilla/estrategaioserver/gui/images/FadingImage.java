package com.frontanilla.estrategaioserver.gui.images;

import com.badlogic.gdx.math.MathUtils;
import com.frontanilla.estrategaioserver.gui.images.RectangleImage;


public class FadingImage extends RectangleImage {

    private float time;
    private float duration;
    private boolean active, fadingIn;

    public FadingImage(float x, float y, float w, float h) {
        super(x, y, w, h);
    }

    public void update(float delta) {
        if (active) {
            time += delta;
            float progress = (1f / duration) * time;
            if (!fadingIn) {
                progress = 1f - progress;
            }
            color.a = MathUtils.clamp(progress, 0f, 1f);
            if ((fadingIn && color.a == 1f) || (!fadingIn && color.a == 1f)) {
                active = false;
            }
        }
    }

    public void fadeIn(float duration) {
        time = 0;
        this.duration = duration;
        fadingIn = true;
        active = true;
    }

    public void fadeOut(float duration) {
        time = 0;
        this.duration = duration;
        fadingIn = false;
        active = true;
    }
}
