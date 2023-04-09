package Project3;

public class WeatherCondition {

    private double temperature;
    private int humidity;
    private String generalWeatherCondition;
    private String generalWeatherDescription;
    private String datetime;
    //private City city;

    public WeatherCondition() {
        // TO determine.

    }

    public WeatherCondition(double temp, int humidity, String main, String description, String datetime) {
        this.temperature = temp;
        this.humidity = humidity;
        this.generalWeatherCondition = main;
        this.generalWeatherDescription = description;
        this.datetime = datetime;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getGeneralWeatherCondition() {
        return generalWeatherCondition;
    }

    public String getGeneralWeatherDescription() {
        return generalWeatherDescription;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setGeneralWeatherCondition(String generalWeatherCondition) {
        this.generalWeatherCondition = generalWeatherCondition;
    }

    public void setGeneralWeatherDescription(String generalWeatherDescription) {
        this.generalWeatherDescription = generalWeatherDescription;
    }


    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "WeatherConditions: " + "temperature=" + temperature + ", humidity=" + humidity + ", General Condition=" + generalWeatherCondition + ", General Description=" + generalWeatherDescription + ", Datetime=" + datetime;
    }

}
