package Project3;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class APIForecastWeatherHandler {

    private static final String BASE_API_URL = "https://api.openweathermap.org/data/2.5/forecast/daily?lat=%s&lon=%s&cnt=7&units=metric&appid=%s"; //api.openweathermap.org/data/2.5/forecast/daily?lat={lat}&lon={lon}&cnt={cnt}&appid={API key}
    private static final String API_KEY = "dbd8574d10549e41443636960496338d";
    private HttpClient client;
    private HttpRequest request;
    private static String apiResponse;

    public APIForecastWeatherHandler() {
    }

    public static String [] fetchForcastWeather(String lat, String lon){

        try {
            apiResponse = null;

            String apiCall = String.format(BASE_API_URL,lat, lon, API_KEY);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiCall)).build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(response -> {
                        apiResponse = response;
                    })
                    .join();

            JSONObject weatherData = new JSONObject(apiResponse);
            JSONArray dailyReports = weatherData.getJSONArray("list");

            String [] dailyData = new String [dailyReports.length()];

            for (int i=0; i < dailyReports.length(); i++){
                JSONObject report = dailyReports.getJSONObject(i);
                int dt = report.getInt("dt");
                double temp = report.getJSONObject("temp").getDouble("day");
                int humidity = report.getInt("humidity");
                String mainWeather = report.getJSONArray("weather").getJSONObject(0).getString("main");
                String desc = report.getJSONArray("weather").getJSONObject(0).getString("description");
                String icon = report.getJSONArray("weather").getJSONObject(0).getString("icon");

                String day = String.format("%d,%.2f,%d,%s,%s,%s", dt, temp, humidity, mainWeather,desc, icon);

                dailyData[i] = day;
            }

            return  dailyData;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
