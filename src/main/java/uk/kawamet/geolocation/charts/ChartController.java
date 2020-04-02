package uk.kawamet.geolocation.charts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ChartController {

    private DataRepoChart dataRepoChart;

    public ChartController(DataRepoChart dataRepoChart) {
        this.dataRepoChart = dataRepoChart;
    }

    @GetMapping("/displayBarGraph")
    public String barGraph(Model model) {
        Map<String, Integer> surveyMap = dataRepoChart.getSurveyMap();
        model.addAttribute("surveyMap", surveyMap);
        return "chart-bar";
    }
}
