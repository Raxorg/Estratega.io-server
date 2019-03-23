package com.frontanilla.estrategaioserver.zones.splash;

import com.frontanilla.estrategaioserver.core.ServerApp;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneConnector;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneInitializer;

public class SplashInitializer extends ZoneInitializer {

    public SplashInitializer(ZoneConnector connector) {
        super(connector);
    }

    @Override
    public void initialize() {
        // Init Screen - Set up the Cameras to be able to show something
        connector.getScreen().initCameras();
        // Init Renderer - Prepares Sprite Batches and/or Shape Renderers
        connector.getRenderer().initRenderers();
        // Init Logic - Prepare the Zone's Initial State
        connector.getLogic().initState();
        // Everything is Initialized, Time to Show the Zone
        ServerApp.instance.setScreen(connector.getScreen());
    }
}
