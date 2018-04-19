package com.seleniumsimplified.pulp.domain;

import com.seleniumsimplified.pulp.PulpApp;
import com.seleniumsimplified.pulp.PulpData;
import com.seleniumsimplified.pulp.reader.SavageReader;
import com.seleniumsimplified.pulp.reader.SpiderReader;
import com.seleniumsimplified.pulp.reader.TheAvengerReader;
import com.seleniumsimplified.pulp.reporting.filtering.BookFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class FilterDataTest {

    @Test
    public void canCreateAFilterForAuthorId(){
        PulpApp app = new PulpApp();
        app.readData( new SavageReader("/data/pulp/doc_savage_test.csv"));

        PulpAuthor will = app.books().authors().findByName("Will Murray");
        BookFilter filter = new BookFilter();
        filter.where().author(will.getId());

        List<PulpBook> books = app.books().books().filteredBy(filter);

        Assert.assertEquals(1, books.size());
        Assert.assertEquals("The Angry Canary", books.get(0).getTitle());

    }

    @Test
    public void canCreateAFilterForPublicationYear(){
        PulpApp app = new PulpApp();
        app.readData( new SavageReader("/data/pulp/doc_savage_test.csv"));

        PulpAuthor will = app.books().authors().findByName("Will Murray");
        BookFilter filter = new BookFilter();
        filter.where().publishedInYear(1949);

        List<PulpBook> books = app.books().books().filteredBy(filter);

        Assert.assertEquals(3, books.size());


        filter.where().publishedInYear(1948);

        books = app.books().books().filteredBy(filter);

        Assert.assertEquals(2, books.size());

    }

    @Test
    public void canCreateAFilterForPublisher(){
        PulpApp app = new PulpApp();
        app.readData( new TheAvengerReader("/data/pulp/the_avenger_test.csv"));

        PulpPublisher smith = app.books().publishers().findByName("Street And Smith");
        BookFilter filter = new BookFilter();
        filter.where().publishedBy(smith.getId());

        List<PulpBook> books = app.books().books().filteredBy(filter);

        Assert.assertEquals(5, books.size());

        PulpPublisher warner = app.books().publishers().findByName("Warner Paperback Library");

        filter = new BookFilter();
        filter.where().publishedBy(warner.getId());

        books = app.books().books().filteredBy(filter);

        Assert.assertEquals(3, books.size());

    }

    @Test
    public void canCreateAFilterForPublisherAndYear(){
        PulpApp app = new PulpApp();
        app.readData( new TheAvengerReader("/data/pulp/the_avenger_test.csv"));

        PulpPublisher smith = app.books().publishers().findByName("Street And Smith");
        BookFilter filter = new BookFilter();
        filter.where().publishedBy(smith.getId()).and().publishedInYear(1942);

        List<PulpBook> books = app.books().books().filteredBy(filter);

        Assert.assertEquals(2, books.size());

    }

    @Test
    public void canCreateAFilterForSeries(){
        PulpApp app = new PulpApp();
        app.readData( new TheAvengerReader("/data/pulp/the_avenger_test.csv"));
        app.readData( new SavageReader("/data/pulp/doc_savage_test.csv"));

        Assert.assertEquals(13, app.books().books().count());

        BookFilter filter = new BookFilter();
        filter.where().partOfSeries(app.books().series().findByName("Doc Savage").getId());

        List<PulpBook> books = app.books().books().filteredBy(filter);

        Assert.assertEquals(5, books.size());

        filter = new BookFilter();
        filter.where().partOfSeries(app.books().series().findByName("The Avenger").getId());
        books = app.books().books().filteredBy(filter);
        Assert.assertEquals(8, books.size());

    }

    @Test
    public void canCreateAFilterForPublisherAndYearAndAuthor(){
        PulpApp app = new PulpApp();
        app.readData( new TheAvengerReader("/data/pulp/the_avenger_test.csv"));

        PulpPublisher smith = app.books().publishers().findByName("Street And Smith");
        BookFilter filter = new BookFilter();
        filter.where().publishedBy(smith.getId());

        List<PulpBook> books = app.books().books().filteredBy(filter);
        Assert.assertEquals(5, books.size());

        PulpAuthor emile = app.books().authors().findByName("Emile C. Tepperman");

        filter.and().author(emile.getId());

        books = app.books().books().filteredBy(filter);

        Assert.assertEquals(3, books.size());

        filter.and().publishedInYear(1941);

        books = app.books().books().filteredBy(filter);

        Assert.assertEquals(1, books.size());
    }
}
