package com.lexandro.xmlandjava.domain;

import java.util.LinkedList;
import java.util.List;

public class Location {

    private String id;
    private String name;

    private int length;
    private int speed;

    private List<Location> nextLocations;

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

    public List<Location> getNextLocations() {
        return nextLocations;
    }

    public void setNextLocations(List<Location> nextLocations) {
        this.nextLocations = nextLocations;
    }

    public void addNextLocation(Location nextLocation) {
        if (nextLocations == null) {
            nextLocations = new LinkedList<>();
        }
        nextLocations.add(nextLocation);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSpeed() {

        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTime() {
        return length / speed;
    }

    @Override
    public String toString() {
        return "" +
                "name='" + name + '\'' +
                "time to go='" + length / speed + "' ";
    }
}
