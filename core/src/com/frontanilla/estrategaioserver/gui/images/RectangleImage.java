package com.frontanilla.estrategaioserver.gui.images;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.estrategaioserver.utils.helpers.Rendering;

public class RectangleImage {

    private Rectangle bounds;
    private TextureRegion textureRegion;
    protected Color color;
    protected float rotation;

    public RectangleImage(float x, float y, float w, float h) {
        bounds = new Rectangle(x, y, w, h);
        color = Color.WHITE.cpy();
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setColor(color);
        Rendering.renderRegionInBounds(spriteBatch, textureRegion, bounds, rotation);
    }

    public void renderDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void setPosition(float x, float y) {
        bounds.x = x;
        bounds.y = y;
    }

    // Getters & Setters
    public Rectangle getBounds() {
        return bounds;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
