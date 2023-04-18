package Project3;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javax.swing.JLabel;



public class GUIWeekHistory extends JFrame {

    private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

    private JButton cmdClose;
    private JButton cmdExport;

    private JPanel pnlDisplay;
    private JPanel pnlCmd;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    private JTable table;
    private String type;

    private String[] columnNames = new String[7];

    JFrame frame;

    String startTime;
    String endTime;


    /**
     * creates GUI window for viewing seven-day weather history
     * @param city
     * @param type
     * @throws IOException
     * @throws ParseException
     */
    public GUIWeekHistory(String city, String type) throws IOException, ParseException {

        this.type = type;

        setTitleWindow(type);

        setColumnNames(type);


        startTime = GUIWeekEntry.dateToUnix(columnNames[0]);
        endTime = GUIWeekEntry.dateToUnix(columnNames[6]);


        for(int i=0; i<7; i++) {
            RainLibrary.getSevenDayWeatherData(RainLibrary.createCityData(city), GUIWeekEntry.dateToUnix(columnNames[i]), GUIWeekEntry.dateToUnix(columnNames[i]), type);
        }


        Object[][] data = {
                {getIconImage(City.historicSevenDayCityData.get(0).getIcon()), getIconImage(City.historicSevenDayCityData.get(1).getIcon()), getIconImage(City.historicSevenDayCityData.get(2).getIcon()), getIconImage(City.historicSevenDayCityData.get(3).getIcon()), getIconImage(City.historicSevenDayCityData.get(4).getIcon()), getIconImage(City.historicSevenDayCityData.get(5).getIcon()), getIconImage(City.historicSevenDayCityData.get(6).getIcon())},
                {City.historicSevenDayCityData.get(0).getDescription(), City.historicSevenDayCityData.get(1).getDescription(), City.historicSevenDayCityData.get(2).getDescription(), City.historicSevenDayCityData.get(3).getDescription(), City.historicSevenDayCityData.get(4).getDescription(), City.historicSevenDayCityData.get(5).getDescription(), City.historicSevenDayCityData.get(6).getDescription()},
                {City.historicSevenDayCityData.get(0).getTemp(), City.historicSevenDayCityData.get(1).getTemp(), City.historicSevenDayCityData.get(2).getTemp(), City.historicSevenDayCityData.get(3).getTemp(), City.historicSevenDayCityData.get(4).getTemp(), City.historicSevenDayCityData.get(5).getTemp(), City.historicSevenDayCityData.get(6).getTemp()},
                {City.historicSevenDayCityData.get(0).getHumidity(), City.historicSevenDayCityData.get(1).getHumidity(), City.historicSevenDayCityData.get(2).getHumidity(), City.historicSevenDayCityData.get(3).getHumidity(), City.historicSevenDayCityData.get(4).getHumidity(), City.historicSevenDayCityData.get(5).getHumidity(), City.historicSevenDayCityData.get(6).getHumidity()}
        };
        tableModel = new DefaultTableModel(data, columnNames) {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            @Override
            public Class<?> getColumnClass(int row) {
                for (int i = 0; i < getRowCount(); i++) {
                    if (row == 0) {
                        return ImageIcon.class;
                    }
                }
                return Object.class;
            }

        };


        table = new JTable(tableModel) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                return new GUIWeekHistory.ImageTableCellRenderer();
            }
        };
        table.getColumnModel().getColumn(0).setCellRenderer(new GUIWeekHistory.ImageTableCellRenderer());


        table.setRowHeight(100);

        TableColumn column;
        for (int i = 0; i < columnNames.length; i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(150); // Set preferred width as desired
        }

        // Enable word wrap and set wrap style word for column names
        TableCellRenderer renderer = table.getTableHeader().getDefaultRenderer();
        JLabel label = (JLabel) renderer;
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);


        JButton cmdClose = new JButton("Close");
        JButton cmdExport = new JButton("Save Data");
        JButton cmdLoad = new JButton("Load Data");

        pnlCmd = new JPanel();

        pnlCmd.setSize(200, 200);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 400));

        // Create the pnlDisplay panel to hold the JScrollPane
        pnlDisplay = new JPanel(new BorderLayout());
        pnlDisplay.add(scrollPane, BorderLayout.CENTER);


        pnlCmd.add(cmdClose);
        pnlCmd.add(cmdExport);
        pnlCmd.add(cmdLoad);

        cmdClose.addActionListener(new GUIWeekHistory.CloseButtonListener());
        cmdExport.addActionListener(new GUIWeekHistory.ExportButtonListener());
        cmdLoad.addActionListener(new GUIWeekHistory.LoadButtonListener());

        // Create the frame to hold the pnlDisplay panel
        frame = new JFrame();
        frame.add(pnlDisplay);
        frame.add(pnlCmd, BorderLayout.SOUTH);
        frame.setSize(1000, 600); // Set the size of the frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Modified line
        frame.setVisible(true);
    }


    // Custom TableCellRenderer for rendering images in JTable
    private class ImageTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // Check if the value is an instance of ImageIcon
            if (value instanceof ImageIcon) {
                // Create a JLabel to render the ImageIcon
                JLabel label = new JLabel((ImageIcon) value);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.TOP);
                return label;
            } else {
                // For other values, use the default renderer
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        }
    }



    private class ExportButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                saveData();
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "File saved to local directory");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }

        /**
         * saves weather data to a local file
         * @throws IOException
         */
        private void saveData() throws IOException {
            StringBuilder strToSave = new StringBuilder();
            Path path = null;
            if (type.equals("Historic"))
                path = Paths.get("./persistence/weekhistory.txt");
            else if (type.equals("Current"))
                path = Paths.get("./persistence/currweatherlist.txt");
            else
                path = Paths.get("./persistence/futureforecast.txt");
            for (var city : City.futureSevenDayCityData) {
                strToSave.append("Day ").append(city.getCityID()).append("\t")
                        .append(city.getCityName()).append("\t")
                        .append(city.getTemp()).append("\t")
                        .append(city.getHumidity()).append("\t")
                        .append(city.getIcon()).append("\t")
                        .append(city.getDescription())
                        .append(System.lineSeparator());
                Files.writeString(path, strToSave.toString());
            }
        }

    }


    private class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadWeatherData();
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "File loaded from local directory");
        }


        /**
         * loads weather data to a local file
         * @throws IOException
         */
        private void loadWeatherData() {
            Path path = null;
            if (type.equals("Historic"))
                path = Paths.get("./persistence/weekhistory.txt");
            else
                path = Paths.get("./persistence/futureforecast.txt");
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                City.futureSevenDayCityData.clear();
                String cityName = null;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split("\t");
                    City city = new City();
                    city.setCityName(data[1]);
                    cityName = data[1];
                    city.setTemp(Double.parseDouble(data[2]));
                    city.setHumidity(Integer.parseInt(data[3]));
                    city.setIcon(data[4]);
                    city.setDescription(data[5]);
                    City.futureSevenDayCityData.add(city);
                    System.out.println("city data for file is " + city.getCityName() + " " + city.getTemp() + " " + city.getHumidity() + " " + city.getIcon() + " " + city.getDescription());
                }
                new GUIWeekHistory(cityName, "Historic");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class CloseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    }



    /**
     * method to parse string of image to image for weather icon display
     * @param url1
     * @return
     */
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


    /**
     * sorts datatable based on Temperature
     */
    private class sortByTempButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tableModel.setRowCount(0);
            Collections.sort(City.historicSevenDayCityData, new SortbyTemperature());
            showTable(City.historicSevenDayCityData);
        }
    }

    /**
     * sorts datatable based on Humidity
     */
    private class sortByHumButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tableModel.setRowCount(0);
            Collections.sort(City.historicSevenDayCityData, new SortByHumidity());

        }

    }

    /**
     * displays the table in current frame
     * @param cityList
     */
    private void showTable(ArrayList<City> cityList)
    {
        tableModel.setRowCount(0);
        for(City city : cityList) {
            addToTable(city);
        }

    }


    /**
     * adds city data to table model
     * @param city
     */
    private void addToTable(City city)
    {
        Object[] item = {getIconImage(city.getIcon()),city.getDescription(), city.getTemp(), city.getHumidity()};

        tableModel.addColumn(item);
    }


    /**
     * sets current frame title to sting value : s
     * @param s
     */
    public void setTitleWindow(String s) {
        if(s.equals("Historic")) {
            setTitle("Rain - 7 day " + s);
        }else {
            setTitle("Rain - 7 day Forecast");
        }
    }


    /**
     * gets column names parsed from current date.
     * @param s
     * @throws ParseException
     */
    public void setColumnNames(String s) throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-EEE, MM, yyyy");
            Date today;
            Date prevDate;
            columnNames[0] = sdf.format(new Date());
            for(int i=1; i<7; i++) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-EEE, MM, yyyy");
                String dateInString = columnNames[i-1];
                Date date = formatter.parse(dateInString);
                prevDate = findPrevDay(date);
                columnNames[i] = sdf.format(prevDate);
            }

    }

    /**
     * finds the next day from a given date.
     * @param date
     * @return
     */
    private static Date findNextDay(Date date)
    {
        return new Date(date.getTime() + MILLIS_IN_A_DAY);
    }



    /**
     * finds the previous date from a given date
     * @param date
     * @return
     */
    private static Date findPrevDay(Date date)
    {
        return new Date(date.getTime() - MILLIS_IN_A_DAY);
    }


    /**
     * returns the date time values for historic 7-day period
     * @return
     */
    public Date[] getDateTime() {
        Date[] dates = new Date[7];
        Date today = new Date();
        dates[0] = today;
        for(int i=1; i<7; i++) {
            dates[i] = findPrevDay(dates[i-1]);
        }
        return dates;
    }

    public class WordWrapTableCellRenderer extends JTextArea implements TableCellRenderer {
        public WordWrapTableCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            return this;
        }
    }


}