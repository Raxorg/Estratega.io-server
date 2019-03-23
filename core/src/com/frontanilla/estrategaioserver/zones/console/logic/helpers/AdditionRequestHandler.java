package com.frontanilla.estrategaioserver.zones.console.logic.helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.frontanilla.estrategaioserver.core.ServerApp;
import com.frontanilla.estrategaioserver.interfacing.firebase.Player;
import com.frontanilla.estrategaioserver.interfacing.firebase.Request;
import com.frontanilla.estrategaioserver.utils.helpers.Transform;
import com.frontanilla.estrategaioserver.zones.console.ConsoleFirebase;
import com.frontanilla.estrategaioserver.zones.console.ConsoleStuff;

import java.util.HashMap;
import java.util.Map;

public class AdditionRequestHandler {

    private static final String TAG = "AdditionRequestHandler";
    // Console
    private ConsoleStuff consoleStuff;
    private ConsoleFirebase consoleFirebase;

    public AdditionRequestHandler(ConsoleStuff consoleStuff, ConsoleFirebase consoleFirebase) {
        this.consoleStuff = consoleStuff;
        this.consoleFirebase = consoleFirebase;
    }

    public void handleRequest(Request request) {
        // Deconstruct the Request
        String[] requestParts = request.getData().split(",");
        String playerPhoneID = request.getPlayerPhoneID();
        String requestedName = requestParts[0];
        String requestedColorString = requestParts[1];
        // Transform Requested Color String to Color
        Color requestedColor = Transform.stringToColor(requestedColorString);
        // Get the Players
        DelayedRemovalArray<Player> players = consoleStuff.getPlayerList().getPlayers();
        // Check if the Requested Color and Name are Available
        for (int i = 0; i < players.size; i++) {
            if (players.get(i).getColor() == requestedColor) {
                consoleStuff.getRequestLog().log(
                        "Addition failed: PhoneID: " + playerPhoneID + "Color unavailable: " + requestedColorString);
                consoleFirebase.clearAdditionRequestField();
                return;
            }
            if (players.get(i).getName().equals(requestedName)) {
                consoleStuff.getRequestLog().log(
                        "Addition Failed: PhoneID: " + playerPhoneID + "Name unavailable: " + requestedName);
                consoleFirebase.clearAdditionRequestField();
                return;
            }
        }
        // Color and Name are Available, Add the Player
        Map<String, Object> newPlayerData = new HashMap<>();
        newPlayerData.put("color", requestedColorString);
        newPlayerData.put("money", 10);
        newPlayerData.put("name", requestedName);
        newPlayerData.put("turn", players.size);
        consoleFirebase.addPlayer(playerPhoneID, newPlayerData);
    }

    public void onSuccessAddingPlayer(String phoneID, Map playerData) {
        // Log in Logcat
        ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG,
                "Success adding a new player for " + phoneID);
        // Log in "REQUESTS" tab
        consoleStuff.getRequestLog().log(
                "Success Adding Player for " + phoneID,
                Transform.stringToColor((String) playerData.get("color")));
        // Clear Addition Request Field
        consoleFirebase.clearAdditionRequestField();
    }

    public void onFailureAddingPlayer(String phoneID, Map<String, Object> playerData) {
        // Log in Logcat
        ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG,
                "Failed to add a new player for " + phoneID + ". Retrying...");
        // Log in "REQUESTS" tab
        consoleStuff.getRequestLog().log("Failed to add a new player for " + phoneID + ". Retrying...");
        consoleFirebase.addPlayer(phoneID, playerData);
    }

    public void onAdditionRequestFieldCleared() {
        // TODO
    }
}
