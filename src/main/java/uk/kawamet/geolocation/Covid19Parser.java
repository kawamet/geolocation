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

@Service
public class Covid19Parser {
    //private static final String COVID_CONFIRMED_URL = "https://data.humdata.org/hxlproxy/api/data-preview.csv?url=https%3A%2F%2Fraw.githubusercontent.com%2FCSSEGISandData%2FCOVID-19%2Fmaster%2Fcsse_covid_19_data%2Fcsse_covid_19_time_series%2Ftime_series_covid19_confirmed_global.csv&filename=time_series_covid19_confirmed_global.csv";

    private static final String COVID_CONFIRMED_URL = "C:\\Users\\Karolina\\Desktop\\java_projekty_all\\spring\\geolocation\\time_series_covid19_confirmed_global.csv";

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

    //saved on local machine
    @EventListener(ApplicationReadyEvent.class)
    public void get() throws IOException {
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.downloadFile();
        Reader in = new FileReader(COVID_CONFIRMED_URL);
        CSVParser parse = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord strings : parse) {
            double lat = Double.parseDouble(strings.get("Lat"));
            double lon = Double.parseDouble(strings.get("Long"));
            String text = strings.get("3/26/20");
            String country = strings.get("Province/State").isEmpty() ? strings.get("Country/Region") : strings.get("Province/State");
            dataRepo.addPoint(new Point(lat,lon, text, country));
        }

    }
}
