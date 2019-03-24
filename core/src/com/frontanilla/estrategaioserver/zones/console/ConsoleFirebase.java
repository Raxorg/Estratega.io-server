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
                        System.out.println(message + " RETRYING");
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
                System.out.println(message + " RETRYING");
                fetchGridRowsInRealtime();
            }

            @Override
            public void onDataFetched(GridRow gridRow) {
                databaseHandler.updateGridRow(gridRow);
            }

            @Override
            public void onEmpty() {
                // TODO
            }
        });
    }

    // Addition Request
    public void fetchAdditionRequestInRealtime() {
        final DatabaseHandler databaseHandler = ((ConsoleLogic) connector.getLogic()).getDatabaseHandler();
        ServerApp.instance.getRealtimeDBInterface().fetchAdditionRequestInRealtime(new RealtimeDBOnChangeFetchListener<Request>() {
            @Override
            public void onFailure(String message) {
                System.out.println(message + " RETRYING");
                ServerApp.instance.getRealtimeDBInterface().fetchAdditionRequestInRealtime(this);
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
        ServerApp.instance.getRealtimeDBInterface().fetchPassTurnRequestInRealtime(new RealtimeDBOnChangeFetchListener<Request>() {
            @Override
            public void onFailure(String message) {
                System.out.println(message + " RETRYING");
                ServerApp.instance.getRealtimeDBInterface().fetchPassTurnRequestInRealtime(this);
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
        ServerApp.instance.getRealtimeDBInterface().fetchPlacementRequestInRealtime(new RealtimeDBOnChangeFetchListener<Request>() {
            @Override
            public void onFailure(String message) {
                System.out.println(message + " RETRYING");
                ServerApp.instance.getRealtimeDBInterface().fetchPlacementRequestInRealtime(this);
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
                System.out.println(message + " RETRYING");
                fetchTurnInRealtime();
            }

            @Override
            public void onDataFetched(Integer turn) {
                databaseHandler.turnChanged(turn);
            }

            @Override
            public void onEmpty() {
                // TODO
            }
        });
    }

    //-----------------
    // PLAYER ADDITION
    //-----------------
    public void addPlayer(final String phoneID, final Map<String, Object> playerData) {
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
        // TODO
    }

    public void passTurn() {
        // TODO
    }

    public void clearPlacementRequestField() {
        // TODO
    }
}
