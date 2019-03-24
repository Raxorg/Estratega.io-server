package com.frontanilla.estrategaioserver.zones.console.components.entities.cellables.units;

import com.frontanilla.estrategaioserver.zones.console.components.database.DBPlayerDocument;
import com.frontanilla.estrategaioserver.zones.console.components.entities.bullets.TankBullet;

public class Tank extends Unit {

    public Tank(DBPlayerDocument owner) {
        super(owner);
    }

    public TankBullet shoot() {
        TankBullet newBullet = new TankBullet(owner);
        return newBullet;
    }

    /*
    public TankBullet fire() {
        Vector2 rotationVector = Transform.rotationToVector2(rotation);
        Vector2 bulletPosition = new Vector2(bounds.getX(), bounds.getY());
        bulletPosition.add(CELL_SIZE / 2, CELL_SIZE / 2);
        bulletPosition.add(-999 / 2, -999 / 2);
        bulletPosition.mulAdd(rotationVector, CELL_SIZE / 2);
        return null;
        return new TankBullet(
                owner,
                new Polygon(),//Util.PolygonFromBullet(TankBullet.class, bulletPosition, rotation),
                999,
                rotation
        );
    }
     */
}
