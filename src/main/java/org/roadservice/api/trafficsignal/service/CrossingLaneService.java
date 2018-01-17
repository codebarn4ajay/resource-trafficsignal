package org.roadservice.api.trafficsignal.service;

import org.roadservice.api.trafficsignal.domain.CrossingLane;
import org.roadservice.api.trafficsignal.domain.StreetCrossing;
import org.roadservice.api.trafficsignal.rest.StreetToSignalQueueTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrossingLaneService {
    @Autowired
    private StreetToSignalQueueTransformer streetToSignalQueueTransformer;
    @Autowired
    private TrafficMovementService taskScheduler;
    private static final Logger LOGGER = LoggerFactory.getLogger(CrossingLaneService.class);

    public List<CrossingLane> addStreetCrossingData(StreetCrossing streetCrossing) {
        List<CrossingLane> crossingLanes= streetToSignalQueueTransformer.fromStreetToSignal(streetCrossing);
        return crossingLanes;
    }
}
