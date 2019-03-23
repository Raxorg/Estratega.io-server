package com.frontanilla.estrategaioserver.utils.structs;

import com.frontanilla.estrategaioserver.firebase.Player;

public class CellableData {

    private Player owner;
    private int representation;
    private int health;
    private float rotation;

    public CellableData(Player owner, int representation, int health, float rotation) {
        this.owner = owner;
        this.representation = representation;
        this.health = health;
        this.rotation = rotation;
    }

    public Player getOwner() {
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
