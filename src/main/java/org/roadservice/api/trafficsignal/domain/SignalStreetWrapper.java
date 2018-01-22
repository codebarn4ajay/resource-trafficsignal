package org.roadservice.api.trafficsignal.domain;

import java.util.List;

public class SignalStreetWrapper {
    private List<Street> streets;
    private Signal signal = null;
    private int noOfCars = 0;

    public SignalStreetWrapper(List<Street> streets, Signal signal) {
        this.streets = streets;
        this.signal = signal;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }

    public Signal getSignal() {
        return signal;
    }

    public void setSignal(Signal signal) {
        this.signal = signal;

    }

    public int getNoOfCars() {
        return noOfCars;
    }

    public void setNoOfCars(int noOfCars) {
        this.noOfCars = noOfCars;

    }


    @Override
    public String toString() {
        return "SignalStreetWrapper{" +
                "streets=" + streets.get(0).getName() +
                ", signal=" + signal +
                ", noOfCars=" + noOfCars +
                '}';
    }

}
