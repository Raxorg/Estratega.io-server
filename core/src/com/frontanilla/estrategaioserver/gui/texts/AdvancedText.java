package com.frontanilla.estrategaioserver.gui.texts;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.frontanilla.estrategaioserver.utils.globals.GlobalConstants;
import com.frontanilla.estrategaioserver.utils.helpers.TextUtils;

public class AdvancedText extends Text {

    private float textWidth;
    private int horizontalAlignment;
    private boolean centerVertical;

    public AdvancedText(float x, float y, BitmapFont font, String text, float textWidth,
                        int horizontalAlign, boolean centerVertical) {
        super(x, y, font, text);
        this.textWidth = textWidth;
        this.horizontalAlignment = horizontalAlign;
        this.centerVertical = centerVertical;
        if (centerVertical) {
            bounds.y += TextUtils.getTextHeight(GlobalConstants.ALPHABET, font) / 2f;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        font.setColor(color);
        font.draw(
                spriteBatch,
                text,
                bounds.x,
                bounds.y,
                textWidth,
                horizontalAlignment,
                true);
    }

    @Override
    public void renderDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(
                bounds.x,
                bounds.y - textHeight,
                textWidth,
                bounds.height); // TODO Calculate Number of Lines and Y Spacing
    }

    public void setPosition(float x, float y) {
        if (centerVertical) {
            bounds.y = y + TextUtils.getTextHeight(GlobalConstants.ALPHABET, font) / 2f;
        } else {
            bounds.y = y;
        }
        bounds.x = x;
    }
}
