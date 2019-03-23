package com.frontanilla.estrategaioserver.interfacing.firebase.realtime;

public abstract class RealtimeDBOnChangeFetchListener<A> {

    public abstract void onFailure(String message);

    public abstract void onDataFetched(A data);

    public abstract void onEmpty();
}
