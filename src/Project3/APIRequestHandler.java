package Project3;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import static java.util.Collections.max;

public class APIRequestHandler {


  //  private static final String apiURL =  "https://history.openweathermap.org/data/2.5/aggregated/day?lat=188.47&lon=-77.92&month=4&day=1&appid=dbd8574d10549e41443636960496338d";

    private static final String BASE_API_URL = "http://api.openweathermap.org/geo/1.0/direct?q=%s,JM&limit=1&appid=%s";

    //http://api.openweathermap.org/geo/1.0/direct?q={city name},{country code}&limit={limit}&appid={API key}

   //private String apiURL = "https://history.openweathermap.org/data/2.5/history/city?lat=18.4724603&lon=-77.9217357&type=hour&start=1680343200&end=1680411599&appid=dbd8574d10549e41443636960496338d";
   //private String apiURLHistoric = "https://history.openweathermap.org/data/2.5/history/city?q=Kingston,JM&type=hour&start=1680343200&end=1680411599&appid=dbd8574d10549e41443636960496338d";
//
//    private String apiURLCurrent = "http://api.openweathermap.org/geo/1.0/direct?q=%s,JM&limit=1&appid=%s";
//    private String apiURLForecast = "http://api.openweathermap.org/geo/1.0/direct?q=%s,JM&limit=1&appid=%s";

    //API Keys
    private static final String aPiKey01 = "dbd8574d10549e41443636960496338d";
    private static final String aPiKey02 = "dbd8574d10549e41443636960496338d";
    private static final String aPiKey03 = "dbd8574d10549e41443636960496338d";

    private String apiURL = "https://history.openweathermap.org/data/2.5/history/city?lat="+ getLatitude() + "&lon="+getLongitude()+"&type=hour&start= " + getStartTime() + "&end=" + getEndTime() + "&appid=" + aPiKey02;

    private int count;


    private String longitude;
    private String latitude;

    //fix city name for output
    private static String cityName;

    private static String apiResponse;

    private String startTime;
    private String endTime;


    public APIRequestHandler(String cityName, String longitude, String latitude, String startTime, String endTime, String type) {
        if(type.equals("Historic")) {
            this.apiURL = "https://history.openweathermap.org/data/2.5/history/city?lat="+ latitude + "&lon="+ longitude+"&type=hour&start=" + startTime + "&end=" + endTime + "&appid=" + aPiKey02+"&units=metric";
        }
        else if(type.equals("Current")) {
            this.apiURL = "https://history.openweathermap.org/data/2.5/history/city?lat="+ latitude + "&lon="+ longitude+"&type=hour&start=" + startTime + "&end=" + endTime + "&appid=" + aPiKey02+"&units=metric";
        }
        else {
            this.apiURL = "https://history.openweathermap.org/data/2.5/history/city?lat="+ latitude + "&lon="+ longitude+"&type=hour&start=" + startTime + "&end=" + endTime + "&appid=" + aPiKey02+"&units=metric";
        }


        this.longitude = longitude;
        this.latitude = latitude;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cityName = cityName;
    }



    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getWeatherData() throws Exception {

        //System.out.println(apiURL);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();

            //do not delete
        return (client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(APIRequestHandler::parseWeatherData)
            .join());
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//            .thenApply(APIRequestHandler::parseWeatherData)
//                .join();
    }

    public static String parseWeatherData(String responseBody) {

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
        //System.out.println(cityData);

        JSONObject weatherData = new JSONObject(responseBody);
        JSONArray hourlyReports = weatherData.getJSONArray("list");
        //System.out.println(weatherData);

        for (int i=0; i < hourlyReports.length(); i++) {
            JSONObject report = hourlyReports.getJSONObject(i);
            int dt = report.getInt("dt");
            temp+= report.getJSONObject("main").getDouble("temp");
            humidity+= report.getJSONObject("main").getInt("humidity");
            count+=1;

            weather = report.getJSONArray("weather").getJSONObject(0).getString("main");
            description = report.getJSONArray("weather").getJSONObject(0).getString("description");
            icon = report.getJSONArray("weather").getJSONObject(0).getString("icon");
        }
        temp /= count;
        temp = Math.round(temp * 100.00)/100.00;
        humidity /= count;
        modeOfWeather.add(weather);
        modeOfDesc.add(description);
        modeOfIcon.add(icon);

        weather = getFrequentItem(modeOfWeather);
        description = getFrequentItem(modeOfWeather);
        icon = getFrequentItem(modeOfIcon);

        City aCity =  new City(APIRequestHandler.cityName, temp, humidity, description, weather, icon, datetime);
        City.cityWeatherData.add(aCity);

        return null;
    }



    //get the most frequent occurring item in collection - code attri : arshajii(stackoverflow)
    public static <T> T getFrequentItem(ArrayList<T> list) {

            Map<T, Integer> map = new HashMap<>();

            for (T t : list) {
                Integer val = map.get(t);
                map.put(t, val == null ? 1 : val + 1);
            }

            Map.Entry<T, Integer> max = null;

            for (Map.Entry<T, Integer> e : map.entrySet()) {
                if (max == null || e.getValue() > max.getValue())
                    max = e;
            }

        assert max != null;
        return max.getKey();

    }



    public static String [] fetchGeoCoordinates(String city){

        try {

            apiResponse = null;

            String query = String.format("%s", URLEncoder.encode(city, "UTF-8"));
            String apiCall = String.format(BASE_API_URL, query, aPiKey03);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiCall)).build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(response -> {apiResponse = response;})
                    .join();


            String [] latLon = new String[2];
            if (apiResponse.equals("[]")){
                System.out.println("Location not found. Please try another city"); //Employ the use of message boxes
            } else{
                JSONArray jArray = new JSONArray(apiResponse);
                JSONObject results = jArray.getJSONObject(0);

                if (results.getString("country").equals("JM")){
                    latLon[0] = Double.toString(results.getDouble("lat"));
                    latLon[1] = Double.toString(results.getDouble("lon"));

                }else
                    System.out.println("Unable to locate data on this city in Jamaica"); //Employ the use of message boxes
            }
            return latLon;

        } catch (UnsupportedEncodingException ueex) {
            ueex.printStackTrace();
        }
        return null;
    }


}
