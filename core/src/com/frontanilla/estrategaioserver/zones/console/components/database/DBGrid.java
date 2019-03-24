package com.frontanilla.estrategaioserver.zones.console.components.database;

public class DBGrid {

    private String[] rows;

    public DBGrid() {
        rows = new String[20];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = "0000,0000,0000,0000,0000,0000,0000,0000,0000,0000," +
                    "0000,0000,0000,0000,0000,0000,0000,0000,0000,0000," +
                    "0000,0000,0000,0000,0000,0000,0000,0000,0000,0000," +
                    "0000,0000,0000,0000,0000,0000,0000,0000,0000,0000";
        }
    }

    public String getRow(int index) {
        return rows[index];
    }

    public void setRow(int index, String row) {
        rows[index] = row;
    }
}
