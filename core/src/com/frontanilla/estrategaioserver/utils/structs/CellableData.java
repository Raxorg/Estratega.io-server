package com.frontanilla.estrategaioserver.utils.structs;

import com.frontanilla.estrategaioserver.zones.console.components.database.DBPlayerDocument;

public class CellableData {

    private DBPlayerDocument owner;
    private int representation;
    private int health;
    private float rotation;

    public CellableData(DBPlayerDocument owner, int representation, int health, float rotation) {
        this.owner = owner;
        this.representation = representation;
        this.health = health;
        this.rotation = rotation;
    }

    public DBPlayerDocument getOwner() {
        return owner;
    }

    public int getRepresentation() {
        return representation;
    }

    public int getHealth() {
        return health;
    }

    public float getRotation() {
        return rotation;
    }
}
