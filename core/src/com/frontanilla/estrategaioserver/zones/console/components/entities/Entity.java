package com.frontanilla.estrategaioserver.zones.console.components.entities;

import com.badlogic.gdx.math.Polygon;
import com.frontanilla.estrategaioserver.interfacing.firebase.Player;

public abstract class Entity {

    protected Player owner;
    protected Polygon bounds;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Polygon getBounds() {
        return bounds;
    }

    public void setBounds(Polygon bounds) {
        this.bounds = bounds;
    }
}
