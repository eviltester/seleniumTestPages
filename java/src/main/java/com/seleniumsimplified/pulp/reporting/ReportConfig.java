package com.seleniumsimplified.pulp.reporting;

public class ReportConfig {
    private final boolean areAuthorNamesLinks;
    private final boolean areYearsLinks;
    private String reportPath = "/apps/pulp/gui/reports/";
    private boolean arePublisherNamesLinks;
    private boolean areSeriesNamesLinks;
    private String linksPostFix;

    public ReportConfig(boolean areAuthorNamesLinks, boolean areYearsLinks, boolean arePublisherNamesLinks, boolean areSeriesNamesLinks) {
        this.areAuthorNamesLinks = areAuthorNamesLinks;
        this.areYearsLinks = areYearsLinks;
        this.arePublisherNamesLinks = arePublisherNamesLinks;
        this.areSeriesNamesLinks = areSeriesNamesLinks;
    }

    public static ReportConfig justStrings() {

        return new ReportConfig(false, false, false, false);
    }


    public static ReportConfig allHTML(String reportPath) {
        ReportConfig config = new ReportConfig(true, true, true, true);
        config.reportPath = reportPath;
        return config;
    }

    public boolean areAuthorNamesLinks() {
        return areAuthorNamesLinks;
    }

    public boolean areYearsLinks() {
        return areYearsLinks;
    }

    public String getReportPath() {
        return reportPath;
    }

    public boolean arePublishersLinks() {
        return arePublisherNamesLinks;
    }

    public boolean areSeriesNamesLinks() {
        return areSeriesNamesLinks;
    }

    public ReportConfig setPostFixPath(String linksPostFix) {
        this.linksPostFix = linksPostFix;
        return this;
    }

    public String getReportPath(String path) {
        StringBuilder retPath = new StringBuilder();
        retPath.append(String.format("%s%s",reportPath,path));
        if(linksPostFix!=null){
            retPath.append(linksPostFix);
        }
        return retPath.toString();
    }
}
