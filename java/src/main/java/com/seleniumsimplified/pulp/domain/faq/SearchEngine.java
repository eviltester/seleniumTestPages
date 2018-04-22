package com.seleniumsimplified.pulp.domain.faq;

public class SearchEngine {
    private final String query;

    public SearchEngine(String queryPrefill) {
        this.query = queryPrefill;
    }

    public String getSearchTerm() {
        return query;
    }
}
