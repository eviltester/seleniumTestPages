package com.seleniumsimplified.pulp.domain;

public class PulpPublisher {
    public static final PulpPublisher UNKNOWN_PUBLISHER = new PulpPublisher("unknonwn", "unknown publisher");
    private final String name;
    private final String id;

    public PulpPublisher(String name) {
        this.name = name;
        this.id = "unknown";
    }

    public PulpPublisher(String indexId, String name) {
        this.name = name;
        this.id = indexId;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
