package com.seleniumsimplified.pulp.reporting.filtering;

import com.seleniumsimplified.pulp.domain.objects.PulpBook;

import java.util.List;

public class BooksFilter {
    private final List<PulpBook> books;

    public BooksFilter(List<PulpBook> books) {
        this.books = books;
    }
}
