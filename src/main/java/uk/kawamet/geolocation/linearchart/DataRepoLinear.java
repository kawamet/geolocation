package uk.kawamet.geolocation.linearchart;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class DataRepoLinear {

    //private Map<String, Map<String, Integer>> linearMap;
    private Map<Integer, Integer> integerMap;


    public DataRepoLinear() {
        integerMap = new HashMap<>();
    }

    public void addPoint(Integer date, Integer cases) {
        integerMap.put(date,cases);
    }

    public Map<Integer, Integer> getIntegerMap() {
        return integerMap;
    }
}
