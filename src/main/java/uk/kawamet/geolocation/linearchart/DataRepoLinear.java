package uk.kawamet.geolocation.linearchart;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class DataRepoLinear {

    //private Map<String, Map<String, Integer>> linearMap;
    private Map<Integer, Integer> integerMapItaly;
    private Map<Integer, Integer> integerMapPoland;
    private Map<Integer, Integer> integerMapUK;


    public DataRepoLinear() {
        integerMapItaly = new HashMap<>();
        integerMapPoland = new HashMap<>();
        integerMapUK = new HashMap<>();
    }

    public void addPoint(String country, String provinceState, Integer date, Integer cases) {
        if(country.equals("Italy")) {
            integerMapItaly.put(date, cases);
        }
        if (country.equals("Poland")){
            integerMapPoland.put(date, cases);
        }
        if (country.equals("United Kingdom") && provinceState.isEmpty()){
            integerMapUK.put(date, cases);
        }
    }


    public Map<Integer, Integer> getIntegerMapItaly() {
        return integerMapItaly;
    }

    public Map<Integer, Integer> getIntegerMapPoland() {
        return integerMapPoland;
    }

    public Map<Integer, Integer> getIntegerMapUK() {
        return integerMapUK;
    }
}
