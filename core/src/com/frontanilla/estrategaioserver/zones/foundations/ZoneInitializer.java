package com.frontanilla.estrategaioserver.zones.foundations;

public abstract class ZoneInitializer {

    protected ZoneConnector connector;

    public ZoneInitializer(ZoneConnector connector) {
        this.connector = connector;
    }

    public abstract void initialize();
}
