package com.frontanilla.estrategaioserver.zones.console.components.entities.cellables.buildings;

import com.frontanilla.estrategaioserver.zones.console.components.database.DBPlayerDocument;
import com.frontanilla.estrategaioserver.zones.console.components.entities.cellables.Cellable;

public abstract class Building extends Cellable {

    public Building(DBPlayerDocument owner) {
        super(owner);
    }
}
