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
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class SignalServiceTest {
    private List<SignalStreetWrapper> signalStreetWrappers = new StreetToSignalQueueTransformer().fromStreetToSignal(TestUtils.createMockData());
    @InjectMocks
    private SignalService signalService;

    @Test
    public void executeSignalSequenceRedTest() {
        SignalService.setSignalStreetWrappers(signalStreetWrappers);
        signalService.executeSignalSequenceGreen();
        assertThat(signalStreetWrappers.get(0).getSignal(), is(0));
    }

}