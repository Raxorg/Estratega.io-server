package com.frontanilla.estrategaioserver.gui.fancy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.frontanilla.estrategaioserver.utils.helpers.Rendering;

public class AnimatedBackground {

    private Rectangle bounds;
    // Received data
    private TextureRegion repeatedImage, backgroundImage;
    private Color backgroundColor, repeatedImageColor;
    private OrthographicCamera camera;
    private int imageRows;
    private float speed;
    // Calculated data
    private float imageSize;
    private Vector2[] positions;

    public AnimatedBackground(float x, float y, float w, float h, Color backgroundColor, Color repeatedImageColor,
                              TextureRegion repeatedImage, TextureRegion backgroundImage, OrthographicCamera camera,
                              int imageRows, float speed) {
        bounds = new Rectangle(x, y, w, h);
        this.backgroundColor = backgroundColor;
        this.repeatedImageColor = repeatedImageColor;
        this.repeatedImage = repeatedImage;
        this.backgroundImage = backgroundImage;
        this.camera = camera;
        this.imageRows = imageRows + (imageRows % 2 == 0 ? 0 : 1);
        this.speed = speed;
        initialize();
    }

    private void initialize() {
        // Calculate Image Size
        imageSize = bounds.height / imageRows;
        // Calculate Columns
        int columns = (int) ((bounds.width / 2) / imageSize);
        // Calculate Spacing
        float xSpacing = imageSize + ((bounds.width + imageSize) - imageSize * (columns - 1)) / columns;
        float ySpacing = imageSize + ((bounds.height + imageSize) - imageSize * imageRows) / (imageRows * 0.7f);
        // Calculate Initial Positions
        positions = new Vector2[imageRows * columns];
        int index = 0;
        for (int r = 0; r < imageRows; r++) {
            for (int c = 0; c < columns; c++, index++) {
                float xPos;
                if (r % 2 != 0) {
                    xPos = -imageSize + xSpacing / 2 + xSpacing * c + bounds.x;
                } else {
                    xPos = -imageSize + xSpacing * c + bounds.x;
                }
                // Place the Calculated Position in the Array
                positions[index] = new Vector2(xPos, -imageSize + ySpacing * r + bounds.y);
            }
        }
    }

    public void update(float delta) {
        for (Vector2 position : positions) {
            position.x += speed * delta;
            position.y += speed * delta;
            // Verify Right Limit
            if (position.x >= bounds.x + bounds.width + imageSize / 2) {
                position.x = bounds.x - imageSize * 1.5f;
            }
            // Verify Top Limit
            if (position.y >= bounds.y + bounds.height + imageSize / 4) {
                position.y = bounds.y - imageSize * 1.25f;
            }
        }
    }

    public void render(SpriteBatch spriteBatch) {
        // Render the Background Image
        spriteBatch.setColor(backgroundColor);
        spriteBatch.draw(
                backgroundImage,
                bounds.x,
                bounds.y,
                bounds.width,
                bounds.height
        );
        // Render the Moving Images
        spriteBatch.setColor(repeatedImageColor);
        renderImages(spriteBatch);
    }

    private void renderImages(SpriteBatch spriteBatch) {
        float x = bounds.x;
        float y = bounds.y;
        float w = bounds.width;
        float h = bounds.height;
        Rectangle scissors = new Rectangle();
        Rectangle clipBounds = new Rectangle(x, y, w, h);
        ScissorStack.calculateScissors(camera, spriteBatch.getTransformMatrix(), clipBounds, scissors);
        boolean pop = ScissorStack.pushScissors(scissors);
        for (Vector2 position : positions) {
            spriteBatch.draw(
                    repeatedImage,
                    position.x, position.y, imageSize / 2f, imageSize / 2f,
                    imageSize, imageSize, 1f, 1f,
                    45f);
        }

        spriteBatch.flush();
        if (pop) {
            ScissorStack.popScissors();
        }
    }

    public void renderDebug(ShapeRenderer shapeRenderer) {
        Rendering.renderRectangleWithBounds(shapeRenderer, bounds);
    }

    // Getters & Setters
    public void setColor(Color color) {
        backgroundColor = color;
        repeatedImageColor = color.cpy().mul(Color.GRAY);
    }
}
