package com.frontanilla.estrategaioserver.gui.texts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.estrategaioserver.utils.helpers.TextUtils;

public class Text {

    protected Rectangle bounds;
    protected BitmapFont font;
    protected String text;
    protected Color color;
    protected float textHeight;

    public Text(float x, float y, BitmapFont font, String text) {
        bounds = new Rectangle(
                x,
                y,
                TextUtils.getTextWidth(text, font),
                TextUtils.getTextHeight(text, font));
        this.font = font;
        this.text = text;
        color = Color.WHITE;
        textHeight = TextUtils.getTextHeight(text, font);
    }

    public void render(SpriteBatch batch) {
        font.setColor(color);
        font.draw(batch, text, bounds.x, bounds.y);
    }

    public void renderDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(
                bounds.x,
                bounds.y - textHeight,
                bounds.width,
                bounds.height);
    }

    // Getters & Setters
    public Rectangle getBounds() {
        return bounds;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        textHeight = TextUtils.getTextHeight(text, font);
        bounds.width = TextUtils.getTextWidth(text, font);
        bounds.height = textHeight;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
