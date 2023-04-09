
package Project3;

import java.util.ArrayList;
import java.util.Arrays;

public class Tester {

	public static void getCities() {

	}
	public static final String api_KeyCloud = "bf35fb6d7822ade28ea3197bae75439c";

	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//		UserAuthenticate thisUser = new UserAuthenticate("tevinH", "password1")

		//new LoginWindow();

		String cityInput = "Montego Bay";
		String lat = GeoCordAPI.fetchGeoCordinates(cityInput)[0];
		String lon = GeoCordAPI.fetchGeoCordinates(cityInput)[1];
		String start = "1680980400";
		String end = "1680991200";

		City city = new City(cityInput);

		String [] weatherData= OpenWeatherMapAPI.fetchWeatherData(lat, lon, start, end);

		for (String record: weatherData) {

			String dt = record.split("\\|")[0];
			double temp = Double.parseDouble(record.split("\\|")[1]);
			int humid = Integer.parseInt(record.split("\\|")[2]);
			String main = record.split("\\|")[3];
			String desc = record.split("\\|")[4];

			city.addToHistory(new WeatherCondition(temp, humid, main, desc, dt));
		}

		for (WeatherCondition wc: city.getWeatherHistory()){
			System.out.println(wc.toString());
		}




		/*ArrayList<String> cityList = new ArrayList<>();
		cityList.add("Lucea");
		cityList.add("Falmouth");
		cityList.add("Mandeville");
		cityList.add("Negril");
		cityList.add("Kingston");
		cityList.add("Montego+Bay");
		cityList.add("Spanish+Town");
		cityList.add("Port+Antonio");
		cityList.add("Port+Maria");
		cityList.add("May+Pen");
		cityList.add("Morant+Bay");
		//cityList.add("Saint+Andrew");
		cityList.add("Ocho+Rios");
		cityList.add("Santa+Cruz");
		//cityList.add("Savanna-La-Mar");


		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("City ID\t\tCity\t\t\t\tTemperature\t\t\t\t\tType\t\t\t\tDescription");
		System.out.println("------------------------------------------------------------------------------------");
//		System.out.println(myCity.getCityName()+"\t\t"+ myCity.getTemp()+"\t\t"+ myCity.getType()+"\t\t"+ myCity.getDescription());

		for(String cityCapital : cityList) {

			APIRequestHandler test = new APIRequestHandler("https://history.openweathermap.org/data/2.5/history/city?q=" + cityCapital + ",JM&type=hour&start=1680343200&end=1680735600&appid="+api_KeyCloud, cityCapital);
			//APIRequestHandler test = new APIRequestHandler("https://history.openweathermap.org/data/2.5/history/city?lat=18.017874&lon=-76.809906&type=hour&start=1680343200&end=1680386400&appid=" + api_KeyCloud);
			try {
				test.getWeatherData();
				for(var city : City.cityWeatherData) {
					System.out.println(City.getCity_id()+ "\t\t" + city.getCityName() + "\t\t\t\t" + city.getTemp() + "\t\t\t\t\t\t" + city.getType() +"\t\t\t\t\t"+ city.getDescription());
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}*/

	}


}