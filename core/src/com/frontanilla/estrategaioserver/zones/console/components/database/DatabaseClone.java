package com.frontanilla.estrategaioserver.zones.console.components.database;

public class DatabaseClone {

    private DBGrid grid;
    private DBRequests requests;
    private DBTurn turn;
    private DBPlayerData playerData;

    public DatabaseClone() {
        grid = new DBGrid();
        requests = new DBRequests();
        turn = new DBTurn();
        playerData = new DBPlayerData();
    }

    public DBGrid getGrid() {
        return grid;
    }

    public DBRequests getRequests() {
        return requests;
    }

    public DBTurn getTurn() {
        return turn;
    }

    public DBPlayerData getPlayerData() {
        return playerData;
    }
}
