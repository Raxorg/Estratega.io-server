package com.frontanilla.estrategaioserver.zones.console.components.database;

import com.badlogic.gdx.utils.DelayedRemovalArray;

public class DBPlayerData {

    private DelayedRemovalArray<DBPlayerDocument> playerDocuments;

    public DBPlayerData() {
        playerDocuments = new DelayedRemovalArray<>();
    }

    public DelayedRemovalArray<DBPlayerDocument> getPlayerDocuments() {
        return playerDocuments;
    }
}
