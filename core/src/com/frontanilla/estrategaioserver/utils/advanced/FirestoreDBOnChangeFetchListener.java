package com.frontanilla.estrategaioserver.utils.advanced;

public abstract class FirestoreDBOnChangeFetchListener<A> {

    public abstract void onFailure(String message);

    public abstract void onAddition(A data);

    public abstract void onModification(A data);

    public abstract void onRemoval(A data);
}
