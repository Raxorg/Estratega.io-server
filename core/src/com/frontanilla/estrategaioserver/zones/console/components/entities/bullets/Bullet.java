package com.frontanilla.estrategaioserver.zones.console.components.entities.bullets;

import com.frontanilla.estrategaioserver.zones.console.components.database.DBPlayerDocument;
import com.frontanilla.estrategaioserver.zones.console.components.entities.Entity;

public abstract class Bullet extends Entity {

    public Bullet(DBPlayerDocument owner) {
        this.owner = owner;
    }
}
