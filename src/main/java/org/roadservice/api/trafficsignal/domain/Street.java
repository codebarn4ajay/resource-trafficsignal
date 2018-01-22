package org.roadservice.api.trafficsignal.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Street {
    private final long id;
    private final String name;
    private final String direction;

    @JsonCreator
    public Street(@JsonProperty("id") long id, @JsonProperty("name") String name, @JsonProperty("direction") String direction) {
        this.id = id;
        this.name = name;
        this.direction = direction;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDirection() {
        return direction;
    }

}
