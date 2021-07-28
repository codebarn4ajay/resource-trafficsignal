package org.roadservice.api.trafficsignal.service;

import org.roadservice.api.trafficsignal.domain.CrossingLane;
import org.roadservice.api.trafficsignal.domain.SignalQueue;
import org.roadservice.api.trafficsignal.exceptions.InvalidInputException;
import org.roadservice.api.trafficsignal.rest.SignalStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.roadservice.api.trafficsignal.service.AddTrafficService.FIRST_INDEX;
import static org.roadservice.api.trafficsignal.service.AddTrafficService.NEXT_INDEX;

@Service
public class TrafficMovementService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrafficMovementService.class);
    private static final int MAX_GREEN = 3;
    private static final int ONE_CAR = 1;
    private static final int ONE_TIME = 1;
    private static final int signalChangeFactor = 5;
    private static int initialRunCounter = 0;
    private List<List<CrossingLane>> signalsToHandle = SignalQueue.getInstance().getSignalsToHandle();
    @Autowired
    private DataLoaderService dataLoaderService;
    @Autowired
    private AddTrafficService addTrafficService;
    private int topToBottom = SignalStatus.RED_SIGNAL.getStatus();   //top to bottom is snell (north and souht)
    private int leftToRight = SignalStatus.GREEN_SIGNAL.getStatus(); // left to right is is weaver (east to west)
    private int maxDelay;

    private void addCarsAtAllSignals() {
        for (List<CrossingLane> crossingLane : signalsToHandle) {
            if (addTrafficService.getCrossingLanes().isEmpty()) {
                addTrafficService.setCrossingLanes(crossingLane);
            } else {
                addTrafficService.addCarsToStreets();
                if (initialRunCounter < 3) {
                    crossingLane.get(topToBottom).setNoOfCars(crossingLane.get(topToBottom).getNoOfCars() - 1);
                    initialRunCounter++;
                }
            }
        }
    }

    @Scheduled(fixedDelay = 1000)
    public void addCarsInStreets() {
      try {
          if (signalsToHandle.isEmpty()) {
              loadSignalQueueIfEmpty();
          } else {
              addCarsAtAllSignals();
              displayNoOfCarsAtEachSignal();

          }
      }catch (InvalidInputException ie) {
          LOGGER.error("Error during signal orchestration");
      }
    }

    private void displayNoOfCarsAtEachSignal() {
        signalsToHandle.forEach(this::showTrafficStatus);
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 4000)
    public void runSignalSequence() {
        for (List<CrossingLane> crossingLane : SignalQueue.getSignalsToHandle()) {
            moveCars(crossingLane);
        }
    }

    //Call this method async for multiple signals
    private void moveCars(List<CrossingLane> crossingLanes) {
        if (topToBottom == SignalStatus.GREEN_SIGNAL.getStatus()) {
            addCarsAsPerSignalsOnStreet(crossingLanes, NEXT_INDEX);
        }
        else {
            addCarsAsPerSignalsOnStreet(crossingLanes, FIRST_INDEX);
        }

    }

    private void addCarsAsPerSignalsOnStreet(List<CrossingLane> crossingLanes, int index) {
        if (crossingLanes.get(index).getNoOfTimeProcessed() >= MAX_GREEN && crossingLanes.get(index).getNoOfTimeProcessed() < signalChangeFactor) {
            crossingLanes.get(index).setNoOfCars(crossingLanes.get(index).getNoOfCars() - ONE_CAR);
            crossingLanes.get(index).setNoOfTimeProcessed(crossingLanes.get(index).getNoOfTimeProcessed() + ONE_CAR);
        } else if (crossingLanes.get(index).getNoOfTimeProcessed() == signalChangeFactor) {
            crossingLanes.get(index).setNoOfTimeProcessed(ONE_TIME);
            topToBottom = topToBottom == SignalStatus.RED_SIGNAL.getStatus() ? SignalStatus.GREEN_SIGNAL.getStatus() : SignalStatus.RED_SIGNAL.getStatus();
            leftToRight = leftToRight == SignalStatus.GREEN_SIGNAL.getStatus() ? SignalStatus.RED_SIGNAL.getStatus() : SignalStatus.GREEN_SIGNAL.getStatus();
        } else {
            crossingLanes.get(index).setNoOfTimeProcessed(crossingLanes.get(index).getNoOfTimeProcessed() + 1);
        }
    }

    private void loadSignalQueueIfEmpty() throws InvalidInputException {
       try {
           signalsToHandle.add(dataLoaderService.loadData());
       } catch (InvalidInputException ie) {
           throw ie;
       }
       for (List<CrossingLane> crossingLane : SignalQueue.getSignalsToHandle()) {
            addTrafficService.addCarsToStreets();
        }
    }

    private void showTrafficStatus(List<CrossingLane> crossingLane) {
        LOGGER.info(crossingLane.get(NEXT_INDEX).getStreets().get(FIRST_INDEX).getDirection() + "=" + crossingLane.get(NEXT_INDEX).getNoOfCars() + " " +
                crossingLane.get(NEXT_INDEX).getStreets().get(NEXT_INDEX).getDirection() + "=" + crossingLane.get(NEXT_INDEX).getNoOfCars() + " " +
                crossingLane.get(FIRST_INDEX).getStreets().get(FIRST_INDEX).getDirection() + "=" + crossingLane.get(FIRST_INDEX).getNoOfCars() + " " +
                crossingLane.get(FIRST_INDEX).getStreets().get(NEXT_INDEX).getDirection() + "=" + crossingLane.get(FIRST_INDEX).getNoOfCars());
    }

}
