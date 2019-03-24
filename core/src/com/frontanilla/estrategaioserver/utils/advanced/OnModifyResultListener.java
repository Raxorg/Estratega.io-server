package com.frontanilla.estrategaioserver.utils.advanced;

public abstract class OnModifyResultListener<A> {

    public abstract void onResult(boolean success, A data);
}
