package Project3;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenWeatherMapAPI {

    //APICall Ur; = https://history.openweathermap.org/data/2.5/history/city?lat={lat}&lon={lon}&type=hour&start={start}&end={end}&appid={API key}
    private static final String BASE_API_URL = "https://history.openweathermap.org/data/2.5/history/city?lat=%s&lon=%s&type=hour&start=%s&end=%s&units=metric&appid=%s";
    private static final String API_KEY = "dbd8574d10549e41443636960496338d";
    private HttpClient client;
    private HttpRequest request;
    private static String apiResponse;

    public OpenWeatherMapAPI() {
    }

    public static String[] fetchWeatherData (String lat, String lon, String start, String end){

        apiResponse = null;
        String apiCall = String.format(BASE_API_URL,lat, lon, start, end, API_KEY);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiCall)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(response -> {apiResponse = response;})
                .join();

        //System.out.println(apiCall);

        JSONObject weatherData = new JSONObject(apiResponse);
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

            String hourReport = String.format("%d|%.2f|%d|%s|%s|%s", dt, temp, humidity, mainWeather,desc, icon);

            hourlyData[i] = hourReport;
            //System.out.println(hourReport);

        }

        return hourlyData;
    }

}
