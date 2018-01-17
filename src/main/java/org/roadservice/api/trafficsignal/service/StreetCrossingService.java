package org.roadservice.api.trafficsignal.service;

import org.roadservice.api.trafficsignal.domain.SignalStreetWrapper;
import org.roadservice.api.trafficsignal.domain.StreetCrossing;
import org.roadservice.api.trafficsignal.rest.StreetToSignalQueueTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetCrossingService {
    @Autowired
    private StreetToSignalQueueTransformer streetToSignalQueueTransformer;
    @Autowired
    private TaskSchedulerService taskScheduler;

    public List<SignalStreetWrapper> addStreetCrossingData(StreetCrossing streetCrossing) throws InterruptedException {
        return streetToSignalQueueTransformer.fromStreetToSignal(streetCrossing);
    }
}
