package com.frontanilla.estrategaioserver.zones.console.logic.helpers;

import com.badlogic.gdx.math.Vector2;
import com.frontanilla.estrategaioserver.zones.console.ConsoleStuff;
import com.frontanilla.estrategaioserver.zones.console.logic.ConsoleLogic;

import static com.frontanilla.estrategaioserver.utils.globals.Enums.ConsoleTab.MAP;

public class MapHandler {

    private ConsoleLogic consoleLogic;
    private ConsoleStuff consoleStuff;

    public MapHandler(ConsoleLogic consoleLogic, ConsoleStuff consoleStuff) {
        this.consoleLogic = consoleLogic;
        this.consoleStuff = consoleStuff;
    }

    void pan(float deltaX, float deltaY) {
        if (consoleLogic.getCurrentTab() == MAP) {
            Vector2 gridPosition = consoleStuff.getGrid().getPosition();
            consoleStuff.getGrid().setPositionInBounds(gridPosition.x + deltaX, gridPosition.y - deltaY);
        }
    }
}
