package com.seleniumsimplified.pulp.reporting;

public class ReportConfig {
    private final boolean areAuthorNamesLinks;

    public ReportConfig(boolean areAuthorNamesLinks) {
        this.areAuthorNamesLinks = areAuthorNamesLinks;
    }

    public static ReportConfig justStrings() {

        ReportConfig config = new ReportConfig(false);
        return config;
    }

    public static ReportConfig allHTML() {
        ReportConfig config = new ReportConfig(true);
        return config;
    }

    public boolean areAuthorNamesLinks() {
        return areAuthorNamesLinks;
    }
}
