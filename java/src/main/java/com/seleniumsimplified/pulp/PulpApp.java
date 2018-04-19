package com.seleniumsimplified.pulp;

import com.seleniumsimplified.pulp.html.HtmlReports;
import com.seleniumsimplified.pulp.reader.PulpDataPopulator;
import com.seleniumsimplified.pulp.reader.PulpSeriesCSVReader;
import com.seleniumsimplified.pulp.reader.SavageReader;
import com.seleniumsimplified.pulp.reader.SpiderReader;
import com.seleniumsimplified.pulp.reporting.PulpReporter;

public class PulpApp {
    private final PulpData books;

    HtmlReports reports;

    public PulpApp() {

        books = new PulpData();
        reports = new HtmlReports(books);
    }


    public void readSavageData(String dataToRead) {
        readData(new SavageReader(dataToRead));
    }

    public void readSpiderData(String dataToRead) {
        readData(new SpiderReader(dataToRead));
    }

    public void readData(PulpSeriesCSVReader reader) {
        PulpDataPopulator populator = new PulpDataPopulator(books);
        populator.populateFrom(reader);
    }

    public HtmlReports reports() {
        return reports;
    }

    public PulpData books() {
        return books;
    }
}
