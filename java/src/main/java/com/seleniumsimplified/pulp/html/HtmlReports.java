package com.seleniumsimplified.pulp.html;

import com.seleniumsimplified.pulp.PulpData;
import com.seleniumsimplified.pulp.reporting.PulpReporter;
import com.seleniumsimplified.pulp.reporting.ReportConfig;
import com.seleniumsimplified.pulp.reporting.filtering.BookFilter;

import java.util.Collection;

import static com.seleniumsimplified.pulp.html.HTMLElements.getLi;
import static com.seleniumsimplified.pulp.html.HTMLElements.getLink;

public class HtmlReports {
    private final PulpReporter reporter;
    private ReportConfig reportConfig;

    public HtmlReports(PulpData books) {
        reporter = new PulpReporter(books);
        reportConfig = ReportConfig.justStrings();
        reporter.configure(reportConfig);
    }

    public String getBooksAsHtmlList(BookFilter filter) {
        return  reportCollectionAsLi(reporter.getBooksAsStrings(filter), "Books");
    }

    public String getBooksAsHtmlList() {
        return getBooksAsHtmlList(new BookFilter());
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

    public String getSeriesNamesAsHtmlList() {
        return reportCollectionAsLi(reporter.getSeriesNamesAsStrings(), "Series");
    }


    private String reportCollectionAsLi(Collection<String> simpleReport, String listOfWhat){

        StringBuilder report = new StringBuilder();

        addHeader(String.format("<title>List of %s</title>%n", listOfWhat), report);

        startBody(report);

        startUl(report);

        report.append(String.format("<h1>List of %s</h1>%n", listOfWhat));

        for(String reportLine : simpleReport){
            report.append(getLi(reportLine));
        }
        endUl(report);

        addReportList(report);

        endBodyAndPage(report);

        return report.toString();

    }

    private void endUl(StringBuilder report) {
        report.append(HTMLElements.endUl());
    }

    private void startUl(StringBuilder report) {
        report.append(HTMLElements.startUl());
    }

    private void endBodyAndPage(StringBuilder report) {
        report.append(HTMLElements.endBody() + HTMLElements.endHTML());
    }

    private void startBody(StringBuilder report) {
        report.append(HTMLElements.startBody());
    }

    public String getIndexPage(){

        StringBuilder report = new StringBuilder();
        addHeader("Pulp App Menu", report);
        startBody(report);

        addReportList(report);

        endBodyAndPage(report);

        return report.toString();
    }

    private void addReportList(StringBuilder report) {

        report.append("<h2>Reports List</h2>");
        startUl(report);

        report.append(getLi(getLink("List of Books", "/apps/pulp/gui/reports/books")));
        report.append(getLi(getLink("List of Authors", "/apps/pulp/gui/reports/authors")));
        report.append(getLi(getLink("List of Publishers", "/apps/pulp/gui/reports/publishers")));
        report.append(getLi(getLink("List of Years", "/apps/pulp/gui/reports/years")));
        report.append(getLi(getLink("List of Series", "/apps/pulp/gui/reports/series")));
        report.append(getLi(getLink("Search Title", "/apps/pulp/gui/reports/books/search")));

        endUl(report);
    }


    private void addHeader(String title, StringBuilder report) {
        report.append(HTMLElements.htmlHeadWithTitle(title));
    }


    public void configure(ReportConfig reportConfig) {
        this.reportConfig = reportConfig;
        this.reporter.configure(this.reportConfig);
    }


}
