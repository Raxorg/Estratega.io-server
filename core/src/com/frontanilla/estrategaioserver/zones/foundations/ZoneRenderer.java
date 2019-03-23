package com.frontanilla.estrategaioserver.zones.foundations;

public abstract class ZoneRenderer {

    protected ZoneConnector connector;

    public ZoneRenderer(ZoneConnector connector) {
        this.connector = connector;
    }

    public abstract void initRenderers();

    public abstract void render();

}
