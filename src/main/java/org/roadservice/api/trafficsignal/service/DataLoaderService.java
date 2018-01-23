package org.roadservice.api.trafficsignal.service;

import org.roadservice.api.trafficsignal.domain.CrossingLane;
import org.roadservice.api.trafficsignal.domain.Street;
import org.roadservice.api.trafficsignal.domain.StreetCrossing;
import org.roadservice.api.trafficsignal.domain.StreetCrossingBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoaderService {

    @Autowired
    private CrossingLaneService crossingLaneService;
    private static final Logger LOGGER = LoggerFactory.getLogger(StreetCrossingBuilder.class);

    public List<CrossingLane> loadData() {
        List<CrossingLane> crossingLanes = crossingLaneService.addStreetCrossingData(addStreetCrossing());
        return crossingLanes;
    }

    public StreetCrossing addStreetCrossing() {
        LOGGER.debug("data loading started");
        List<Street> streetList1 = new ArrayList();
        Street street1 = new Street(1011, "Weaver", "E");
        Street street2 = new Street(1012, "Weaver", "W");
        streetList1.add(street1);
        streetList1.add(street2);

        List<Street> streetList2 = new ArrayList();
        Street street3 = new Street(2011, "Snell", "N");
        Street street4 = new Street(2012, "Snell", "S");
        streetList2.add(street3);
        streetList2.add(street4);
        StreetCrossing streetCrossing = new StreetCrossingBuilder()
                .setId(101)
                .setLeftAndRightStreets(streetList1)
                .setTopAndBottomStreets(streetList2)
                .createCrossing();
        LOGGER.debug("data loading started - END");
        return streetCrossing;

    }
}