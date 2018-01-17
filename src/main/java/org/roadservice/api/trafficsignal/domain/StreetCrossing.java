package org.roadservice.api.trafficsignal.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StreetCrossing {
    private final long id;
    private final String name;
    private final List<Street> leftAndRightStreets;
    private final List<Street> topAndBottomStreets;
    public static final String RESOURCE_URI = "/streetCrossing/v1";

    @JsonCreator
    public StreetCrossing(@JsonProperty("id") long id,
                          @JsonProperty("name") String name,
                          @JsonProperty("leftAndRightStreets") List<Street> leftAndRightStreets,
                          @JsonProperty("topAndBottomStreets") List<Street> topAndBottomStreets) {
        this.id = id;
        this.name = name;
        this.leftAndRightStreets = leftAndRightStreets;
        this.topAndBottomStreets = topAndBottomStreets;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Street> getLeftAndRightStreets() {
        return leftAndRightStreets;
    }

    public List<Street> getTopAndBottomStreets() {
        return topAndBottomStreets;
    }
}
