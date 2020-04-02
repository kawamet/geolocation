package uk.kawamet.geolocation.linearchart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DataRepoLinear {

    @Value("${country1}")
    private String country1;
    @Value("${country2}")
    private String country2;
    @Value("${country3}")
    private String country3;

    private Map<Integer, Integer> integerMapItaly;
    private Map<Integer, Integer> integerMapPoland;
    private Map<Integer, Integer> integerMapUK;


    public DataRepoLinear() {
        integerMapItaly = new HashMap<>();
        integerMapPoland = new HashMap<>();
        integerMapUK = new HashMap<>();
    }

    public void addPoint(String country, String provinceState, Integer date, Integer cases) {
        if (country.equals(country1) && provinceState.isEmpty()) {
            integerMapItaly.put(date, cases);
        }
        if (country.equals(country2) && provinceState.isEmpty()) {
            integerMapPoland.put(date, cases);
        }
        if (country.equals(country3) && provinceState.isEmpty()) {
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
