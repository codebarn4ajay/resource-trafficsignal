package org.roadservice.api.trafficsignal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.roadservice.api.trafficsignal.domain.CrossingLane;
import org.roadservice.api.trafficsignal.domain.StreetCrossing;
import org.roadservice.api.trafficsignal.rest.StreetToSignalQueueTransformer;
import org.roadservice.api.trafficsignal.rest.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CrossingLaneServiceTest {
    @Mock
    private StreetToSignalQueueTransformer streetToSignalQueueTransformer;
    @InjectMocks
    private CrossingLaneService crossingLaneService;

    @Test
    public void addStreetCrossingDataTest() {
        StreetCrossing streetCrossing = TestUtils.createMockData();
        List<CrossingLane> crossingLanes = new ArrayList<>();
        when(streetToSignalQueueTransformer.fromStreetToSignal(streetCrossing)).thenReturn(crossingLanes);
        assertThat(crossingLaneService.addStreetCrossingData(streetCrossing),is(crossingLanes));
    }
}