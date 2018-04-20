package com.seleniumsimplified.pulp.html;

import com.seleniumsimplified.pulp.domain.groupings.PulpData;
import com.seleniumsimplified.pulp.reporting.PulpReporter;
import com.seleniumsimplified.pulp.reporting.ReportConfig;
import com.seleniumsimplified.pulp.reporting.filtering.BookFilter;
import com.seleniumsimplified.pulp.reporting.reporters.BookReporter;
import com.seleniumsimplified.seleniumtestpages.ResourceReader;

import java.util.Collection;

public class HtmlReports {
    private final PulpReporter reporter;
    private final PulpData data;
    private ReportConfig reportConfig;

    public HtmlReports(PulpData books) {
        this.data = books;
        reporter = new PulpReporter(books);
        reportConfig = ReportConfig.justStrings();
        reporter.configure(reportConfig);
    }

    public String getBooksAsHtmlList(BookFilter filter) {
        BookReporter bReporter = new BookReporter(reportConfig, data.authors(), data.publishers(), data.series() );
        return  reportCollectionAsLiPage(bReporter.getBooksAsLines(this.data.books().filteredBy(filter)), "Books");
    }

    public String getBooksAsHtmlTable(BookFilter filter) {
        BookReporter bReporter = new BookReporter(reportConfig, data.authors(), data.publishers(), data.series() );
        return reportCollectionAsTablePage(bReporter.getBooksAsTable(this.data.books().filteredBy(filter)), "Books");
    }


    public String getBooksAsHtmlList() {
        return getBooksAsHtmlList(new BookFilter());
    }

    public String getAuthorsAsHtmlList() {
        return reportCollectionAsLiPage(reporter.getAuthorsAsStrings(), "Authors");
    }


    public String getPublishersAsHtmlList() {
        return reportCollectionAsLiPage(reporter.getPublishersAsStrings(), "Publishers");
    }

    public String getYearsAsHtmlList() {
        return reportCollectionAsLiPage(reporter.getYearsAsStrings(), "Years");
    }

    public String getSeriesNamesAsHtmlList() {
        return reportCollectionAsLiPage(reporter.getSeriesNamesAsStrings(), "Series");
    }


    private String reportCollectionAsLiPage(Collection<String> simpleReport, String listOfWhat){

        StringBuilder report = new StringBuilder();

        addHeader(String.format("List of %s", listOfWhat), report);

        startBody(report);

        report.append(String.format("<h1>List of %s</h1>%n", listOfWhat));

        report.append(new HTMLReporter().getAsUl(simpleReport));


        addReportList(report);

        endBodyAndPage(report);

        return report.toString();

    }

    private String reportCollectionAsTablePage(String thingsAsTable, String things) {
        StringBuilder report = new StringBuilder();

        addHeader(String.format("Table of %s", things), report);

        startBody(report);

        report.append(String.format("<h1>Table of %s</h1>%n", things));

        report.append(thingsAsTable);


        addReportList(report);

        endBodyAndPage(report);

        return report.toString();
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

        report.append(new ResourceReader().asString("/web/apps/pulp/page-template/reports-list-widget.html"));

    }


    private void addHeader(String title, StringBuilder report) {
        report.append(HTMLElements.htmlHeadWithTitle(title));
    }


    public HtmlReports configure(ReportConfig reportConfig) {
        this.reportConfig = reportConfig;
        this.reporter.configure(this.reportConfig);
        return this;
    }


    public HtmlReports configurePostFixPath(String linksPostFix) {
        this.reportConfig.setPostFixPath(linksPostFix);
        this.reporter.configure(this.reportConfig);
        return this;
    }
}
