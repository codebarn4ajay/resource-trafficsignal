package org.roadservice.api.trafficsignal.service;

import org.roadservice.api.trafficsignal.domain.CrossingLane;
import org.roadservice.api.trafficsignal.domain.StreetCrossing;
import org.roadservice.api.trafficsignal.exceptions.InvalidInputException;
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


    public List<CrossingLane> addStreetCrossingData(StreetCrossing streetCrossing) throws InvalidInputException {
       try {
           return streetToSignalQueueTransformer.fromStreetToSignal(streetCrossing);
       }catch (InvalidInputException ie) {
           throw ie;
       }
    }
}
