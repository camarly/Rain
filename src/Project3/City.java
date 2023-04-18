package Project3;

import java.util.ArrayList;

public class City implements Comparable<City> {

    public static int city_id = 1;
    private int cityID = 0;
    private String cityName;
    private double temp = 0.00;
    private int humidity = 0;
    private String description = null;
    private String type = null;
    private String icon  =  null;
    private int datetime = 0;
    static ArrayList<City> cityWeatherData = new ArrayList<>();

    //historic 7 day
    static ArrayList<City> historicSevenDayCityData = new ArrayList<>();

    //current data
    static ArrayList<City> cityList = new ArrayList<>();

    //forecast 7 day
    static ArrayList<City> futureSevenDayCityData = new ArrayList<>();

    public City(String cityName, double temp, int humidity, String description, String type, String icon, int datetime) {
        this.cityName = cityName;
        this.temp = temp;
        this.humidity = humidity;
        this.description = description;
        this.type = type;
        this.icon = icon;
        this.datetime = datetime;
        cityID = getCity_id();
        generateID();
    }

    public City() {

    }

    public static void generateID() {
        city_id+=1;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.cityID = city_id;
    }


    public City(String cityName) {
        this.cityName = cityName;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }


    @Override
    public int compareTo(City o) {

        return this.getCityName().compareTo(o.getCityName());
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

//    public ArrayList<String> getCityWeatherData() {
//        return cityWeatherData;
//    }

//    public void setCityWeatherData(ArrayList<String> cityWeatherData) {
//        this.cityWeatherData = cityWeatherData;
//    }

    public void setCurrentWeather(){
        String lat = APIRequestHandler.fetchGeoCoordinates(getCityName())[0];
        String lon = APIRequestHandler.fetchGeoCoordinates(getCityName())[1];

        setTemp(Double.parseDouble(APICurrentWeatherHandler.fetchWeatherData(lat, lon)[1]));
        setHumidity(Integer.parseInt(APICurrentWeatherHandler.fetchWeatherData(lat, lon)[2]));
        setType(String.valueOf(APICurrentWeatherHandler.fetchWeatherData(lat, lon)[3]));
        setDescription(APICurrentWeatherHandler.fetchWeatherData(lat, lon)[4]);

    }

}
