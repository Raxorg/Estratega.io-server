package com.frontanilla.estrategaioserver.oldentities.cellables.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frontanilla.estrategaioserver.firebase.Player;
import com.frontanilla.estrategaioserver.oldvisualization.logic.Constants;
import com.frontanilla.estrategaioserver.zones.console.components.map.Cell;

import static com.frontanilla.estrategaioserver.zones.console.constants.ConsoleConstants.CELL_SIZE;

public class Base extends Building {

    public Base(Player player, Cell cell, int health, float rotation) {
        super(player, cell, health, CELL_SIZE, CELL_SIZE, rotation, Constants.BASE_REPRESENTATION);
    }

    @Override
    public TextureRegion textureOfHealth() {
        switch (health) {
            case 1:
                return null; // return Assets.instance.gameAssets.base1;
            case 2:
                return null; // return Assets.instance.gameAssets.base2;
            case 3:
                return null; // return Assets.instance.gameAssets.base3;
            default:
                return null;
        }
    }

    @Override
    public void update(float delta) {
        // Nothing happens by default
    }
}