package com.seleniumsimplified.pulp.reporting;

public class ReportConfig {
    private final boolean areAuthorNamesLinks;
    private final boolean areYearsLinks;
    private String reportPath = "/apps/pulp/gui/reports/";
    private boolean arePublisherNamesLinks;

    public ReportConfig(boolean areAuthorNamesLinks, boolean areYearsLinks, boolean arePublisherNamesLinks) {
        this.areAuthorNamesLinks = areAuthorNamesLinks;
        this.areYearsLinks = areYearsLinks;
        this.arePublisherNamesLinks = arePublisherNamesLinks;
    }

    public static ReportConfig justStrings() {

        ReportConfig config = new ReportConfig(false, false, false);
        return config;
    }


    public static ReportConfig allHTML(String reportPath) {
        ReportConfig config = new ReportConfig(true, true, true);
        config.reportPath = reportPath;
        return config;
    }

    public boolean areAuthorNamesLinks() {
        return areAuthorNamesLinks;
    }

    public boolean areYearsLinks() {
        return areAuthorNamesLinks;
    }

    public String getReportPath() {
        return reportPath;
    }

    public boolean arePublishersLinks() {
        return arePublisherNamesLinks;
    }
}
