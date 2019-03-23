package com.frontanilla.estrategaioserver.gui.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AdvancedButton extends RectangleButton {

    private Color currentColor, middleColor, transparentColor;

    public AdvancedButton(float x, float y, float w, float h) {
        super(x, y, w, h);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setColor(currentColor);
        spriteBatch.draw(textureRegion, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        currentColor = color;
        middleColor = color.cpy().lerp(Color.CLEAR, 0.2f);
        transparentColor = color.cpy().lerp(Color.CLEAR, 0.4f);
    }

    public void useOpaqueColor() {
        currentColor = color;
    }

    public void useMiddleColor() {
        currentColor = middleColor;
    }

    public void useTransparentColor() {
        currentColor = transparentColor;
    }

    public boolean isUsingOpaqueColor() {
        return currentColor == color;
    }

    public boolean isUsingMiddleColor() {
        return currentColor == middleColor;
    }

    public boolean isUsingTransparentColor() {
        return currentColor == transparentColor;
    }
}
