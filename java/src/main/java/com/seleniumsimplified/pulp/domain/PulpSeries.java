package com.seleniumsimplified.pulp.domain;

public class PulpSeries {
    private final String name;
    private final String id;

    public PulpSeries(String name) {
        this.name = name;
        this.id = name.replaceAll(" ", "");
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
