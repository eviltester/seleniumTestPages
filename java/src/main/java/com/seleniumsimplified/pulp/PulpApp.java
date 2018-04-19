package com.seleniumsimplified.pulp;

import com.seleniumsimplified.pulp.html.HtmlReports;
import com.seleniumsimplified.pulp.reader.PulpDataPopulator;
import com.seleniumsimplified.pulp.reader.SavageReader;
import com.seleniumsimplified.pulp.reporting.PulpReporter;

public class PulpApp {
    private final PulpData books;

    HtmlReports reports;

    public PulpApp(String initialData) {

        books = new PulpData();

        readData(initialData);

        reports = new HtmlReports(books);

    }


    private void readData(String dataToRead) {
        PulpDataPopulator populator = new PulpDataPopulator(books);
        SavageReader reader = new SavageReader(dataToRead);
        populator.populateFrom(reader);

    }

    public HtmlReports reports() {
        return reports;
    }

    public PulpData books() {
        return books;
    }
}
