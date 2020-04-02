package uk.kawamet.geolocation.linearchart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LinearController {

    private DataRepoLinear dataRepoLinear;

    public LinearController(DataRepoLinear dataRepoLinear) {
        this.dataRepoLinear = dataRepoLinear;
    }

    @GetMapping("/displayLinear")
    public String barGraph(Model model) {
        model.addAttribute("surveyMapItaly", dataRepoLinear.getIntegerMapItaly());
        model.addAttribute("surveyMapPoland", dataRepoLinear.getIntegerMapPoland());
        model.addAttribute("surveyMapUK", dataRepoLinear.getIntegerMapUK());
        return "chart-linear";
    }

}
