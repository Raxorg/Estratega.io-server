package com.frontanilla.estrategaioserver.zones.foundations;

public abstract class ZoneConnector {

    protected ZoneAssets assets;
    protected ZoneFirebase firestore;
    protected ZoneInitializer initializer;
    protected ZoneInput input;
    protected ZoneLogic logic;
    protected ZoneRenderer renderer;
    protected ZoneScreen screen;
    protected ZoneStuff stuff;

    public ZoneAssets getAssets() {
        return assets;
    }

    public ZoneFirebase getFirebase() {
        return firestore;
    }

    public ZoneInitializer getInitializer() {
        return initializer;
    }

    public ZoneInput getInput() {
        return input;
    }

    public ZoneLogic getLogic() {
        return logic;
    }

    public ZoneRenderer getRenderer() {
        return renderer;
    }

    public ZoneScreen getScreen() {
        return screen;
    }

    public ZoneStuff getStuff() {
        return stuff;
    }
}
