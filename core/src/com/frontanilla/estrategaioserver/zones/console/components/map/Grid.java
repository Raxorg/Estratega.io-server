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
import com.frontanilla.estrategaioserver.firebase.Player;
import com.frontanilla.estrategaioserver.gui.images.RectangleImage;
import com.frontanilla.estrategaioserver.oldentities.cellables.Cellable;
import com.frontanilla.estrategaioserver.oldentities.cellables.buildings.Base;
import com.frontanilla.estrategaioserver.oldentities.cellables.buildings.Wall;
import com.frontanilla.estrategaioserver.oldentities.cellables.units.Tank;
import com.frontanilla.estrategaioserver.utils.helpers.Transform;
import com.frontanilla.estrategaioserver.utils.structs.CellableData;

import static com.frontanilla.estrategaioserver.zones.console.constants.ConsoleConstants.CONSOLE_CAMERA_HEIGHT;
import static com.frontanilla.estrategaioserver.zones.console.constants.ConsoleConstants.CONSOLE_MAP_SIZE;
import static com.frontanilla.estrategaioserver.zones.console.constants.GameConstants.*;

public class Grid {

    private Cell[][] cells;
    private OrthographicCamera camera;
    private RectangleImage backgroundImage;

    public Grid(OrthographicCamera camera, TextureRegion pixel) {
        cells = new Cell[GRID_ROWS][GRID_COLUMNS];
        for (int row = 0; row < GRID_ROWS; row++) {
            cells[row] = new Cell[GRID_COLUMNS];
            for (int column = 0; column < cells[row].length; column++) {
                float cellX = GRID_BORDER + (CELL_SIZE + CELL_SPACING) * column;
                float cellY = GRID_BORDER + (CELL_SIZE + CELL_SPACING) * row;
                cells[row][column] = new Cell(new Vector2(cellX, cellY), pixel);
            }
        }
        this.camera = camera;
        backgroundImage = new RectangleImage(
                camera.viewportWidth / 2f - CONSOLE_CAMERA_HEIGHT / 2f, 0,
                CONSOLE_CAMERA_HEIGHT, CONSOLE_CAMERA_HEIGHT);
        backgroundImage.setTextureRegion(pixel);
        backgroundImage.setColor(Color.SCARLET.cpy().lerp(Color.ORANGE, 0.5f).lerp(Color.CLEAR, 0.5f));
    }

    public void update(String[] gridRows, DelayedRemovalArray<Player> players) {
        CellableData[][] cellableData = gridRowsToCellableDataArray(gridRows, players);
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int column = 0; column < GRID_COLUMNS; column++) {
                cells[row][column].setOwner(cellableData[row][column].getOwner());
                cells[row][column].setContent(contentFromCellableData(row, column, cellableData[row][column]));
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

    private Cellable contentFromCellableData(int row, int column, CellableData contentData) {
        switch (contentData.getRepresentation()) {
            case 1:
                return new Wall(
                        contentData.getOwner(),
                        cells[row][column],
                        contentData.getHealth(),
                        contentData.getRotation()
                );
            case 2:
                return new Tank(
                        contentData.getOwner(),
                        cells[row][column],
                        contentData.getHealth(),
                        contentData.getRotation()
                );
            case 3:
                return new Base(
                        contentData.getOwner(),
                        cells[row][column],
                        contentData.getHealth(),
                        contentData.getRotation()
                );
            default:
                return null;
        }
    }

    private CellableData[][] gridRowsToCellableDataArray(String[] gridRows, DelayedRemovalArray<Player> players) {
        CellableData[][] cellableDataArray = new CellableData[GRID_ROWS][GRID_COLUMNS];
        for (int row = 0; row < cellableDataArray.length; row++) {
            cellableDataArray[row] = new CellableData[GRID_COLUMNS];
            String[] data = gridRows[row].split(",");
            for (int column = 0; column < GRID_COLUMNS; column++) {
                cellableDataArray[row][column] = new CellableData(
                        getPlayerFromColorChar(data[column].charAt(0), players),
                        Integer.valueOf(data[column].charAt(1) + ""),
                        Integer.valueOf(data[column].charAt(2) + ""),
                        Float.valueOf(data[column].substring(3))
                );
            }
        }
        return cellableDataArray;
    }

    private Player getPlayerFromColorChar(char playerColor, DelayedRemovalArray<Player> players) {
        for (Player p : players) {
            if (p.getColor() == Transform.stringToColor(playerColor + "")) {
                return p;
            }
        }
        return null;
    }

    // Getters & Setters
    public Cell[][] getCells() {
        return cells;
    }

    public void setPosition(float x, float y) {
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
        return cells[0][0].getPosition();
    }
}