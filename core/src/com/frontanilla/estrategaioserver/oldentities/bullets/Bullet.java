package com.frontanilla.estrategaioserver.oldentities.bullets;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.frontanilla.estrategaioserver.firebase.Player;
import com.frontanilla.estrategaioserver.oldentities.Entity;

public abstract class Bullet extends Entity {

    protected Vector2 velocity;

    public Bullet(Player player, Polygon bounds, int health, float width, float height,
                  float rotation, Vector2 velocity) {
        super(player, bounds, health, width, height, rotation);
        this.velocity = velocity;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

}