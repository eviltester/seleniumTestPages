package com.seleniumsimplified.pulp.reporting;

public class ReportConfig {
    private final boolean areAuthorNamesLinks;
    private final boolean areYearsLinks;
    private String reportPath = "/apps/pulp/gui/reports/";

    public ReportConfig(boolean areAuthorNamesLinks, boolean areYearsLinks) {
        this.areAuthorNamesLinks = areAuthorNamesLinks;
        this.areYearsLinks = areYearsLinks;
    }

    public static ReportConfig justStrings() {

        ReportConfig config = new ReportConfig(false, false);
        return config;
    }


    public static ReportConfig allHTML(String reportPath) {
        ReportConfig config = new ReportConfig(true, true);
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
}
