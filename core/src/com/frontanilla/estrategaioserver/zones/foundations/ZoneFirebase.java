package com.frontanilla.estrategaioserver.zones.foundations;

public abstract class ZoneFirebase {

    protected ZoneConnector connector;

    public ZoneFirebase(ZoneConnector connector) {
        this.connector = connector;
    }
}
