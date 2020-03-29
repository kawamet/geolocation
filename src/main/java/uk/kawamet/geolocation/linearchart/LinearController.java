package uk.kawamet.geolocation.linearchart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class LinearController {

    private DataRepoLinear dataRepoLinear;

    public LinearController(DataRepoLinear dataRepoLinear) {
        this.dataRepoLinear = dataRepoLinear;
    }

    @GetMapping("/displayLinear")
    public String barGraph(Model model) {

        Map<Integer, Integer> surveyMapItaly = dataRepoLinear.getIntegerMapItaly();
        Map<Integer, Integer> surveyMapPoland = dataRepoLinear.getIntegerMapPoland();
        Map<Integer, Integer> surveyMapUK = dataRepoLinear.getIntegerMapUK();

        model.addAttribute("surveyMapItaly", surveyMapItaly);
        model.addAttribute("surveyMapPoland", surveyMapPoland);
        model.addAttribute("surveyMapUK", surveyMapUK);
        return "chart-linear";
    }

}
