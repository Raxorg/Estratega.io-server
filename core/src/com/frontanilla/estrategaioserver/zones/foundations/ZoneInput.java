package com.frontanilla.estrategaioserver.zones.foundations;

import com.badlogic.gdx.InputAdapter;

public abstract class ZoneInput extends InputAdapter {

    protected ZoneConnector connector;
    protected boolean enabled;

    public ZoneInput(ZoneConnector connector) {
        this.connector = connector;
    }

    public abstract void initConfig();

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
