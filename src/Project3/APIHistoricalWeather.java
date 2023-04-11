package Project3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;


public class APIHistoricalWeather {

    //APICall Url = https://history.openweathermap.org/data/2.5/history/city?lat={lat}&lon={lon}&type=hour&start={start}&end={end}&appid={API key}
    private static final String BASE_API_URL = "https://history.openweathermap.org/data/2.5/history/city?lat=%s&lon=%s&type=hour&start=%s&end=%s&units=metric&appid=%s";
    private static final String API_KEY = "dbd8574d10549e41443636960496338d";


    public APIHistoricalWeather() {
    }

    public static String[] getData (String lat, String lon, String start, String end){

        try {
            String urlString = String.format(BASE_API_URL, lat, lon, start, end, API_KEY);
            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
//            System.out.println(response);

            return parseData(response.toString());
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (ProtocolException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    private static String [] parseData (String response){

        JSONObject weatherData = new JSONObject(response);
        JSONArray hourlyReports = weatherData.getJSONArray("list");

        String [] hourlyData = new String[hourlyReports.length()];

        for (int i=0; i < hourlyReports.length(); i++){
            JSONObject report = hourlyReports.getJSONObject(i);
            int dt = report.getInt("dt");
            double temp = report.getJSONObject("main").getDouble("temp");
            int humidity = report.getJSONObject("main").getInt("humidity");
            String mainWeather = report.getJSONArray("weather").getJSONObject(0).getString("main");
            String desc = report.getJSONArray("weather").getJSONObject(0).getString("description");
            String icon = report.getJSONArray("weather").getJSONObject(0).getString("icon");

            String hourReport = String.format("%d,%.2f,%d,%s,%s,%s", dt, temp, humidity, mainWeather,desc, icon);

            hourlyData[i] = hourReport;
//            System.out.println(hourReport);
        }
        return hourlyData;
    }

}
