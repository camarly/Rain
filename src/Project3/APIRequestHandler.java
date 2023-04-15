package Project3;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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


    public APIRequestHandler(String cityName, String longitude, String latitude, String startTime, String endTime, String type) {

        this.longitude = longitude;
        this.latitude = latitude;
        this.startTime = startTime;
        this.endTime = endTime;
        APIRequestHandler.cityName = cityName;

        if(type.equals("Historic")) {
//            this.apiURL = "https://history.openweathermap.org/data/2.5/history/city?lat="+ latitude + "&lon="+ longitude +"&type=hour&start=" + startTime + "&end=" + endTime + "&appid=" + aPiKey02+"&units=metric";
//            this.apiURL = "https://history.openweathermap.org/data/2.5/history/city?lat=" + latitude + "&lon=" + longitude + "&type=hour&start=" + startTime + "&cnt=8&appid=" + aPiKey02+"&units=metric";
            this.apiURL = "https://history.openweathermap.org/data/2.5/history/city?lat=" + getLatitude() + "&lon=" + getLongitude() + "&appid="+ aPiKey03 + "&units=metric";
        }
        else if (type.equals("Current")){
            this.apiURL = "https://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude +"&appid=" + aPiKey02+"&units=metric";
        }
        else {
            this.apiURL = "https://api.openweathermap.org/data/2.5/forecast/daily?lat=" + latitude + "&lon="+ longitude + "&cnt=7&appid=bf35fb6d7822ade28ea3197bae75439c&units=metric";
        }

    }

    public APIRequestHandler(TemperatureMap tmpMap) {
        this.apiURL = "http://maps.openweathermap.org/maps/2.0/weather/"+ tmpMap.getLayer() +"/"+ tmpMap.getZoom() +"/"+ tmpMap.getXcoord() +"/"+ tmpMap.getYcoord() +"?date="+tmpMap.getDatetime()+"&appid="+aPiKey03;
    }

    public APIRequestHandler(TemperatureMap tmpMap, int type) {
        this.apiURL = "http://maps.openweathermap.org/maps/2.0/weather/"+tmpMap.getLayer()+"/"+tmpMap.getZoom()+"/"+tmpMap.getXcoord()+"/"+tmpMap.getYcoord()+"?appid="+aPiKey02+"&fill_bound=true&opacity=0.6&palette=-65:821692;-55:821692;-45:821692;-40:821692;-30:8257db;-20:208cec;-10:20c4e8;0:23dddd;10:c2ff28;20:fff028;25:ffc228;30:fc8014";
    }

    public APIRequestHandler(String cityName, String latitude, String longitude, String current) {
        this.apiURL = "https://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude +"&appid=" + aPiKey02+"&units=metric";
        APIRequestHandler.cityName = cityName;
    }


    public APIRequestHandler(String cityName, String latitude, String longitude, String current, int x) {
        this.apiURL = "https://api.openweathermap.org/data/2.5/forecast/daily?lat=" + latitude + "&lon="+ longitude + "&cnt=7&appid=bf35fb6d7822ade28ea3197bae75439c&units=metric";
        APIRequestHandler.cityName = cityName;

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



    // gets weather data
    public String getWeatherData() throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();

        return (client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(APIRequestHandler::parseWeatherData)
                        .join());

    }


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
            //System.out.println("temp adding: " + report.getJSONObject("main").getDouble("temp"));
            temp += report.getJSONObject("main").getDouble("temp");
            humidity += report.getJSONObject("main").getInt("humidity");
            count += 1;

            weather = report.getJSONArray("weather").getJSONObject(0).getString("main");
            description = report.getJSONArray("weather").getJSONObject(0).getString("description");
            icon = "https://openweathermap.org/img/wn/" + report.getJSONArray("weather").getJSONObject(0).getString("icon") + "@2x.png";
        }
        //System.out.println(temp);
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

//        City aCity = new City(APIRequestHandler.cityName, temp, humidity, description, weather, icon, datetime);
//        City.cityWeatherData.add(aCity);
        modeOfWeather.clear();
        modeOfDesc.clear();
        modeOfIcon.clear();

        return null;
    }



    /*
    Methods for historic weather data
    */
    public String getHistoricWeatherData() throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();

        return (client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(APIRequestHandler::parseHistoricData)
                .join());

    }

    public static String parseHistoricData(String responseBody) {

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
            temp += report.getJSONObject("main").getDouble("temp");
            humidity += report.getJSONObject("main").getInt("humidity");

            weather = report.getJSONArray("weather").getJSONObject(0).getString("main");
            description = report.getJSONArray("weather").getJSONObject(0).getString("description");
            icon = icon = "https://openweathermap.org/img/wn/" + report.getJSONArray("weather").getJSONObject(0).getString("icon") + "@2x.png";


            if (i % 24 == 0 && i != 0) {
                temp /= 24;
                temp = Math.round(temp * 100.00) / 100.00;
                humidity /= 24;
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

            }
        }
        City aCity = new City(APIRequestHandler.cityName, temp, humidity, description, weather, icon, datetime);
        City.historicSevenDayCityData.add(aCity);
//        System.out.println(City.historicSevenDayCityData.isEmpty());
//        for(City city : City.historicSevenDayCityData) {
//            System.out.println(city.getCityName() + "\t" + city.getTemp() + "\t" + city.getHumidity() + "\t" + city.getIcon());
//        }

        return null;

//
//
//        JSONObject cityData = new JSONObject(responseBody);
//
//        JSONArray hourlyReports = cityData.getJSONArray("list");
//
//        for (int i = 0; i < hourlyReports.length(); i++) {
//            JSONObject report = hourlyReports.getJSONObject(i);
//            temp += report.getJSONObject("main").getDouble("temp");
//            humidity += report.getJSONObject("main").getInt("humidity");
//
//            count += 1;
//
//            weather = report.getJSONArray("weather").getJSONObject(0).getString("main");
//            description = report.getJSONArray("weather").getJSONObject(0).getString("description");
//            icon  = "https://openweathermap.org/img/wn/" + report.getJSONArray("weather").getJSONObject(0).getString("icon") + "@2x.png";
//
//            modeOfWeather.add(weather);
//            modeOfDesc.add(description);
//            modeOfIcon.add(icon);
//
//            System.out.print(APIRequestHandler.cityName+"\t");
//            System.out.print(temp+"\t");
//            System.out.print(humidity+"\t");
//            System.out.println(weather+"\t");
//
//
//        }
//
//        temp /= count;
//        temp = Math.round(temp * 100.00) / 100.00;
//        humidity /= count;
//
//
//        weather = getFrequentItem(modeOfWeather);
//        description = getFrequentItem(modeOfWeather);
//        icon = getFrequentItem(modeOfIcon);
//
//
//        System.out.println("---------------------------------------------------------------");
//        System.out.print(APIRequestHandler.cityName+"\t");
//        System.out.print(temp+"\t");
//        System.out.print(humidity+"\t");
//        System.out.print(weather+"\t");
//
//        City aCity = new City(APIRequestHandler.cityName, temp, humidity, description, weather, icon, datetime);
//        City.historicSevenDayCityData.add(aCity);
//
//        System.out.println(City.historicSevenDayCityData.isEmpty());
//        for(City city : City.historicSevenDayCityData) {
//            System.out.print(city.getCityName() + "\t" + city.getTemp() + "\t" + city.getHumidity() + "\t" + city.getIcon());
//        }
//
//        modeOfWeather.clear();
//        modeOfDesc.clear();
//        modeOfIcon.clear();
//
//        return null;
//    }
    }

    //temperature map handler
    //parse map data

    public String getTempMapData() throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();

        return (client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(APIRequestHandler::parseTempMapData)
                .join());
    }

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
        //System.out.println(temp);
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




    /*
   Methods for future weather data
    */

    public String getFutureWeatherData() throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();

        return (client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(APIRequestHandler::sevenDayfutureData)
                .join());

//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//                .thenAccept(System.out::println)
//                .join();
    }

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
            System.out.println(report);
            temp = report.getJSONObject("temp").getDouble("day");
            humidity = report.getInt("humidity");

            weather = report.getJSONArray("weather").getJSONObject(0).getString("main");
            description = report.getJSONArray("weather").getJSONObject(0).getString("description");
            icon = icon = "https://openweathermap.org/img/wn/" + report.getJSONArray("weather").getJSONObject(0).getString("icon") + "@2x.png";

            City aCity = new City(APIRequestHandler.cityName, temp, humidity, description, weather, icon, datetime);
            City.futureSevenDayCityData.add(aCity);
        }

        return null;

    }


    /*
    Methods for current weather data
     */


    public String getCurrentWeatherData() throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();

        return (client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(APIRequestHandler::currentWeatherData).join());
    }


    public static String currentWeatherData(String responseBody) {
        double temp = 0.00;
        int humidity = 0;
        String description = null;
        String weather = null;
        String icon = null;
        int datetime = 0;

        JSONObject data = new JSONObject(responseBody);
        icon = "https://openweathermap.org/img/wd/"+ data.getJSONArray("weather").getJSONObject(0).getString("icon")+"@2x.png";
        description = data.getJSONArray("weather").getJSONObject(0).getString("description");
        weather = data.getJSONArray("weather").getJSONObject(0).getString("main");

        temp = data.getJSONObject("main").getDouble("temp");
        humidity = data.getJSONObject("main").getInt("humidity");


        City aCity = new City(APIRequestHandler.cityName, temp, humidity, description, weather, icon, datetime);
        City.cityList.add(aCity);

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
