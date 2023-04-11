package Project3;

import org.json.JSONObject;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class APICurrentWeather {

    //https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&units=metric&appid={API key}
    private static final String BASE_API_URL = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric&appid=%s";
    private static final String API_KEY = "dbd8574d10549e41443636960496338d";


    public APICurrentWeather() {
    }

    public static String [] getData (String lat, String lon){

        try {
            String urlString = String.format(BASE_API_URL, lat, lon, API_KEY);
            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //System.out.println(response);
            return parseData(response.toString());

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (ProtocolException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static String [] parseData (String response){

        JSONObject weatherData = new JSONObject(response);

        int dt = weatherData.getInt("dt");
        double temp = weatherData.getJSONObject("main").getDouble("temp");
        int humidity = weatherData.getJSONObject("main").getInt("humidity");
        String mainWeather = weatherData.getJSONArray("weather").getJSONObject(0).getString("main");
        String desc = weatherData.getJSONArray("weather").getJSONObject(0).getString("description");
        String icon = weatherData.getJSONArray("weather").getJSONObject(0).getString("icon");

//        String weather = String.format("%d|%.2f|%d|%s|%s|%s", dt, temp, humidity, mainWeather,desc, icon);
//        System.out.println(weather);


        return new String [] {String.valueOf(dt), String.valueOf(temp), String.valueOf(humidity), mainWeather, desc, icon};
    }
}
