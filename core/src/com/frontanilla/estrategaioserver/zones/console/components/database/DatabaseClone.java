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

    public DBGrid getDBGrid() {
        return grid;
    }

    public DBRequests getDBRequests() {
        return requests;
    }

    public DBTurn getDBTurn() {
        return turn;
    }

    public DBPlayerData getDBPlayerData() {
        return playerData;
    }
}
