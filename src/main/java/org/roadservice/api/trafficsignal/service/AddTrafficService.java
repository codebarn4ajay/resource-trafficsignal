package org.roadservice.api.trafficsignal.service;

import org.roadservice.api.trafficsignal.domain.CrossingLane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@Component
public class AddTrafficService {
    public static final int FIRST_INDEX = 0;
    public static final int NEXT_INDEX = 1;
    public static final int ONE_MORE_CAR = 1;
    private  List<CrossingLane> crossingLanes = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(AddTrafficService.class);


    public void addCarsToStreets() {
        CrossingLane signalStreetWrapperStreetFirst = null;
        CrossingLane signalStreetWrapperStreetSecond = null;
        if (!CollectionUtils.isEmpty(crossingLanes)) {
            signalStreetWrapperStreetFirst = crossingLanes.get(FIRST_INDEX);
            addCars(signalStreetWrapperStreetFirst);
        }
        //Currently we are handing streets with intersection of signals with square and T points only.
        if (!CollectionUtils.isEmpty(crossingLanes) && crossingLanes.size() > 1) {
            signalStreetWrapperStreetSecond = crossingLanes.get(NEXT_INDEX);
            addCars(signalStreetWrapperStreetSecond);
        }

    }

    public  List<CrossingLane> getCrossingLanes() {
        return crossingLanes;
    }

    private  void addCars(CrossingLane signalStreetWrapperStreet) {
        signalStreetWrapperStreet.setNoOfCars(signalStreetWrapperStreet.getNoOfCars() + ONE_MORE_CAR);
    }

    public   void setCrossingLanes(List<CrossingLane> crossingLanes) {
        this.crossingLanes = crossingLanes;
    }
}
