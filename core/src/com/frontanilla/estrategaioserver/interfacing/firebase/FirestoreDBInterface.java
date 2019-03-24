package com.frontanilla.estrategaioserver.interfacing.firebase;

import com.frontanilla.estrategaioserver.utils.advanced.FirestoreDBOnChangeFetchListener;
import com.frontanilla.estrategaioserver.utils.advanced.OnResultListener;
import com.frontanilla.estrategaioserver.zones.console.components.database.DBPlayerDocument;

import java.util.Map;

public interface FirestoreDBInterface {

    //--------------------
    // REAL TIME FETCHING
    //--------------------

    void fetchPlayersInRealTime(FirestoreDBOnChangeFetchListener<DBPlayerDocument> playersListener);

    void stopFetchingPlayersInRealtime();

    //-----------
    // MODIFYING
    //-----------

    void saveGridRows(String[] rows, OnResultListener listener);

    void savePlayerData(String phoneID, Map<String, Object> playerData, OnResultListener listener);
}
