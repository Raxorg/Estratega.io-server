package com.frontanilla.estrategaioserver.zones.console.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.estrategaioserver.gui.images.RectangleImage;
import com.frontanilla.estrategaioserver.gui.texts.AdvancedText;
import com.frontanilla.estrategaioserver.gui.texts.BackgroundAdvancedText;

public class RequestLog {

    // Rendering
    private TextureRegion textBackground;
    private float margin;
    private BitmapFont textsFont;
    // Components
    private RectangleImage backgroundImage;
    private AdvancedText title;
    private DelayedRemovalArray<BackgroundAdvancedText> requestTexts;

    public RequestLog(float x, float y, float w, float h, TextureRegion textBackground, BitmapFont titleFont,
                      BitmapFont textsFont) {
        // Rendering
        this.textBackground = textBackground;
        margin = (h - 8.5f * (h / 9)) / 2;
        this.textsFont = textsFont;
        // Components
        backgroundImage = new RectangleImage(x, y, w, h);
        title = new AdvancedText(x, y + h - margin, titleFont, "Request Log", w, Align.center, false);
        requestTexts = new DelayedRemovalArray<>();
    }

    // Rendering
    public void render(SpriteBatch spriteBatch) {
        backgroundImage.render(spriteBatch);
        title.render(spriteBatch);
        for (int i = 0; i < requestTexts.size; i++) {
            requestTexts.get(i).render(spriteBatch);
        }
    }

    public void renderDebug(ShapeRenderer shapeRenderer) {
        backgroundImage.renderDebug(shapeRenderer);
        title.renderDebug(shapeRenderer);
        for (int i = 0; i < requestTexts.size; i++) {
            requestTexts.get(i).renderDebug(shapeRenderer);
        }
    }

    public void setPosition(float x, float y) {
        backgroundImage.setPosition(x, y);
        Rectangle bounds = backgroundImage.getBounds();
        title.setPosition(x, y + bounds.height - margin);
        updateTexts(bounds);
    }

    // Called by the Request Handler
    public void log(String message) {
        log(message, Color.WHITE);
    }

    public void log(String message, Color color) {
        Rectangle bounds = backgroundImage.getBounds();
        // Build and Add a New Background Advanced Text with the Given Message and Color
        BackgroundAdvancedText requestText = new BackgroundAdvancedText(
                bounds.x, bounds.y + bounds.height - (bounds.height / 9) * (requestTexts.size + 1) - margin,
                textsFont, message, bounds.width, Align.center, false, Color.BLACK.cpy().lerp(Color.CLEAR, 0.35f));
        requestText.setColor(color);
        requestText.getBackground().setTextureRegion(textBackground);
        requestTexts.add(requestText);
    }

    private void updateTexts(Rectangle bounds) {
        float firstTextY = bounds.y + bounds.height - margin;
        for (int i = 0; i < requestTexts.size; i++) {
            requestTexts.get(i).setPosition(bounds.x, firstTextY - (bounds.height / 9) * (i + 1));
        }
    }

    // Getters & Setters
    public RectangleImage getBackgroundImage() {
        return backgroundImage;
    }
}
