package com.lexandro.xmlandjava.domain;

import java.util.LinkedList;
import java.util.List;

public class Location {

    private String id;
    private String name;
    private List<NextLocation> nextLocations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NextLocation> getNextLocations() {
        return nextLocations;
    }

    public void setNextLocations(List<NextLocation> nextLocations) {
        this.nextLocations = nextLocations;
    }

    public void addNextLocation(NextLocation nextLocation) {
        if (nextLocations == null) {
            nextLocations = new LinkedList<>();
        }
        nextLocations.add(nextLocation);
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                '}';
    }
}
