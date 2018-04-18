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
        return reportCollectionasLi(reporter.getBooksAsStrings(), "Books");
    }

    public String getAuthorsAsHtmlList() {
        return reportCollectionasLi(reporter.getAuthorsAsStrings(), "Authors");
    }

    private String reportCollectionasLi(Collection<String> simpleReport, String listOfWhat){

        StringBuilder report = new StringBuilder();

        report.append("<html><head>\n");
        report.append(String.format("<title>List of %s</title>%n", listOfWhat));
        report.append("</head>\n");

        report.append("<body>\n");

        report.append("<ul>\n");
        for(String reportLine : simpleReport){
            report.append(String.format("<li>%s</li>%n", reportLine));
        }
        report.append("</ul>");

        report.append("</body></html>");

        return report.toString();

    }
}
