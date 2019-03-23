package com.frontanilla.estrategaioserver.zones.console;

import com.frontanilla.estrategaioserver.core.ServerApp;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneConnector;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneInitializer;

public class ConsoleInitializer extends ZoneInitializer {

    public ConsoleInitializer(ZoneConnector connector) {
        super(connector);
    }

    @Override
    public void initialize() {
        // Init Screen Cameras - To be able to show something
        connector.getScreen().initCameras();
        // Init Renderer - Prepares Sprite Batches and/or Shape Renderers
        connector.getRenderer().initRenderers();
        // Init Input - Configure Input Processor and Prepare for User Input
        connector.getInput().initConfig();
        // Init Logic - Prepare the Zone's Initial State
        connector.getLogic().initState();
        // Everything is Initialized, Time to Show the Zone
        ServerApp.instance.setScreen(connector.getScreen());
    }
}
