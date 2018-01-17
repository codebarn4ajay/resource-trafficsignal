package org.roadservice.api.trafficsignal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.roadservice.api.trafficsignal.domain.CrossingLane;
import org.roadservice.api.trafficsignal.rest.StreetToSignalQueueTransformer;
import org.roadservice.api.trafficsignal.rest.TestUtils;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AddTrafficServiceTest {
    private List<CrossingLane> crossingLanes = new StreetToSignalQueueTransformer().fromStreetToSignal(TestUtils.createMockData());
    @InjectMocks
    private AddTrafficService addTrafficService;

    @Test
    public void addCarsToStreetsTest() {
        addTrafficService.setCrossingLanes(crossingLanes);
        addTrafficService.addCarsToStreets();
        assertThat(crossingLanes.get(0), is(notNullValue()));
        assertThat(crossingLanes.get(0).getNoOfCars(), is(1));
    }

}