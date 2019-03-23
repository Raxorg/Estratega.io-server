package com.frontanilla.estrategaioserver.zones.console.components.entities.cellables.units;

import com.frontanilla.estrategaioserver.interfacing.firebase.Player;
import com.frontanilla.estrategaioserver.zones.console.components.entities.bullets.TankBullet;

public class Tank extends Unit {

    public Tank(Player owner) {
        super(owner);
    }

    public TankBullet shoot() {
        TankBullet newBullet = new TankBullet();
        return newBullet;
    }
}
