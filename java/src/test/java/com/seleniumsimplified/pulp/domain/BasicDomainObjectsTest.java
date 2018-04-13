package com.seleniumsimplified.pulp.domain;

import com.seleniumsimplified.pulp.PulpData;
import com.seleniumsimplified.pulp.reader.PulpDataPopulator;
import com.seleniumsimplified.pulp.reader.SavageReader;
import com.seleniumsimplified.seleniumtestpages.CsvReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BasicDomainObjectsTest {

    @Test
    public void canCreateACharacter(){
        PulpHero doc = new PulpHero("Doc Savage");
        Assert.assertEquals("Doc Savage", doc.getName());
        Assert.assertEquals("unknown", doc.getId());

        // TODO: create character with an index
    }


    @Test
    public void canCreateASeries(){
        PulpSeries docSavage = new PulpSeries("Doc Savage");
        Assert.assertEquals("Doc Savage", docSavage.getName());
        Assert.assertEquals("unknown", docSavage.getId());

        PulpSeries spider = new PulpSeries("1", "The Spider");
        Assert.assertEquals("The Spider", spider.getName());
        Assert.assertEquals("1", spider.getId());
    }


    @Test
    public void canCreateABook(){

        // indexes do not have spaces
        PulpBook savageOne = new PulpBook("1","Doc Savage", "Lester Dent", "Kenneth Robeson","The Man of Bronze", "March, 1933", 1933, "Street And Smith");
        Assert.assertEquals("1", savageOne.getId());
        Assert.assertEquals("Doc Savage", savageOne.getSeriesIndex());
        Assert.assertEquals("Lester Dent", savageOne.getAuthorIndex());
        Assert.assertEquals("Kenneth Robeson", savageOne.getHouseAuthorIndex());
        Assert.assertEquals("The Man of Bronze", savageOne.getTitle());
        Assert.assertEquals("March, 1933", savageOne.getSeriesId());
        Assert.assertEquals(1933, savageOne.getPublicationYear());
        Assert.assertEquals("Street And Smith", savageOne.getPublisherIndex());
    }

    @Test
    public void canCreateAnAuthor(){
        PulpAuthor lesterDent = new PulpAuthor("Lester Dent");
        Assert.assertEquals("Lester Dent", lesterDent.getName());
        Assert.assertEquals("unknown", lesterDent.getId());

        PulpAuthor callMeKenneth = new PulpAuthor("Kenneth", "Kenneth Robeson");
        Assert.assertEquals("Kenneth Robeson", callMeKenneth.getName());
        Assert.assertEquals("Kenneth", callMeKenneth.getId());
    }

    @Test
    public void canCreateAPublisher(){
        PulpPublisher sas = new PulpPublisher("Street And Smith");
        Assert.assertEquals("Street And Smith", sas.getName());
        Assert.assertEquals("unknown", sas.getId());

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
    public void canUseSavageReader() {
        SavageReader reader = new SavageReader("/data/pulp/doc_savage_test.csv");

        Assert.assertEquals(5, reader.numberOfLines());

        PulpBook book = reader.getBook(0);

        Assert.assertEquals("Kenneth Robeson", book.getHouseAuthorIndex());
        Assert.assertEquals("Street And Smith", book.getPublisherIndex());
        Assert.assertEquals("Lester Dent", book.getAuthorIndex());
        Assert.assertEquals("The Angry Canary", book.getTitle());
        Assert.assertEquals("Jul / Aug, 1948", book.getSeriesId());
        Assert.assertEquals(1948, book.getPublicationYear());

    }

    @Test
    public void canReadAuthorNamesFromSavageReader(){

        SavageReader reader = new SavageReader("/data/pulp/doc_savage_test.csv");

        List<String> authorNames = reader.getAuthorNames();
        Assert.assertEquals(2, authorNames.size());
        Assert.assertTrue(authorNames.contains("Kenneth Robeson"));
        Assert.assertTrue(authorNames.contains("Lester Dent"));
    }

    @Test
    public void canHaveAStaticDataPopulator(){

        PulpData books = new PulpData();
        PulpDataPopulator populator = new PulpDataPopulator(books);
        SavageReader reader = new SavageReader("/data/pulp/doc_savage_test.csv");
        populator.populateFrom(reader);

        Assert.assertEquals(2, books.authors().count());

        // not convinced this will always be true - ordering may be different on some machines
        Assert.assertEquals("Lester Dent", books.authors().get("1").getName());

        Assert.assertEquals("Lester Dent", books.authors().findByName("Lester Dent").getName());


        Assert.assertEquals(1, books.publishers().count());
        Assert.assertEquals("Street And Smith", books.publishers().get("1").getName());

        Assert.assertEquals(1, books.series().count());
        Assert.assertEquals("Doc Savage", books.series().get("1").getName());

        Assert.assertEquals(5, books.books().count());

        Assert.assertEquals("Kenneth Robeson", books.authors().get(books.books().get("1").getHouseAuthorIndex()).getName());
        Assert.assertEquals("Lester Dent", books.authors().get(books.books().get("1").getAuthorIndex()).getName());

    }

    // I could use an in memory database but I'm much more likely to make a mistake if I don't, and this is a test app
    // TODO: books.authors().delete(1) - delete all books from that author
    // TODO: the static data reader and collection classes were created with a high level exploratory test - not low level TDD, really neeed class specific tests on the collections

    // TODO create unit tests for each of the pages and apps routings

}
