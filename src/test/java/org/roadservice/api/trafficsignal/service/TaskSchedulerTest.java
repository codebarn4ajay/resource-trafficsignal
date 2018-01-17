package org.roadservice.api.trafficsignal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.roadservice.api.trafficsignal.domain.Street;
import org.roadservice.api.trafficsignal.domain.StreetCrossing;
import org.roadservice.api.trafficsignal.domain.StreetCrossingBuilder;
import org.roadservice.api.trafficsignal.rest.StreetToSignalQueueTransformer;

import java.util.ArrayList;
import java.util.List;
@RunWith(MockitoJUnitRunner.class)
public class TaskSchedulerTest {
    private TaskSchedulerService taskScheduler = new TaskSchedulerService();
    private StreetToSignalQueueTransformer streetToSignalQueueTransformer = new StreetToSignalQueueTransformer();
    @Test
    public void addCarsInStreetsTest() {
//        TaskScheduler taskScheduler = new TaskScheduler();
//        List<List<SignalStreetWrapper>> signalsToHandle = TaskScheduler.getSignalsToHandle();
//        List<SignalStreetWrapper> signalStreetWrappers = streetToSignalQueueTransformer.fromStreetToSignal(addStreetCrossing());
//        signalsToHandle.add(signalStreetWrappers);
//        TaskScheduler.addSignalStreetWrappers(signalStreetWrappers);
//        taskScheduler.addCarsInStreets();
//        signalsToHandle = TaskScheduler.getSignalsToHandle();
//        int street1Cars= signalsToHandle.get(0).get(0).getNoOfCars();
//        int street2Cars= signalsToHandle.get(0).get(1).getNoOfCars();
//        Thread.sleep(3000);
//        assertThat(street1Cars, is(0));
//        assertThat(street2Cars, is(0));

    }

    public  StreetCrossing addStreetCrossing(){
        List<Street> streetList1 = new ArrayList();
        Street street1 = new Street(1011,"Weaver", "W");
        Street street2 = new Street(1012,"Weaver", "W");
        streetList1.add(street1);
        streetList1.add(street2);

        List<Street> streetList2 = new ArrayList();
        Street street3 = new Street(2011,"Snyder", "N");
        Street street4 = new Street(2012,"Snyder", "S");
        streetList2.add(street3);
        streetList2.add(street4);
        StreetCrossing streetCrossing = new StreetCrossingBuilder()
                .setId(101)
                .setLeftAndRightStreets(streetList1)
                .setTopAndBottomStreets(streetList2)
                .createCrossing();
        return streetCrossing;
    }

}