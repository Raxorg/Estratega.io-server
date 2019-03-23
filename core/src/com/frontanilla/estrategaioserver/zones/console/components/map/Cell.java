package com.frontanilla.estrategaioserver.zones.console.components.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.frontanilla.estrategaioserver.interfacing.firebase.Player;
import com.frontanilla.estrategaioserver.oldentities.cellables.Cellable;
import com.frontanilla.estrategaioserver.utils.helpers.Coloring;

import static com.frontanilla.estrategaioserver.zones.console.constants.GameConstants.CELL_SIZE;

public class Cell {

    private Player owner;
    private Cellable content;
    private Vector2 position;
    private boolean selected;
    private TextureRegion background;

    public Cell(Vector2 position, TextureRegion background) {
        this.position = position;
        this.background = background;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Cellable getContent() {
        return content;
    }

    public void setContent(Cellable content) {
        this.content = content;
    }

    public boolean isEmpty() {
        return content == null;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Color getColor() {
        if (owner != null) {
            return owner.getColor();
        }
        return Color.WHITE;
    }

    // TODO for map visualizer
    public TextureRegion getTexture() {
        return null; // return Assets.instance.displayAssets.pixel;
    }

    public void render(SpriteBatch batch) {
        // Draw cell image
        batch.setColor(Color.WHITE);
        batch.draw(
                background, // Assets.instance.displayAssets.pixel,
                position.x,
                position.y,
                CELL_SIZE,
                CELL_SIZE
        );
        // Draw owner mark
        if (owner != null) {
            batch.setColor(Coloring.colorWithAlpha(owner.getColor(), 0.5f));
            batch.draw(
                    background, // Assets.instance.displayAssets.pixel,
                    position.x,
                    position.y,
                    CELL_SIZE,
                    CELL_SIZE
            );
        }
    }

    public boolean contains(float x, float y) {
        if (x >= position.x && x <= position.x + CELL_SIZE) {
            if (y >= position.y && y <= position.y + CELL_SIZE) {
                return true;
            }
        }
        return false;
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
    }
}
