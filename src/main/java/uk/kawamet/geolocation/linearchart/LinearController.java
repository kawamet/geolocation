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
       /* Map<Integer, Integer> surveyMap = new LinkedHashMap<>();
        surveyMap.put(1, 10);
        surveyMap.put(2, 20);
        surveyMap.put(3, 30);
        surveyMap.put(4, 40);
        surveyMap.put(5, 50);
        surveyMap.put(6, 60);
        surveyMap.put(7, 80);*/

        Map<Integer, Integer> surveyMap = dataRepoLinear.getIntegerMap();

        model.addAttribute("surveyMap", surveyMap);
        return "chart-linear";
    }

}
