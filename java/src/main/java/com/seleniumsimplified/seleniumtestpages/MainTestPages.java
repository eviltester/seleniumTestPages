package com.seleniumsimplified.seleniumtestpages;

import com.seleniumsimplified.seleniumtestpages.php.*;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

/**
 * Created by Alan on 15/06/2016.
 */
public class MainTestPages {

    public static void main(String[] args) {

        staticFileLocation("/web");

        get("/ajaxselect.php", (req, res) -> {return new PhpAjaxSelect(req,res).get();});
        get("/calculate.php", (req, res) -> {return new PhpCalculate(req,res).get();});
        post("/calculate.php", (req, res) -> {return new PhpCalculate(req,res).post();});
        get("/refresh.php", (req, res) -> {return new PhpRefresh(req,res).get();});

        post("/form_processor.php", (req, res) -> {return new PhpFormProcessor(req,res).post();});

        //search.php  - do not use a search engine, just have a set of random urls that we put up so it looks like a search
        // testing, java, related
        post("/search.php", (req, res) -> {return new PhpSearch(req,res).post();});
        get("/search.php", (req, res) -> {return new PhpSearch(req,res).get();});
        // I Was going to rewrite the find_by_playground.php but then realised that the html is actually static now
        // os I just added the output of the old php to the resources and serve it up as a static html page
        //get("/find_by_playground.php", (req, res) -> {return new PhpFindByPlayground(req,res).get();});


    }


}
