package com.frontanilla.estrategaioserver.zones.console.components.database;

public class DBRequests {

    private String addition;
    private String passTurn;
    private String placement;

    public DBRequests() {
        addition = "";
        passTurn = "";
        placement = "";
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public String getPassTurn() {
        return passTurn;
    }

    public void setPassTurn(String passTurn) {
        this.passTurn = passTurn;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }
}
