package Project3;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.JOptionPane.showMessageDialog;

public class APIRequestHandler {

    private static final String BASE_API_URL = "http://api.openweathermap.org/geo/1.0/direct?q=%s,JM&limit=1&appid=%s";


    //API Keys
    private static final String aPiKey01 = "dbd8574d10549e41443636960496338d";
    private static final String aPiKey02 = "dbd8574d10549e41443636960496338d";
    private static final String aPiKey03 = "dbd8574d10549e41443636960496338d";

    private String apiURL;

    private int count;


    private String longitude;
    private String latitude;

    //fix city name for output
    private static String cityName;

    private static String apiResponse;

    private String startTime;
    private String endTime;


    /**
     * constructor for creating api url
     * @param cityName
     * @param longitude
     * @param latitude
     * @param startTime
     * @param endTime
     * @param type
     */
    public APIRequestHandler(String cityName, String longitude, String latitude, String startTime, String endTime, String type) {

        this.longitude = longitude;
        this.latitude = latitude;
        this.startTime = startTime;
        this.endTime = endTime;
        APIRequestHandler.cityName = cityName;

        if(type.equals("Historic")) {
            this.apiURL = "https://history.openweathermap.org/data/2.5/history/city?lat="+ latitude + "&lon="+ longitude +"&type=hour&start=" + endTime + "&end=" + startTime + "&appid=" + aPiKey02+"&units=metric";
        }
        else if (type.equals("Current")){
            this.apiURL = "https://api.openweathermap.org/data/2.5/weather?lat="+longitude+"&lon="+longitude +"&appid=" + aPiKey02+"&units=metric";
        }
        else {
            this.apiURL = "https://api.openweathermap.org/data/2.5/forecast/daily?lat=" + latitude + "&lon="+ longitude + "&cnt=7&appid=bf35fb6d7822ade28ea3197bae75439c&units=metric";
        }

    }

    /**
     * Temperature map constructor - creates a temperature map api url
     * @param tmpMap
     */
    public APIRequestHandler(TemperatureMap tmpMap) {
        this.apiURL = "http://maps.openweathermap.org/maps/2.0/weather/"+ tmpMap.getLayer() +"/"+ tmpMap.getZoom() +"/"+ tmpMap.getXcoord() +"/"+ tmpMap.getYcoord() +"?date="+tmpMap.getDatetime()+"&appid="+aPiKey03;
    }

    /**
     * Temperature map constructor - creates a temperature map api url
     * @param tmpMap
     * @param type
     */
    public APIRequestHandler(TemperatureMap tmpMap, int type) {
        this.apiURL = "http://maps.openweathermap.org/maps/2.0/weather/"+tmpMap.getLayer()+"/"+tmpMap.getZoom()+"/"+tmpMap.getXcoord()+"/"+tmpMap.getYcoord()+"?appid="+aPiKey02+"&fill_bound=true&opacity=0.6&palette=-65:821692;-55:821692;-45:821692;-40:821692;-30:8257db;-20:208cec;-10:20c4e8;0:23dddd;10:c2ff28;20:fff028;25:ffc228;30:fc8014";
    }


    /**
     * constructor for creating API url for current weather data
     * @param cityName
     * @param latitude
     * @param longitude
     * @param current
     */
    public APIRequestHandler(String cityName, String latitude, String longitude, String current) {
        this.apiURL = "https://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude +"&appid=" + aPiKey02+"&units=metric";
        APIRequestHandler.cityName = cityName;
    }


    public APIRequestHandler(String cityName, String latitude, String longitude, String current, int x) {
        this.apiURL = "https://api.openweathermap.org/data/2.5/forecast/daily?lat=" + latitude + "&lon="+ longitude + "&cnt=7&appid=bf35fb6d7822ade28ea3197bae75439c&units=metric";
        APIRequestHandler.cityName = cityName;

    }


    /**
     * gets the longitude from this object
     * @return
     */
    public String getLongitude() {
        return longitude;
    }


    /**
     * sets longitude of this object
     * @param longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    /**
     * gets the latitude of this object
     * @return
     */
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


    /**
     * gets non-specific weather data from API url
     * @return
     * @throws Exception
     */
    public String getWeatherData() throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();

        return (client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(APIRequestHandler::parseWeatherData)
                .join());

    }


    /**
     * parses non-specific weather data from API and creates city holding data
     * @param responseBody
     * @return
     */
    public static String parseWeatherData(String responseBody) {

        double temp = 0.00;
        int humidity = 0;
        String description = null;
        String weather = null;
        String type = null;
        String icon = null;
        int datetime = 0;
        int count = 0;
        ArrayList<String> modeOfWeather = new ArrayList<>();
        ArrayList<String> modeOfDesc = new ArrayList<>();
        ArrayList<String> modeOfIcon = new ArrayList<>();

        JSONObject cityData = new JSONObject(responseBody);

        JSONObject weatherData = new JSONObject(responseBody);
        JSONArray hourlyReports = weatherData.getJSONArray("list");

        for (int i = 0; i < hourlyReports.length(); i++) {
            JSONObject report = hourlyReports.getJSONObject(i);
            int dt = report.getInt("dt");
            temp += report.getJSONObject("main").getDouble("temp");
            humidity += report.getJSONObject("main").getInt("humidity");
            count += 1;

            weather = report.getJSONArray("weather").getJSONObject(0).getString("main");
            description = report.getJSONArray("weather").getJSONObject(0).getString("description");
            icon = "https://openweathermap.org/img/wn/" + report.getJSONArray("weather").getJSONObject(0).getString("icon") + "@2x.png";
        }
        temp /= count;
        temp = Math.round(temp * 100.00) / 100.00;
        humidity /= count;
        modeOfWeather.add(weather);
        modeOfDesc.add(description);
        modeOfIcon.add(icon);

        weather = getFrequentItem(modeOfWeather);
        description = getFrequentItem(modeOfWeather);
        icon = getFrequentItem(modeOfIcon);

        City aCity = new City(APIRequestHandler.cityName, temp, humidity, description, weather, icon, datetime);
        City.historicSevenDayCityData.add(aCity);

        modeOfWeather.clear();
        modeOfDesc.clear();
        modeOfIcon.clear();

        return null;
    }



    /**
     * Method for getting historic weather data from API url
     * @return
     * @throws Exception
     */
    public String getHistoricWeatherData() throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();

        return (client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(APIRequestHandler::parseHistoricData)
                .join());

    }



    /**
     * Methods for parsing historic weather data
     * @param responseBody
     * @return
     */
    public static String parseHistoricData(String responseBody) {
        double temp = 0.00;
        int humidity = 0;
        String description = null;
        String weather = null;
        String type = null;
        String icon = null;
        int datetime = 0;
        ArrayList<String> modeOfWeather = new ArrayList<>();
        ArrayList<String> modeOfDesc = new ArrayList<>();
        ArrayList<String> modeOfIcon = new ArrayList<>();

        JSONObject cityData = new JSONObject(responseBody);

        JSONObject weatherData = new JSONObject(responseBody);
        JSONArray hourlyReports = weatherData.getJSONArray("list");


        int count = 0;

        for (int i = 0; i < hourlyReports.length(); i++) {


            JSONObject report = hourlyReports.getJSONObject(i);
            temp = report.getJSONObject("main").getDouble("temp");
            humidity = report.getJSONObject("main").getInt("humidity");
            count += 1;
            weather = report.getJSONArray("weather").getJSONObject(0).getString("main");
            description = report.getJSONArray("weather").getJSONObject(0).getString("description");
            String pre_icon = report.getJSONArray("weather").getJSONObject(0).getString("icon");

            int place = 2;
            pre_icon = pre_icon.substring(0,place)+"d"+pre_icon.substring(place+1);
            icon = "https://openweathermap.org/img/wn/" + pre_icon + "@2x.png";

            City aCity = new City(APIRequestHandler.cityName, temp, humidity, description, weather, icon, datetime);
            City.historicSevenDayCityData.add(aCity);

        }

        return null;
    }



    /**
     * fetches JSON map data from API url
     * @return
     * @throws Exception
     */
    public String getTempMapData() throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();

        return (client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(APIRequestHandler::parseTempMapData)
                .join());
    }

    /**
     * method to parse weather map data from JSON Array
     * @param responseBody
     * @return
     */
    public static String parseTempMapData(String responseBody) {

        double temp = 0.00;
        int humidity = 0;
        String description = null;
        String weather = null;
        String type = null;
        String icon = null;
        int datetime = 0;
        int count = 0;
        ArrayList<String> modeOfWeather = new ArrayList<>();
        ArrayList<String> modeOfDesc = new ArrayList<>();
        ArrayList<String> modeOfIcon = new ArrayList<>();

        JSONObject cityData = new JSONObject(responseBody);

        JSONObject weatherData = new JSONObject(responseBody);
        JSONArray hourlyReports = weatherData.getJSONArray("list");

        for (int i = 0; i < hourlyReports.length(); i++) {
            JSONObject report = hourlyReports.getJSONObject(i);
            int dt = report.getInt("dt");
            //System.out.println("temp adding: " + report.getJSONObject("main").getDouble("temp"));
            temp += report.getJSONObject("main").getDouble("temp");
            humidity += report.getJSONObject("main").getInt("humidity");
            count += 1;

            weather = report.getJSONArray("weather").getJSONObject(0).getString("main");
            description = report.getJSONArray("weather").getJSONObject(0).getString("description");
            icon = "https://openweathermap.org/img/wn/" + report.getJSONArray("weather").getJSONObject(0).getString("icon") + "@2x.png";
        }
        temp /= count;
        temp = Math.round(temp * 100.00) / 100.00;
        humidity /= count;
        modeOfWeather.add(weather);
        modeOfDesc.add(description);
        modeOfIcon.add(icon);

        weather = getFrequentItem(modeOfWeather);
        description = getFrequentItem(modeOfWeather);
        icon = getFrequentItem(modeOfIcon);

        City aCity = new City(APIRequestHandler.cityName, temp, humidity, description, weather, icon, datetime);
        City.cityWeatherData.add(aCity);

        modeOfWeather.clear();
        modeOfDesc.clear();
        modeOfIcon.clear();

        return null;
    }




    /**
     * Method for getting future weather data from API
     * @return
     * @throws Exception
     */
    public String getFutureWeatherData() throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();

        return (client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(APIRequestHandler::sevenDayfutureData)
                .join());
    }




    /**
     * Method for parsing future weather data from API and creating city object
     * @param responseBody
     * @return
     */
    public static String sevenDayfutureData(String responseBody) {

        double temp = 0.00;
        int humidity = 0;
        String description = null;
        String weather = null;
        String type = null;
        String icon = null;
        int datetime = 0;

        JSONObject cityData = new JSONObject(responseBody);
        JSONArray dailyReports = cityData.getJSONArray("list");

        for (int i = 0; i < dailyReports.length(); i++) {
            JSONObject report = dailyReports.getJSONObject(i);
            temp = report.getJSONObject("temp").getDouble("day");
            humidity = report.getInt("humidity");

            weather = report.getJSONArray("weather").getJSONObject(0).getString("main");
            description = report.getJSONArray("weather").getJSONObject(0).getString("description");

            String pre_icon = report.getJSONArray("weather").getJSONObject(0).getString("icon");

            int place = 2;
            pre_icon = pre_icon.substring(0,place)+"d"+pre_icon.substring(place+1);
            icon = "https://openweathermap.org/img/wn/" + pre_icon + "@2x.png";

            City aCity = new City(APIRequestHandler.cityName, temp, humidity, description, weather, icon, datetime);
            City.futureSevenDayCityData.add(aCity);
        }

        return null;

    }


    /**
     * Method for retrieving current weather data from API
     * @return
     * @throws Exception
     */
    public String getCurrentWeatherData() throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();

        return (client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(APIRequestHandler::currentWeatherData).join());
    }


    /**
     * Method for parsing current weather data
     * @param responseBody
     * @return
     */
    public static String currentWeatherData(String responseBody) {
        double temp = 0.00;
        int humidity = 0;
        String description = null;
        String weather = null;
        String icon = null;
        int datetime = 0;

        JSONObject data = new JSONObject(responseBody);
        String pre_icon = data.getJSONArray("weather").getJSONObject(0).getString("icon");

        int place = 2;
        pre_icon = pre_icon.substring(0,place)+"d"+pre_icon.substring(place+1);
        icon = "https://openweathermap.org/img/wn/" + pre_icon + "@2x.png";

        description = data.getJSONArray("weather").getJSONObject(0).getString("description");
        weather = data.getJSONArray("weather").getJSONObject(0).getString("main");

        temp = data.getJSONObject("main").getDouble("temp");
        humidity = data.getJSONObject("main").getInt("humidity");


        City aCity = new City(APIRequestHandler.cityName, temp, humidity, description, weather, icon, datetime);
        City.cityList.add(aCity);

        return null;
    }


    /**
     * get the most frequent occurring item in collection - code attri : arshajii(stackoverflow)
     * @param list
     * @return
     * @param <T>
     */
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



//    public static String [] fetchGeoCoordinates(String city){
//
//        try {
//
//            apiResponse = null;
//
//            String query = String.format("%s", URLEncoder.encode(city, "UTF-8"));
//            String apiCall = String.format(BASE_API_URL, query, aPiKey03);
//
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiCall)).build();
//            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                    .thenApply(HttpResponse::body)
//                    .thenAccept(response -> {apiResponse = response;})
//                    .join();
//
//
//            String [] latLon = new String[2];
//            if (apiResponse.equals("[]")){
//                System.out.println("Location not found. Please try another city"); //Employ the use of message boxes
//            } else{
//                JSONArray jArray = new JSONArray(apiResponse);
//                JSONObject results = jArray.getJSONObject(0);
//
//                if (results.getString("country").equals("JM")){
//                    latLon[0] = Double.toString(results.getDouble("lat"));
//                    latLon[1] = Double.toString(results.getDouble("lon"));
//
//                }else
//                    System.out.println("Unable to locate data on this city in Jamaica"); //Employ the use of message boxes
//            }
//            return latLon;
//
//        } catch (UnsupportedEncodingException ueex) {
//            ueex.printStackTrace();
//        }
//        return null;
//    }

    /**
     * gets the longitude and latitude of a city
     * @param city
     * @return String[] of latitude and longitude
     */
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

            JSONArray jArray = new JSONArray(apiResponse);
            JSONObject results = jArray.getJSONObject(0);

            if (results.getString("country").equals("JM")){
                latLon[0] = Double.toString(results.getDouble("lat"));
                latLon[1] = Double.toString(results.getDouble("lon"));

            }else {
                throw new IllegalArgumentException();
            }

            return latLon;

        } catch (UnsupportedEncodingException ueex) {
//            ueex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unsupported encoding: " + ueex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (JSONException | IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null, "Location not found. Please try another city", "Error",
                        JOptionPane.ERROR_MESSAGE);
//            showMessageDialog(null, "Location not found in our database.\t\t \nPlease try another city.", "Location not found!", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

//    public static String[] fetchGeoCoordinates(String city) {
//        try {
//            apiResponse = null;
//
//            String query = String.format("%s", URLEncoder.encode(city, "UTF-8"));
//            String apiCall = String.format(BASE_API_URL, query, aPiKey03);
//
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiCall)).build();
//            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                    .thenApply(HttpResponse::body)
//                    .thenAccept(response -> {
//                        apiResponse = response;
//                    })
//                    .join();
//
//            String[] latLon = new String[2];
//            if (apiResponse.equals("[]")) {
//                JOptionPane.showMessageDialog(null, "Location not found. Please try another city", "Error",
//                        JOptionPane.ERROR_MESSAGE);
//            } else {
//                JSONArray jArray = new JSONArray(apiResponse);
//                JSONObject results = jArray.getJSONObject(0);
//
//                if (results.getString("country").equals("JM")) {
//                    latLon[0] = Double.toString(results.getDouble("lat"));
//                    latLon[1] = Double.toString(results.getDouble("lon"));
//                } else {
//                    JOptionPane.showMessageDialog(null,
//                            "Unable to locate data on this city in Jamaica", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//            return latLon;
//
//        } catch (UnsupportedEncodingException ueex) {
//            JOptionPane.showMessageDialog(null, "Unsupported encoding: " + ueex.getMessage(), "Error",
//                    JOptionPane.ERROR_MESSAGE);
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage(), "Error",
//                    JOptionPane.ERROR_MESSAGE);
//        }
//        return null;
//    }


}
