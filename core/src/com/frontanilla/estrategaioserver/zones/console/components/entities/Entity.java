package com.frontanilla.estrategaioserver.zones.console.components.entities;

import com.badlogic.gdx.math.Polygon;
import com.frontanilla.estrategaioserver.zones.console.components.database.DBPlayerDocument;

public abstract class Entity {

    protected DBPlayerDocument owner;
    protected Polygon bounds;

    public DBPlayerDocument getOwner() {
        return owner;
    }

    public void setOwner(DBPlayerDocument owner) {
        this.owner = owner;
    }

    public Polygon getBounds() {
        return bounds;
    }

    public void setBounds(Polygon bounds) {
        this.bounds = bounds;
    }

    /*
    public void render(SpriteBatch batch) {
        batch.setColor(color);
        Rendering.renderRegionInBounds(batch, texture, bounds, rotation);
    }

    public void renderDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.polygon(bounds.getTransformedVertices());
    }
     */
}
