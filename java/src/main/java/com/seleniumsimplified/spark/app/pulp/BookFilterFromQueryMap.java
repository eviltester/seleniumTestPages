package com.seleniumsimplified.spark.app.pulp;

import com.seleniumsimplified.pulp.reporting.filtering.BookFilter;
import spark.QueryParamsMap;

public class BookFilterFromQueryMap {
    public static BookFilter getBookFilter(QueryParamsMap queryParamsMap) {
        BookFilter filter = new BookFilter();
        if(queryParamsMap.hasKeys() && queryParamsMap.value("author")!=null){
            filter.author(queryParamsMap.value("author"));
        }
        if(queryParamsMap.hasKeys() && queryParamsMap.value("year")!=null){
            filter.publishedInYear(Integer.valueOf(queryParamsMap.value("year")));
        }
        if(queryParamsMap.hasKeys() && queryParamsMap.value("publisher")!=null){
            filter.publishedBy(queryParamsMap.value("publisher"));
        }
        if(queryParamsMap.hasKeys() && queryParamsMap.value("series")!=null){
            filter.partOfSeries(queryParamsMap.value("series"));
        }
        return filter;
    }
}
