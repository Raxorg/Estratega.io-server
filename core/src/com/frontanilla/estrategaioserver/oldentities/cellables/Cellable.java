package com.frontanilla.estrategaioserver.oldentities.cellables;

import com.frontanilla.estrategaioserver.firebase.Player;
import com.frontanilla.estrategaioserver.oldentities.Entity;
import com.frontanilla.estrategaioserver.zones.console.components.map.Cell;
import com.frontanilla.estrategaioserver.oldvisualization.logic.Util;

public abstract class Cellable extends Entity {

    protected Cell cell;
    protected int representation;

    public Cellable(Player owner, Cell cell, int health, float width, float height,
                    float rotation, int representation) {
        super(owner, Util.PolygonFromCell(cell), health, width, height, rotation);
        this.cell = cell;
        this.representation = representation;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getRepresentation() {
        return representation;
    }
}