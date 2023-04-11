package Project3;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.json.*;

import static java.util.Collections.frequency;
import static java.util.Collections.max;

public class APIRequestHandler {
    private static String cityName = "";
    private String apiUrl;

    //Camarly's key
    //public static final String api_KeyCloud = "bf35fb6d7822ade28ea3197bae75439c";
    //Tevin's key
    //Michael's key

    //longitude
    //latitude

    //public static final String POSTS_API_URL = "https://history.openweathermap.org/data/2.5/history/city?lat=18.017874&lon=-76.809906&type=hour&start=1680343200&end=1680386400&appid=" + api_KeyCloud;
//	public static final String POSTS_API_URL = "https://history.openweathermap.org/data/2.5/history/city?lat=18.017874&lon=76.809906&type=hour&start=1680343200&end=1680735600&appid=ad063a14892951f9896784bfef4481d6";



    public APIRequestHandler(String apiUrl, String cityName) {
        this.apiUrl = apiUrl;
        APIRequestHandler.cityName = cityName;
    }

    public APIRequestHandler(String WeatherMapApiUrl, City city) {
        this.apiUrl = WeatherMapApiUrl;
    }

    public void getWeatherData() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();

        //do not delete
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(APIRequestHandler::forecastData)
                .join();
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//            .thenAccept(System.out::println)
//                .join();
    }


    public static void parseWeatherData(String responseBody) {

        double temp = 0.00;
        int humidity = 0;
        String description = null;
        String weather = null;
        String type = null;
        String icon  =  null;
        int datetime = 0;
        int count = 0;
        ArrayList<String> modeOfWeather = new ArrayList<>();
        ArrayList<String> modeOfDesc = new ArrayList<>();
        ArrayList<String> modeOfIcon = new ArrayList<>();

        JSONObject cityData = new JSONObject(responseBody);

        JSONObject weatherData = new JSONObject(responseBody);
        JSONArray hourlyReports = weatherData.getJSONArray("list");

        for (int i=0; i < hourlyReports.length(); i++) {
            JSONObject report = hourlyReports.getJSONObject(i);
            int dt = report.getInt("dt");
            System.out.println(dt);
            temp+= report.getJSONObject("main").getDouble("temp");
            humidity+= report.getJSONObject("main").getInt("humidity");
            count+=1;

            weather = report.getJSONArray("weather").getJSONObject(0).getString("main");
            description = report.getJSONArray("weather").getJSONObject(0).getString("description");
            icon = report.getJSONArray("weather").getJSONObject(0).getString("icon");
        }
        temp /= count;
        humidity /= count;
        modeOfWeather.add(weather);
        modeOfDesc.add(description);
        modeOfIcon.add(icon);
        City aCity =  new City(cityName, temp, humidity, max(modeOfDesc), max(modeOfWeather), max(modeOfIcon), datetime);
        City.cityWeatherData.add(aCity);

    }

    public static void forecastData(String responseBody) {

        double temp = 0.00;
        int humidity = 0;
        String description = null;
        String weather = null;
        String type = null;
        String icon = null;
        int count = 0;
        ArrayList<String> modeOfWeather = new ArrayList<>();
        ArrayList<String> modeOfDesc = new ArrayList<>();
        ArrayList<String> modeOfIcon = new ArrayList<>();

        JSONObject cityData = new JSONObject(responseBody);

        JSONObject weatherData = new JSONObject(responseBody);
        JSONArray hourlyReports = weatherData.getJSONArray("list");

        for (int i = 0; i < hourlyReports.length(); i++) {
            JSONObject report = hourlyReports.getJSONObject(i);
            temp += report.getJSONObject("main").getDouble("temp");
            humidity += report.getJSONObject("main").getInt("humidity");
            count += 1;
            weather = report.getJSONArray("weather").getJSONObject(0).getString("main");
            description = report.getJSONArray("weather").getJSONObject(0).getString("description");
            icon = report.getJSONArray("weather").getJSONObject(0).getString("icon");

            if (i % 24 ==0 && i != 0) {
                System.out.println("Day " + i/24);
                temp /= 24;
                humidity /= 24;
                modeOfWeather.add(weather);
                modeOfDesc.add(description);
                modeOfIcon.add(icon);
                System.out.println(cityName + " " + temp + " " + humidity + " " + max(modeOfDesc) + " " + max(modeOfWeather) + " " + max(modeOfIcon));
                modeOfWeather.clear();
                modeOfDesc.clear();
                modeOfIcon.clear();
            }
        }

    }
}
