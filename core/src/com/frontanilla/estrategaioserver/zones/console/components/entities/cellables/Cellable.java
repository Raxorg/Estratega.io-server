package com.frontanilla.estrategaioserver.zones.console.components.entities.cellables;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.estrategaioserver.utils.helpers.Rendering;
import com.frontanilla.estrategaioserver.utils.helpers.Transform;
import com.frontanilla.estrategaioserver.zones.console.components.database.DBPlayerDocument;
import com.frontanilla.estrategaioserver.zones.console.components.entities.Entity;

import java.util.Map;

public abstract class Cellable extends Entity {

    // Regions Map
    protected Map<Integer, TextureRegion> regionsMap;
    // Cellable Data
    protected DBPlayerDocument owner;
    protected int representation;
    protected int health;
    protected float rotation;

    public Cellable(DBPlayerDocument owner) {
        this.owner = owner;
    }

    public void render(SpriteBatch batch) {
        batch.setColor(Transform.stringToColor(owner.getColor()));
        Rendering.renderRegionInBounds(batch, regionsMap.get(health), bounds, rotation);
    }

    //-------------------
    // Getters & Setters
    //-------------------
    // Regions Map
    public Map<Integer, TextureRegion> getRegionsMap() {
        return regionsMap;
    }

    public void setRegionsMap(Map<Integer, TextureRegion> regionsMap) {
        this.regionsMap = regionsMap;
    }

    // Cellable Data
    public DBPlayerDocument getOwner() {
        return owner;
    }

    public void setOwner(DBPlayerDocument owner) {
        this.owner = owner;
    }

    public int getRepresentation() {
        return representation;
    }

    public void setRepresentation(int representation) {
        this.representation = representation;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
