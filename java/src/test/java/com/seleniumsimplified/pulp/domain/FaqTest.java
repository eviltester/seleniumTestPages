package com.seleniumsimplified.pulp.domain;

import com.seleniumsimplified.pulp.domain.faq.Faq;
import com.seleniumsimplified.pulp.domain.faq.SearchEngine;
import org.junit.Assert;
import org.junit.Test;

public class FaqTest {

    @Test
    public void faqsExist(){
        Faq faq = new Faq("Who was Pulp author !!name!!");
        Assert.assertEquals("Who was Pulp author !!name!!", faq.getTemplate());
    }

    @Test
    public void searchEngine(){
        SearchEngine google = new SearchEngine("https://www.google.com/search?q=");
        Assert.assertEquals("https://www.google.com/search?q=", google.getSearchTerm());
//        https://www.bing.com/search?q=test+this


        // The following search engines do not allow embedding
        // https://www.google.com/search?q=
        //https://duckduckgo.com/?q=
    }


}
