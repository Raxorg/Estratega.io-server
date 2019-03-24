package com.frontanilla.estrategaioserver.zones.console.constants;

public class GameConstants {
    // Cells
    public static final int CELL_SIZE = 50;
    public static final int CELL_SPACING = 6;
    public static final int CELL_TOUCH_MARGIN = CELL_SPACING / 2;
    // Grid
    public static final int GRID_BORDER = CELL_SIZE;
    public static final int GRID_X = GRID_BORDER;
    public static final int GRID_Y = GRID_BORDER;
    public static final int GRID_ROWS = 20;
    public static final int GRID_COLUMNS = 40;
    public static final int GRID_WIDTH = GRID_COLUMNS * CELL_SIZE + CELL_SPACING * (GRID_COLUMNS - 1);
    public static final int GRID_HEIGHT = GRID_ROWS * CELL_SIZE + CELL_SPACING * (GRID_ROWS - 1);
    // Wall
    public static final int WALL_REPRESENTATION = 1;
    public static final int WALL_COST = 2;
    // Tank
    public static final int TANK_REPRESENTATION = 2;
    public static final int TANK_COST = 3;
    // Base
    public static final int BASE_REPRESENTATION = 3;
    public static final int BASE_COST = 0;
}
