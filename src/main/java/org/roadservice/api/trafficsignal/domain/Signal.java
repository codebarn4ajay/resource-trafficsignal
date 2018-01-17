package org.roadservice.api.trafficsignal.domain;

import org.roadservice.api.trafficsignal.rest.SignalStatus;
import org.roadservice.api.trafficsignal.utils.Observable;
import org.roadservice.api.trafficsignal.utils.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Signal implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private long id;
    private int signalStatus;
    private static final Logger LOGGER = LoggerFactory.getLogger(Signal.class);
    private boolean redToGreen = false;
    private boolean firstNotificationSent = false;

    public Signal(long id, int signalStatus) {
        this.id = id;
        this.signalStatus = signalStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSignalStatus() {
        return signalStatus;
    }

    @Override
    public String toString() {
        return "Signal{" +
                "id=" + id +
                ", signalStatus=" + signalStatus +
                '}';
    }


    public void setSignalStatus(int signalStatus) throws InterruptedException {
        if (this.signalStatus == SignalStatus.RED_SIGNAL.getStatus() && signalStatus == SignalStatus.GREEN_SIGNAL.getStatus()) {
            redToGreen = true;
        } else {
            redToGreen = false;
        }
        int greenSignalWait = firstNotificationSent ? 2000 : 4000;
        int redSignalWait = firstNotificationSent ? 4000 : 2000;

        if (signalStatus == SignalStatus.RED_SIGNAL.getStatus()) {
            Thread.sleep(redSignalWait);
            this.signalStatus = signalStatus;
            notifyObserver();
            Thread.sleep(3000);
            setSignalStatus(SignalStatus.GREEN_SIGNAL.getStatus());

        } else if (signalStatus == SignalStatus.GREEN_SIGNAL.getStatus()) {
            Thread.sleep(greenSignalWait);
            this.signalStatus = signalStatus;
            notifyObserver();
            Thread.sleep(2000);
            setSignalStatus(SignalStatus.RED_SIGNAL.getStatus());
        }
    }

    public boolean isRedToGreen() {
        return redToGreen;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);

    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        firstNotificationSent = true;
        for (Observer observer : observers) {
            observer.update(signalStatus, redToGreen, id);
        }
    }
}
