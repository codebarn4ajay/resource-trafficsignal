package org.roadservice.api.trafficsignal.service;

import org.roadservice.api.trafficsignal.domain.SignalStreetWrapper;
import org.roadservice.api.trafficsignal.rest.SignalStatus;
import org.roadservice.api.trafficsignal.utils.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.roadservice.api.trafficsignal.service.TrafficMoverService.FIRST_INDEX;
import static org.roadservice.api.trafficsignal.service.TrafficMoverService.NEXT_INDEX;

@Service
public class TaskSchedulerService implements Observer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskSchedulerService.class);
    private static List<List<SignalStreetWrapper>> signalsToHandle = new ArrayList();
    private static boolean isVeryFirstStart = true;
    @Autowired
    private DataLoaderService dataLoaderService;
    @Autowired
    private TrafficMoverService trafficMoverService;
    @Autowired
    private SignalService signalService;
    private static boolean isFirstRun = true;
    private boolean firstSignalChange = false;

    @Scheduled(fixedDelay = 1000)
    public void addCarsInStreets() throws InterruptedException {
        if (signalsToHandle.isEmpty()) {
            signalsToHandle.add(dataLoaderService.loadData());
            for (List<SignalStreetWrapper> signalStreetWrapper : signalsToHandle) {
                trafficMoverService.addCarsToStreets();
            }
        } else {
            for (List<SignalStreetWrapper> signalStreetWrapper : signalsToHandle) {
                if (TrafficMoverService.getSignalStreetWrappers().isEmpty()) {
                    TrafficMoverService.setSignalStreetWrappers(signalStreetWrapper);
                }
                trafficMoverService.addCarsToStreets();
                if (!firstSignalChange) {
                    trafficMoverService.moveCarsFromStreets();
                }
                if (isFirstRun) {
                    initiateSignalSequence();
                    isFirstRun = false;
                }

            }
        }
        showTrafficStatus(signalsToHandle.get(FIRST_INDEX));
    }

    private void showTrafficStatus(List<SignalStreetWrapper> signalStreetWrapper) {
        LOGGER.info(signalStreetWrapper.get(NEXT_INDEX).getStreets().get(FIRST_INDEX).getDirection() + "=" + signalStreetWrapper.get(NEXT_INDEX).getNoOfCars() + " " +
                signalStreetWrapper.get(NEXT_INDEX).getStreets().get(NEXT_INDEX).getDirection() + "=" + signalStreetWrapper.get(NEXT_INDEX).getNoOfCars() + " " +
                signalStreetWrapper.get(FIRST_INDEX).getStreets().get(FIRST_INDEX).getDirection() + "=" + signalStreetWrapper.get(FIRST_INDEX).getNoOfCars() + " " +
                signalStreetWrapper.get(FIRST_INDEX).getStreets().get(NEXT_INDEX).getDirection() + "=" + signalStreetWrapper.get(FIRST_INDEX).getNoOfCars());
    }

    private void initiateSignalSequence() {
        if (!signalsToHandle.isEmpty()) {
            for (List<SignalStreetWrapper> signalStreetWrapper : signalsToHandle) {
                if (SignalService.getSignalStreetWrappers().isEmpty()) {
                    SignalService.setSignalStreetWrappers(signalStreetWrapper);
                }
                signalService.executeSignalSequenceRed();
                signalService.executeSignalSequenceGreen();

            }
        }

    }

    public synchronized static void addSignalStreetWrappers(List<SignalStreetWrapper> wrappers) {
        signalsToHandle.add(wrappers);
    }

    public static boolean getIsVeryFirstStart() {
        return isVeryFirstStart;
    }

    public static void setIsVeryFirstStart(boolean isVeryFirstStart) {
        TaskSchedulerService.isVeryFirstStart = isVeryFirstStart;
    }

    public static List<List<SignalStreetWrapper>> getSignalsToHandle() {
        return signalsToHandle;

    }

    @Override
    public void update(int signalStatus, boolean redToGreen, long signalId) {
        firstSignalChange = true;
        if (signalStatus == SignalStatus.GREEN_SIGNAL.getStatus()) {
            for (List<SignalStreetWrapper> signalStreetWrappers : signalsToHandle) {
                for (SignalStreetWrapper signalStreetWrapper : signalStreetWrappers) {
                    if (signalStreetWrapper.getSignal().getId() == signalId) {
                        signalStreetWrapper.setNoOfCars(signalStreetWrapper.getNoOfCars() - NEXT_INDEX);

                    }
                }

            }
        }
    }
}
