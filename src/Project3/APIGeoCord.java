package Project3;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class APIGeoCord {

    private static final String BASE_API_URL = "http://api.openweathermap.org/geo/1.0/direct?q=%s,JM&limit=1&appid=%s"; //http://api.openweathermap.org/geo/1.0/direct?q={city name},{country code}&limit={limit}&appid={API key}
    private static final String API_KEY = "dbd8574d10549e41443636960496338d";


    public APIGeoCord() {
    }

    public static String [] getData(String city){


        try {
            String query = String.format("%s", URLEncoder.encode(city, StandardCharsets.UTF_8));
            String urlString = String.format(BASE_API_URL, query, API_KEY);
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static String [] parseData (String response){

        String [] latLon = new String[2];

        if (response.equals("[]")){
            System.out.println("Location not found. Please try another city"); //Employ the use of message boxes
        } else{
            JSONArray jArray = new JSONArray(response);
            JSONObject results = jArray.getJSONObject(0);

            if (results.getString("country").equals("JM")){
                latLon[0] = Double.toString(results.getDouble("lat"));
                latLon[1] = Double.toString(results.getDouble("lon"));
//                System.out.println("Lat: " + latLon[0] + "Lon: " + latLon[0]);

            }else
                System.out.println("Unable to locate data on this city in Jamaica"); //Employ the use of message boxes
        }
        return latLon;
    }

}
