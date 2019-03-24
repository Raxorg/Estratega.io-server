package com.frontanilla.estrategaioserver.interfacing.firebase;

import com.frontanilla.estrategaioserver.utils.globals.Enums.RequestType;

public class Request {

    private RequestType requestType;
    private String playerPhoneID;
    private String data;

    // TODO: DELETE THIS SINCE WE'LL BE USING DATABASE CLONE CLASS
    public Request(RequestType requestType, String playerPhoneID, String data) {
        this.requestType = requestType;
        this.playerPhoneID = playerPhoneID;
        this.data = data;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getPlayerPhoneID() {
        return playerPhoneID;
    }

    public String getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        Request other;
        if (o instanceof Request) {
            other = (Request) o;
        } else {
            return false;
        }
        return other.requestType == requestType && other.playerPhoneID.equals(playerPhoneID) && other.data.equals(data);
    }
}
