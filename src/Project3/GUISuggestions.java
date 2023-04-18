package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GUISuggestions extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JButton button;

    public GUISuggestions() {
        super("Umbrella Checker");
        panel = new JPanel();
        label = new JLabel("Enter a city:");
        textField = new JTextField(20);
        button = new JButton("Check");
        button.addActionListener(this);

        panel.add(label);
        panel.add(textField);
        panel.add(button);

        add(panel);
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Modified line
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String city = textField.getText();
        String[] validCities = {"Lucea",
        "Falmouth"
        ,"Mandeville"
        ,"Negril"
       ,"Kingston"
        ,"Montego Bay"
        ,"Spanish Town"
        ,"Port Antonio"
        ,"Port Maria"
        ,"May Pen"
        ,"Morant Bay"
        ,"Ocho Rios"
        ,"Santa Cruz"
        ,"Savanna-La-Mar"};

        if (city.trim().equals("")) {
            JOptionPane.showMessageDialog(panel, "Please enter a city.");
        } else if (contains(validCities, city)) {
            City.cityList = new ArrayList<>();
            //the below code creates the city and adds it to the list.
            RainLibrary.getCurrentCityData(RainLibrary.createCityData(city));
            compareTemperatures(City.historicSevenDayCityData, City.futureSevenDayCityData,City.cityList.get(0));
//            JOptionPane.showMessageDialog(panel, "You should bring an umbrella!");
        } else {
            City.cityList = new ArrayList<>();
            RainLibrary.getCurrentCityData(RainLibrary.createCityData(city));
            compareTemperatures(City.historicSevenDayCityData, City.futureSevenDayCityData,City.cityList.get(0));
//            JOptionPane.showMessageDialog(panel, "You don't need an umbrella.");
        }
    }

    private boolean contains(String[] array, String value) {
        for (String element : array) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public int compareTemperatures(ArrayList<City> list1, ArrayList<City> list2, City currentCity) {
        if(list1.isEmpty() || list2.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Use the main menu options to run 7-Day forecast, \n7-day weather history and the current weather first before requesting a suggestion");
        }
        City city1 = null;
        City city2 = null;

        // Find the first city in the first array list that matches the name of the current city
        for (City city : list1) {
            if (city.getCityName().equals(currentCity.getCityName())) {
                city1 = city;
                break;
            }
        }

        // Find the first city in the second array list that matches the name of the current city
        for (City city : list2) {
            if (city.getCityName().equals(currentCity.getCityName())) {
                city2 = city;
                break;
            }
        }

        // Compare the temperatures and descriptions of the two cities
        if (city1 != null && city2 != null) {
            if (city1.getDescription().equals(city2.getDescription())) {
                if(city1.getTemp() <= 25) {
                    JOptionPane.showMessageDialog(panel, "You should bring an umbrella!");
                    return Double.compare(city1.getTemp(), city2.getTemp());
                }
            } else {
                double temp1 = city1.getTemp();
                double temp2 = city2.getTemp();
                String desc1 = city1.getDescription();
                String desc2 = city2.getDescription();

                if (desc1.compareTo(desc2) > 0) {
                    double thisV = Double.compare(temp1, currentCity.getTemp());
                    if(thisV > 25) {
                        JOptionPane.showMessageDialog(panel, "Good day to visit the beach!");
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Expect changing weather conditions!");
                    return Double.compare(temp2, currentCity.getTemp());
                }
            }
        }

        // If either city is not found, return 0
        return 0;
    }



}
