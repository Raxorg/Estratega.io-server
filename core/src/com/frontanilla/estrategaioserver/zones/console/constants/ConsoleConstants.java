package com.frontanilla.estrategaioserver.zones.console.constants;

import static com.frontanilla.estrategaioserver.zones.console.constants.GameConstants.GRID_BORDER;

public class ConsoleConstants {

    // Assets
    public static final String CONSOLE_ATLAS_FILE = "Images/Console/consoleAtlas.png";
    public static final String TIMES_SQUARE_FILE_1 = "fonts/timesSquare1.fnt";
    public static final String TIMES_SQUARE_FILE_2 = "fonts/timesSquare2.fnt";
    // Screen
    public static final int CONSOLE_CAMERA_HEIGHT = 1000;
    // Request Log
    public static final int REQUEST_LOG_SIZE = 1000;
    public static final int REQUEST_LOG_Y = 0;
    // Player List
    public static final int PLAYER_LIST_SIZE = 800;
    public static final int PLAYER_LIST_Y = (CONSOLE_CAMERA_HEIGHT - PLAYER_LIST_SIZE) / 2;
    // Console Buttons
    public static final int CONSOLE_BUTTON_SIZE = 150;
    // Map
    public static final int CONSOLE_MAP_SIZE = CONSOLE_CAMERA_HEIGHT - GRID_BORDER * 2;
}
