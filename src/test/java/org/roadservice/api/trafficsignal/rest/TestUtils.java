package org.roadservice.api.trafficsignal.rest;

import org.roadservice.api.trafficsignal.domain.Street;
import org.roadservice.api.trafficsignal.domain.StreetCrossing;
import org.roadservice.api.trafficsignal.domain.StreetCrossingBuilder;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static StreetCrossing createMockData() {
        List<Street> streetList1 = new ArrayList();
        Street street1 = new Street(1012, "Weaver", "E");
        Street street2 = new Street(1013, "Weaver", "W");
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
        return streetCrossing;
    }
}
