package com.frontanilla.estrategaioserver.zones.console.components.map;

public class GridRow {

    private int row;
    private String[] content;

    public GridRow(int row, String contentString) {
        this.row = row;
        this.content = contentString.split(",");
    }

    public int getRow() {
        return row;
    }

    public String[] getContent() {
        return content;
    }
}
