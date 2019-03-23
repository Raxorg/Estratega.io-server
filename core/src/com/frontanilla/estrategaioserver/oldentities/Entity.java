package com.frontanilla.estrategaioserver.oldentities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.frontanilla.estrategaioserver.interfacing.firebase.Player;

public abstract class Entity {

    protected Player owner;
    protected Polygon bounds;
    protected int health;
    protected float width, height, rotation;
    protected TextureRegion texture;
    protected Color color;

    public Entity(Player owner, Polygon bounds, int health,
                  float width, float height, float rotation) {
        this.owner = owner;
        this.bounds = bounds;
        this.health = health;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.texture = textureOfHealth();
        this.color = owner.getColor();
        bounds.setRotation(rotation);
    }

    public abstract TextureRegion textureOfHealth();

    public Player getOwner() {
        return owner;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }

    public Polygon getBounds() {
        return bounds;
    }

    public void setBounds(Polygon bounds) {
        this.bounds = bounds;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public abstract void update(float delta);

    public void render(SpriteBatch batch) {
        batch.setColor(color);
        batch.draw(
                texture,
                bounds.getX(),
                bounds.getY(),
                width / 2,
                height / 2,
                width,
                height,
                1,
                1,
                rotation
        );
    }

    public void renderDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.polygon(bounds.getTransformedVertices());
    }
}