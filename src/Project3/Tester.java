
package Project3;

import java.util.*;

public class Tester {

	public static void getCities() {

	}

	public static final String api_KeyCloud = "bf35fb6d7822ade28ea3197bae75439c";

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




	public static void main(String[] args) throws Exception {
//		 TODO Auto-generated method stub

		new GUILoginWindow();
/*
		//new LoginWindow();

		String startTime = "1680343200";
		//7day
//		String startTime = "1680256800";
		String endTime = "1680386400";
//		String endTime = "1680948000";

		ArrayList<String> cityList = new ArrayList<>();
		cityList.add("Lucea");
		cityList.add("Falmouth");
		cityList.add("Mandeville");
		cityList.add("Negril");
		cityList.add("Kingston");
		cityList.add("Montego Bay");
		cityList.add("Spanish Town");
		cityList.add("Port Antonio");
		cityList.add("Port Maria");
		cityList.add("May Pen");
		cityList.add("Morant Bay");
		//cityList.add("Saint Andrew");
		cityList.add("Ocho Rios");
		cityList.add("Santa Cruz");
		cityList.add("Savanna-La-Mar");

		//rename
		var cityData = createCityData(cityList);



		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("City ID\t\tCity\t\t\t\t\t\tTemperature\t\tHumidity\t\tWeather\t\tDescription\t\t\t");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");

//		for (String cityCapital : cityList) {
//
//			APIRequestHandler test = new APIRequestHandler( latitude, startTime, endTime,"Historic");
//
//			//APIRequestHandler test = new APIRequestHandler("https://history.openweathermap.org/data/2.5/history/city?q=" + cityCapital + ",JM&type=hour&start=1680343200&end=1680411599&appid=" + api_KeyCloud, cityCapital);
//			//APIRequestHandler test = new APIRequestHandler("https://history.openweathermap.org/data/2.5/history/city?lat=18.017874&lon=-76.809906&type=hour&start=1680343200&end=1680386400&appid=" + api_KeyCloud);
//			try {
//				test.getWeatherData();
//			} catch (Exception e) {
//				throw new RuntimeException(e);
//			}
//		}

		for(Map.Entry<String, String[]> set: cityData.entrySet()) {
			String latitude = set.getValue()[1];
			String longitude = set.getValue()[0];

			APIRequestHandler test = new APIRequestHandler(set.getKey(), latitude, longitude, startTime, endTime, "Historic");

			try {
				test.getWeatherData();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			//if 7 day data
//		try {
//			test.getForecastWeatherData();
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}

		}

//
		//to get daily data
		for (var city : City.cityWeatherData) {
			System.out.println(city.getCityID() + "\t\t" + city.getCityName() + "\t\t\t\t\t\t" + city.getTemp() + "\t" + city.getHumidity() + "\t" +  city.getIcon() + "\t" + city.getDescription());
		}

		//to get 7day data
//		for (var city : City.sevenDayCityData) {
//			System.out.println(city.getCityID() + "\t\t" + city.getCityName() + "\t\t\t\t\t\t" + city.getTemp() + "\t" + city.getHumidity() + "\t" +  city.getIcon() + "\t" + city.getDescription());
//		}
		*/
	}


}