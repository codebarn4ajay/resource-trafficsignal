package org.roadservice.api.trafficsignal.service;

import org.roadservice.api.trafficsignal.domain.SignalStreetWrapper;
import org.roadservice.api.trafficsignal.rest.SignalStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static org.roadservice.api.trafficsignal.service.TrafficMoverService.FIRST_INDEX;
import static org.roadservice.api.trafficsignal.service.TrafficMoverService.NEXT_INDEX;

@Service
public class SignalService {
    private static List<SignalStreetWrapper> signalStreetWrappers = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(SignalService.class);

    public static List<SignalStreetWrapper> getSignalStreetWrappers() {
        return signalStreetWrappers;
    }

    public static void setSignalStreetWrappers(List<SignalStreetWrapper> signalStreetWrappers) {
        SignalService.signalStreetWrappers = signalStreetWrappers;
    }

    @Async
    public void executeSignalSequenceRed() {
        SignalStreetWrapper signalStreetWrapperStreetFirst;
        if (!CollectionUtils.isEmpty(signalStreetWrappers)) {
            signalStreetWrapperStreetFirst = signalStreetWrappers.get(NEXT_INDEX);
            try {
                changeSignalRed(signalStreetWrapperStreetFirst);
            } catch (InterruptedException intException) {
                LOGGER.error(intException.getMessage());
            }
        }
    }

    @Async
    public void executeSignalSequenceGreen() {
        SignalStreetWrapper signalStreetWrapperStreetSecond;//Currently we are handing streets with intersection of signals with square and T points only.
        if (!CollectionUtils.isEmpty(signalStreetWrappers) && signalStreetWrappers.size() > 1) {
            signalStreetWrapperStreetSecond = signalStreetWrappers.get(FIRST_INDEX);
            try {
                changeSignalGreen(signalStreetWrapperStreetSecond);
            } catch (InterruptedException intException) {
                LOGGER.error(intException.getMessage());
            }
        }
    }


    private void changeSignalGreen(SignalStreetWrapper signalStreetWrapperStreetFirst) throws InterruptedException {
        signalStreetWrapperStreetFirst.getSignal().setSignalStatus(SignalStatus.GREEN_SIGNAL.getStatus());
    }


    private void changeSignalRed(SignalStreetWrapper signalStreetWrapperStreetFirst) throws InterruptedException {
        signalStreetWrapperStreetFirst.getSignal().setSignalStatus(SignalStatus.RED_SIGNAL.getStatus());
    }
}
