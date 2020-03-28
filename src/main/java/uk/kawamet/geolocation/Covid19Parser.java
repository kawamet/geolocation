package uk.kawamet.geolocation;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import uk.kawamet.geolocation.charts.DataRepoChart;
import uk.kawamet.geolocation.linearchart.DataRepoLinear;
import uk.kawamet.geolocation.map.DataRepo;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class Covid19Parser {

    private static final String COVID_CONFIRMED_URL = "time_series_covid19_confirmed_global.csv";
    private final DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("M/dd/yy");
    private LocalDate lastDate = LocalDate.now();

    private DataRepo dataRepo;
    private DataRepoChart dataRepoChart;
    private DataRepoLinear dataRepoLinear;


    public Covid19Parser(DataRepo dataRepo, DataRepoChart dataRepoChart, DataRepoLinear dataRepoLinear) {
        this.dataRepo = dataRepo;
        this.dataRepoChart = dataRepoChart;
        this.dataRepoLinear = dataRepoLinear;
    }


//    @EventListener(ApplicationReadyEvent.class)
//    public void get() throws IOException {
//        RestTemplate restTemplate = new RestTemplate();
//        String forObject = restTemplate.getForObject(COVID_CONFIRMED_URL, String.class);
//        System.out.println(forObject);
//
//        StringReader stringReader = new StringReader(forObject);
//        CSVParser parse = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);
//
//        for (CSVRecord strings : parse) {
//            double lat = Double.parseDouble(strings.get("Lat"));
//           double lon = Double.parseDouble(strings.get("Long"));
//            String text = strings.get("3/26/20");
//            String country = strings.get("Province/State").isEmpty() ? strings.get("Country/Region") : strings.get("Province/State");
//            dataRepo.addPoint(new Point(lat,lon, text, country));
//        }
//
//
//    }


   // @EventListener(ApplicationReadyEvent.class)
    private CSVParser getCSVData() throws IOException {
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.downloadFile();
        Reader in = new FileReader(COVID_CONFIRMED_URL);
        return CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
    }

    @EventListener(ApplicationReadyEvent.class)
    private void addRecordsToMap() throws IOException {
        CSVParser parse = getCSVData();
        for (CSVRecord strings : parse) {
            double lat = Double.parseDouble(strings.get("Lat"));
            double lon = Double.parseDouble(strings.get("Long"));
            String text = getLastDate(strings);
            String country = strings.get("Province/State").isEmpty() ? strings.get("Country/Region") : strings.get("Province/State");
            dataRepo.addPoint(new Point(lat,lon, text, country));

        }
    }
    @EventListener(ApplicationReadyEvent.class)
    private void addRecordsToChart() throws IOException {
        CSVParser parse = getCSVData();
        for (CSVRecord strings : parse) {
            String cases = getLastDate(strings);
            String country = strings.get("Province/State").isEmpty() ? strings.get("Country/Region") : strings.get("Province/State");
            dataRepoChart.addRecord(country, Integer.valueOf(cases));
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    private void addRecordsToLinear() throws IOException {
        CSVParser parse = getCSVData();

        for (CSVRecord strings : parse) {
            getLastDate(strings);

            if (strings.get("Country/Region").equals("Poland")){
                ArrayList<String> sevenLastDaysLit = getLastSevenDays();
                int i = 0;
                for (String day : sevenLastDaysLit) {
                    String s = strings.get(day);
                    dataRepoLinear.addPoint(i++, Integer.valueOf(s));
                }
            }
        }
    }

    private ArrayList<String> getLastSevenDays() {
        ArrayList<String>  sevenLastDaysLit = new ArrayList<>();
        for (int i = 13; i >= 0; i--) {
            String format = lastDate.minusDays(i).format(newPattern);
            sevenLastDaysLit.add(String.valueOf(format));
        }
        return sevenLastDaysLit;
    }

    private String getLastDate(CSVRecord strings) {
        String text;
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        String todayS = today.format(newPattern);
        String yesterdayS = yesterday.format(newPattern);

        try{
            text = strings.get(todayS);
            lastDate = today;
        }catch (IllegalArgumentException e){
            text = strings.get(yesterdayS);
            lastDate = yesterday;
        }
        return text;
    }

}
