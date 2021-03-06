package com.frontanilla.estrategaioserver.zones.console;

import com.frontanilla.estrategaioserver.core.ServerApp;
import com.frontanilla.estrategaioserver.interfacing.firebase.Request;
import com.frontanilla.estrategaioserver.utils.advanced.FirestoreDBOnChangeFetchListener;
import com.frontanilla.estrategaioserver.utils.advanced.OnResultListener;
import com.frontanilla.estrategaioserver.utils.advanced.RealtimeDBOnChangeFetchListener;
import com.frontanilla.estrategaioserver.zones.console.components.database.DBPlayerDocument;
import com.frontanilla.estrategaioserver.zones.console.components.map.GridRow;
import com.frontanilla.estrategaioserver.zones.console.logic.ConsoleLogic;
import com.frontanilla.estrategaioserver.zones.console.logic.helpers.AdditionRequestHandler;
import com.frontanilla.estrategaioserver.zones.console.logic.helpers.DatabaseHandler;
import com.frontanilla.estrategaioserver.zones.console.logic.helpers.PassTurnRequestHandler;
import com.frontanilla.estrategaioserver.zones.console.logic.helpers.PlacementRequestHandler;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneConnector;
import com.frontanilla.estrategaioserver.zones.foundations.ZoneFirebase;

import java.util.Map;

public class ConsoleFirebase extends ZoneFirebase {

    private static final String TAG = "ConsoleFirebase";

    ConsoleFirebase(ZoneConnector connector) {
        super(connector);
    }

    //-------------------
    // REALTIME FETCHING
    //-------------------
    // Players
    public void fetchPlayersInRealtime() {
        final DatabaseHandler databaseHandler = ((ConsoleLogic) connector.getLogic()).getDatabaseHandler();
        ServerApp.instance.getFirestoreDBInterface().fetchPlayersInRealTime(
                new FirestoreDBOnChangeFetchListener<DBPlayerDocument>() {
                    @Override
                    public void onFailure(String message) {
                        ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG, message + " Retrying...");
                        fetchPlayersInRealtime();
                    }

                    @Override
                    public void onAddition(DBPlayerDocument playerDocument) {
                        databaseHandler.addPlayer(playerDocument);
                    }

                    @Override
                    public void onModification(DBPlayerDocument playerDocument) {
                        databaseHandler.modifyPlayer(playerDocument);
                    }

                    @Override
                    public void onRemoval(DBPlayerDocument dbPlayerDocument) {
                        databaseHandler.removePlayer(dbPlayerDocument);
                    }
                });
    }

    // Grid Rows
    public void fetchGridRowsInRealtime() {
        final DatabaseHandler databaseHandler = ((ConsoleLogic) connector.getLogic()).getDatabaseHandler();
        ServerApp.instance.getRealtimeDBInterface().fetchGridRowsInRealtime(new RealtimeDBOnChangeFetchListener<GridRow>() {
            @Override
            public void onFailure(String message) {
                ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG, message + " Retrying...");
                fetchGridRowsInRealtime();
            }

            @Override
            public void onDataFetched(GridRow gridRow) {
                databaseHandler.updateGridRow(gridRow);
            }

            @Override
            public void onEmpty() {
                ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG, "Empty result from fetchGridRowsInRealtime");
            }
        });
    }

    // Addition Request
    public void fetchAdditionRequestInRealtime() {
        final DatabaseHandler databaseHandler = ((ConsoleLogic) connector.getLogic()).getDatabaseHandler();
        ServerApp.instance.getRealtimeDBInterface().fetchAdditionRequestInRealtime(
                new RealtimeDBOnChangeFetchListener<Request>() {
                    @Override
                    public void onFailure(String message) {
                        ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG, message + " Retrying...");
                        fetchAdditionRequestInRealtime();
                    }

                    @Override
                    public void onDataFetched(Request additionRequest) {
                        databaseHandler.addRequest(additionRequest);
                    }

                    @Override
                    public void onEmpty() {
                        // TODO
                    }
                });
    }

    // Pass Turn
    public void fetchPassTurnRequestInRealtime() {
        final DatabaseHandler databaseHandler = ((ConsoleLogic) connector.getLogic()).getDatabaseHandler();
        ServerApp.instance.getRealtimeDBInterface().fetchPassTurnRequestInRealtime(
                new RealtimeDBOnChangeFetchListener<Request>() {
                    @Override
                    public void onFailure(String message) {
                        ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG, message + " Retrying...");
                        fetchPassTurnRequestInRealtime();
                    }

                    @Override
                    public void onDataFetched(Request passTurn) {
                        databaseHandler.addRequest(passTurn);
                    }

                    @Override
                    public void onEmpty() {
                        // TODO
                    }
                });
    }

    // Placement
    public void fetchPlacementRequestInRealtime() {
        final DatabaseHandler databaseHandler = ((ConsoleLogic) connector.getLogic()).getDatabaseHandler();
        ServerApp.instance.getRealtimeDBInterface().fetchPlacementRequestInRealtime(
                new RealtimeDBOnChangeFetchListener<Request>() {
                    @Override
                    public void onFailure(String message) {
                        ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG, message + " Retrying...");
                        fetchPlacementRequestInRealtime();
                    }

                    @Override
                    public void onDataFetched(Request placementRequest) {
                        databaseHandler.addRequest(placementRequest);
                    }

                    @Override
                    public void onEmpty() {
                        // TODO
                    }
                });
    }

    // Turn
    public void fetchTurnInRealtime() {
        final DatabaseHandler databaseHandler = ((ConsoleLogic) connector.getLogic()).getDatabaseHandler();
        ServerApp.instance.getRealtimeDBInterface().fetchTurnInRealtime(new RealtimeDBOnChangeFetchListener<Integer>() {
            @Override
            public void onFailure(String message) {
                ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG, message + " Retrying...");
                fetchTurnInRealtime();
            }

            @Override
            public void onDataFetched(Integer turn) {
                databaseHandler.turnChanged(turn);
            }

            @Override
            public void onEmpty() {
                ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG, "Empty result from fetchTurnInRealtime");
            }
        });
    }

    //-----------------
    // PLAYER ADDITION
    //-----------------
    public void addPlayerDocument(final String phoneID, final Map<String, Object> playerData) {
        final AdditionRequestHandler additionRequestHandler = ((ConsoleLogic) connector.getLogic()).getAdditionRequestHandler();
        ServerApp.instance.getFirestoreDBInterface().savePlayerData(phoneID, playerData, new OnResultListener() {
            @Override
            public void onResult(boolean success) {
                if (success) {
                    additionRequestHandler.onSuccessAddingPlayer(phoneID, playerData);
                } else {
                    additionRequestHandler.onFailureAddingPlayer(phoneID, playerData);
                }
            }
        });
    }

    //-------------
    // MODIFY TURN
    //-------------
    public void modifyTurn(final int turn) {
        final PassTurnRequestHandler passTurnRequestHandler = ((ConsoleLogic) connector.getLogic()).getPassTurnRequestHandler();
        ServerApp.instance.getRealtimeDBInterface().modifyTurn(turn, new OnResultListener() {
            @Override
            public void onResult(boolean success) {
                if (success) {
                    passTurnRequestHandler.onSuccessModifyingTurn(turn);
                } else {
                    passTurnRequestHandler.onFailureModifyingTurn(turn);
                }
            }
        });
    }

    //---------------------
    // MODIFY PLAYER MONEY
    //---------------------
    public void modifyPlayerMoney(final String phoneID, final int money) {
        final PassTurnRequestHandler passTurnRequestHandler = ((ConsoleLogic) connector.getLogic()).getPassTurnRequestHandler();
        ServerApp.instance.getFirestoreDBInterface().modifyPlayerMoney(phoneID, money, new OnResultListener() {
            @Override
            public void onResult(boolean success) {
                if (success) {
                    passTurnRequestHandler.onSuccessModifyingPlayerMoney(phoneID, money);
                } else {
                    passTurnRequestHandler.onFailureModifyingPlayerMoney(phoneID, money);
                }
            }
        });
    }

    //------------------------
    // REQUEST FIELD CLEARING
    //------------------------
    public void clearAdditionRequestField() {
        final AdditionRequestHandler additionRequestHandler = ((ConsoleLogic) connector.getLogic()).getAdditionRequestHandler();
        ServerApp.instance.getRealtimeDBInterface().clearAdditionRequestField(new OnResultListener() {
            @Override
            public void onResult(boolean success) {
                if (success) {
                    additionRequestHandler.onAdditionRequestFieldCleared();
                } else {
                    // Log
                    ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG,
                            "Failed to clear Addition Request field. Retrying...");
                    // Retry
                    clearAdditionRequestField();
                }
            }
        });
    }

    public void clearPassTurnRequestField() {
        final PassTurnRequestHandler passTurnRequestHandler = ((ConsoleLogic) connector.getLogic()).getPassTurnRequestHandler();
        ServerApp.instance.getRealtimeDBInterface().clearPassTurnRequestField(new OnResultListener() {
            @Override
            public void onResult(boolean success) {
                if (success) {
                    passTurnRequestHandler.onPassTurnRequestFieldCleared();
                } else {
                    // Log
                    ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG,
                            "Failed to clear Pass Turn Request field. Retrying...");
                    // Retry
                    clearAdditionRequestField();
                }
            }
        });
    }

    public void clearPlacementRequestField() {
        final PlacementRequestHandler placementRequestHandler;
        placementRequestHandler = ((ConsoleLogic) connector.getLogic()).getPlacementRequestHandler();
        ServerApp.instance.getRealtimeDBInterface().clearPlacementRequestField(new OnResultListener() {
            @Override
            public void onResult(boolean success) {
                if (success) {
                    placementRequestHandler.onPlacementRequestFieldCleared();
                } else {
                    // Log
                    ServerApp.instance.getDebugLoggerInterface().debugInfo(TAG,
                            "Failed to clear Placement Request field. Retrying...");
                    // Retry
                    clearAdditionRequestField();
                }
            }
        });
    }
}
