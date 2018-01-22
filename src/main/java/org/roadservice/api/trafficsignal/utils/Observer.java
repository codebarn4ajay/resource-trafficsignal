package org.roadservice.api.trafficsignal.utils;

public interface Observer {
    public void update(int signalStatus, boolean redToGreen,long signalID);
}
