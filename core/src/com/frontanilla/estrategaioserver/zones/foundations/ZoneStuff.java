package com.frontanilla.estrategaioserver.zones.foundations;

public abstract class ZoneStuff {

    protected ZoneConnector connector;

    public ZoneStuff(ZoneConnector connector) {
        this.connector = connector;
    }

    public abstract void initStuff();
}
