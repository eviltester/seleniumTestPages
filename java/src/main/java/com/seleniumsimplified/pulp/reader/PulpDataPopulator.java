package com.seleniumsimplified.pulp.reader;

import com.seleniumsimplified.pulp.PulpData;
import com.seleniumsimplified.pulp.PulpPublishers;
import com.seleniumsimplified.pulp.domain.PulpAuthor;
import com.seleniumsimplified.pulp.domain.PulpBook;
import com.seleniumsimplified.pulp.domain.PulpPublisher;
import com.seleniumsimplified.pulp.domain.PulpSeries;

import javax.print.DocFlavor;
import java.util.List;

public class PulpDataPopulator {
    private final PulpData data;
    private SavageReader reader;

    public PulpDataPopulator(PulpData data) {
        this.data = data;
    }

    public void populateFrom(SavageReader reader) {
        this.reader = reader;

        addTheAuthorNames(reader, data);
        addThePublisherNames(reader, data);
        addTheSeriesNames(reader, data);
        addTheBookData(reader, data);

    }

    private void addThePublisherNames(SavageReader reader, PulpData data) {
        List<String> names = reader.getPublisherNames();

        for(String publisherName : names){
            data.publishers().add(publisherName);
        }

    }

    private void addTheAuthorNames(SavageReader reader, PulpData data) {
        List<String> names = reader.getAuthorNames();

        for(String authorName : names){
            data.authors().add(authorName);
        }
    }

    private void addTheSeriesNames(SavageReader reader, PulpData data) {
        List<String> names = reader.getPulpSeries();

        for(String seriesName : names){
            data.series().add(seriesName);
        }
    }


    private void addTheBookData(SavageReader reader, PulpData data) {

        for(int bookNumber=0; bookNumber < reader.numberOfLines(); bookNumber++){

            PulpBook aDraftBook = reader.getBook(bookNumber);

            String title = aDraftBook.getTitle();
            int publicationYear = aDraftBook.getPublicationYear();
            String seriesId = aDraftBook.getSeriesId();

            // find the series index
            PulpSeries series = data.series().findByName(aDraftBook.getSeriesIndex());

            // find the author index
            PulpAuthor author = data.authors().findByName(aDraftBook.getAuthorIndex());

            // find the house author index
            PulpAuthor houseAuthor = data.authors().findByName(aDraftBook.getHouseAuthorIndex());

            // find the publisher index
            PulpPublisher publisher = data.publishers().findByName(aDraftBook.getPublisherIndex());

            if(allIndexesArePresent(series, author, houseAuthor, publisher )){
                data.books().add(series.getId(), author.getId(), houseAuthor.getId(), title, seriesId, publicationYear, publisher.getId());
            }else{
                System.out.println(String.format("Warning: Could not add SERIES %s, %s, %s, by %s (published as %s) by publisher %s", aDraftBook.getSeriesIndex(), title, seriesId, aDraftBook.getAuthorIndex(), aDraftBook.getHouseAuthorIndex(), aDraftBook.getPublisherIndex()));
                System.out.println(String.format("Because: %s", whichIndexesAreMissing(series, author, houseAuthor, publisher)));
            }

        }
    }

    private String whichIndexesAreMissing(PulpSeries series, PulpAuthor author, PulpAuthor houseAuthor, PulpPublisher publisher) {

        String output = "";

        if(series==PulpSeries.UNKNOWN_SERIES){
            output +=" Unknown Series,";
        }
        if(author==PulpAuthor.UNKNOWN_AUTHOR){
            output +=" Unknown Author,";
        }
        if(houseAuthor==PulpAuthor.UNKNOWN_AUTHOR){
            output +=" Unknown House Author,";
        }
        if(publisher==PulpPublisher.UNKNOWN_PUBLISHER){
            output +=" Unknown Publisher,";
        }

        return output;
    }


    private boolean allIndexesArePresent(PulpSeries series, PulpAuthor author, PulpAuthor houseAuthor, PulpPublisher publisher) {
        if(series==PulpSeries.UNKNOWN_SERIES){
            return false;
        }
        if(author==PulpAuthor.UNKNOWN_AUTHOR){
            return false;
        }
        if(houseAuthor==PulpAuthor.UNKNOWN_AUTHOR){
            return false;
        }
        if(publisher==PulpPublisher.UNKNOWN_PUBLISHER){
            return false;
        }


        return true;
    }

}