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
        return reportCollectionAsLi(reporter.getBooksAsStrings(), "Books");
    }

    public String getAuthorsAsHtmlList() {
        return reportCollectionAsLi(reporter.getAuthorsAsStrings(), "Authors");
    }

    public String getPublishersAsHtmlList() {
        return reportCollectionAsLi(reporter.getPublishersAsStrings(), "Publishers");
    }

    public String getYearsAsHtmlList() {
        return reportCollectionAsLi(reporter.getYearsAsStrings(), "Years");
    }

    private String reportCollectionAsLi(Collection<String> simpleReport, String listOfWhat){

        StringBuilder report = new StringBuilder();

        addHeader(String.format("<title>List of %s</title>%n", listOfWhat), report);

        startBody(report);

        startUl(report);

        for(String reportLine : simpleReport){
            report.append(getLi(reportLine));
        }
        endUl(report);

        endBodyAndPage(report);

        return report.toString();

    }

    private void endUl(StringBuilder report) {
        report.append("</ul>");
    }

    private void startUl(StringBuilder report) {
        report.append("<ul>\n");
    }

    private void endBodyAndPage(StringBuilder report) {
        report.append("</body></html>");
    }

    private void startBody(StringBuilder report) {
        report.append("<body>\n");
    }

    public String getIndexPage(){

        StringBuilder report = new StringBuilder();
        addHeader("Pulp App Menu", report);
        startBody(report);

        startUl(report);

        report.append(getLi(getLink("List of Books", "/apps/pulp/gui/reports/books")));
        report.append(getLi(getLink("List of Authors", "/apps/pulp/gui/reports/authors")));
        report.append(getLi(getLink("List of Publishers", "/apps/pulp/gui/reports/publishers")));
        report.append(getLi(getLink("List of Years", "/apps/pulp/gui/reports/years")));

        endUl(report);
        endBodyAndPage(report);

        return report.toString();
    }

    private String getLi(String text) {
        return String.format("<li>%s</li>%n", text);
    }

    private String getLink(String text, String href) {
        return String.format("<a href='%s'>%s</a>", href, text);
    }

    private void addHeader(String title, StringBuilder report) {
        report.append("<html><head>\n");
        report.append(title);
        report.append("</head>\n");
    }


}
