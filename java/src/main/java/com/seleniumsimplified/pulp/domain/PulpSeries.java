package com.seleniumsimplified.pulp.domain;

public class PulpSeries {
    public static final PulpSeries UNKNOWN_SERIES = new PulpSeries("unknown", "unknown series");
    private final String name;
    private final String id;

    public PulpSeries(String name) {
        this.name = name;
        this.id = "unknown";
    }

    public PulpSeries(String id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
