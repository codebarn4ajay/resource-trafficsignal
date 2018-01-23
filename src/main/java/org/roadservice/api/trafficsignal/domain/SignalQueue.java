package org.roadservice.api.trafficsignal.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SignalQueue {
    private static SignalQueue signalQueue;
    private static List<List<CrossingLane>> signalsToHandle = new ArrayList();

    private SignalQueue() {

    }

    public static List<List<CrossingLane>> getSignalsToHandle() {
        return signalsToHandle;
    }

    public static void setSignalsToHandle(List<List<CrossingLane>> signalsToHandle) {
        SignalQueue.signalsToHandle = signalsToHandle;
    }

    public static SignalQueue getSignalQueue() {
        return signalQueue;
    }

    public static void setSignalQueue(SignalQueue signalQueue) {
        SignalQueue.signalQueue = signalQueue;
    }

    public static SignalQueue getInstance() {
        if (signalQueue == null) {
            signalQueue = new SignalQueue();
        }
        return signalQueue;
    }


}
