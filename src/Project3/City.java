package Project3;

import java.util.ArrayList;

public class City implements Comparable {

    public static int city_id = 0;

    private String cityName = null;
    private double temp = 0.00;
    private int humidity = 0;
    private String description = null;
    private String type = null;
    private String icon  =  null;
    private int datetime = 0;
    private ArrayList<String> cityWeatherData = new ArrayList<>();

    public City(String cityName, double temp, int humidity, String description, String type, String icon, int datetime) {
        this.cityName = cityName;
        this.temp = temp;
        this.description = description;
        this.type = type;
        this.icon = icon;
        this.datetime = datetime;
        generateID();
    }

    private void generateID() {
        this.city_id++;
    }



    public City(String cityName) {
        this.cityName = cityName;
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getDatetime() {
        return datetime;
    }

    public void setDatetime(int datetime) {
        this.datetime = datetime;
    }

    public ArrayList<String> getCityWeatherData() {
        return cityWeatherData;
    }

    public void setCityWeatherData(ArrayList<String> cityWeatherData) {
        this.cityWeatherData = cityWeatherData;
    }
}
