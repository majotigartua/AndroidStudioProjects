package com.example.seismograph.api.model;

import java.util.List;

public class Response {

    private List<Feature> features;

    public Response() {
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

}