package com.seleniumsimplified.pulp.reporting;

import com.seleniumsimplified.pulp.PulpData;
import com.seleniumsimplified.pulp.domain.PulpAuthor;
import com.seleniumsimplified.pulp.domain.PulpBook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PulpReporter {
    private final PulpData data;

    public PulpReporter(PulpData books) {
        this.data = books;
    }

    public Collection<String> getBooksAsStrings() {

        List<String> report = new ArrayList<>();

        StringBuilder line;

        List<String> keys = data.books().keys();

        for(String key : keys){
            line = new StringBuilder();
            PulpBook book = data.books().get(key);
            line.append(book.getTitle());
            line.append(" | ");

            for(String authorId : book.getAllAuthorIndexes()){
                line.append(data.authors().get(authorId).getName());
                line.append(", ");
            }
            line.replace(line.lastIndexOf(","), line.lastIndexOf(",")+1, "");
            line.append(" | ");

            line.append(book.getPublicationYear());
            line.append(" | ");

            line.append(data.series().get(book.getSeriesIndex()).getName());

            report.add(line.toString());
        }

        return report;
    }

    public Collection<String> getAuthorsAsStrings() {
        List<String> report = new ArrayList<>();
        StringBuilder line;

        List<String> keys = data.authors().keys();

        for(String key : keys) {
            line = new StringBuilder();
            PulpAuthor author = data.authors().get(key);
            line.append(author.getName());

            report.add(line.toString());
        }

        return report;

    }
}
