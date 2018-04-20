package com.seleniumsimplified.pulp.html.gui;

import com.seleniumsimplified.pulp.PulpApp;
import com.seleniumsimplified.pulp.reader.SavageReader;
import com.seleniumsimplified.pulp.reporting.filtering.BookFilter;
import org.junit.Assert;
import org.junit.Test;

public class AlertSearchPageTest {

    @Test
    public void haveSearchPage(){
        PulpApp app = new PulpApp();
        app.readData( new SavageReader("/data/pulp/doc_savage_test.csv"));

        AlertSearchPage page = new AlertSearchPage();

        //System.out.println(page.asHTMLString());

        Assert.assertNotEquals("",page.asHTMLString());
    }

    @Test
    public void haveSearchPageWhichCanShowSearches(){
        PulpApp app = new PulpApp();
        app.readData( new SavageReader("/data/pulp/doc_savage_test.csv"));

        AlertSearchPage page = new AlertSearchPage().setSearchTerms("title", "contains", "The");
        page.setConfirmSearch(true);
        page.setDataFrom(app);

        String pageToRender = page.asHTMLString();
        System.out.println(pageToRender);

        Assert.assertNotEquals("",pageToRender);

    }
}
