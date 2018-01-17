package org.roadservice.api.trafficsignal.rest;

import org.roadservice.api.trafficsignal.domain.*;
import org.roadservice.api.trafficsignal.service.TrafficMovementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StreetToSignalQueueTransformer {

    @Autowired
    private TrafficMovementService trafficMovementService;
    private static final Logger LOGGER = LoggerFactory.getLogger(StreetCrossingBuilder.class);

    public List<CrossingLane> fromStreetToSignal(StreetCrossing streetCrossing) {
        List<CrossingLane> signalQueue = new ArrayList<>();
        CrossingLane signalLeftToRightStreetWrapper = null;
        CrossingLane signalTopToBottomWrapper = null;
        if (streetCrossing.getLeftAndRightStreets() != null) {
            signalLeftToRightStreetWrapper = streetToWrapper(streetCrossing.getLeftAndRightStreets(), false);
            signalQueue.add(signalLeftToRightStreetWrapper);
        }
        if (streetCrossing.getLeftAndRightStreets() != null) {
            //Make TopToBottom (North to South) as green as per requirements
            signalTopToBottomWrapper = streetToWrapper(streetCrossing.getTopAndBottomStreets(), true);
            signalQueue.add(signalTopToBottomWrapper);
        }
        return signalQueue;
    }

    private CrossingLane streetToWrapper(List<Street> leftAndRightStreets, boolean isGreen) {
        LOGGER.debug("convertin street to wrapper");
        CrossingLane crossingLane = null;
        if (isGreen) {

            crossingLane = new CrossingLane(leftAndRightStreets);
        } else {
            crossingLane = new CrossingLane(leftAndRightStreets);
        }
        LOGGER.debug("converting street to wrapper - done");
        return crossingLane;
    }
}
