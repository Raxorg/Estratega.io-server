package com.frontanilla.estrategaioserver.gui.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.estrategaioserver.utils.helpers.Rendering;

public class RectangleButton {

    protected Rectangle bounds;
    protected TextureRegion textureRegion;
    protected Color color;

    public RectangleButton(float x, float y, float w, float h) {
        bounds = new Rectangle(x, y, w, h);
        color = Color.WHITE;
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setColor(color);
        spriteBatch.draw(textureRegion, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void renderDebug(ShapeRenderer shapeRenderer) {
        Rendering.renderRectangleWithBounds(shapeRenderer, bounds);
    }

    public boolean contains(float x, float y) {
        return bounds.contains(x, y);
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
