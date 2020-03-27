package uk.kawamet.geolocation;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DataRepo {
    private List<Point> pointList;

    public DataRepo() {
        pointList = new ArrayList<>();
}

    public void addPoint(Point point){
        pointList.add(point);
    }

    public List<Point> getPointList() {
        return pointList;
    }
}
