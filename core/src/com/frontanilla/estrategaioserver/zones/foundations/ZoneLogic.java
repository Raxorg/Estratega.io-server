package com.frontanilla.estrategaioserver.zones.foundations;

public abstract class ZoneLogic {

    protected ZoneConnector connector;

    public ZoneLogic(ZoneConnector connector) {
        this.connector = connector;
    }

    public abstract void initState();

    public abstract void update(float delta);
}
