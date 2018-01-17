package org.roadservice.api.trafficsignal.service;

import org.roadservice.api.trafficsignal.domain.SignalStreetWrapper;
import org.roadservice.api.trafficsignal.rest.SignalStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@Component
public class TrafficMoverService {
    public static final int FIRST_INDEX = 0;
    public static final int NEXT_INDEX = 1;
    public static final int ONE_MORE_CAR = 1;
    private static List<SignalStreetWrapper> signalStreetWrappers = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(TrafficMoverService.class);


    public void addCarsToStreets() {
        SignalStreetWrapper signalStreetWrapperStreetFirst = null;
        SignalStreetWrapper signalStreetWrapperStreetSecond = null;
        if (!CollectionUtils.isEmpty(signalStreetWrappers)) {
            signalStreetWrapperStreetFirst = signalStreetWrappers.get(FIRST_INDEX);
            addCars(signalStreetWrapperStreetFirst);
        }
        //Currently we are handing streets with intersection of signals with square and T points only.
        if (!CollectionUtils.isEmpty(signalStreetWrappers) && signalStreetWrappers.size() > 1) {
            signalStreetWrapperStreetSecond = signalStreetWrappers.get(NEXT_INDEX);
            addCars(signalStreetWrapperStreetSecond);
        }

    }

    public void moveCarsFromStreets() {
        SignalStreetWrapper signalStreetWrapperStreetFirst = null;
        SignalStreetWrapper signalStreetWrapperStreetSecond = null;
        if (!CollectionUtils.isEmpty(signalStreetWrappers)) {
            signalStreetWrapperStreetFirst = signalStreetWrappers.get(FIRST_INDEX);
            moveCars(signalStreetWrapperStreetFirst);
        }
        //Currently we are handing streets with intersection of signals with square and T points only.
        if (!CollectionUtils.isEmpty(signalStreetWrappers) && signalStreetWrappers.size() > 1) {
            signalStreetWrapperStreetSecond = signalStreetWrappers.get(NEXT_INDEX);
            moveCars(signalStreetWrapperStreetSecond);
        }

    }


    private void moveCars(SignalStreetWrapper signalStreetWrapperStreet) {
        if (signalStreetWrapperStreet.getNoOfCars() > 0 && signalStreetWrapperStreet.getSignal().getSignalStatus() != SignalStatus.RED_SIGNAL.getStatus()) {
            signalStreetWrapperStreet.setNoOfCars(signalStreetWrapperStreet.getNoOfCars() - ONE_MORE_CAR);
        }
    }

    public static List<SignalStreetWrapper> getSignalStreetWrappers() {
        return signalStreetWrappers;
    }

    private synchronized void addCars(SignalStreetWrapper signalStreetWrapperStreet) {
        signalStreetWrapperStreet.setNoOfCars(signalStreetWrapperStreet.getNoOfCars() + ONE_MORE_CAR);
    }

    public static synchronized void setSignalStreetWrappers(List<SignalStreetWrapper> signalStreetWrappers) {
        TrafficMoverService.signalStreetWrappers = signalStreetWrappers;
    }
}
