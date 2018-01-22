package org.roadservice.api.trafficsignal.domain;

import java.util.List;

public class StreetCrossingBuilder {
    private long id;
    private String name;
    private List<Street> leftAndRightStreets;
    private List<Street> topAndBottomStreets;

    public StreetCrossingBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public StreetCrossingBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StreetCrossingBuilder setLeftAndRightStreets(List<Street> leftAndRightStreets) {
        this.leftAndRightStreets = leftAndRightStreets;
        return this;
    }

    public StreetCrossingBuilder setTopAndBottomStreets(List<Street> topAndBottomStreets) {
        this.topAndBottomStreets = topAndBottomStreets;
        return this;
    }

    public StreetCrossing createCrossing() {
        return new StreetCrossing(id, name, leftAndRightStreets, topAndBottomStreets);
    }
}