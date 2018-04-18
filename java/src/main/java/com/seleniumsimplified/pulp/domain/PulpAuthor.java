package com.seleniumsimplified.pulp.domain;

import java.util.ArrayList;
import java.util.Collection;

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

    public static Collection<String> parseNameAsMultipleAuthors(String authorsField) {

        Collection<String> authorsToAdd = new ArrayList<>();

        String[] authorNames = authorsField.split(" / ");
        for(String authorName : authorNames){

            authorsToAdd.add(authorName.trim());
        }

        return authorsToAdd;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
