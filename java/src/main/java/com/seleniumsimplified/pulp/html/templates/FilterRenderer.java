package com.seleniumsimplified.pulp.html.templates;

import com.seleniumsimplified.pulp.reporting.filtering.BookFilter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class FilterRenderer {
    private final BookFilter filter;

    public FilterRenderer(BookFilter filter) {
        this.filter = filter;
    }

    // TODO: create a map of url arguments
    public String asUrlArgsList(int page) {
        if(filter==null){
            return "page="+page;
        }else{
            StringBuilder url = new StringBuilder();
            if(filter.isPaging()){
                url.append(String.format("page=%d&pagelimit=%d&", page, filter.getNumberPerPage()));
            }
            if(filter.isByPartialTitleMatch()){
                try {
                    url.append(String.format("searchterm=%s&",URLEncoder.encode(filter.getPartialTitleMatchString(),"UTF-8")));
                } catch (UnsupportedEncodingException e) {
                    System.out.println("Failed to encode " + filter.getPartialTitleMatchString());
                    e.printStackTrace();
                    url.append(String.format("searchterm=%s&",filter.getPartialTitleMatchString()));
                }
            }
            if(filter.isBySeries()){
                url.append(String.format("series=%s&", filter.getSeriesId()));
            }
            if(filter.isByPublisher()){
                url.append(String.format("publisher=%s&", filter.getPublisherId()));
            }
            if(filter.isByYear()){
                url.append(String.format("year=%d&", filter.getYear()));
            }
            if(filter.isByAuthor()){
                url.append(String.format("author=%s&", filter.getAuthorId()));
            }
            if(url.lastIndexOf("&") == url.length()-1){
                url.replace(url.length()-1, url.length(),"");
            }
            return url.toString();
        }
    }
}
