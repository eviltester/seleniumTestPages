package com.seleniumsimplified.seleniumtestpages;

import com.seleniumsimplified.pulp.PulpApp;
import com.seleniumsimplified.seleniumtestpages.php.*;

import static spark.Spark.*;

/**
 * Created by Alan on 15/06/2016.
 */
public class MainTestPages {

    public static void main(String[] args) {

        //port(4568); //for testing in case I forget to shutdown
        staticFileLocation("/web");

        // add a shutdown url in case left running on port 4567
        get("/shutdown", (req, res) -> {System.exit(0); return "";});

        // javascript games
        get("/games/", (req, res) -> {res.redirect("/games/buggygames/index.html"); return "";});
        get("/games", (req, res) -> {res.redirect("/games/buggygames/index.html"); return "";});
        get("/games/buggygames", (req, res) -> {res.redirect("/games/buggygames/index.html"); return "";});
        get("/games/buggygames/", (req, res) -> {res.redirect("/games/buggygames/index.html"); return "";});

        // apps
        get("/apps/eprimer/", (req, res) -> {res.redirect("/apps/eprimer/eprimer.html"); return "";});
        get("/apps/eprimer", (req, res) -> {res.redirect("/apps/eprimer/eprimer.html"); return "";});

        get("/apps/7charval/", (req, res) -> {res.redirect("/testing_exercises/7charval/simiple7charvalidation.htm"); return "";});
        get("/apps/7charval", (req, res) -> {res.redirect("/testing_exercises/7charval/simiple7charvalidation.htm"); return "";});


        //sloganizer header redirects
        get("/page/dearEvilTester", (req, res) -> {res.redirect("http://eviltester.com/page/dearEvilTester"); return "";});
        get("/contact.html", (req, res) -> {res.redirect("http://eviltester.com/contact.html"); return "";});

        // create backwards compatibility with selenium page on compendiumdev.co.uk
        // avoid redirects

        get("/selenium", (req, res) -> {res.redirect("/selenium.html"); return "";});
        get("/selenium/", (req, res) -> {res.redirect("/selenium.html"); return "";});


        get("/selenium/ajaxselect.php", (req, res) -> {return new PhpAjaxSelect(req,res).get();});
        get("/selenium/calculate.php", (req, res) -> {return new PhpCalculate(req,res).get();});
        post("/selenium/calculate.php", (req, res) -> {return new PhpCalculate(req,res).post();});
        get("/selenium/refresh.php", (req, res) -> {return new PhpRefresh(req,res).get();});
        post("/selenium/form_processor.php", (req, res) -> {return new PhpFormProcessor(req,res).post();});


        // some tests check the url of the asked for page so don't redirect this one
        get("/selenium/basic_web_page.html", (req, res) -> {return new ResourceReader().asString("/web/basic_web_page.html");});
        get("/selenium/gui_user_interactions.html", (req, res) -> {return new ResourceReader().asString("/web/gui_user_interactions.html");});


        get("/ajaxselect.php", (req, res) -> {return new PhpAjaxSelect(req,res).get();});
        get("/calculate.php", (req, res) -> {return new PhpCalculate(req,res).get();});
        post("/calculate.php", (req, res) -> {return new PhpCalculate(req,res).post();});
        get("/refresh.php", (req, res) -> {return new PhpRefresh(req,res).get();});
        post("/form_processor.php", (req, res) -> {return new PhpFormProcessor(req,res).post();});

        //search.php  - do not use a search engine, just have a set of random urls that we put up so it looks like a search
        // testing, java, related
        post("/selenium/search.php", (req, res) -> {return new PhpSearch(req,res).post();});
        get("/selenium/search.php", (req, res) -> {return new PhpSearch(req,res).get();});
        post("/search.php", (req, res) -> {return new PhpSearch(req,res).post();});
        get("/search.php", (req, res) -> {return new PhpSearch(req,res).get();});
        // I Was going to rewrite the find_by_playground.php but then realised that the html is actually static now
        // as I just added the output of the old php to the resources and serve it up as a static html page
        //get("/find_by_playground.php", (req, res) -> {return new PhpFindByPlayground(req,res).get();});

        // everything else just redirect
        get("/selenium/*", (req, res) -> {res.redirect("/" + req.splat()[0]); return "";});



        // pulp app
        PulpApp pulp = new PulpApp("/data/pulp/doc_savage.csv");
        get("/apps/pulp/gui/reports/books", (req, res) -> { return pulp.reports().getBooksAsHtmlList();});

    }


}
