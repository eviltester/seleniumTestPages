package com.seleniumsimplified.pulp.domain;

import com.seleniumsimplified.pulp.PulpApp;
import com.seleniumsimplified.pulp.PulpData;
import com.seleniumsimplified.pulp.reader.PulpDataPopulator;
import com.seleniumsimplified.pulp.reader.SavageReader;
import com.seleniumsimplified.pulp.reporting.PulpReporter;
import com.seleniumsimplified.seleniumtestpages.CsvReader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
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
        Assert.assertEquals("Lester Dent", savageOne.getAuthorIndexesAsString());
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

        Assert.assertEquals("Lester Dent / Will Murray", reader.getLineField(0,0));
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
        Assert.assertEquals("Lester Dent", book.getAuthorIndexes().get(0));
        Assert.assertEquals("Will Murray", book.getAuthorIndexes().get(1));
        Assert.assertEquals("The Angry Canary", book.getTitle());
        Assert.assertEquals("Jul / Aug, 1948", book.getSeriesId());
        Assert.assertEquals(1948, book.getPublicationYear());

    }

    @Test
    public void canReadAuthorNamesFromSavageReader(){

        SavageReader reader = new SavageReader("/data/pulp/doc_savage_test.csv");

        List<String> authorNames = reader.getAuthorNames();
        Assert.assertEquals(3, authorNames.size());
        Assert.assertTrue(authorNames.contains("Kenneth Robeson"));
        Assert.assertTrue(authorNames.contains("Lester Dent"));
        Assert.assertTrue(authorNames.contains("Will Murray"));

    }

    @Test
    public void canHaveAStaticDataPopulator(){

        PulpData books = new PulpData();
        PulpDataPopulator populator = new PulpDataPopulator(books);
        SavageReader reader = new SavageReader("/data/pulp/doc_savage_test.csv");
        populator.populateFrom(reader);

        Assert.assertEquals(3, books.authors().count());

        // not convinced this will always be true - ordering may be different on some machines
        Assert.assertEquals("Lester Dent", books.authors().get("1").getName());

        Assert.assertEquals("Lester Dent", books.authors().findByName("Lester Dent").getName());


        Assert.assertEquals(1, books.publishers().count());
        Assert.assertEquals("Street And Smith", books.publishers().get("1").getName());

        Assert.assertEquals(1, books.series().count());
        Assert.assertEquals("Doc Savage", books.series().get("1").getName());

        Assert.assertEquals(5, books.books().count());

        Assert.assertEquals("Kenneth Robeson", books.authors().get(books.books().get("1").getHouseAuthorIndex()).getName());
        Assert.assertEquals("Lester Dent", books.authors().get(books.books().get("1").getAuthorIndexes().get(0)).getName());
        Assert.assertEquals("Will Murray", books.authors().get(books.books().get("1").getAuthorIndexes().get(1)).getName());

        String bookID = books.books().keys().get(0);
        PulpBook book = books.books().get(bookID);
        Assert.assertEquals("Doc Savage", books.series().get(book.getSeriesIndex()).getName());

        Assert.assertEquals("The Angry Canary", books.books().get("1").getTitle());
        Assert.assertEquals("The Swooning Lady", books.books().get("2").getTitle());


    }

    @Test
    public void canReportOnData(){

        PulpData books = new PulpData();
        PulpDataPopulator populator = new PulpDataPopulator(books);
        SavageReader reader = new SavageReader("/data/pulp/doc_savage_test.csv");
        populator.populateFrom(reader);


        PulpReporter reporter = new PulpReporter(books);

        Collection<String> simpleReport = reporter.getBooksAsStrings();

        for(String reportLine : simpleReport){
            System.out.println(reportLine);
        }

        Assert.assertEquals(simpleReport.size(), books.books().count());

    }

    @Test
    public void haveBasicAppWrapperForBooks(){
        PulpApp app = new PulpApp("/data/pulp/doc_savage_test.csv");
        System.out.println(app.reports().getBooksAsHtmlList());
        Assert.assertTrue(app.reports().getBooksAsHtmlList().contains("<li>The Angry Canary | Lester Dent"));
    }


    @Test
    public void haveBasicAppWrapperForAuthors(){
        PulpApp app = new PulpApp("/data/pulp/doc_savage_test.csv");
        System.out.println(app.reports().getAuthorsAsHtmlList());
        Assert.assertTrue(app.reports().getAuthorsAsHtmlList().contains("<li>Lester Dent</li>"));
    }

    // I could use an in memory database but I'm much more likely to make a mistake if I don't, and this is a test app so mistakes are OK
    // TODO: Add data for The Spider, The Avenger
    // TODO: add report classes to use for JSON and XML serialisation
    // TODO: create a list of basic end points and add methods to support end point reporting
    // TODO: add a basic REST API for get requests and reporting
    // TODO: add a simple GUI that uses the REST API for reporting the data
    // TODO: add an AJAX based GUI that uses the REST API for reporting the data
    // TODO: can READ and get without authentication
    // TODO: add a single admin user hard coded
    // TODO: can see an admin interface when logged in
    // TODO: expand back end for delete, replace, partial amend - require admin user permissions and authentication
    // TODO: books.authors().delete(1) - delete all books from that author
    // TODO: add other users
    // TODO: add permissions on entities, created-by, owner
    // TODO: amendment based on permissions of user
    // TODO: the static data reader and collection classes were created with a high level exploratory test - not low level TDD, really neeed class specific tests on the collections
    //       20180415 bugs that slipped through because of this - hard coded paths, not reading books as unique books - i.e. every book was first in csv file

    // TODO create unit tests for each of the pages and apps routings

}
