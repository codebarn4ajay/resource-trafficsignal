package org.roadservice.api.trafficsignal.rest;

public enum SignalStatus {
    RED_SIGNAL(1),GREEN_SIGNAL(0);
    private int status;

   private  SignalStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
