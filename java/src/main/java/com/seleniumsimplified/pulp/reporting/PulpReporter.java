package com.seleniumsimplified.pulp.reporting;

import com.seleniumsimplified.pulp.PulpData;
import com.seleniumsimplified.pulp.domain.PulpAuthor;
import com.seleniumsimplified.pulp.domain.PulpBook;
import com.seleniumsimplified.pulp.domain.PulpPublisher;
import com.seleniumsimplified.pulp.domain.PulpSeries;
import com.seleniumsimplified.pulp.reporting.filtering.BookFilter;

import java.util.*;

public class PulpReporter {
    private final PulpData data;
    private ReportConfig reportConfig;

    public PulpReporter(PulpData books) {
        this.data = books;
        this.reportConfig = ReportConfig.justStrings();
    }

    public PulpData data() {
        return data;
    }

    public Collection<String> getBooksAsStrings() {

        return getBooksAsStrings(new BookFilter());
    }

    public Collection<String> getBooksAsStrings(BookFilter filter) {

        List<String> report = new ArrayList<>();

        List<PulpBook> filteredSet = data.books().filteredBy(filter);

        for(PulpBook book : filteredSet){
            report.add(getLineFrom(book));
        }

        return report;
    }


    private String getLineFrom(PulpBook book) {
        StringBuilder line;

        line = new StringBuilder();

        line.append(book.getTitle());
        line.append(" | ");

        for(String authorId : book.getAllAuthorIndexes()){
            line.append(getAuthorName(data.authors().get(authorId)));
            line.append(", ");
        }
        line.replace(line.lastIndexOf(","), line.lastIndexOf(",")+1, "");
        line.append(" | ");

        line.append(getYear(book.getPublicationYear()));
        line.append(" | ");

        line.append(getPublisher(data.publishers().get(book.getPublisherIndex())));
        line.append(" | ");

        line.append(getSeries(data.series().get(book.getSeriesIndex())));

        return line.toString();
    }

    private String getSeries(PulpSeries item) {
        if(reportConfig!=null && reportConfig.areSeriesNamesLinks()){
            return String.format("<a href='%sbooks?series=%s'>%s</a>", reportConfig.getReportPath(), item.getId(),item.getName());
        }else{
            return item.getName();
        }
    }


    private String getAuthorName(PulpAuthor author) {

        if(reportConfig!=null && reportConfig.areAuthorNamesLinks()){
            return String.format("<a href='%sbooks?author=%s'>%s</a>", reportConfig.getReportPath(), author.getId(),author.getName());
        }else{
            return author.getName();
        }

    }

    private String getYear(int publicationYear) {
        if(reportConfig!=null && reportConfig.areYearsLinks()){
            return String.format("<a href='%sbooks?year=%d'>%d</a>", reportConfig.getReportPath(), publicationYear, publicationYear);
        }else{
            return String.valueOf(publicationYear);
        }
    }

    private String getPublisher(PulpPublisher item) {
        if(reportConfig!=null && reportConfig.arePublishersLinks()){
            return String.format("<a href='%sbooks?publisher=%s'>%s</a>", reportConfig.getReportPath(), item.getId(), item.getName());
        }else{
            return item.getName();
        }
    }


    public Collection<String> getAuthorsAsStrings() {
        List<String> report = new ArrayList<>();
        StringBuilder line;

        List<String> keys = data.authors().keys();

        for(String key : keys) {
            line = new StringBuilder();
            PulpAuthor author = data.authors().get(key);
            line.append(getAuthorName(author));

            report.add(line.toString());
        }

        return report;

    }

    public Collection<String> getPublishersAsStrings() {
            List<String> report = new ArrayList<>();
            StringBuilder line;

            for(String key : data.publishers().keys()) {
                line = new StringBuilder();
                PulpPublisher item = data.publishers().get(key);
                line.append(getPublisher(item));

                report.add(line.toString());
            }

            return report;

    }


    public Collection<String> getYearsAsStrings() {
        List<String> report = new ArrayList<>();

        List<String> keys = data.books().keys();

        Set<Integer> years = new TreeSet<>();

        for(String key : keys) {
            PulpBook book = data.books().get(key);
            years.add(Integer.valueOf(book.getPublicationYear()));
        }


        for(Integer year : years){
            report.add(getYear(year));
        }

        return report;
    }

    public Collection<String> getSeriesNamesAsStrings() {

        List<String> keys = data.series().keys();

        Set<String> itemNames = new TreeSet<>();

        for(String key : keys) {
            PulpSeries item = data.series().get(key);
            itemNames.add(getSeries(item));
        }

        return itemNames;
    }


    public void configure(ReportConfig reportConfig) {
        this.reportConfig = reportConfig;
    }


}
