
package Project3;

import java.util.ArrayList;

public class Tester {

	public static void getCities() {

	}

	public static final String api_KeyCloud = "bf35fb6d7822ade28ea3197bae75439c";

	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//		UserAuthenticate thisUser = new UserAuthenticate("tevinH", "password1")

		/*new LoginWindow();
		new GUICurrentWeatherList();
		new GUICityEntry();
		new GUICityEdit();
		new GUIWeekHistory();
		new TemperatureMap();

		new GUIWeekHistoryEntry();

		long unix = 1672585200;
		String readable = "January 1, 2023";*/

		new HomeScreen();


		/*
//		//Convert Unix time to readable time
//		public String UnixToDate (long unix){
//			Date date = new Date (unix * 1000);
//
//			SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
//			dateFormat.setTimeZone((TimeZone.getTimeZone("UTC-5")));
//			String readableDT = dateFormat.format(date);
//			return readableDT;
//		}
//
//		//Convert readable time to Unix
//
//		public String DateToUnix (String readableDT){
//
//			SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
//			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC-5"));
//			Date date = dateFormat.parse(readableDT);
//			long unixDT = date.toInstant().getEpochSecond();
//			System.out.println("unixDT");
//
//		}

//		Date date = new Date (unix * 1000);
//
//			SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM-dd-yyyy");
//			dateFormat.setTimeZone((TimeZone.getTimeZone("UTC-5")));
//			String readableDT = dateFormat.format(date);
//			System.out.println(readableDT);
//
//
//		SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
//			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC-5"));
//			Date date1 = dateFormat.parse(readable +" 00:00:00");
//			long unixDT = date.toInstant().getEpochSecond();
//			System.out.println(unixDT);



		//Convert readable time to Unix Time:



		//APIGeoCord.getData("Montego Bay");

        /*String cityInput = "Montego Bay";
		String lat = APIGeoCord.fetchGeoCordinates(cityInput)[0];
		String lon = APIGeoCord.fetchGeoCordinates(cityInput)[1];
		String start = "1680980400";
		String end = "1680991200";

		City city = new City(cityInput);

		String [] weatherData= APIHistoricalWeather.fetchWeatherData(lat, lon, start, end);

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
		}*/

        /*String[] latLon = APIGeoCord.fetchGeoCordinates("Savanna-La-Mar");

		System.out.println(latLon[0]);
		System.out.println(latLon[1]);*/

		//new GUIWeekHistory();

//		String lat = "18.476223";
//		String lon = "-77.893890";
//		String start = "1681045200";
//		String end = "1681092000";

		//APICurrentWeather.getData(lat, lon);
		//APIGeoCord.getData("Montego Bay");
		//APIHistoricalWeather.getData(lat, lon, start, end);

        /*String start = "1681045200";
		String end = "1681092000";

		ArrayList<String> cityList = new ArrayList<>();
		ArrayList<City>  cities = new ArrayList<>();

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
		cityList.add("Ocho Rios");
		cityList.add("Santa Cruz");
		cityList.add("Savanna-La-Mar");

		for (String cityInput: cityList){
			City city = new City(cityInput);
			cities.add(city);

			String [] weatherData = APIHistoricalWeather.getData(city.getLat(),city.getLon(),start, end);

			for (String weather: weatherData){
				String [] record = weather.split(",");
				double temp = Double.parseDouble(record[1]);
				int humidity = Integer.parseInt(record[2]);
				String mainWeather = record[3];
				String desc = record[4];
				String dt = record[0];
				WeatherCondition wc = new WeatherCondition(temp, humidity, mainWeather, desc, dt);
				city.addToHistory(wc);
				String toPrint = String.format("City: %s - %d, Temp: %.2f, Humidity: %d, Weather: %s, Description: %s, Daytime: %s", city.getCityName(),city.getId(), temp, humidity, mainWeather, desc, dt);
				System.out.println(toPrint);


			}


		}*/

		/*for (WeatherCondition weather : cities.get(0).getWeatherHistory() ){
			System.out.println(weather.toString());
		}*/

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
		cityList.add("Saint+Andrew");
		cityList.add("Ocho+Rios");
		cityList.add("Santa+Cruz");
		cityList.add("Savanna-La-Mar");


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