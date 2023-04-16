
package Project3;

import java.awt.*;
import java.util.*;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Tester {

	public static final String api_KeyCloud = "bf35fb6d7822ade28ea3197bae75439c";


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
					GUILoginWindow loginWindow = new GUILoginWindow();
					loginWindow.setVisible(true);
//					MainMenu mainMenu = new MainMenu();
//					mainMenu.setVisible(true);
//					APIRequestHandler test = new APIRequestHandler("44.34", "10.99", "Current");
					//test.getCurrentWeatherData();
//					APIRequestHandler.currentWeatherData(test.getCurrentWeatherData());
//					RainLibrary.getSevenDayWeatherData(RainLibrary.createCityData("Kingston"), "1681516800", "1680998400", "Historic");
//					displayWeatherData();
//					APIRequestHandler test = new APIRequestHandler("Kingston","17.9712148", "-76.7928128", "1681516800", "1680998400" ,"Historic");

//				    APIRequestHandler.parseHistoricData(test.getHistoricWeatherData());
//					APIRequestHandler test = new APIRequestHandler("Kingston","00000", "00000", "Future", 3);
//					test.getFutureWeatherData();
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
		var allCityData = RainLibrary.createCityData(cityList);


	}




}