package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class GUIWeekEntry extends JFrame {

    private final static String[] MONTHS = {"January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October", "November", "December"};
    private final static Integer[] DAYS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private final static Integer[] YEARS = {2023, 2022, 2021, 2020, 2019, 2018, 2017, 2016,
            2015, 2014, 2013};

    private JTextField cityField;
    private JComboBox<String> dbMonth;
    private JComboBox<Integer> dbDay;
    private JComboBox<Integer> dbYear;

    private JButton cmdSubmit;
    private JButton cmdCancel;
    private JPanel pnlDisplay;
    private JPanel pnlCmd;
    private JPanel pnlDate;

    private GUIWeekEntry thisFrame;

    private String type = null;

    public GUIWeekEntry(String type) {
        this.type = type;
        thisFrame = this;

        setTitle("Rain - Select city and date");

        pnlDisplay = new JPanel();
        pnlCmd = new JPanel();
        pnlDate = new JPanel();

        pnlDisplay.add(new JLabel("City Name"));
        cityField = new JTextField(20);
        pnlDisplay.add(cityField);

        dbMonth = new JComboBox<>(MONTHS);
        pnlDate.add(new JLabel("Month"));
        pnlDate.add(dbMonth);

        dbDay = new JComboBox<>(DAYS);
        pnlDate.add(new JLabel("Day"));
        pnlDate.add(dbDay);

        dbYear = new JComboBox<>(YEARS);
        pnlDate.add(new JLabel("Year"));
        pnlDate.add(dbYear);

        cmdSubmit = new JButton("Submit");
        cmdCancel = new JButton("Cancel");
        cmdSubmit.addActionListener(new SubmitButtonListener());
        cmdCancel.addActionListener(new CancelButtonListener());

        pnlCmd.add(cmdSubmit);
        pnlCmd.add(cmdCancel);
        //setLayout(new GridLayout(4, 3));

        add(pnlDisplay, BorderLayout.NORTH);
        add(pnlDate, BorderLayout.CENTER);
        add(pnlCmd, BorderLayout.SOUTH);

        if (type.equals("Future")){
            remove(pnlDate);
        }

        pack();
        setVisible(true);
    }

    //Convert Unix time to readable time
    public static String unixToDate (long unix){
        Date date = new Date (unix * 1000);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        dateFormat.setTimeZone((TimeZone.getTimeZone("UTC-5")));
        String readableDT = dateFormat.format(date);
        return readableDT;
    }


    //Convert readable time to Unix
    public String dateToUnix (String readableDT){

        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC-5"));
            Date date = dateFormat.parse(readableDT);
            String unixDT = String.valueOf(date.toInstant().getEpochSecond());
            System.out.println(unixDT);
            return unixDT;
        }catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<City> forecastGenerator(String cityName){
        ArrayList<City> forecastList = new ArrayList<>();

        String [] latLon = APIGeoCordHandler.fetchGeoCordinates(cityName);
        String [] forecast = APIForecastWeatherHandler.fetchForcastWeather(latLon[0], latLon[1]);

        for (String weather: forecast){
            String[] splitData = weather.split(",");
            int dt = Integer.parseInt(splitData[0]);
            double temp = Double.parseDouble(splitData[1]);
            int humidity = Integer.parseInt(splitData[2]);
            String mainWeather = splitData[3];
            String desc = splitData[4];
            String icon = splitData[5];

            City city = new City(cityName, temp, humidity, desc, mainWeather, icon, dt);
            forecastList.add(city);
        }
        return forecastList;
    }


    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           if (type.equals("Future")){
               String cityName = cityField.getText();
               ArrayList<City> forecastList = forecastGenerator(cityName);
               GUIWeekForecast weekForecastList = new GUIWeekForecast(forecastList);
               weekForecastList.setVisible(true);

           }
           else{

           } dispose();
        }
    }

    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }



    public String getReportType() {
        return this.type;
    }
}