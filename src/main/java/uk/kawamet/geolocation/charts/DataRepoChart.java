package uk.kawamet.geolocation.charts;

import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class DataRepoChart {
    private Map<String, Integer> surveyMap;

    public DataRepoChart() {
        surveyMap = new LinkedHashMap<>();
    }

    public void addRecord(String country, Integer cases){
        surveyMap.put(country, cases);
    }

    public Map<String, Integer> getSurveyMap() {
        return surveyMap;
    }
}
