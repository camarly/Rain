
package Project3;

import javax.swing.*;
import java.util.*;

public class Tester {

	public static void getCities() {

	}

	public static final String api_KeyCloud = "bf35fb6d7822ade28ea3197bae75439c";

	public static HashMap<String, String[]> createCityData(ArrayList<String> cities) {
		HashMap<String, String[]> cityGeoData = new HashMap<>();
		String[] geoData = new String[2];
		String longitude = null;
		String latitude = null;

		for(String city : cities) {
			if(city.contains(" ")) {
				latitude = Objects.requireNonNull(APIRequestHandler.fetchGeoCoordinates(city))[0];
				longitude = Objects.requireNonNull(APIRequestHandler.fetchGeoCoordinates(city))[1];
			}
			geoData[0] = latitude;
			geoData[1] = longitude;

			cityGeoData.put(city, geoData);
		}
		return cityGeoData;

	}


	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//		UserAuthenticate thisUser = new UserAuthenticate("tevinH", "password1")

		//new LoginWindow();

		String startTime = "1680343200";
		String endTime = "1680386400";

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
		cityList.add("Saint Andrew");
		cityList.add("Ocho Rios");
		cityList.add("Santa Cruz");
		cityList.add("Savanna-La-Mar");

		//rename
		var cityData = createCityData(cityList);

//		for(Map.Entry<String, String[]> set: cityData.entrySet())  {
//			System.out.println(set.getKey() + " : " + set.getValue()[0] + ", " + set.getValue()[1]);
//		}

//
//		String cityInput = "Montego Bay";
//
//		String latitude = Objects.requireNonNull(APIRequestHandler.fetchGeoCoordinates(cityInput))[0];
//		String longitude = Objects.requireNonNull(APIRequestHandler.fetchGeoCoordinates(cityInput))[1];

//		System.out.println(latitude);
//		System.out.println(longitude);

		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("City ID\t\tCity\t\t\t\t\t\tTemperature\t\tHumidity\t\tWeather\t\tDescription\t\t\t");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
//		System.out.println(myCity.getCityName()+"\t\t"+ myCity.getTemp()+"\t\t"+ myCity.getType()+"\t\t"+ myCity.getDescription());

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

		for(Map.Entry<String, String[]> set: cityData.entrySet())  {

			APIRequestHandler test = new APIRequestHandler(set.getKey(), set.getValue()[0], set.getValue()[1], startTime, endTime,"Historic");

			//APIRequestHandler test = new APIRequestHandler("https://history.openweathermap.org/data/2.5/history/city?q=" + cityCapital + ",JM&type=hour&start=1680343200&end=1680411599&appid=" + api_KeyCloud, cityCapital);
			//APIRequestHandler test = new APIRequestHandler("https://history.openweathermap.org/data/2.5/history/city?lat=18.017874&lon=-76.809906&type=hour&start=1680343200&end=1680386400&appid=" + api_KeyCloud);
			try {
				test.getWeatherData();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			//System.out.println(set.getKey() + " : " + set.getValue()[0] + ", " + set.getValue()[1]);
		}


		for (var city : City.cityWeatherData) {
			System.out.println(city.getCityID() + "\t\t" + city.getCityName() + "\t\t\t\t\t\t" + city.getTemp() + "\t" + city.getHumidity() + "\t" + "https://openweathermap.org/img/wn/"+ city.getIcon() +"@2x.png" + "\t" + city.getDescription());
		}

	}


}