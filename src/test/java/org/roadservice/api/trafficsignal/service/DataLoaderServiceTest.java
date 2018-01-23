package org.roadservice.api.trafficsignal.service;

import org.hibernate.validator.constraints.ModCheck;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.roadservice.api.trafficsignal.domain.CrossingLane;
import org.roadservice.api.trafficsignal.domain.StreetCrossing;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class DataLoaderServiceTest {
    @Mock
    private CrossingLaneService crossingLaneService;
    @InjectMocks
    private DataLoaderService dataLoaderService= new DataLoaderService();
    @Test
    public void addStreetCrossingTest(){
        StreetCrossing streetCrossing =dataLoaderService.addStreetCrossing();
        assertNotNull(streetCrossing);
        assertNotNull(streetCrossing.getLeftAndRightStreets());
        assertNotNull(streetCrossing.getTopAndBottomStreets());
    }
}