package com.seleniumsimplified.pulp.reader;

import com.seleniumsimplified.pulp.domain.PulpAuthor;
import com.seleniumsimplified.pulp.domain.PulpBook;
import com.seleniumsimplified.seleniumtestpages.CsvReader;

import java.util.*;

public class TheAvengerReader implements PulpSeriesCSVReader {
    private final CsvReader reader;
    private String defaultSeriesName;



    public TheAvengerReader(String resourcePath) {
        reader = new CsvReader(resourcePath);
        reader.read();
        this.setSeriesName("The Avenger");
    }

    private void setSeriesName(String defaultSeriesName) {
        this.defaultSeriesName = defaultSeriesName;
    }


    public int numberOfLines() {
        return reader.numberOfLines();
    }

    public PulpBook getBook(int atLine) {

        String houseAuthor = "Kenneth Robeson";
        String authors = reader.getLineField(atLine,0);

        if(!authors.contains(houseAuthor)){
            houseAuthor="";
        }

        PulpBook book = new PulpBook(   "unknown",
                                        defaultSeriesName,
                                        authors,
                                        houseAuthor,
                                        reader.getLineField(atLine,1),
                                        reader.getLineField(atLine,2),
                                        Integer.valueOf(reader.getLineField(atLine,3)),
                                        reader.getLineField(atLine,4)
                            );
        return book;
    }

    public List<String> getAuthorNames() {

        Set<String> authorNames = new HashSet<>();

        for(int line=0; line<reader.numberOfLines(); line++){
            authorNames.addAll(getAuthorsFromLine(line));
        }

        List<String> names = new ArrayList<>();
        names.addAll(authorNames);

        return names;
    }

    private Collection<String> getAuthorsFromLine(int line) {

        String authorsField = reader.getLineField(line,0).trim();

        return PulpAuthor.parseNameAsMultipleAuthors(authorsField);

    }

    public List<String> getPublisherNames() {
        Set<String> publisherNames = new HashSet<>();

        for(int line=0; line<reader.numberOfLines(); line++){
            publisherNames.add(reader.getLineField(line,4));
        }

        List<String> publishers = new ArrayList<>();
        publishers.addAll(publisherNames);
        return publishers;
    }


    public List<String> getPulpSeries() {
        List<String> seriesnames = new ArrayList<>();
        seriesnames.add(defaultSeriesName);
        return seriesnames;
    }
}