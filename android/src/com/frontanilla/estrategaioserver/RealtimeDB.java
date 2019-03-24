package com.frontanilla.estrategaioserver;

import android.support.annotation.NonNull;
import com.frontanilla.estrategaioserver.interfacing.firebase.RealtimeDBInterface;
import com.frontanilla.estrategaioserver.interfacing.firebase.Request;
import com.frontanilla.estrategaioserver.utils.advanced.OnResultListener;
import com.frontanilla.estrategaioserver.utils.advanced.RealtimeDBOnChangeFetchListener;
import com.frontanilla.estrategaioserver.zones.console.components.map.GridRow;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.*;

import static com.frontanilla.estrategaioserver.utils.globals.Enums.RequestType.ADDITION;
import static com.frontanilla.estrategaioserver.utils.globals.Enums.RequestType.PASS_TURN;

public class RealtimeDB implements RealtimeDBInterface {

    private DatabaseReference[] gridRowReferences;
    private DatabaseReference additionReference, passTurnReference, placementReference;
    private DatabaseReference turnReference;

    RealtimeDB() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        gridRowReferences = new DatabaseReference[20];
        for (int i = 0; i < gridRowReferences.length; i++) {
            if (i < 10) {
                gridRowReferences[i] = database.getReference("Grid/Row0" + i);
            } else {
                gridRowReferences[i] = database.getReference("Grid/Row" + i);
            }
        }
        additionReference = database.getReference("Requests/Addition");
        passTurnReference = database.getReference("Requests/PassTurn");
        placementReference = database.getReference("Requests/Placement");
        turnReference = database.getReference("Turn");
    }

    //-------------------
    // REALTIME FETCHING
    //-------------------
    // Grid Rows
    @Override
    public void fetchGridRowsInRealtime(final RealtimeDBOnChangeFetchListener<GridRow> gridRowListener) {
        for (int i = 0; i < gridRowReferences.length; i++) {
            final int finalI = i;
            gridRowReferences[i].addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String gridRowString = dataSnapshot.getValue(String.class);
                    if (gridRowString == null) {
                        gridRowListener.onFailure("Error in fetchGridRowsInRealtime: Value From DataSnapshot is null");
                    } else {
                        GridRow gridRow = new GridRow(finalI, gridRowString);
                        gridRowListener.onDataFetched(gridRow);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    gridRowListener.onFailure("Error Fetching Grid Rows: " + databaseError.getMessage());
                }
            });
        }
    }

    // Addition Request
    @Override
    public void fetchAdditionRequestInRealtime(final RealtimeDBOnChangeFetchListener<Request> additionRequestListener) {
        additionReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String additionRequestString = dataSnapshot.getValue(String.class);
                if (additionRequestString == null) {
                    additionRequestListener.onFailure("Error in fetchAdditionRequestInRealtime: Value From DataSnapshot is null");
                } else if (additionRequestString.equals("")) {
                    additionRequestListener.onEmpty();
                } else {
                    // Build and Return the Addition Request
                    String[] parts = additionRequestString.split("\\.");
                    Request additionRequest = new Request(ADDITION, parts[0], parts[1]);
                    additionRequestListener.onDataFetched(additionRequest);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                additionRequestListener.onFailure("Error Fetching Addition Request: " + databaseError.getMessage());
            }
        });
    }

    // Pass Turn Request
    @Override
    public void fetchPassTurnRequestInRealtime(final RealtimeDBOnChangeFetchListener<Request> passTurnRequestListener) {
        passTurnReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String passTurnRequestString = dataSnapshot.getValue(String.class);
                if (passTurnRequestString == null) {
                    passTurnRequestListener.onFailure("Error in fetchPassTurnRequestInRealtime: Value From DataSnapshot is null");
                } else if (passTurnRequestString.equals("")) {
                    passTurnRequestListener.onEmpty();
                } else {
                    // Build and Return the Pass Turn Request
                    Request additionRequest = new Request(PASS_TURN, passTurnRequestString, "");
                    passTurnRequestListener.onDataFetched(additionRequest);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                passTurnRequestListener.onFailure("Error Fetching Pass Turn Request: " + databaseError.getMessage());
            }
        });
    }

    // Placement Request
    @Override
    public void fetchPlacementRequestInRealtime(final RealtimeDBOnChangeFetchListener<Request> requestListener) {
        placementReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // TODO
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                requestListener.onFailure("Error Fetching Placement Request: " + databaseError.getMessage());
            }
        });
    }

    @Override
    public void fetchTurnInRealtime(final RealtimeDBOnChangeFetchListener<Integer> turnListener) {
        turnReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // TODO
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                turnListener.onFailure("Error Fetching Turn: " + databaseError.getMessage());
            }
        });
    }

    //-----------
    // MODIFYING
    //-----------
    // Turn
    @Override
    public void modifyTurn(int turn, final OnResultListener listener) {
        turnReference.setValue(turn).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                listener.onResult(task.isSuccessful());
            }
        });
    }

    //------------------------
    // REQUEST FIELD CLEARING
    //------------------------
    // Addition Request
    @Override
    public void clearAdditionRequestField(final OnResultListener onResultListener) {
        additionReference.setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                onResultListener.onResult(task.isSuccessful());
            }
        });
    }

    // Pass Turn Request
    @Override
    public void clearPassTurnRequestField(final OnResultListener onResultListener) {
        passTurnReference.setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                onResultListener.onResult(task.isSuccessful());
            }
        });
    }

    // Placement Request
    @Override
    public void clearPlacementRequestField(final OnResultListener onResultListener) {
        placementReference.setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                onResultListener.onResult(task.isSuccessful());
            }
        });
    }
}
