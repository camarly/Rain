package Project3;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APICurrentWeatherHandler {

    //https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&units=metric&appid={API key}
    private static final String BASE_API_URL = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric&appid=%s";
    private static final String API_KEY = "dbd8574d10549e41443636960496338d";
    private HttpClient client;
    private HttpRequest request;
    private static String apiResponse;

    public APICurrentWeatherHandler() {
    }


    public static String[] fetchWeatherData (String lat, String lon){

        apiResponse = null;
        String apiCall = String.format(BASE_API_URL,lat, lon, API_KEY);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiCall)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(response -> {apiResponse = response;})
                .join();



        JSONObject weatherData = new JSONObject(apiResponse);

        int dt = weatherData.getInt("dt");
        double temp = weatherData.getJSONObject("main").getDouble("temp");
        int humidity = weatherData.getJSONObject("main").getInt("humidity");
        String mainWeather = weatherData.getJSONArray("weather").getJSONObject(0).getString("main");
        String desc = weatherData.getJSONArray("weather").getJSONObject(0).getString("description");
        String icon = weatherData.getJSONArray("weather").getJSONObject(0).getString("icon");

//        String weather = String.format("%d|%.2f|%d|%s|%s|%s", dt, temp, humidity, mainWeather,desc, icon);
//        System.out.println(weather);


        return new String [] {String.valueOf(dt), String.valueOf(temp), String.valueOf(humidity), mainWeather, desc, icon};
    }
}