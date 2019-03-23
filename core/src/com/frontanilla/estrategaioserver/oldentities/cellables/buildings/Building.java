package com.frontanilla.estrategaioserver.oldentities.cellables.buildings;

import com.frontanilla.estrategaioserver.interfacing.firebase.Player;
import com.frontanilla.estrategaioserver.oldentities.cellables.Cellable;
import com.frontanilla.estrategaioserver.zones.console.components.map.Cell;

public abstract class Building extends Cellable {

    public Building(Player player, Cell cell, int health, float width, float height,
                    float rotation, int representation) {
        super(player, cell, health, width, height, rotation, representation);
    }
}
