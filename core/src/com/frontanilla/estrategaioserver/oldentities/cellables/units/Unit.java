package com.frontanilla.estrategaioserver.oldentities.cellables.units;

import com.frontanilla.estrategaioserver.oldentities.cellables.Cellable;
import com.frontanilla.estrategaioserver.interfacing.firebase.Player;
import com.frontanilla.estrategaioserver.zones.console.components.map.Cell;

public abstract class Unit extends Cellable {

    public Unit(Player player, Cell cell, int health,
                float width, float height, float rotation, int representation) {
        super(player, cell, health, width, height, rotation, representation);
    }
}