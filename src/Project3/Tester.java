
package Project3;

import java.awt.*;
import java.util.*;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Tester {

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

	//7day display
	//get lat long
	// get the 7 day data
	// create city
	// display information


	//move to similar function
	public static HashMap<String, String[]> createCityData(String city) {
		HashMap<String, String[]> cityGeoData = new HashMap<>();
		String[] geoData = {Objects.requireNonNull(APIRequestHandler.fetchGeoCoordinates(city))[0], Objects.requireNonNull(APIRequestHandler.fetchGeoCoordinates(city))[1]};
		cityGeoData.put(city, geoData);
		return cityGeoData;
	}


	//get 7 day weather data
	public static void getSevenWeatherData(HashMap<String, String[]> cityGeoData, String startTime, String endTime) {
		String city = null;
		String latitude = null;
		String longitude = null;
		try {
			for(Map.Entry<String, String[]> set: cityGeoData.entrySet()) {
				city = set.getKey();
				latitude = set.getValue()[1];
				longitude = set.getValue()[0];
			}
			APIRequestHandler svnDayData = new APIRequestHandler(city, longitude, latitude, startTime,endTime, "Historic");
			svnDayData.getForecastWeatherData();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	//		try {
//			TemperatureMap tmpMap = new TemperatureMap("PAR0", "2", "1", "1", startTime);
//			APIRequestHandler test = new APIRequestHandler(tmpMap);
//			test.getForecastWeatherData();
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}


	public static void main(String[] args) throws Exception {


		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
//					GUILoginWindow loginWindow = new GUILoginWindow();
//					loginWindow.setVisible(true);
					MainMenu mainMenu = new MainMenu();
					mainMenu.setVisible(true);
				} catch (Exception e) {
					// If Nimbus is not available, you can set the GUI to another look and feel.
				}
			}
		});


		String startTime = "1680343200";
		String endTime = "1680386400";

		String startTimeSevenDay = "1680256800";
		String endTimeSevenDay = "1680948000";

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
		var allCityData = createCityData(cityList);


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

//		for(Map.Entry<String, String[]> set: cityData.entrySet()) {
//			String latitude = set.getValue()[1];
//			String longitude = set.getValue()[0];
//
//			APIRequestHandler test = new APIRequestHandler(set.getKey(), latitude, longitude, startTime, endTime, "Historic");
//
//			try {
//				test.getWeatherData();
//			} catch (Exception e) {
//				throw new RuntimeException(e);
//			}

		//if 7 day data
		//			try {
//				test.getWeatherData();
//			} catch (Exception e) {
//				throw new RuntimeException(e);
//			}


		//weatherMaptest
//		try {
//			TemperatureMap tmpMap = new TemperatureMap("PAR0", "2", "1", "1", startTime);
//			APIRequestHandler test = new APIRequestHandler(tmpMap);
//			test.getForecastWeatherData();
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}


//
		//to get daily data
//		for (var city : City.cityWeatherData) {
//			System.out.println(city.getCityID() + "\t\t" + city.getCityName() + "\t\t\t\t\t\t" + city.getTemp() + "\t" + city.getHumidity() + "\t" +  city.getIcon() + "\t" + city.getDescription());
//		}

		//to get 7day data
//		for (var city : City.sevenDayCityData) {
//			System.out.println(city.getCityID() + "\t\t" + city.getCityName() + "\t\t\t\t\t\t" + city.getTemp() + "\t" + city.getHumidity() + "\t" +  city.getIcon() + "\t" + city.getDescription());
//		}
	}




}