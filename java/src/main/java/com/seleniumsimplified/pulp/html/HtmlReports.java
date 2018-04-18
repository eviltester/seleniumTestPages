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
        return reportCollectionasLi(reporter.getBooksAsStrings());
    }

    public String getAuthorsAsHtmlList() {
        return reportCollectionasLi(reporter.getAuthorsAsStrings());
    }

    private String reportCollectionasLi(Collection<String> simpleReport){

        StringBuilder report = new StringBuilder();

        report.append("<ul>\n");
        for(String reportLine : simpleReport){
            report.append(String.format("<li>%s</li>%n", reportLine));
        }
        report.append("</ul>");
        return report.toString();
    }
}
