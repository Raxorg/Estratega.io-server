package com.frontanilla.estrategaioserver.zones.console.logic.helpers;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.estrategaioserver.core.ServerApp;
import com.frontanilla.estrategaioserver.interfacing.firebase.Request;
import com.frontanilla.estrategaioserver.utils.helpers.Transform;
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

    void handleRequest(Request request) {
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
                    if (turn == 7) {
                        turn = -1;
                    }
                    consoleFirebase.modifyTurn(turn + 1);
                    return;
                }
            }
        }
        consoleFirebase.clearPassTurnRequestField();
    }

    public void onSuccessModifyingTurn(int turn) {
        // Calculate Last Player's Turn
        int lastPlayerTurn = turn == 0 ? 7 : turn - 1;
        // Get the Players
        DelayedRemovalArray<DBPlayerDocument> playerDocuments;
        playerDocuments = consoleLogic.getDatabaseClone().getDBPlayerData().getPlayerDocuments();
        // Get the Last Turn's Player
        for (int i = 0; i < playerDocuments.size; i++) {
            if (playerDocuments.get(i).getTurn() == lastPlayerTurn) {
                // Player Found, Add Money
                String phoneID = playerDocuments.get(i).getPhoneID();
                int newMoney = playerDocuments.get(i).getMoney() + 4;
                consoleFirebase.modifyPlayerMoney(phoneID, newMoney);
                return;
            }
        }
        ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG, "Failed to Find Last Turn's Player");
    }

    public void onFailureModifyingTurn(int turn) {
        // Log in Logcat
        ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG, "Failed to Modify Turn. Retrying...");
        // Log in "REQUESTS" tab
        consoleStuff.getRequestLog().log("Failed to Modify Turn. Retrying...");
        // Retry
        consoleFirebase.modifyTurn(turn);
    }

    public void onSuccessModifyingPlayerMoney(String phoneID, int money) {
        // Get the Players
        DelayedRemovalArray<DBPlayerDocument> playerDocuments;
        playerDocuments = consoleLogic.getDatabaseClone().getDBPlayerData().getPlayerDocuments();
        // Find the Player
        for (int i = 0; i < playerDocuments.size; i++) {
            if (playerDocuments.get(i).getPhoneID().equals(phoneID)) {
                String playerName = playerDocuments.get(i).getName();
                // Log in Logcat
                ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG,
                        "Success Modifying " + playerName + "'s Money. New Money: " + money);
                // Log in "REQUESTS" tab
                consoleStuff.getRequestLog().log("Success Modifying " + playerName + "'s Money. New Money: " + money,
                        Transform.stringToColor(playerDocuments.get(i).getColor()));
            }
        }
        // Clear Pass Turn Request Field
        consoleFirebase.clearPassTurnRequestField();
    }

    public void onFailureModifyingPlayerMoney(String phoneID, int money) {
        // Log in Logcat
        ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG, "Failed to Modify Player's Money. Retrying...");
        // Log in "REQUESTS" tab
        consoleStuff.getRequestLog().log("Failed to Modify Player's Money. Retrying...");
        // Retry
        consoleFirebase.modifyPlayerMoney(phoneID, money);
    }

    public void onPassTurnRequestFieldCleared() {
        // Log in Logcat
        ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG, "Pass Turn Field Cleared");
        // Log in "REQUESTS" tab
        consoleStuff.getRequestLog().log("Pass Turn Field Cleared");
    }
}