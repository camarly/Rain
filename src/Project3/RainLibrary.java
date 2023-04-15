package Project3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 *  Library for all methods needed for operation
 */
public class RainLibrary {


    //creates multiple city
    public static HashMap<String, String[]> createCityData(ArrayList<String> cities) {
        HashMap<String, String[]> cityGeoData = new HashMap<>();
        String longitude = null;
        String latitude = null;

        for(String city : cities) {
            String [] geoData = {Objects.requireNonNull(APIRequestHandler.fetchGeoCoordinates(city))[0], Objects.requireNonNull(APIRequestHandler.fetchGeoCoordinates(city))[1]};

            cityGeoData.put(city, geoData);
        }
        return cityGeoData;

    }

    //creates single city
    public static HashMap<String, String[]> createCityData(String city) {
        HashMap<String, String[]> cityGeoData = new HashMap<>();
        String[] geoData = {Objects.requireNonNull(APIRequestHandler.fetchGeoCoordinates(city))[0], Objects.requireNonNull(APIRequestHandler.fetchGeoCoordinates(city))[1]};
        cityGeoData.put(city, geoData);
        return cityGeoData;
    }

    //get 7-day weather data
    public static void getSevenDayWeatherData(HashMap<String, String[]> cityGeoData, String startTime, String endTime, String type) {
        String city = null;
        String latitude = null;
        String longitude = null;
        try {
            for(Map.Entry<String, String[]> set: cityGeoData.entrySet()) {
                city = set.getKey();
                latitude = set.getValue()[0];
                longitude = set.getValue()[1];
            }
            if (type.equals("Historic")) {
                APIRequestHandler svnDayData = new APIRequestHandler(city, longitude, latitude, startTime, endTime, "Historic");
                svnDayData.getHistoricWeatherData();
            } else {
                APIRequestHandler futuresvnDayData = new APIRequestHandler(city, longitude, latitude, startTime, endTime, "Future");
                futuresvnDayData.getFutureWeatherData();
                //System.out.println(longitude + ":" +latitude);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void getAllCityData(HashMap<String, String[]> allCityData) {
        for (Map.Entry<String, String[]> set : allCityData.entrySet()) {
            String latitude = set.getValue()[1];
            String longitude = set.getValue()[0];

            APIRequestHandler currentData = new APIRequestHandler(set.getKey(), latitude, longitude, "Current");

            try {
                currentData.getWeatherData();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }


    public static void getCurrentCityData(HashMap<String, String[]> allCityData) {
        for (Map.Entry<String, String[]> set : allCityData.entrySet()) {
            String latitude = set.getValue()[1];
            String longitude = set.getValue()[0];

            APIRequestHandler currentData = new APIRequestHandler(set.getKey(), latitude,longitude, "Current");

            try {
                currentData.getCurrentWeatherData();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }


//    public static void getWeatherData(HashMap<String, String[]> cityData, String startTime, String endTime, String current) {
//    }
}
