package com.frontanilla.estrategaioserver.zones.console.logic.helpers;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.estrategaioserver.interfacing.firebase.Request;
import com.frontanilla.estrategaioserver.zones.console.ConsoleFirebase;
import com.frontanilla.estrategaioserver.zones.console.ConsoleStuff;
import com.frontanilla.estrategaioserver.zones.console.components.database.DBPlayerDocument;
import com.frontanilla.estrategaioserver.zones.console.components.map.GridRow;
import com.frontanilla.estrategaioserver.zones.console.logic.ConsoleLogic;

public class DatabaseHandler {

    private ConsoleLogic consoleLogic;
    private ConsoleStuff consoleStuff;
    private ConsoleFirebase consoleFirebase;

    public DatabaseHandler(ConsoleLogic consoleLogic, ConsoleStuff consoleStuff, ConsoleFirebase consoleFirebase) {
        this.consoleLogic = consoleLogic;
        this.consoleStuff = consoleStuff;
        this.consoleFirebase = consoleFirebase;
    }

    public void startListeningToDatabasesInRealTime() {
        // Start Listening to Changes in Players
        consoleFirebase.fetchPlayersInRealtime();
        // Start Listening to Changes in the Grid Rows
        consoleFirebase.fetchGridRowsInRealtime();
        // Start Listening to Changes in the Requests
        consoleFirebase.fetchAdditionRequestInRealtime();
        consoleFirebase.fetchPassTurnRequestInRealtime();
        consoleFirebase.fetchPlacementRequestInRealtime();
        // Start Listening to Changes in the Turn
        consoleFirebase.fetchTurnInRealtime();
    }

    public void addPlayer(DBPlayerDocument playerDocument) {
        consoleLogic.getDatabaseClone().getPlayerData().getPlayerDocuments().add(playerDocument);
        consoleStuff.getPlayerList().updateTexts();
    }

    public void modifyPlayer(DBPlayerDocument playerDocument) {
        DelayedRemovalArray<DBPlayerDocument> playerDocuments;
        playerDocuments = consoleLogic.getDatabaseClone().getPlayerData().getPlayerDocuments();
        for (int i = 0; i < playerDocuments.size; i++) {
            if (playerDocuments.get(i).getPhoneID().equals(playerDocument.getPhoneID())) {
                playerDocuments.get(i).set(playerDocument);
                break;
            }
        }
        consoleStuff.getPlayerList().updateTexts();
    }

    public void removePlayer(DBPlayerDocument playerDocument) {
        DelayedRemovalArray<DBPlayerDocument> playerDocuments;
        playerDocuments = consoleLogic.getDatabaseClone().getPlayerData().getPlayerDocuments();
        playerDocuments.begin();
        playerDocuments.removeValue(playerDocument, false);
        playerDocuments.end();
        consoleStuff.getPlayerList().updateTexts();
    }

    public void updateGridRow(GridRow gridRow) {
        // TODO
    }

    public void addRequest(Request request) {
        consoleStuff.getRequestLog().log(request.getRequestType() + request.getPlayerPhoneID() + request.getData());
        switch (request.getRequestType()) {
            case ADDITION:
                consoleLogic.getAdditionRequestHandler().handleRequest(request);
                break;
            case PASS_TURN:
                consoleLogic.getPassTurnRequestHandler().handleRequest(request);
                break;
            case PLACEMENT:
                consoleLogic.getPlacementRequestHandler().handleRequest(request);
                break;
        }
    }

    public void turnChanged(int newTurn) {
        // TODO
    }
}
