package com.frontanilla.estrategaioserver.zones.console.components.entities.cellables.units;

import com.frontanilla.estrategaioserver.zones.console.components.database.DBPlayerDocument;
import com.frontanilla.estrategaioserver.zones.console.components.entities.cellables.Cellable;

public abstract class Unit extends Cellable {

    public Unit(DBPlayerDocument owner) {
        super(owner);
    }
}
