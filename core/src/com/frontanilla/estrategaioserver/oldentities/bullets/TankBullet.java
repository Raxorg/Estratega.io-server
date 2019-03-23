package com.frontanilla.estrategaioserver.oldentities.bullets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.frontanilla.estrategaioserver.firebase.Player;
import com.frontanilla.estrategaioserver.oldvisualization.logic.Constants;
import com.frontanilla.estrategaioserver.oldvisualization.logic.Util;

import static com.frontanilla.estrategaioserver.oldvisualization.logic.Constants.TANK_BULLET_HEIGHT;
import static com.frontanilla.estrategaioserver.oldvisualization.logic.Constants.TANK_BULLET_WIDTH;

public class TankBullet extends Bullet {

    public TankBullet(Player player, Polygon bounds, int health, float rotation) {
        super(player, bounds, health, TANK_BULLET_WIDTH, TANK_BULLET_HEIGHT,
                rotation, Util.getVector2FromRotation(rotation));
    }

    @Override
    public TextureRegion textureOfHealth() {
        switch (health) {
            case 1:
                return null; //return Assets.instance.displayAssets.pixel;
            default:
                return null;
        }
    }

    @Override
    public void update(float delta) {
        Vector2 position = new Vector2(bounds.getX(), bounds.getY());
        position.mulAdd(velocity, Constants.BULLET_SPEED * delta);
        bounds.setPosition(position.x, position.y);
    }
}