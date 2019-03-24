package com.frontanilla.estrategaioserver.zones.console.components.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.frontanilla.estrategaioserver.gui.buttons.RectangleButton;
import com.frontanilla.estrategaioserver.gui.images.RectangleImage;
import com.frontanilla.estrategaioserver.utils.helpers.Coloring;
import com.frontanilla.estrategaioserver.utils.helpers.Transform;
import com.frontanilla.estrategaioserver.zones.console.components.database.DBPlayerDocument;
import com.frontanilla.estrategaioserver.zones.console.components.entities.cellables.Cellable;

import static com.frontanilla.estrategaioserver.zones.console.constants.GameConstants.CELL_SIZE;
import static com.frontanilla.estrategaioserver.zones.console.constants.GameConstants.CELL_TOUCH_MARGIN;

public class Cell extends RectangleButton {

    private Rectangle touchBounds;
    // Components
    private RectangleImage backgroundImage, ownerColorImage, selectionFrameImage;
    // Game Related
    private DBPlayerDocument owner;
    private Cellable content;
    private boolean selected;

    public Cell(float x, float y, TextureRegion background, TextureRegion ownerColor, TextureRegion selectionFrame) {
        super(x, y, CELL_SIZE, CELL_SIZE);
        backgroundImage = new RectangleImage(x, y, CELL_SIZE, CELL_SIZE);
        backgroundImage.setTextureRegion(background);
        ownerColorImage = new RectangleImage(x, y, CELL_SIZE, CELL_SIZE);
        ownerColorImage.setTextureRegion(ownerColor);
        selectionFrameImage = new RectangleImage(x, y, CELL_SIZE, CELL_SIZE);
        selectionFrameImage.setTextureRegion(selectionFrame);
        selectionFrameImage.setColor(Color.DARK_GRAY);
        touchBounds = new Rectangle(
                x - CELL_TOUCH_MARGIN, y - CELL_TOUCH_MARGIN,
                CELL_SIZE + 2 * CELL_TOUCH_MARGIN, CELL_SIZE + 2 * CELL_TOUCH_MARGIN);
    }

    @Override
    public void render(SpriteBatch batch) {
        backgroundImage.render(batch);
        if (owner != null) {
            ownerColorImage.render(batch);
        }
        if (selected) {
            selectionFrameImage.render(batch);
        }
    }

    @Override
    public boolean contains(float x, float y) {
        return touchBounds.contains(x, y);
    }

    public boolean isEmpty() {
        return content == null;
    }

    //-------------------
    // Getters & Setters
    //-------------------
    //region Getters & Setters

    public RectangleImage getBackgroundImage() {
        return backgroundImage;
    }

    public DBPlayerDocument getOwner() {
        return owner;
    }

    public void setOwner(DBPlayerDocument owner) {
        this.owner = owner;
        if (owner != null) {
            ownerColorImage.setColor(Coloring.colorWithAlpha(Transform.stringToColor(owner.getColor()), 0.5f));
            selectionFrameImage.setColor(Transform.stringToColor(owner.getColor()));
        } else {
            selectionFrameImage.setColor(Color.DARK_GRAY);
        }
    }

    public Cellable getContent() {
        return content;
    }

    public void setContent(Cellable content) {
        this.content = content;
        if (content != null) {
            setOwner(content.getOwner());
        }
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setPosition(float x, float y) {
        bounds.x = x;
        bounds.y = y;
        backgroundImage.setPosition(x, y);
        ownerColorImage.setPosition(x, y);
        selectionFrameImage.setPosition(x, y);
    }
    //endregion
}
