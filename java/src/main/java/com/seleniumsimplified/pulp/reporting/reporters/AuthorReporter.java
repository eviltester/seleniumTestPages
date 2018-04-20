package com.seleniumsimplified.pulp.reporting.reporters;

import com.seleniumsimplified.pulp.domain.objects.PulpAuthor;
import com.seleniumsimplified.pulp.reporting.ReportConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthorReporter {
    private final ReportConfig reportConfig;

    public AuthorReporter(ReportConfig reportConfig) {
        this.reportConfig = reportConfig;
    }

    public Collection<String> getAsStrings(Collection<PulpAuthor> authors) {
        List<String> report = new ArrayList<>();

        for(PulpAuthor author : authors) {

            report.add(getAuthorName(author));
        }

        return report;
    }

    public String getAuthorName(PulpAuthor author) {

        if(reportConfig!=null && reportConfig.areAuthorNamesLinks()){
            return String.format("<a href='%s?author=%s'>%s</a>", reportConfig.getReportPath("books"), author.getId(),author.getName());
        }else{
            return author.getName();
        }

    }

    public String getConcatenated(Collection<PulpAuthor> authors, String concatonator) {
        StringBuilder line = new StringBuilder();
        int authorCount = authors.size();

        for(PulpAuthor author : authors){
            line.append(getAuthorName(author));
            authorCount--;
            if(authorCount!=0) {
                line.append(concatonator);
            }
        }

        return line.toString();
    }
}
