package org.roadservice.api.trafficsignal.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.roadservice.api.trafficsignal.domain.CrossingLane;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class StreetToSignalQueueTransformerTest {

    @InjectMocks
    private StreetToSignalQueueTransformer streetToSignalQueueTransformer;

    @Test
    public void fromStreetToSignalTest() {
        List<CrossingLane> wrappers = streetToSignalQueueTransformer.fromStreetToSignal(TestUtils.createMockData());
        assertThat(wrappers, is(notNullValue()));
        assertThat(wrappers.get(0).getStreets().get(0).getName(), is("Weaver"));
        assertThat(wrappers.get(1).getStreets().get(0).getName(), is("Snell"));
        assertThat(wrappers.get(0).getStreets().get(0).getId(), is(1012L));
        assertThat(wrappers.get(1).getStreets().get(0).getId(), is(2011L));
    }


}