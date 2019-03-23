package com.frontanilla.estrategaioserver.interfacing.firebase.firestore;

import com.frontanilla.estrategaioserver.interfacing.firebase.Player;
import com.frontanilla.estrategaioserver.utils.advanced.OnResultListener;

import java.util.Map;

public interface FirestoreDBInterface {

    //--------------------
    // REAL TIME FETCHING
    //--------------------

    void fetchPlayersInRealTime(FirestoreDBOnChangeFetchListener<Player> playersListener);

    void stopFetchingPlayersInRealtime();

    //-----------
    // MODIFYING
    //-----------

    void saveGridRows(String[] rows, OnResultListener listener);

    void saveTurn(int turn, OnResultListener listener);

    void savePlayerData(String phoneID, Map<String, Object> playerData, OnResultListener listener);

    //------------------------
    // REQUEST FIELD CLEARING
    //------------------------

    void additionRequestsHandled(String[] requests, OnResultListener listener);

    void passTurnRequestHandled(OnResultListener listener);

    void placementRequestHandled(String[] requests, OnResultListener listener);
}
