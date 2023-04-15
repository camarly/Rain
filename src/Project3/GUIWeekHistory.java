package Project3;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;


public class GUIWeekHistory extends JFrame {

    private JButton cmdClose;
    private JButton cmdExport;
    private JButton cmdSortByName;
    private JButton cmdSortByTemperature;
    private JButton cmdSortByHumidity;

    private JPanel pnlDisplay;
    private JPanel pnlCmd;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    private JTable table;
    private String type = null;

    JFrame frame = new JFrame();


    public GUIWeekHistory(String city, String startTime, String endTime, String type) throws IOException {

        setSize(600, 600);

        this.type = type;
        setTitle("Rain - Prior Week History");

        //Date date = ne
        String[] columnNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] rowNames = {"Date", "City", "Temperature", "Humidity", "Weather Condition", "Description"};

        RainLibrary.getSevenWeatherData(RainLibrary.createCityData(city), startTime, endTime, type);
        displayWeatherData();


        Object[][] data = {
                {getIconImage(City.sevenDayCityData.get(0).getIcon()), getIconImage(City.sevenDayCityData.get(1).getIcon()), getIconImage(City.sevenDayCityData.get(2).getIcon()), getIconImage(City.sevenDayCityData.get(3).getIcon()), getIconImage(City.sevenDayCityData.get(4).getIcon()), getIconImage(City.sevenDayCityData.get(5).getIcon()), getIconImage(City.sevenDayCityData.get(6).getIcon())},
                {City.sevenDayCityData.get(0).getDescription(), City.sevenDayCityData.get(1).getDescription(), City.sevenDayCityData.get(2).getDescription(), City.sevenDayCityData.get(3).getDescription(), City.sevenDayCityData.get(4).getDescription(), City.sevenDayCityData.get(5).getDescription(), City.sevenDayCityData.get(6).getDescription()},
                {City.sevenDayCityData.get(0).getTemp(), City.sevenDayCityData.get(1).getTemp(), City.sevenDayCityData.get(2).getTemp(), City.sevenDayCityData.get(3).getTemp(), City.sevenDayCityData.get(4).getTemp(), City.sevenDayCityData.get(5).getTemp(), City.sevenDayCityData.get(6).getTemp()},
                {City.sevenDayCityData.get(0).getHumidity(), City.sevenDayCityData.get(1).getHumidity(), City.sevenDayCityData.get(2).getHumidity(), City.sevenDayCityData.get(3).getHumidity(), City.sevenDayCityData.get(4).getHumidity(), City.sevenDayCityData.get(5).getHumidity(), City.sevenDayCityData.get(6).getHumidity()}
        };
        tableModel = new DefaultTableModel(data, columnNames) {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class

            @Override
            public Class<?> getColumnClass(int row) {
                for (int i = 0; i < getColumnCount(); i++) {
                    if (getRowCount() == 0) {
                        return ImageIcon.class;
                    }
                }
                return Object.class;
            }
        };


        JTable table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        JButton sortByName = new JButton("Sort by Name");
        JButton sortByTemp = new JButton("Sort by Temperature");
        JButton sortByHum = new JButton("Sort by Humidity");


        pnlDisplay = new JPanel();
        pnlCmd = new JPanel();

        pnlDisplay.setSize(600, 600);
        pnlCmd.setSize(200, 200);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 600));
        pnlDisplay.add(scrollPane);


        pnlCmd.add(sortByName);
        pnlCmd.add(sortByTemp);
        pnlCmd.add(sortByHum);


        frame.setPreferredSize(new Dimension(600, 600));
        frame.add(pnlDisplay, BorderLayout.CENTER);
        frame.add(pnlCmd, BorderLayout.SOUTH);

        pack();
        frame.setVisible(true);
    }


    private class ExportButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class CloseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public void displayWeatherData() throws IOException {
        StringBuilder strToSave = new StringBuilder();
        Path path = null;
        if(type.equals("Historic"))
            path = Paths.get("./persistence/weekhistory.txt");
        else if (type.equals("Current"))
            path = Paths.get("./persistence/currweatherlist.txt");
        else
            path = Paths.get("./persistence/futureforecast.txt");
        for (var city : City.sevenDayCityData) {
            strToSave.append("Day ").append(city.getCityID()).append("\t")
                    .append(city.getCityName()).append("\t")
                    .append(city.getTemp()).append("\t")
                    .append(city.getHumidity()).append("\t")
                    .append(city.getIcon()).append("\t")
                    .append(city.getDescription())
                    .append(System.lineSeparator());
            Files.writeString(path, strToSave.toString());
            System.out.println(city.getCityID() + "\t\t" + city.getCityName() + "\t\t\t\t\t\t" + city.getTemp() + "\t" + city.getHumidity() + "\t" + city.getIcon() + "\t" + city.getDescription());
        }
        strToSave.setLength(0);
    }

//this function is never used btw
    public void displaySvnDayWeatherData() {

        for (var city : City.futureSevenDayCityData) {
            System.out.println(city.getCityID() + "\t\t" + city.getCityName() + "\t\t\t\t\t\t" + city.getTemp() + "\t" + city.getHumidity() + "\t" + city.getIcon() + "\t" + city.getDescription());
        }

    }



    //method to parse string of image to image for weather icon display
    public ImageIcon getIconImage(String url1) {
        URL url = null;
        BufferedImage img = null;
        try {
            url = new URL(url1);
            img = ImageIO.read(url);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        ImageIcon weatherIcon;
        weatherIcon = new ImageIcon(img);

        return weatherIcon;
    }

    private class sortByTempButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tableModel.setRowCount(0);
            Collections.sort(City.sevenDayCityData, new SortbyTemperature());
            showTable(City.sevenDayCityData);
        }
    }

    private class sortByHumButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tableModel.setRowCount(0);
            Collections.sort(City.sevenDayCityData, new SortByHumidity());

        }

    }

    private void showTable(ArrayList<City> cityList)
    {
        for(City city : cityList) {
            addToTable(city);
        }

    }


    private void addToTable(City city)
    {
        //ImageIcon img = getIconImage(City.sevenDayCityData.get(0).getIcon());
        Object[] item = {getIconImage(city.getIcon()),city.getDescription(), city.getTemp(), city.getHumidity()};

//        Object[][] data = {
//                {getIconImage(City.sevenDayCityData.get(0).getIcon()),getIconImage(City.sevenDayCityData.get(1).getIcon()),getIconImage(City.sevenDayCityData.get(2).getIcon()),getIconImage(City.sevenDayCityData.get(3).getIcon()),getIconImage(City.sevenDayCityData.get(4).getIcon()),getIconImage(City.sevenDayCityData.get(5).getIcon()),getIconImage(City.sevenDayCityData.get(6).getIcon())},
//                {City.sevenDayCityData.get(0).getDescription(),City.sevenDayCityData.get(1).getDescription(), City.sevenDayCityData.get(2).getDescription(), City.sevenDayCityData.get(3).getDescription(), City.sevenDayCityData.get(4).getDescription(), City.sevenDayCityData.get(5).getDescription(), City.sevenDayCityData.get(6).getDescription()},
//                {City.sevenDayCityData.get(0).getTemp(),City.sevenDayCityData.get(1).getTemp(),City.sevenDayCityData.get(2).getTemp(),City.sevenDayCityData.get(3).getTemp(),City.sevenDayCityData.get(4).getTemp(),City.sevenDayCityData.get(5).getTemp(),City.sevenDayCityData.get(6).getTemp()},
//                {City.sevenDayCityData.get(0).getHumidity(),City.sevenDayCityData.get(1).getHumidity(),City.sevenDayCityData.get(2).getHumidity(),City.sevenDayCityData.get(3).getHumidity(),City.sevenDayCityData.get(4).getHumidity(),City.sevenDayCityData.get(5).getHumidity(),City.sevenDayCityData.get(6).getHumidity()}
//        };

        tableModel.addColumn(item);
    }






}