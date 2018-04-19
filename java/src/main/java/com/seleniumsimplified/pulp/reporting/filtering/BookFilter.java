package com.seleniumsimplified.pulp.reporting.filtering;

public class BookFilter {
    private String authorId;
    private Integer year;
    private String publisherId;

    public BookFilter where() {
        return this;
    }

    public BookFilter author(String id) {
        this.authorId = id;
        return this;
    }

    public boolean isByAuthor() {
        return authorId!=null;
    }

    public String getAuthorId() {
        return authorId;
    }

    public BookFilter publishedInYear(int year) {
        this.year = Integer.valueOf(year);
        return this;
    }

    public boolean isByYear() {
        return year!=null;
    }

    public int getYear() {
        return year.intValue();
    }

    public BookFilter publishedBy(String id) {
        this.publisherId = id;
        return this;
    }

    public boolean isByPublisher() {
        return publisherId!=null;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public BookFilter and() {
        return this;
    }
}
