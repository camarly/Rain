package Project3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GUICurrentWeatherList extends JFrame {

    private JButton cmdAddCity;
    private JButton cmdRemoveCity;
    private JButton cmdEditCity;
    private JButton cmdRefresh;
    private JButton cmdClose;
    private JButton cmdSortName;
    private JButton cmdSortTemp;
    private JButton cmdSortHumidity;

    private JPanel pnlCrudCmd;
    private JPanel pnlSearchCmd;

    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    private GUICurrentWeatherList thisFrame;

    private ArrayList<City> cityList;

    public GUICurrentWeatherList() {
        super("Rain - Current Weather Listing");
        setIconImage(new ImageIcon("frameIcon.png").getImage());

        thisFrame = this;
        cityList = loadCities("cities.dat");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        String[] columnNames = {"City", "Temp", "Humidity", "Weather Condition", "Description"};

        model = new DefaultTableModel(columnNames, 0);

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
        cmdSortName = new JButton("Sort Name");
        cmdSortTemp = new JButton("Sort Temperature");
        cmdSortHumidity = new JButton("Sort Humidity");
        cmdClose = new JButton("Close");

        cmdAddCity.addActionListener(new AddCityButtonListener());
        cmdEditCity.addActionListener(new EditCityButtonListener());
        cmdRemoveCity.addActionListener(new RemoveCityButtonListener());
        cmdRefresh.addActionListener(new RefreshButtonListener());
        cmdSortName.addActionListener(new SortNameButtonListener());
        cmdSortTemp.addActionListener(new SortTempButtonListener());
        cmdSortHumidity.addActionListener(new SortHumidityButtonListener());
        cmdClose.addActionListener(new CloseButtonListener());

        pnlCrudCmd.add(cmdAddCity);
        pnlCrudCmd.add(cmdEditCity);
        pnlCrudCmd.add(cmdRemoveCity);
        pnlCrudCmd.add(cmdRefresh);
        pnlCrudCmd.add(cmdSortName);
        pnlCrudCmd.add(cmdSortTemp);
        pnlCrudCmd.add(cmdSortHumidity);
        pnlCrudCmd.add(cmdClose);

        add(scrollPane, BorderLayout.CENTER);
        add(pnlCrudCmd, BorderLayout.SOUTH);

        showTable(cityList);

        pack();
        setVisible(true);
    }

    public void showTable(ArrayList <City> cityList){
        model.setRowCount(0);
        //saveCities(cityList);
        for (City city: cityList){
            addToTable(city);
        }
    }

    private void addToTable(City city)
    {
        String name= city.getCityName();
        String[] item={name,""+ city.getTemp(),""+city.getHumidity(),""+ city.getType() ,""+ city.getDescription() };
        model.addRow(item);

    }

    public void addCity(City city)
    {
        cityList.add(city);
        addToTable(city);

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

    public void saveCities(ArrayList <City> clist){
        try {
            FileWriter writer = new FileWriter("cities.dat");
            for (City city: clist){
                writer.write(city.getCityName()+"\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public ArrayList<City> getCityList() {
        return cityList;
    }

    private class AddCityButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new GUICityEntry(thisFrame);

        }
    }

    private class EditCityButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if(selectedRow != -1) {
                String editCity = (String) model.getValueAt(selectedRow, 0);
                new GUICityEdit(thisFrame, editCity);
            }
        }
    }

    private class RemoveCityButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if(selectedRow != -1){
                String cityName = (String) model.getValueAt(selectedRow, 0);
                model.removeRow(table.getSelectedRow());


                for (City city: cityList){
                    if (city.getCityName().equals(cityName)) {
                        cityList.remove(city);
                        break;
                    }
                    saveCities(cityList);
                }
            }
        }
    }

    private class RefreshButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            for (City city: cityList){
                city.setCurrentWeather();
            }
            showTable(cityList);

        }
    }

    private class SortNameButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Collections.sort(cityList);
            thisFrame.showTable(cityList);
        }
    }

    private class SortTempButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Collections.sort(cityList,new SortbyTemperature());
            thisFrame.showTable(cityList);
        }
    }

    private class SortHumidityButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Collections.sort(cityList,new SortByHumidity());
            thisFrame.showTable(cityList);
        }
    }

    private class CloseButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            thisFrame.setVisible(false);

        }
    }


}