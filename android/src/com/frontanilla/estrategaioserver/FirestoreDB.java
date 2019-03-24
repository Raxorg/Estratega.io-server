package com.frontanilla.estrategaioserver;

import android.support.annotation.NonNull;
import com.frontanilla.estrategaioserver.interfacing.firebase.FirestoreDBInterface;
import com.frontanilla.estrategaioserver.utils.advanced.FirestoreDBOnChangeFetchListener;
import com.frontanilla.estrategaioserver.utils.advanced.OnResultListener;
import com.frontanilla.estrategaioserver.utils.helpers.Transform;
import com.frontanilla.estrategaioserver.zones.console.components.database.DBPlayerDocument;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.*;

import java.util.HashMap;
import java.util.Map;

class FirestoreDB implements FirestoreDBInterface {

    private AndroidLauncher androidLauncher;
    // Database
    private DocumentReference gridReference;
    private DocumentReference requestsReference;
    private DocumentReference turnReference;
    private CollectionReference playerDataReference;
    private ListenerRegistration playerDataListenerRegistration;

    FirestoreDB(AndroidLauncher androidLauncher) {
        this.androidLauncher = androidLauncher;
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        gridReference = database.document("gameData/grid");
        requestsReference = database.document("gameData/requests");
        turnReference = database.document("gameData/turn");
        playerDataReference = database.collection("playerData");
    }

    //--------------------
    // REAL TIME FETCHING
    //--------------------
    @Override
    public void fetchPlayersInRealTime(final FirestoreDBOnChangeFetchListener<DBPlayerDocument> playersListener) {
        playerDataListenerRegistration = playerDataReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    playersListener.onFailure("Listen failed. " + e.getMessage());
                    return;
                }
                for (DocumentChange documentChange : documentSnapshots.getDocumentChanges()) {
                    DocumentSnapshot documentSnapshot = documentChange.getDocument();
                    if (documentSnapshot.getMetadata().isFromCache()) {
                        androidLauncher.clearApplicationData();
                    }
                    Map<String, Object> documentData = documentSnapshot.getData();
                    if (documentData != null) {
                        DBPlayerDocument playerDocument;
                        playerDocument = Transform.snapshotDataToPlayerDocument(documentSnapshot.getId(), documentData);
                        switch (documentChange.getType()) {
                            case ADDED:
                                playersListener.onAddition(playerDocument);
                                break;
                            case MODIFIED:
                                playersListener.onModification(playerDocument);
                                break;
                            case REMOVED:
                                playersListener.onRemoval(playerDocument);
                                break;
                        }
                    }
                }
            }
        });
    }

    @Override
    public void stopFetchingPlayersInRealtime() {
        if (playerDataListenerRegistration != null) {
            playerDataListenerRegistration.remove();
        }
    }

    //--------------------------------
    //         SAVING CHANGES
    //--------------------------------

    @Override
    public void saveGridRows(String[] gridRows, final OnResultListener listener) {
        Map<String, Object> gridRowsMap = new HashMap<>();
        for (int i = 0; i < gridRows.length; i++) {
            String rowNumber = i < 10 ? "0" + i : "" + i;
            gridRowsMap.put("row" + rowNumber, gridRows[i]);
        }
        gridReference.set(gridRowsMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                listener.onResult(task.isSuccessful());
            }
        });
    }

    @Override
    public void saveTurn(int turn, final OnResultListener listener) {
        Map<String, Object> turnMap = new HashMap<>();
        turnMap.put("turn", turn);
        turnReference.set(turnMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                listener.onResult(task.isSuccessful());
            }
        });
    }

    @Override
    public void savePlayerData(String phoneID, Map<String, Object> playerData, final OnResultListener listener) {
        playerDataReference.document(phoneID).set(playerData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                listener.onResult(task.isSuccessful());
            }
        });
    }

    //------------------------
    // HANDLING
    //------------------------

    @Override
    public void additionRequestsHandled(final String[] requests, final OnResultListener listener) {
        requestsReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String additionRequests = documentSnapshot.getString("additions");
                String passTurnRequest = documentSnapshot.getString("passTurn");
                String placementRequest = documentSnapshot.getString("placement");
                for (String request : requests) {
                    if (request.contains(".")) {
                        additionRequests = additionRequests.replace(request + ",", "");
                    }
                }
                Map<String, Object> requestsMap = new HashMap<>();
                requestsMap.put("additions", additionRequests);
                requestsMap.put("passTurn", passTurnRequest);
                requestsMap.put("placement", placementRequest);
                requestsReference.set(requestsMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        listener.onResult(task.isSuccessful());
                    }
                });
            }
        });
    }

    @Override
    public void passTurnRequestHandled(final OnResultListener listener) {
        requestsReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String additionRequests = documentSnapshot.getString("additions");
                String passTurnRequest = documentSnapshot.getString("passTurn");
                String placementRequest = documentSnapshot.getString("placement");
                Map<String, Object> requestsMap = new HashMap<>();
                requestsMap.put("additions", additionRequests);
                requestsMap.put("passTurn", "");
                requestsMap.put("placement", placementRequest);
                requestsReference.set(requestsMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        listener.onResult(task.isSuccessful());
                    }
                });
            }
        });
    }

    @Override
    public void placementRequestHandled(final String[] requests, final OnResultListener listener) {
        requestsReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String additionRequests = documentSnapshot.getString("additions");
                String passTurnRequest = documentSnapshot.getString("passTurn");
                String placementRequest = documentSnapshot.getString("placement");
                for (String request : requests) {
                    if (request.contains(".")) {
                        placementRequest = placementRequest.replace(request + ",", "");
                    }
                }
                Map<String, Object> requestsMap = new HashMap<>();
                requestsMap.put("additions", additionRequests);
                requestsMap.put("passTurn", passTurnRequest);
                requestsMap.put("placement", placementRequest);
                requestsReference.set(requestsMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        listener.onResult(task.isSuccessful());
                    }
                });
            }
        });
    }
}
