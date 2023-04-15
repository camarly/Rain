package Project3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUICurrentWeatherList extends JFrame {

    private JButton cmdAddCity;
    private JButton cmdRemoveCity;
    private JButton cmdEditCity;
    private JButton cmdRefresh;
    private JButton cmdClose;
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
        cityList = new ArrayList<>();

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
        cmdClose = new JButton("Close");

        cmdAddCity.addActionListener(new AddCityButtonListener());
        cmdEditCity.addActionListener(new EditCityButtonListener());
        cmdRemoveCity.addActionListener(new RemoveCityButtonListener());
        cmdRefresh.addActionListener(new RefreshButtonListener());
        cmdClose.addActionListener(new CloseButtonListener());

        pnlCrudCmd.add(cmdAddCity);
        pnlCrudCmd.add(cmdEditCity);
        pnlCrudCmd.add(cmdRemoveCity);
        pnlCrudCmd.add(cmdRefresh);
        pnlCrudCmd.add(cmdClose);

        add(scrollPane, BorderLayout.CENTER);
        add(pnlCrudCmd, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public void showTable(ArrayList <City> cityList){
        model.setRowCount(0);
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

    private class CloseButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            thisFrame.setVisible(false);

        }
    }


}