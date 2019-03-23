package com.frontanilla.estrategaioserver.oldentities.cellables.units;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.frontanilla.estrategaioserver.firebase.Player;
import com.frontanilla.estrategaioserver.oldentities.bullets.TankBullet;
import com.frontanilla.estrategaioserver.zones.console.components.map.Cell;
import com.frontanilla.estrategaioserver.oldvisualization.logic.Constants;
import com.frontanilla.estrategaioserver.oldvisualization.logic.Util;

import static com.frontanilla.estrategaioserver.oldvisualization.logic.Constants.TANK_REPRESENTATION;
import static com.frontanilla.estrategaioserver.zones.console.constants.ConsoleConstants.CELL_SIZE;

public class Tank extends Unit {

    public Tank(Player player, Cell cell, int health, float rotation) {
        super(player, cell, health, rotation, CELL_SIZE, CELL_SIZE, TANK_REPRESENTATION);
    }

    public TankBullet fire() {
        Vector2 rotationVector = Util.getVector2FromRotation(rotation);
        Vector2 bulletPosition = new Vector2(bounds.getX(), bounds.getY());
        bulletPosition.add(CELL_SIZE / 2, CELL_SIZE / 2);
        bulletPosition.add(-Constants.TANK_BULLET_WIDTH / 2, -Constants.TANK_BULLET_HEIGHT / 2);
        bulletPosition.mulAdd(rotationVector, CELL_SIZE / 2);
        return new TankBullet(
                owner,
                Util.PolygonFromBullet(TankBullet.class, bulletPosition, rotation),
                Constants.TANK_BULLET_INITIAL_HEALTH,
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