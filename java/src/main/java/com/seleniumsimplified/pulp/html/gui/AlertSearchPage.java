package com.seleniumsimplified.pulp.html.gui;

import com.seleniumsimplified.pulp.PulpApp;
import com.seleniumsimplified.pulp.domain.PulpBook;
import com.seleniumsimplified.pulp.html.HTMLElements;
import com.seleniumsimplified.pulp.reporting.PulpReporter;
import com.seleniumsimplified.pulp.reporting.ReportConfig;
import com.seleniumsimplified.pulp.reporting.filtering.BookFilter;
import com.seleniumsimplified.seleniumtestpages.ResourceReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AlertSearchPage {


    private String searchWhat="";
    private String searchHow="";
    private String searchTerm="";
    private boolean confirmSearch=true;
    private PulpApp app;

    public String asHTMLString() {
        String pageToRender = new ResourceReader().asString("/web/apps/pulp/page-template/alert-search-page.html");
        pageToRender = pageToRender.replace("!!searchterm!!", searchTerm);
        String checked = confirmSearch ? "checked" : "notchecked";
        pageToRender = pageToRender.replace("!!checked!!", checked);

        if(app!=null && searchWhat.length()>0 && searchHow.length()>0){
            app.reports().configure(ReportConfig.justStrings());

            PulpReporter reporter = new PulpReporter(app.books());

            BookFilter filter = new BookFilter();

            if(searchWhat.equalsIgnoreCase("title") && searchHow.equalsIgnoreCase("contains")){
                filter.titleContains(searchTerm);
            }

            StringBuilder dataOutput = new StringBuilder();

            dataOutput.append("<div id='dataoutput'>");

            Collection<String> outputList = reporter.getBooksAsStrings(filter);

            if(outputList.size()==0){
                dataOutput.append("<p>No Books found Matching Search Criteria</p>");
            }else {
                dataOutput.append(HTMLElements.startUl());
                for (String output : outputList) {
                    dataOutput.append(HTMLElements.getLi(output));
                }
                dataOutput.append(HTMLElements.endUl());
            }

            dataOutput.append("</div>");

            return pageToRender + dataOutput.toString();
        }

        return pageToRender;

    }

    public AlertSearchPage setSearchTerms(String searchWhat, String searchHow, String searchTerm) {
        this.searchWhat = searchWhat;
        this.searchHow = searchHow;
        this.searchTerm = searchTerm;
        return this;
    }

    public AlertSearchPage setConfirmSearch(boolean doWeHaveToConfirmSearch) {
        confirmSearch = doWeHaveToConfirmSearch;
        return this;
    }

    public AlertSearchPage setDataFrom(PulpApp app) {
        this.app = app;
        return this;
    }
}
