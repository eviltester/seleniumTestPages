package com.seleniumsimplified.pulp;

import com.seleniumsimplified.pulp.domain.PulpAuthor;
import com.seleniumsimplified.pulp.domain.PulpBook;
import com.seleniumsimplified.pulp.domain.PulpPublisher;
import com.seleniumsimplified.pulp.domain.PulpSeries;

import java.util.ArrayList;
import java.util.List;

public class PulpSeriesCollection {
    private int key;
    private ArrayList<PulpSeries> serieses;

    public PulpSeriesCollection(){
        serieses = new ArrayList<>();
        key = 1;
    }

    public int count() {
        return serieses.size();
    }

    public void add(String seriesName) {
        PulpSeries series = getNextSeries(seriesName);
        serieses.add(series);
    }

    private PulpSeries getNextSeries(String seriesName) {
        return new PulpSeries(String.valueOf(key++), seriesName);
    }

    public PulpSeries get(String key) {
        for(PulpSeries aSeries : serieses){
            if(aSeries.getId().contentEquals(key)){
                return aSeries;
            }
        }

        return PulpSeries.UNKNOWN_SERIES;
    }

    public PulpSeries findByName(String name) {
        for(PulpSeries aSeries : serieses){
            if(aSeries.getName().equalsIgnoreCase(name)){
                return aSeries;
            }
        }

        return PulpSeries.UNKNOWN_SERIES;
    }

    public List<String> keys() {
        List<String> itemKeys = new ArrayList<>();
        for(PulpSeries item : serieses){
            itemKeys.add(item.getId());
        }
        return itemKeys;
    }
}
