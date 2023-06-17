package com.example.seismograph.api.model;

import java.util.List;

public class Geometry {

    private List<Double> coordinates;

    public Geometry() {
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

}