package com.seleniumsimplified.pulp.html;

import com.seleniumsimplified.pulp.PulpData;
import com.seleniumsimplified.pulp.reporting.PulpReporter;

import java.util.Collection;

public class HtmlReports {
    private final PulpReporter reporter;

    public HtmlReports(PulpData books) {
        reporter = new PulpReporter(books);
    }

    public String getBooksAsHtmlList() {
        Collection<String> simpleReport = reporter.getBooksAsStrings();

        StringBuilder report = new StringBuilder();

        report.append("<ul>");
        for(String reportLine : simpleReport){
            report.append(String.format("<li>%s</li>", reportLine));
        }
        report.append("</ul>");
        return report.toString();
    }
}
