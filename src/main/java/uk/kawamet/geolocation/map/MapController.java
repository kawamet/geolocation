package uk.kawamet.geolocation.map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.kawamet.geolocation.Point;

import java.util.List;

@Controller
public class MapController {

    private DataRepo dataRepo;

    public MapController(DataRepo dataRepo) {
        this.dataRepo = dataRepo;
    }

    //    @RequestMapping(method = RequestMethod.GET)
//    public String getMap(Model model, @RequestParam String x, @RequestParam String y) {
//        model.addAttribute("x", x);
//        model.addAttribute("y", y);
//        return "map";
//    }

    @GetMapping("/displayMap")
    public String getMap(Model model) {
   /*     List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(52.231, 21.00, "Warszawa"));
        pointList.add(new Point(50.25, 19.021, "Katowice"));*/
        List<Point> pointList = dataRepo.getPointList();
        model.addAttribute("points", pointList);
        return "map";
    }

}
