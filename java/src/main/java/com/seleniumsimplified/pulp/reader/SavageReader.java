package com.seleniumsimplified.pulp.reader;

import com.seleniumsimplified.pulp.domain.PulpBook;
import com.seleniumsimplified.seleniumtestpages.CsvReader;

public class SavageReader {
    private final CsvReader reader;
    private String defaultHouseAuthor;
    private String defaultSeriesName;
    private String defaultPulpHero;
    private String defaultPublisherName;

    public SavageReader(String resourcePath) {
        reader = new CsvReader(resourcePath);
        reader.read();
        this.setHouseAuthor("Kenneth Robeson");
        this.setSeriesName("Doc Savage");
        this.setPulpHero("Doc Savage");
        this.setPublisher("Street And Smith");
    }

    private void setSeriesName(String defaultSeriesName) {
        this.defaultSeriesName = defaultSeriesName;
    }

    private void setPulpHero(String defaultPulpHero) {
        this.defaultPulpHero = defaultPulpHero;
    }

    private void setPublisher(String defaultPublisherName) {
        this.defaultPublisherName = defaultPublisherName;
    }

    private void setHouseAuthor(String defaultHouseAuthor) {
        this.defaultHouseAuthor = defaultHouseAuthor;
    }

    public int numberOfLines() {
        return reader.numberOfLines();
    }

    public PulpBook getBook(int atLine) {

        PulpBook book = new PulpBook(   defaultSeriesName,
                                        reader.getLineField(atLine,0),
                                        defaultHouseAuthor,
                                        reader.getLineField(atLine,1),
                                        reader.getLineField(atLine,2),
                                        Integer.valueOf(reader.getLineField(atLine,3)),
                                        defaultPublisherName
                            );
        return book;
    }
}
