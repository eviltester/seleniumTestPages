package com.seleniumsimplified.pulp.domain;

public class PulpAuthor {
    public static final PulpAuthor UNKNOWN_AUTHOR = new PulpAuthor("unknown", "unknown author");
    private final String name;
    private final String id;

    public PulpAuthor(String name) {
        this.name = name;
        this.id = "unknown";
    }

    public PulpAuthor(String id, String name) {
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
