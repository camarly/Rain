package Project3;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GeoCordAPI {

    private static final String BASE_API_URL = "http://api.openweathermap.org/geo/1.0/direct?q=%s,JM&limit=1&appid=%s"; //http://api.openweathermap.org/geo/1.0/direct?q={city name},{country code}&limit={limit}&appid={API key}
    private static final String API_KEY = "dbd8574d10549e41443636960496338d";
    private HttpClient client;
    private HttpRequest request;
    private static String apiResponse;

    public GeoCordAPI() {
    }

    public static String [] fetchGeoCordinates(String city){

        try {
            apiResponse = null;


            String query = String.format("%s", URLEncoder.encode(city, "UTF-8"));
            String apiCall = String.format(BASE_API_URL, query, API_KEY);

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
