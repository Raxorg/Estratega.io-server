package com.frontanilla.estrategaioserver.zones.console.components.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.estrategaioserver.gui.images.RectangleImage;
import com.frontanilla.estrategaioserver.utils.helpers.Transform;
import com.frontanilla.estrategaioserver.utils.structs.CellableData;
import com.frontanilla.estrategaioserver.zones.console.components.database.DBPlayerDocument;

import static com.frontanilla.estrategaioserver.zones.console.constants.ConsoleConstants.CONSOLE_CAMERA_HEIGHT;
import static com.frontanilla.estrategaioserver.zones.console.constants.ConsoleConstants.CONSOLE_MAP_SIZE;
import static com.frontanilla.estrategaioserver.zones.console.constants.GameConstants.*;

public class GridRepresentation {

    private Cell[][] cells;
    private OrthographicCamera camera;
    private RectangleImage backgroundImage;

    public GridRepresentation(TextureRegion cellBackground, TextureRegion ownerColor, TextureRegion selectionFrame,
                              OrthographicCamera camera, TextureRegion mapBackground) {
        cells = new Cell[GRID_ROWS][GRID_COLUMNS];
        for (int row = 0; row < GRID_ROWS; row++) {
            cells[row] = new Cell[GRID_COLUMNS];
            for (int column = 0; column < cells[row].length; column++) {
                float cellX = GRID_X + (CELL_SIZE + CELL_SPACING) * column;
                float cellY = GRID_Y + (CELL_SIZE + CELL_SPACING) * row;
                cells[row][column] = new Cell(cellX, cellY, cellBackground, ownerColor, selectionFrame);
            }
        }
        this.camera = camera;
        backgroundImage = new RectangleImage(
                camera.viewportWidth / 2f - CONSOLE_CAMERA_HEIGHT / 2f, 0,
                CONSOLE_CAMERA_HEIGHT, CONSOLE_CAMERA_HEIGHT);
        backgroundImage.setTextureRegion(mapBackground);
        backgroundImage.setColor(Color.SCARLET.cpy().lerp(Color.ORANGE, 0.5f).lerp(Color.CLEAR, 0.5f));
    }

    public void update(String[] gridRows, DelayedRemovalArray<DBPlayerDocument> players) {
        CellableData[][] cellableData = Transform.gridRowsToCellableDataArray(gridRows, players);
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int column = 0; column < GRID_COLUMNS; column++) {
                cells[row][column].setOwner(cellableData[row][column].getOwner());
                cells[row][column].setContent(Transform.cellableDataToCellable(cellableData[row][column], null));
            }
        }
    }

    public void render(SpriteBatch spriteBatch) {
        // Render Background
        backgroundImage.render(spriteBatch);
        spriteBatch.flush();
        // Render Cells Clipped by a Rectangle
        Rectangle scissors = new Rectangle();
        Rectangle clipBounds = new Rectangle(
                camera.viewportWidth / 2f - CONSOLE_MAP_SIZE / 2f, GRID_BORDER,
                CONSOLE_MAP_SIZE, CONSOLE_MAP_SIZE);
        ScissorStack.calculateScissors(camera, spriteBatch.getTransformMatrix(), clipBounds, scissors);
        boolean pop = ScissorStack.pushScissors(scissors);
        for (Cell[] cellRow : cells) {
            for (Cell cell : cellRow) {
                cell.render(spriteBatch);
            }
        }
        spriteBatch.flush();
        if (pop) {
            ScissorStack.popScissors();
        }
    }

    // Getters & Setters
    public Cell[][] getCells() {
        return cells;
    }

    public void setPositionInBounds(float x, float y) {
        float maxX = camera.viewportWidth / 2f - CONSOLE_MAP_SIZE / 2f;
        x = MathUtils.clamp(x, maxX - GRID_WIDTH + CONSOLE_MAP_SIZE, maxX);
        y = MathUtils.clamp(y, GRID_BORDER + CONSOLE_MAP_SIZE - GRID_HEIGHT, GRID_BORDER);
        position(x, y);
    }

    private void position(float x, float y) {
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                float cellX = x + (CELL_SIZE + CELL_SPACING) * column;
                float cellY = y + (CELL_SIZE + CELL_SPACING) * row;
                cells[row][column].setPosition(cellX, cellY);
            }
        }
        backgroundImage.setPosition(camera.viewportWidth / 2f - CONSOLE_CAMERA_HEIGHT / 2f, 0);
    }

    public void hide() {
        position(-5000, -5000);
        backgroundImage.setPosition(-5000, -5000);
    }

    public Vector2 getPosition() {
        return new Vector2(cells[0][0].getBounds().x, cells[0][0].getBounds().y);
    }
}
