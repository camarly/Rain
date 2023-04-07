package Project3;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIRequestHandler {
    private String apiUrl;

    //Camarly's key
    public static final String api_KeyCloud = "bf35fb6d7822ade28ea3197bae75439c";
    //Tevin's key
    //Michael's key

    //longitude
    //latitude

    public static final String POSTS_API_URL = "https://history.openweathermap.org/data/2.5/history/city?lat=18.017874&lon=-76.809906&type=hour&start=1680343200&end=1680386400&appid=" + api_KeyCloud;
//	public static final String POSTS_API_URL = "https://history.openweathermap.org/data/2.5/history/city?lat=18.017874&lon=76.809906&type=hour&start=1680343200&end=1680735600&appid=ad063a14892951f9896784bfef4481d6";



    public APIRequestHandler(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public APIRequestHandler(String WeatherMapApiUrl, City city) {
        this.apiUrl = WeatherMapApiUrl;
    }

    public void getWeatherData() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(POSTS_API_URL)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(APIRequestHandler::parseWeatherData)
                .join();
    }


    public static City parseWeatherData(String responseBody) {

        String cityName = null;
        double temp = 0.00;
        int humidity = 0;
        String description = null;
        String type = null;
        String icon  =  null;
        int datetime = 0;
                
        JSONObject cityData = new JSONObject(responseBody);

        int city_id = cityData.getInt("city_id");
        JSONArray _list = cityData.getJSONArray("list");

        for (int i = 0; i < _list.length(); i++){
            JSONObject data = _list.getJSONObject(i);
            JSONObject MAIN = data.getJSONObject("main");
            temp = MAIN.getDouble("temp");
            humidity = MAIN.getInt("humidity");
            //System.out.println("temp: " + temp	+ " humidity:" + humidity);

        }

        for (int i = 0; i < _list.length(); i++) {
            JSONObject city = _list.getJSONObject(i);
            JSONArray weather = city.getJSONArray("weather");
            for (int j = 0; j < weather.length(); j++) {
                JSONObject weatherInfo = weather.getJSONObject(j);
                description = weatherInfo.getString("description");
                type = weatherInfo.getString("main");
                icon = weatherInfo.getString("icon");
                //System.out.println(type + " " + description + " " + icon);
            }
            datetime = city.getInt("dt");
        }
        City city = new City(cityName, temp, humidity, description, type, icon, datetime);
        return city;//returns weather data for city in desired format before creating city class.

    }



}
