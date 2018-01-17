package org.roadservice.api.trafficsignal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.roadservice.api.trafficsignal.domain.SignalStreetWrapper;
import org.roadservice.api.trafficsignal.rest.StreetToSignalQueueTransformer;
import org.roadservice.api.trafficsignal.rest.TestUtils;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TrafficMoverServiceTest {
    private List<SignalStreetWrapper> signalStreetWrappers = new StreetToSignalQueueTransformer().fromStreetToSignal(TestUtils.createMockData());
    @InjectMocks
    private TrafficMoverService trafficMoverService;

    @Test
    public void addCarsToStreetsTest() {
        TrafficMoverService.setSignalStreetWrappers(signalStreetWrappers);
        trafficMoverService.addCarsToStreets();
        assertThat(signalStreetWrappers.get(0), is(notNullValue()));
        assertThat(signalStreetWrappers.get(0).getNoOfCars(), is(1));
    }

    @Test
    public void moveCarsFromStreetsTest() {
        TrafficMoverService.setSignalStreetWrappers(signalStreetWrappers);
        trafficMoverService.addCarsToStreets();
        trafficMoverService.moveCarsFromStreets();
        assertThat(signalStreetWrappers.get(1), is(notNullValue()));
        assertThat(signalStreetWrappers.get(1).getNoOfCars(), is(0));
    }


}