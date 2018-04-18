package com.seleniumsimplified.pulp.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PulpBook {
    public static final PulpBook UNKNOWN_BOOK = new PulpBook("unknown", "unknown", "unknown", "unknown", "Unknown Title", "unknown", 0, "unknown");
    private final List<String> authorIndexNames;
    private final String seriesIndexName;
    private final String title;
    private final String seriesId;
    private final int publicationYear;
    private final String publisherIndexName;
    private String houseAuthorIndexName;
    private String id;

    public PulpBook(String id, String seriesIndexName, String authorIndexName, String houseAuthorIndex, String title, String seriesId, int publicationYear, String publisherIndexName) {
        this.id = id;
        this.seriesIndexName = seriesIndexName;
        this.authorIndexNames = new ArrayList<>();

        Collection<String> authorNames = PulpAuthor.parseNameAsMultipleAuthors(authorIndexName);
        authorIndexNames.addAll(authorNames);

        this.houseAuthorIndexName = houseAuthorIndex;
        this.title = title;
        this.seriesId = seriesId;
        this.publicationYear = publicationYear;
        this.publisherIndexName = publisherIndexName;
    }

    public String getSeriesIndex() {
        return this.seriesIndexName;
    }


    public List<String> getAuthorIndexes() {
        List<String> indexes = new ArrayList<>();
        indexes.addAll(authorIndexNames);
        return indexes;
    }

    public String getTitle() {
        return title;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public String getPublisherIndex() {
        return this.publisherIndexName;
    }

    public String getHouseAuthorIndex() {
        return this.houseAuthorIndexName;
    }

    public String getId() {
        return id;
    }

    public void addCoAuthor(String authorId) {
        if(!authorIndexNames.contains(authorId)){
            authorIndexNames.add(authorId);
        }
    }

    public String getAuthorIndexesAsString() {
        StringBuilder authors = new StringBuilder();
        int authorCount=0;

        for(String index : authorIndexNames){
            if(authorCount!=0 && authorCount <authorIndexNames.size()-1){
                authors.append(", ");
            }
            authors.append(index);
        }

        return authors.toString();
    }
}
