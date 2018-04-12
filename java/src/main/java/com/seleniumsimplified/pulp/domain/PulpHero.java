package com.seleniumsimplified.pulp.domain;

public class PulpHero {
    private String name;
    private String id;

    public PulpHero(String name) {
        this.name = name;
        this.id = name.replaceAll(" ", "");
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return this.id;
    }
}
