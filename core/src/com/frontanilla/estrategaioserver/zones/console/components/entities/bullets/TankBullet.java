package com.frontanilla.estrategaioserver.zones.console.components.entities.bullets;

import com.frontanilla.estrategaioserver.zones.console.components.database.DBPlayerDocument;

public class TankBullet extends Bullet {

    public TankBullet(DBPlayerDocument owner) {
        super(owner);
    }

    public void update() {
        /*
        Vector2 position = new Vector2(bounds.getX(), bounds.getY());
        position.mulAdd(velocity, 999 * delta);
        bounds.setPosition(position.x, position.y);
         */
    }
}
