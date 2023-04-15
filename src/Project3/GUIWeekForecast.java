package Project3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GUIWeekForecast extends JFrame{
    private JButton cmdClose;
    private JButton cmdExport;

    private JPanel pnlDisplay;
    private JPanel pnlCmd;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private ArrayList <City> forecastList;



    public GUIWeekForecast(ArrayList <City> forecastList) {

        this.forecastList = forecastList;
        setTitle("Rain - Week Forecast");
        setIconImage(new ImageIcon("frameIcon.png").getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);

        String[] columnNames = {"Date", "Temp", "Humidity", "Weather Condition", "Description"};
        model = new DefaultTableModel(columnNames, 0);

        table = new JTable(model);
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 400));

        cmdExport = new JButton("Export");
        cmdClose = new JButton("Close");
        cmdExport.addActionListener(new ExportButtonListener());
        cmdClose.addActionListener(new CloseButtonListener());

        pnlCmd = new JPanel();
        pnlCmd.add(cmdExport);
        pnlCmd.add(cmdClose);

        add(scrollPane, BorderLayout.CENTER);
        add(pnlCmd, BorderLayout.SOUTH);

        pack();
        showTable(forecastList);
        //setVisible(true);

    }

    public void showTable(ArrayList <City> forecastList){
        model.setRowCount(0);
        for (City forecast: forecastList){
            addToTable(forecast);
        }
    }

    private void addToTable(City forecast)
    {
        String date = GUIWeekEntry.unixToDate(forecast.getDatetime());
        String[] item={date,""+ forecast.getTemp(),""+forecast.getHumidity(),""+ forecast.getType() ,""+ forecast.getDescription() };
        model.addRow(item);

    }

    public void addCity(City city)
    {
        forecastList.add(city);
        addToTable(city);

    }

    public ArrayList<City> getCityList() {

        return forecastList;
    }


    private class ExportButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cityName = forecastList.get(0).getCityName();
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Choose save directory");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = chooser.showSaveDialog(new JFrame());

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File dir = chooser.getSelectedFile();
                File file = new File(dir, "weather_forecast_"+cityName+".csv");
                try {
                    FileWriter writer = new FileWriter(file);

                    writer.write("Date,Temperature,Humidity,General Weather Condition,Weather Description\n"); //header for csv file

                    for (City forecast : forecastList) {
                        String dt = GUIWeekEntry.unixToDate((long)forecast.getDatetime());
                        double temperature = forecast.getTemp();
                        int humidity = forecast.getHumidity();
                        String generalCondition = forecast.getType();
                        String weatherDescription = forecast.getDescription();
                        writer.write(dt + "," + temperature + "," + humidity + "," + generalCondition + "," + weatherDescription + "\n");
                    }

                    writer.close();

                    JOptionPane.showMessageDialog(new JFrame(), "CSV file saved successfully.");

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "Error saving CSV file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }


    private class CloseButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();

        }
    }



}
