package com.frontanilla.estrategaioserver.zones.splash;

import com.frontanilla.estrategaioserver.zones.foundations.ZoneConnector;

public class SplashConnector extends ZoneConnector {

    public SplashConnector() {
        assets = new SplashAssets();
        initializer = new SplashInitializer(this);
        logic = new SplashLogic(this);
        renderer = new SplashRenderer(this);
        screen = new SplashScreen(this);
        stuff = new SplashStuff(this);
    }
}
