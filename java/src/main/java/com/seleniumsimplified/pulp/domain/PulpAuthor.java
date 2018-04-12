package com.seleniumsimplified.pulp.domain;

public class PulpAuthor {
    private final String name;
    private final String id;

    public PulpAuthor(String name) {
        this.name = name;
        this.id = name.replaceAll(" ", "");
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
