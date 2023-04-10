package Project3;

import java.util.ArrayList;

public class City implements Comparable<City> {


    public static int city_id = 0;
    private int id;
    private String cityName;
    private String lat;
    private String lon;
    private WeatherCondition current; //?Usage Questionable
    private ArrayList <WeatherCondition> weatherHistory;
    //public static ArrayList <City> cityList;


    public City(String cityName) {
        this.cityName = cityName;
        this.lat = APIGeoCord.fetchGeoCordinates(cityName)[0];
        this.lon = APIGeoCord.fetchGeoCordinates(cityName)[1];
        this.weatherHistory = new ArrayList<>();
        assignId();
//      cityList.add(this);
    }

    private void assignId() {
        this.id = city_id;
        city_id++;
    }

    public static int getCity_id() {
        return city_id;
    }

    public static void setCity_id(int city_id) {
        City.city_id = city_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public WeatherCondition getCurrent() {
        return current;
    }

    public void setCurrent(WeatherCondition current) {
        this.current = current;
    }

    public ArrayList<WeatherCondition> getWeatherHistory() {
        return weatherHistory;
    }

    public void setWeatherHistory(ArrayList<WeatherCondition> weatherHistory) {
        this.weatherHistory = weatherHistory;
    }

    public void addToHistory(WeatherCondition wc){
        weatherHistory.add(wc);
    }

    public void removeHistory(WeatherCondition wc) {
        weatherHistory.remove(wc);
    }

//        public static void removeCity(City city){
//        cityList.remove(city);
//    }

    @Override
    public int compareTo(City o) {
        return 0;
    }






}
