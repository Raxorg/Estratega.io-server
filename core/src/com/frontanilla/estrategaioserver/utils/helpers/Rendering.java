package com.frontanilla.estrategaioserver.utils.helpers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;

public class Rendering {

    public static void renderRegionInBounds(SpriteBatch batch, TextureRegion region, Shape2D bounds) {
        renderRegionInBounds(batch, region, bounds, 0);
    }

    public static void renderRegionInBounds(SpriteBatch batch, TextureRegion region, Shape2D bounds, float rotation) {
        if (bounds instanceof Rectangle) {
            renderRegionInBounds(batch, region, (Rectangle) bounds, rotation);
        } else if (bounds instanceof Circle) {
            renderRegionInBounds(batch, region, (Circle) bounds, rotation);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void renderRegionInBounds(SpriteBatch batch, TextureRegion region, Rectangle bounds) {
        renderRegionInBounds(batch, region, bounds, 0);
    }

    public static void renderRegionInBounds(SpriteBatch batch, TextureRegion region, Rectangle bounds, float rotation) {
        float originX = bounds.width / 2f;
        float originY = bounds.height / 2f;
        batch.draw(region,
                bounds.x, bounds.y, originX, originY,
                bounds.width, bounds.height, 1f, 1f,
                rotation);
    }

    public static void renderRegionInBounds(SpriteBatch batch, TextureRegion region, Circle bounds) {
        renderRegionInBounds(batch, region, bounds, 0);
    }

    public static void renderRegionInBounds(SpriteBatch batch, TextureRegion region, Circle bounds, float rotation) {
        float x = bounds.x - bounds.radius;
        float y = bounds.y - bounds.radius;
        float sizeXY = bounds.radius * 2f;
        float originXY = sizeXY / 2f;
        batch.draw(region,
                x, y, originXY, originXY,
                sizeXY, sizeXY, 1f, 1f,
                rotation);
    }

    public static void renderRectangleWithBounds(ShapeRenderer renderer, Rectangle bounds) {
        renderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
