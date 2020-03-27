package uk.kawamet.geolocation;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class Covid19Parser {

    private static final String COVID_CONFIRMED_URL = "time_series_covid19_confirmed_global.csv";

    private DataRepo dataRepo;

    public Covid19Parser(DataRepo dataRepo) {
        this.dataRepo = dataRepo;
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


    @EventListener(ApplicationReadyEvent.class)
    public void get() throws IOException {
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.downloadFile();
        Reader in = new FileReader(COVID_CONFIRMED_URL);
        CSVParser parse = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord strings : parse) {
            double lat = Double.parseDouble(strings.get("Lat"));
            double lon = Double.parseDouble(strings.get("Long"));
            String text = getLastDate(strings);
            String country = strings.get("Province/State").isEmpty() ? strings.get("Country/Region") : strings.get("Province/State");
            dataRepo.addPoint(new Point(lat,lon, text, country));
        }

    }

    private String getLastDate(CSVRecord strings) {
        String text;
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("M/dd/yy");
        String todayS = today.format(newPattern);
        String yesterdayS = yesterday.format(newPattern);

        try{
            text = strings.get(todayS);
        }catch (IllegalArgumentException e){
            text = strings.get(yesterdayS);
        }
        return text;
    }
}
