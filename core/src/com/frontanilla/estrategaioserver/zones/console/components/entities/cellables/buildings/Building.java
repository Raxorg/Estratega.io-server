package com.frontanilla.estrategaioserver.zones.console.components.entities.cellables.buildings;

import com.frontanilla.estrategaioserver.firebase.Player;
import com.frontanilla.estrategaioserver.zones.console.components.entities.cellables.Cellable;

public abstract class Building extends Cellable {

    public Building(Player owner) {
        super(owner);
    }
}
