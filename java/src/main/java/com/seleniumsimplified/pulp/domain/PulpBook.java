package com.seleniumsimplified.pulp.domain;

public class PulpBook {
    private final String authorIndexName;
    private final String seriesIndexName;
    private final String title;
    private final String seriesId;
    private final int publicationYear;
    private final String publisherIndexName;
    private String houseAuthorIndexName;

    public PulpBook(String seriesIndexName, String authorIndexName, String houseAuthorIndex, String title, String seriesId, int publicationYear, String publisherIndexName) {
        this.seriesIndexName = seriesIndexName.replaceAll(" ","");
        this.authorIndexName = authorIndexName.replaceAll(" ","");
        this.houseAuthorIndexName = houseAuthorIndex.replaceAll(" ","");
        this.title = title;
        this.seriesId = seriesId;
        this.publicationYear = publicationYear;
        this.publisherIndexName = publisherIndexName.replaceAll(" ", "");
    }

    public String getSeriesIndex() {
        return this.seriesIndexName;
    }

    public String getAuthorIndex() {
        return this.authorIndexName;
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
}
