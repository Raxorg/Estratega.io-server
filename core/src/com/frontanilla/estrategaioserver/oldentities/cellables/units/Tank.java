package com.frontanilla.estrategaioserver.oldentities.cellables.units;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.frontanilla.estrategaioserver.interfacing.firebase.Player;
import com.frontanilla.estrategaioserver.oldentities.bullets.TankBullet;
import com.frontanilla.estrategaioserver.utils.helpers.Transform;
import com.frontanilla.estrategaioserver.zones.console.components.map.Cell;

import static com.frontanilla.estrategaioserver.zones.console.constants.GameConstants.CELL_SIZE;
import static com.frontanilla.estrategaioserver.zones.console.constants.GameConstants.TANK_REPRESENTATION;

public class Tank extends Unit {

    public Tank(Player player, Cell cell, int health, float rotation) {
        super(player, cell, health, rotation, CELL_SIZE, CELL_SIZE, TANK_REPRESENTATION);
    }

    public TankBullet fire() {
        Vector2 rotationVector = Transform.rotationToVector2(rotation);
        Vector2 bulletPosition = new Vector2(bounds.getX(), bounds.getY());
        bulletPosition.add(CELL_SIZE / 2, CELL_SIZE / 2);
        bulletPosition.add(-999/ 2, -999 / 2);
        bulletPosition.mulAdd(rotationVector, CELL_SIZE / 2);
        return new TankBullet(
                owner,
                new Polygon(),//Util.PolygonFromBullet(TankBullet.class, bulletPosition, rotation),
                999,
                rotation
        );
    }

    @Override
    public TextureRegion textureOfHealth() {
        switch (health) {
            case 1:
                return null; // return Assets.instance.gameAssets.tank;
            default:
                return null;
        }
    }

    @Override
    public void update(float delta) {
        // Nothing happens by default
    }
}