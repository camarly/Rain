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


public class GUIWeekHistory extends JFrame {

    private JButton cmdClose;
    private JButton cmdExport;
    private JButton cmdSortByName;
    private JButton cmdSortByTemperature;
    private JButton cmdSortByHumidity;

    private JPanel pnlDisplay;
    private JPanel pnlCmd;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    private JTable table;
    private String type= null;

    JFrame frame = new JFrame();


    public GUIWeekHistory(String city, String startTime, String endTime, String type) throws IOException {

        this.type = type;
        setTitle("Rain - Prior Week History");

        //Date date = ne
        String[] columnNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] rowNames = {"Date", "City", "Temperature", "Humidity", "Weather Condition", "Description"};

        RainLibrary.getSevenWeatherData(RainLibrary.createCityData(city), startTime, endTime,type);
        displayWeatherData();


        Object[][] data = {
                    {getIconImage(City.sevenDayCityData.get(0).getIcon()),getIconImage(City.sevenDayCityData.get(1).getIcon()),getIconImage(City.sevenDayCityData.get(2).getIcon()),getIconImage(City.sevenDayCityData.get(3).getIcon()),getIconImage(City.sevenDayCityData.get(4).getIcon()),getIconImage(City.sevenDayCityData.get(5).getIcon()),getIconImage(City.sevenDayCityData.get(6).getIcon())},
                    {City.sevenDayCityData.get(0).getDescription(),City.sevenDayCityData.get(1).getDescription(), City.sevenDayCityData.get(2).getDescription(), City.sevenDayCityData.get(3).getDescription(), City.sevenDayCityData.get(4).getDescription(), City.sevenDayCityData.get(5).getDescription(), City.sevenDayCityData.get(6).getDescription()},
                    {City.sevenDayCityData.get(0).getTemp(),City.sevenDayCityData.get(1).getTemp(),City.sevenDayCityData.get(2).getTemp(),City.sevenDayCityData.get(3).getTemp(),City.sevenDayCityData.get(4).getTemp(),City.sevenDayCityData.get(5).getTemp(),City.sevenDayCityData.get(6).getTemp()},
                    {City.sevenDayCityData.get(0).getHumidity(),City.sevenDayCityData.get(1).getHumidity(),City.sevenDayCityData.get(2).getHumidity(),City.sevenDayCityData.get(3).getHumidity(),City.sevenDayCityData.get(4).getHumidity(),City.sevenDayCityData.get(5).getHumidity(),City.sevenDayCityData.get(6).getHumidity()}
            };
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames)
        {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class

            @Override
            public Class<?> getColumnClass(int row) {
                for(int i=0; i<getColumnCount(); i++) {
                    if(getRowCount() == 0) {
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



        pnlCmd.add(sortByName);
        pnlCmd.add(sortByTemp);
        pnlCmd.add(sortByHum);



        JScrollPane scrollPane = new JScrollPane(table);
        pnlDisplay.add(scrollPane);

        frame.add(pnlDisplay);
        frame.add(pnlCmd);
        pack();
        frame.setVisible(true);
    }


    private class ExportButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class CloseButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public void displayWeatherData() {
        for (var city : City.sevenDayCityData) {
            System.out.println(city.getCityID() + "\t\t" + city.getCityName() + "\t\t\t\t\t\t" + city.getTemp() + "\t" + city.getHumidity() + "\t" +  city.getIcon() + "\t" + city.getDescription());
        }
    }

    public void displaySvnDayWeatherData() {
        for (var city : City.futureSevenDayCityData) {
            System.out.println(city.getCityID() + "\t\t" + city.getCityName() + "\t\t\t\t\t\t" + city.getTemp() + "\t" + city.getHumidity() + "\t" +  city.getIcon() + "\t" + city.getDescription());
        }
    }
//
//    private void updateRowHeights()
//    {
//        try
//        {
//            for (int row = 0; row < _table.getRowCount(); row++)
//            {
//                int rowHeight = _table.getRowHeight();
//
//                for (int column = 0; column < _table.getColumnCount(); column++)
//                {
//                    Component comp = _table.prepareRenderer(_table.getCellRenderer(row, column), row, column);
//                    rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
//                }
//                _table.setRowHeight(row, rowHeight);
//            }
//        }
//        catch(ClassCastException e) {
//        }
//    }


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

}