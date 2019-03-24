package com.frontanilla.estrategaioserver.zones.console.logic.helpers;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.estrategaioserver.interfacing.firebase.Request;
import com.frontanilla.estrategaioserver.zones.console.ConsoleConnector;
import com.frontanilla.estrategaioserver.zones.console.ConsoleFirebase;
import com.frontanilla.estrategaioserver.zones.console.ConsoleStuff;
import com.frontanilla.estrategaioserver.zones.console.components.database.DBPlayerDocument;
import com.frontanilla.estrategaioserver.zones.console.logic.ConsoleLogic;

public class PassTurnRequestHandler {

    private static final String TAG = "PassTurnRequestHandler";
    // Console
    private ConsoleLogic consoleLogic;
    private ConsoleStuff consoleStuff;
    private ConsoleFirebase consoleFirebase;

    public PassTurnRequestHandler(ConsoleConnector consoleConnector) {
        consoleLogic = (ConsoleLogic) consoleConnector.getLogic();
        consoleStuff = (ConsoleStuff) consoleConnector.getStuff();
        consoleFirebase = (ConsoleFirebase) consoleConnector.getFirebase();
    }

    public void handleRequest(Request request) {
        // Deconstruct the Request
        String requesterPhoneID = request.getPlayerPhoneID();
        // Get the Turn
        int turn = consoleLogic.getDatabaseClone().getDBTurn().getTurn();
        // Get the Players
        DelayedRemovalArray<DBPlayerDocument> playerDocuments;
        playerDocuments = consoleLogic.getDatabaseClone().getDBPlayerData().getPlayerDocuments();
        // Check if it's the Requester's Turn
        for (int i = 0; i < playerDocuments.size; i++) {
            if (playerDocuments.get(i).getPhoneID().equals(requesterPhoneID)) {
                // The Requester is in the Database Clone
                if (playerDocuments.get(i).getTurn() == turn) {
                    // It's the Requester's Turn, Pass it
                    consoleFirebase.passTurn();
                    return;
                }
            }
        }
        consoleFirebase.clearPassTurnRequestField();
    }

    public void onPassTurnRequestFieldCleared() {

    }
}
/*

    private void passTurn(final int turn, final DelayedRemovalArray<Player> players) {
        System.out.println("GameLogic-passTurn");
        // Calculate next turn
        final int nextTurn = turn == players.size - 1 ? 0 : turn + 1;
        // Save changes
        FirestoreDBConnection.getInstance().saveTurn(nextTurn, new OnResultListener() {
            @Override
            public void onResult(boolean success) {
                if (success) {
                    // Update last player's money
                    ((GameWorld) screenWorld).updatePlayerInfo(players, nextTurn);
                    incrementLastPlayerMoney(turn, players);
                } else {
                    FirestoreDBConnection.getInstance().saveTurn(nextTurn, this);
                }
            }
        });
    }

    private void incrementLastPlayerMoney(final int turn, final DelayedRemovalArray<Player> players) {
        System.out.println("GameLogic-incrementLastPlayerMoney");
        final HashMap<String, Object> playerData = new HashMap<>();
        String playerPhoneID = "";
        for (Player player : players) {
            if (player.getTurn() == turn) {
                playerPhoneID = player.getPhoneID();
                playerData.put("color", Util.getStringFromColor(player.getColor()));
                playerData.put("money", player.getMoney() + 4);
                playerData.put("name", player.getName());
                playerData.put("turn", player.getTurn());
                break;
            }
        }
        final String finalPlayerPhoneID = playerPhoneID;
        FirestoreDBConnection.getInstance().savePlayerData(playerPhoneID, playerData, new OnResultListener() {
            @Override
            public void onResult(boolean success) {
                if (success) {
                    // update player info
                    for (Player player : players) {
                        if (player.getPhoneID().equals(finalPlayerPhoneID)) {
                            player.setMoney(player.getMoney() + 4);
                        }
                    }
                    ((GameWorld) screenWorld).updatePlayerInfo(players, turn);
                    passTurnRequestHandled();
                } else {
                    FirestoreDBConnection.getInstance().savePlayerData(finalPlayerPhoneID, playerData, this);
                }
            }
        });
    }
    */