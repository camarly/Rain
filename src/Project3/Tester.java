package Project3;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import org.json.JSONObject;
import org.json.JSONArray;


public class Tester {

	public static final String api_KeyCloud = "bf35fb6d7822ade28ea3197bae75439c";
	//longitude
	//latitude

	public static final String POSTS_API_URL = "https://history.openweathermap.org/data/2.5/history/city?lat=18.017874&lon=-76.809906&type=hour&start=1680343200&end=1680386400&appid=" + api_KeyCloud;
//	public static final String POSTS_API_URL = "https://history.openweathermap.org/data/2.5/history/city?lat=18.017874&lon=76.809906&type=hour&start=1680343200&end=1680735600&appid=ad063a14892951f9896784bfef4481d6";


	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		UserAuthenticate thisUser = new UserAuthenticate("tevinH", "password1");
//		if(thisUser.isValid) {
//			System.out.println("Valid");
//		}
//		else {
//			System.out.println("Invalid");
//		}
//
//
//		new LoginWindow();
//

		//Testing API
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(POSTS_API_URL)).build();
		client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
				.thenApply(HttpResponse::body)
				.thenAccept(Tester::parse)
				.join();
	}



		//parse method
//		public static String parseWeatherData(String responseBody) {
//			JSONObject cityData = new JSONObject(responseBody);
//				JSONArray _list = cityData.getJSONArray("list");
//				for(int i=0; i<_list.length(); i++) {
//
//				}
//			return null;
//		}


	public static void parse(String response) {

		JSONObject cityData = new JSONObject(response);

		int city_id = cityData.getInt("city_id");
		JSONArray _list = cityData.getJSONArray("list");

		for (int i = 0; i < _list.length(); i++){
			JSONObject data = _list.getJSONObject(i);
			JSONObject MAIN = data.getJSONObject("main");
			double temp = MAIN.getDouble("temp");
			int humidity = MAIN.getInt("humidity");
			System.out.println("temp: " + temp	+ " humidity:" + humidity);

		}

		for (int i = 0; i < _list.length(); i++) {
			JSONObject city = _list.getJSONObject(i);
			JSONArray weather = city.getJSONArray("weather");
			for (int j = 0; j < weather.length(); j++) {
				JSONObject weatherInfo = weather.getJSONObject(j);
				String description = weatherInfo.getString("description");
				String type = weatherInfo.getString("main");
				String icon = weatherInfo.getString("icon");
				System.out.println(type + " " + description + " " + icon);
			}
			int datetime = city.getInt("dt");
		}

	}



}
