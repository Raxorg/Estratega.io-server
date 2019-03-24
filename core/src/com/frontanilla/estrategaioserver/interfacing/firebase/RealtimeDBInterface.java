package com.frontanilla.estrategaioserver.interfacing.firebase;

import com.frontanilla.estrategaioserver.utils.advanced.OnModifyResultListener;
import com.frontanilla.estrategaioserver.utils.advanced.OnResultListener;
import com.frontanilla.estrategaioserver.utils.advanced.RealtimeDBOnChangeFetchListener;
import com.frontanilla.estrategaioserver.zones.console.components.map.GridRow;

public interface RealtimeDBInterface {

    //-------------------
    // Realtime Fetching
    //-------------------

    void fetchGridRowsInRealtime(RealtimeDBOnChangeFetchListener<GridRow> gridRowListener);

    void fetchAdditionRequestInRealtime(RealtimeDBOnChangeFetchListener<Request> additionRequestListener);

    void fetchPassTurnRequestInRealtime(RealtimeDBOnChangeFetchListener<Request> passTurnRequestListener);

    void fetchPlacementRequestInRealtime(RealtimeDBOnChangeFetchListener<Request> placementRequestListener);

    void fetchTurnInRealtime(RealtimeDBOnChangeFetchListener<Integer> turnListener);

    //-----------
    // Modifying
    //-----------

    void modifyTurn(int turn, OnModifyResultListener<Integer> turnListener);

    //------------------------
    // Request Field Clearing
    //------------------------

    void clearAdditionRequestField(OnResultListener onResultListener);

    void clearPassTurnRequestField(OnResultListener onResultListener);

    void clearPlacementRequestField(OnResultListener onResultListener);
}
