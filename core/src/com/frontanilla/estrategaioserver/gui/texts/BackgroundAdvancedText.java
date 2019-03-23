package com.frontanilla.estrategaioserver.gui.texts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.frontanilla.estrategaioserver.gui.images.RectangleImage;
import com.frontanilla.estrategaioserver.utils.helpers.TextUtils;

import static com.frontanilla.estrategaioserver.utils.globals.GlobalConstants.ALPHABET;

public class BackgroundAdvancedText extends AdvancedText {

    private RectangleImage background;

    public BackgroundAdvancedText(float x, float y, BitmapFont font, String text, float textWidth, int horizontalAlign,
                                  boolean centerVertical, Color backgroundColor) {
        super(x, y, font, text, textWidth, horizontalAlign, centerVertical);
        float textHeight = TextUtils.getTextHeight(ALPHABET, font);
        background = new RectangleImage(x, y - textHeight - 15, textWidth, textHeight + 30);
        background.setColor(backgroundColor);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        background.render(spriteBatch);
        super.render(spriteBatch);
    }

    @Override
    public void renderDebug(ShapeRenderer shapeRenderer) {
        super.renderDebug(shapeRenderer);
        background.renderDebug(shapeRenderer);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        background.setPosition(x, y - textHeight - 15);
    }

    public RectangleImage getBackground() {
        return background;
    }
}
