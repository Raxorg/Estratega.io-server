package com.frontanilla.estrategaioserver.oldentities.bullets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.frontanilla.estrategaioserver.firebase.Player;
import com.frontanilla.estrategaioserver.utils.helpers.Transform;

public class TankBullet extends Bullet {

    public TankBullet(Player player, Polygon bounds, int health, float rotation) {
        super(player, bounds, health, 999, 999,
                rotation, Transform.rotationToVector2(rotation));
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
        position.mulAdd(velocity, 999 * delta);
        bounds.setPosition(position.x, position.y);
    }
}