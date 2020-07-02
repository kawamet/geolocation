package uk.kawamet.geolocation.charts;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class DataRepoChart {
    private Map<String, Integer> surveyMap;

    public DataRepoChart() {
        surveyMap = new LinkedHashMap<>();
    }

    public void addRecord(String country, Integer cases){
        surveyMap.put(country, cases);
    }

    //top 10
    public Map<String, Integer> getSurveyMap() {
        HashMap<String, Integer> collect = surveyMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue((a, b) -> b - a)).limit(10).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, HashMap::new));
        return collect;
    }
}
