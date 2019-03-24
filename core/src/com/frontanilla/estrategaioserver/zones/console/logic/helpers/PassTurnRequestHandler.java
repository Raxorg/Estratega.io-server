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
        String playerPhoneID = request.getPlayerPhoneID();
        // Get the Turn
        int turn = consoleLogic.getDatabaseClone().getDBTurn().getTurn();
        // Check if it's the Player's Turn

    }

    public void onPassTurnRequestFieldCleared() {

    }
}
/*
//--------------------------------
    //       PASS TURN REQUEST
    //--------------------------------

    private void handlePassTurnRequest(final String passTurn) {
        System.out.println("GameLogic-handlePassTurnRequest");
        // TODO Process the Pass Turn String
//        if (passTurn) {
//            // TODO make tanks fire xd
//            // Get the current turn
//            FirestoreDBConnection.getInstance().fetchTurn(new FetchListener<Integer>() {
//                @Override
//                public void onDataFetched(final Integer turn) {
//                    // Get the players to know how to increment the turn
//                    FirestoreDBConnection.getInstance().fetchPlayers(new FetchListener<ArrayList<Player>>() {
//                        @Override
//                        public void onDataFetched(ArrayList<Player> players) {
//                            ((GameWorld) screenWorld).getGrid().update(players);
//                            ((GameWorld) screenWorld).updatePlayerInfo(players, turn);
//                            passTurn(turn, players);
//                        }
//                    });
//                }
//            });
//        } else {
//            handlingPassTurnRequest = false;
//        }
    }

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

    private void passTurnRequestHandled() {
        FirestoreDBConnection.getInstance().passTurnRequestHandled(new OnResultListener() {
            @Override
            public void onResult(boolean success) {
                if (success) {
                    System.out.println("Pass turn request set to false");
                    handlingPassTurnRequest = false;
                } else {
                    System.out.println("Reattempting to false pass turn");
                    FirestoreDBConnection.getInstance().passTurnRequestHandled(this);
                }
            }
        });
    }
    */