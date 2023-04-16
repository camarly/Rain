package Project3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class GUICurrentWeatherList extends JFrame {

    private JButton cmdAddCity;
    private JButton cmdRemoveCity;
    private JButton cmdEditCity;
    private JButton cmdRefresh;
    private JButton cmdSave;
    private JPanel pnlCrudCmd;
    private JButton cmdSortByName;
    private JButton cmdSortByTemp;
    private JButton cmdSortByHumidity;

    private JPanel pnlSearchCmd;

    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    private GUICurrentWeatherList thisFrame;


    public GUICurrentWeatherList() {
        super("Rain - Current Weather Listing");
        setIconImage(new ImageIcon("frameIcon.png").getImage());

        thisFrame = this;
        City.cityList = new ArrayList<>();

//        City.cityList = loadCities("./persistence/currweatherlist.txt");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        String[] columnNames = {"City", "Temp", "Humidity", "Weather Condition", "Description"};

//        model = new DefaultTableModel(columnNames, 0);
        model = new DefaultTableModel(columnNames, 0) {
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


        table = new JTable(model);
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        //getContentPane().add(scrollPane, BorderLayout.NORTH);

        pnlCrudCmd = new JPanel();
        pnlSearchCmd = new JPanel();

        cmdAddCity = new JButton("Add City");
        cmdEditCity = new JButton("Edit City");
        cmdRemoveCity = new JButton("Remove City");
        cmdRefresh = new JButton("Refresh");
        cmdSortByName = new JButton("Sort by Name");
        cmdSortByTemp = new JButton("Sort by Temperature");
        cmdSortByHumidity = new JButton("Sort by Humidity");
        cmdSave = new JButton("Save");

        cmdAddCity.addActionListener(new AddCityButtonListener());
        cmdEditCity.addActionListener(new EditCityButtonListener());
        cmdRemoveCity.addActionListener(new RemoveCityButtonListener());
        cmdRefresh.addActionListener(new RefreshButtonListener());
        cmdSortByName.addActionListener(new SortByNameButtonListener());
        cmdSortByTemp.addActionListener(new SortByTempButtonListener());
        cmdSortByHumidity.addActionListener(new SortByHumidityButtonListener());
        cmdSave.addActionListener(new CloseButtonListener());

        pnlCrudCmd.add(cmdAddCity);
        pnlCrudCmd.add(cmdEditCity);
        pnlCrudCmd.add(cmdRemoveCity);
        pnlCrudCmd.add(cmdRefresh);
        pnlCrudCmd.add(cmdSortByName);
        pnlCrudCmd.add(cmdSortByTemp);
        pnlCrudCmd.add(cmdSortByHumidity);
        pnlCrudCmd.add(cmdSave);

        add(scrollPane, BorderLayout.CENTER);
        add(pnlCrudCmd, BorderLayout.SOUTH);

        pack();
        setVisible(true);

        showDetails();
    }

    void showTable(ArrayList<City> cityList) {
        model.setRowCount(0);
        for (City city : cityList) {
            addToTable(city);
        }
    }

    public ArrayList<City> getCityList() {
        return City.cityList;
    }

    private void addToTable(City city) {
        String name = city.getCityName();
        String[] item = {name, "" + city.getTemp(), "" + city.getHumidity(), "" + city.getIcon(), "" + city.getDescription()};
        model.addRow(item);
        StringBuilder strToSave = new StringBuilder();
        for (var citi : City.cityList) {
            strToSave.append("Day ").append(citi.getCityID()).append("\t")
                    .append(citi.getCityName()).append("\t")
                    .append(citi.getTemp()).append("\t")
                    .append(citi.getHumidity()).append("\t")
                    .append(citi.getIcon()).append("\t")
                    .append(citi.getDescription())
                    .append(System.lineSeparator());
            try{
                Files.writeString(Paths.get("./persistence/currweatherlist.txt"), strToSave.toString());
                System.out.println("saved to file: " + city.getCityName());
            }
            catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(Arrays.toString(item));
        }

    }

    public void addCity(City city) {
        City.cityList.add(city);
        addToTable(city);
    }


    private class AddCityButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new GUICityEntry(thisFrame);

        }
    }

    private class EditCityButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if(selectedRow != -1) {
                String editCity = (String) model.getValueAt(selectedRow, 0);
                new GUICityEdit(thisFrame, editCity);
            }
        }
    }

    private class RemoveCityButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(table.getSelectedRow());
                String cityName = (String) model.getValueAt(selectedRow, 0);

                for (City city : City.cityList) {
                    if (city.getCityName().equals(cityName)) {
                        City.cityList.remove(city);
                    }
                }
            }
        }
    }

    private class RefreshButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (City city : City.cityList) {
                city.setCurrentWeather();
            }
            model.setRowCount(0);
            showTable(City.cityList);

        }
    }

    private class CloseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            thisFrame.setVisible(false);

        }
    }

    public void showDetails() {
        model.setRowCount(0);
        for (City city : City.cityList) {
            addToTable(city);
        }
    }


    private class SortByNameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.setRowCount(0);
            Collections.sort(City.cityList);
            thisFrame.showTable(City.cityList);
        }
    }

    private class SortByTempButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.setRowCount(0);
            Collections.sort(City.cityList, new SortbyTemperature());
            thisFrame.showTable(City.cityList);


        }
    }

    private class SortByHumidityButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            model.setRowCount(0);
            Collections.sort(City.cityList,new SortByHumidity());
            thisFrame.showTable(City.cityList);
        }
    }


    private ArrayList<City> loadCities(String pfile){
        Scanner scan = null;
        ArrayList<City> clist = new ArrayList<>();

        try {
            scan = new Scanner(new File(pfile));
            while (scan.hasNext()){
                String cityName = scan.nextLine();

                City city = new City(cityName);
                city.setCurrentWeather();
                clist.add(city);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } return clist;

    }

}