package org.roadservice.api.trafficsignal.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class CrossingLane {
    private List<Street> streets;
    private int noOfCars = 0;
    private int noOfTimeProcessed = 0;

    public CrossingLane(List<Street> streets) {
        this.streets = streets;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }

    public int getNoOfTimeProcessed() {
        return noOfTimeProcessed;
    }

    public void setNoOfTimeProcessed(int noOfTimeProcessed) {
        this.noOfTimeProcessed = noOfTimeProcessed;
    }

    public int getNoOfCars() {
        return noOfCars;
    }

    public void setNoOfCars(int noOfCars) {
        this.noOfCars = noOfCars;

    }

    @Override
    public String toString() {
        return "CrossingLane{" +
                "streets=" + streets.get(0).getName() +
                ", noOfCars=" + noOfCars +
                '}';
    }


}

