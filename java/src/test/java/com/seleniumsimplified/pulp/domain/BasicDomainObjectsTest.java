package com.seleniumsimplified.pulp.domain;

import com.seleniumsimplified.pulp.reader.SavageReader;
import com.seleniumsimplified.seleniumtestpages.CsvReader;
import com.seleniumsimplified.seleniumtestpages.ResourceReader;
import org.junit.Assert;
import org.junit.Test;

public class BasicDomainObjectsTest {

    @Test
    public void canCreateACharacter(){
        PulpHero doc = new PulpHero("Doc Savage");
        Assert.assertEquals("Doc Savage", doc.getName());
        Assert.assertEquals("DocSavage", doc.getId());

        // TODO: create character with an index
    }

    @Test
    public void canCreateACharacterWithDefaultIdWithSpaces(){
        PulpHero doc = new PulpHero("Doc Savage Junior");
        Assert.assertEquals("Doc Savage Junior", doc.getName());
        Assert.assertEquals("DocSavageJunior", doc.getId());
    }

    @Test
    public void canCreateASeries(){
        PulpSeries docSavage = new PulpSeries("Doc Savage");
        Assert.assertEquals("Doc Savage", docSavage.getName());
        Assert.assertEquals("DocSavage", docSavage.getId());

        // TODO: create series with an index
    }

    @Test
    public void canCreateASeriesWithDefaultIdWithSpaces(){
        PulpSeries spider = new PulpSeries("Spider The Master Of Men");
        Assert.assertEquals("Spider The Master Of Men", spider.getName());
        Assert.assertEquals("SpiderTheMasterOfMen", spider.getId());
    }

    @Test
    public void canCreateABook(){

        // indexes do not have spaces
        PulpBook savageOne = new PulpBook("Doc Savage", "Lester Dent", "Kenneth Robeson","The Man of Bronze", "March, 1933", 1933, "Street And Smith");
        Assert.assertEquals("DocSavage", savageOne.getSeriesIndex());
        Assert.assertEquals("LesterDent", savageOne.getAuthorIndex());
        Assert.assertEquals("KennethRobeson", savageOne.getHouseAuthorIndex());
        Assert.assertEquals("The Man of Bronze", savageOne.getTitle());
        Assert.assertEquals("March, 1933", savageOne.getSeriesId());
        Assert.assertEquals(1933, savageOne.getPublicationYear());
        Assert.assertEquals("StreetAndSmith", savageOne.getPublisherIndex());
    }

    @Test
    public void canCreateAnAuthor(){
        PulpAuthor lesterDent = new PulpAuthor("Lester Dent");
        Assert.assertEquals("Lester Dent", lesterDent.getName());
        Assert.assertEquals("LesterDent", lesterDent.getId());

        PulpAuthor callMeKenneth = new PulpAuthor("Kenneth", "Kenneth Robeson");
        Assert.assertEquals("Kenneth Robeson", callMeKenneth.getName());
        Assert.assertEquals("Kenneth", callMeKenneth.getId());
    }

    @Test
    public void canCreateAPublisher(){
        PulpPublisher sas = new PulpPublisher("Street And Smith");
        Assert.assertEquals("Street And Smith", sas.getName());
        Assert.assertEquals("StreetAndSmith", sas.getId());

        sas = new PulpPublisher("SAS", "Street and Smith");
        Assert.assertEquals("Street and Smith", sas.getName());
        Assert.assertEquals("SAS", sas.getId());
    }

    @Test
    public void canReadSavage(){
        CsvReader reader = new CsvReader("/data/pulp/doc_savage_test.csv");
        reader.read();

        Assert.assertEquals(5, reader.numberOfLines());

        String fields[] = reader.getLines(0).split("\",\"");
        Assert.assertEquals(4, fields.length);

        Assert.assertEquals("Lester Dent", reader.getLineField(0,0));
        Assert.assertEquals("The Angry Canary", reader.getLineField(0,1));
        Assert.assertEquals("Jul / Aug, 1948", reader.getLineField(0,2));
        Assert.assertEquals("1948", reader.getLineField(0,3));

    }

    @Test
    public void canUseSavageReader(){
        SavageReader reader = new SavageReader("/data/pulp/doc_savage_test.csv");

        Assert.assertEquals(5, reader.numberOfLines());

        PulpBook book = reader.getBook(0);

        Assert.assertEquals("KennethRobeson", book.getHouseAuthorIndex());
        Assert.assertEquals("StreetAndSmith", book.getPublisherIndex());
        Assert.assertEquals("LesterDent", book.getAuthorIndex());
        Assert.assertEquals("The Angry Canary", book.getTitle());
        Assert.assertEquals("Jul / Aug, 1948", book.getSeriesId());
        Assert.assertEquals(1948, book.getPublicationYear());

    }

    // I could use an in memory database but I'm much more likely to make a mistake if I don't, and this is a test app
    // TODO create books ("database") class
    // TODO create classes for collection of authors, publishers, heroes, series, books
    //   e.g. books.books()
    // TODO create numeric indexes when adding an entity
    // TODO replace the text index in book e.g. KennethRobeson with the numeric index
    // TODO: books.authors().get("1")
    // TODO: books.authors().delete(1) - delete all books from that author

    // TODO create unit tests for each of the pages and apps routings

}
