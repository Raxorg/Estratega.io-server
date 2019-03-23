package com.frontanilla.estrategaioserver.zones.console.components.entities.cellables.units;

import com.frontanilla.estrategaioserver.firebase.Player;
import com.frontanilla.estrategaioserver.zones.console.components.entities.cellables.Cellable;

public abstract class Unit extends Cellable {

    public Unit(Player owner) {
        super(owner);
    }
}
