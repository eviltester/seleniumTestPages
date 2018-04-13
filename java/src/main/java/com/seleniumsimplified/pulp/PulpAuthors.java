package com.seleniumsimplified.pulp;

import com.seleniumsimplified.pulp.domain.PulpAuthor;

import java.util.ArrayList;

public class PulpAuthors {
    private int key;
    private ArrayList<PulpAuthor> authors;

    public PulpAuthors(){
        authors = new ArrayList<>();
        key = 1;
    }
    public int count() {
        return authors.size();
    }

    public void add(String authorName) {
        PulpAuthor author = getNextAuthor(authorName);
        authors.add(author);
    }

    private PulpAuthor getNextAuthor(String authorName) {
        return new PulpAuthor(String.valueOf(key++), authorName);
    }

    public PulpAuthor get(String key) {
        for(PulpAuthor author : authors){
            if(author.getId().contentEquals(key)){
                return author;
            }
        }

        return PulpAuthor.UNKNOWN_AUTHOR;
    }

    public PulpAuthor findByName(String name) {
        for(PulpAuthor author : authors){
            if(author.getName().equalsIgnoreCase(name)){
                return author;
            }
        }

        return PulpAuthor.UNKNOWN_AUTHOR;
    }
}
