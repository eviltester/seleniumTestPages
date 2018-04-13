package com.seleniumsimplified.pulp.domain;

public class PulpBook {
    public static final PulpBook UNKNOWN_BOOK = new PulpBook("unknown", "unknown", "unknown", "unknown", "Unknown Title", "unknown", 0, "unknown");
    private final String authorIndexName;
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
        this.authorIndexName = authorIndexName;
        this.houseAuthorIndexName = houseAuthorIndex;
        this.title = title;
        this.seriesId = seriesId;
        this.publicationYear = publicationYear;
        this.publisherIndexName = publisherIndexName;
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

    public String getId() {
        return id;
    }
}
