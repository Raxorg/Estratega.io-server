package com.frontanilla.estrategaioserver.zones.console;

import com.frontanilla.estrategaioserver.zones.console.input.ConsoleInput;
import com.frontanilla.estrategaioserver.zones.console.logic.ConsoleLogic;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneConnector;

public class ConsoleConnector extends ZoneConnector {

    public ConsoleConnector(ConsoleAssets consoleAssets) {
        assets = consoleAssets;
        firestore = new ConsoleFirebase(this);
        initializer = new ConsoleInitializer(this);
        input = new ConsoleInput(this);
        logic = new ConsoleLogic(this);
        renderer = new ConsoleRenderer(this);
        screen = new ConsoleScreen(this);
        stuff = new ConsoleStuff(this);
    }
}
