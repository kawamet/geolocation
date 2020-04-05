package uk.kawamet.geolocation;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadFile {

    public void downloadFile() throws MalformedURLException {

        URL url = new URL("https://data.humdata.org/hxlproxy/api/data-preview.csv?url=https%3A%2F%2Fraw.githubusercontent.com%2FCSSEGISandData%2FCOVID-19%2Fmaster%2Fcsse_covid_19_data%2Fcsse_covid_19_time_series%2Ftime_series_covid19_confirmed_global.csv&filename=time_series_covid19_confirmed_global.csv");

        File file = new File("time_series_covid19_confirmed_global.csv");

        int connectionTimeout = 10 * 1000; // 10 sec
        int readTimeout = 300 * 1000; // 3 min

        try {
            FileUtils.copyURLToFile(url, file, connectionTimeout, readTimeout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
