package com.frontanilla.estrategaioserver.firebase.realtime;

import com.frontanilla.estrategaioserver.firebase.Request;
import com.frontanilla.estrategaioserver.utils.advanced.OnResultListener;
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

    //------------------------
    // Request Field Clearing
    //------------------------

    void clearAdditionRequestField(OnResultListener onResultListener);

    void clearPassTurnRequestField(OnResultListener onResultListener);

    void clearPlacementRequestField(OnResultListener onResultListener);
}
