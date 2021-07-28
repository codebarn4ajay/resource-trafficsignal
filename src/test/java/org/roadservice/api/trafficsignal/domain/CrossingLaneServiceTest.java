package org.roadservice.api.trafficsignal.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.roadservice.api.trafficsignal.rest.SignalStatus;
import org.roadservice.api.trafficsignal.rest.StreetToSignalQueueTransformer;
import org.roadservice.api.trafficsignal.rest.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class CrossingLaneServiceTest {
    @InjectMocks
    private StreetToSignalQueueTransformer streetToSignalQueueTransformer;
    @Test
    public void update() {
        SignalQueue signalQueue = SignalQueue.getInstance();
        SignalQueue.getSignalsToHandle().add(streetToSignalQueueTransformer.fromStreetToSignal(TestUtils.createMockData()));
       List<CrossingLane> crossingLanesFirst= SignalQueue.getSignalsToHandle().get(0);

        //assertThat(crossingLanesFirst.get(1).getSignal().getSignalStatus(), is(1));
    }
    private CrossingLane createData(){
       return new CrossingLane(TestUtils.createMockData().getLeftAndRightStreets());
    }
}
