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


public class GUIWeekForecast extends JFrame {

    private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

    private JButton cmdClose;
    private JButton cmdExport;

    private JPanel pnlDisplay;
    private JPanel pnlCmd;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    private JTable table;
    private String type;

    private String startTime=null;
    private String endTime=null;

    private String[] columnNames = new String[7];

    JFrame frame;


    /**
     * constructor for creating forecast frame to display city forecasted weather data
     * @param city
     * @param type
     * @throws IOException
     */
    public GUIWeekForecast(String city, String type) throws IOException {

        setSize(600, 600);

        this.type = type;

        setTitleWindow(type);

        RainLibrary.getSevenDayWeatherData(RainLibrary.createCityData(city), startTime, endTime, type);
        displayWeatherData();

        setColumnNames();
        // Set preferred width for each column


        Object[][] data = {
                {getIconImage(City.futureSevenDayCityData.get(0).getIcon()), getIconImage(City.futureSevenDayCityData.get(1).getIcon()), getIconImage(City.futureSevenDayCityData.get(2).getIcon()), getIconImage(City.futureSevenDayCityData.get(3).getIcon()), getIconImage(City.futureSevenDayCityData.get(4).getIcon()), getIconImage(City.futureSevenDayCityData.get(5).getIcon()), getIconImage(City.futureSevenDayCityData.get(6).getIcon())},
                {City.futureSevenDayCityData.get(0).getDescription(), City.futureSevenDayCityData.get(1).getDescription(), City.futureSevenDayCityData.get(2).getDescription(), City.futureSevenDayCityData.get(3).getDescription(), City.futureSevenDayCityData.get(4).getDescription(), City.futureSevenDayCityData.get(5).getDescription(), City.futureSevenDayCityData.get(6).getDescription()},
                {City.futureSevenDayCityData.get(0).getTemp(), City.futureSevenDayCityData.get(1).getTemp(), City.futureSevenDayCityData.get(2).getTemp(), City.futureSevenDayCityData.get(3).getTemp(), City.futureSevenDayCityData.get(4).getTemp(), City.futureSevenDayCityData.get(5).getTemp(), City.futureSevenDayCityData.get(6).getTemp()},
                {City.futureSevenDayCityData.get(0).getHumidity(), City.futureSevenDayCityData.get(1).getHumidity(), City.futureSevenDayCityData.get(2).getHumidity(), City.futureSevenDayCityData.get(3).getHumidity(), City.futureSevenDayCityData.get(4).getHumidity(), City.futureSevenDayCityData.get(5).getHumidity(), City.futureSevenDayCityData.get(6).getHumidity()}
        };

        // Modify the data array to include row labels
//        Object[][] data = {
//                {"Weather", getIconImage(City.futureSevenDayCityData.get(0).getIcon()), getIconImage(City.futureSevenDayCityData.get(1).getIcon()), getIconImage(City.futureSevenDayCityData.get(2).getIcon()), getIconImage(City.futureSevenDayCityData.get(3).getIcon()), getIconImage(City.futureSevenDayCityData.get(4).getIcon()), getIconImage(City.futureSevenDayCityData.get(5).getIcon()), getIconImage(City.futureSevenDayCityData.get(6).getIcon())},
//                {"Description", City.futureSevenDayCityData.get(0).getDescription(), City.futureSevenDayCityData.get(1).getDescription(), City.futureSevenDayCityData.get(2).getDescription(), City.futureSevenDayCityData.get(3).getDescription(), City.futureSevenDayCityData.get(4).getDescription(), City.futureSevenDayCityData.get(5).getDescription(), City.futureSevenDayCityData.get(6).getDescription()},
//                {"Temperature", City.futureSevenDayCityData.get(0).getTemp(), City.futureSevenDayCityData.get(1).getTemp(), City.futureSevenDayCityData.get(2).getTemp(), City.futureSevenDayCityData.get(3).getTemp(), City.futureSevenDayCityData.get(4).getTemp(), City.futureSevenDayCityData.get(5).getTemp(), City.futureSevenDayCityData.get(6).getTemp()},
//                {"Humidity", City.futureSevenDayCityData.get(0).getHumidity(), City.futureSevenDayCityData.get(1).getHumidity(), City.futureSevenDayCityData.get(2).getHumidity(), City.futureSevenDayCityData.get(3).getHumidity(), City.futureSevenDayCityData.get(4).getHumidity(), City.futureSevenDayCityData.get(5).getHumidity(), City.futureSevenDayCityData.get(6).getHumidity()}
//        };
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
                return new WordWrapTableCellRenderer();
            }
        };



// Create a custom TableCellRenderer that wraps the text using HTML tags


        table = new JTable(tableModel) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                return new ImageTableCellRenderer();
            }
        };

//        // Iterate through all columns in the table
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            // Get the TableColumn for the current column index
//            TableColumn column = table.getColumnModel().getColumn(i);
//            int preferredWidth = 0;
//
//            // Iterate through all rows to find the maximum width of the text in the cells of the current column
//            for (int j = 0; j < table.getRowCount(); j++) {
//                TableCellRenderer renderer = table.getCellRenderer(j, i);
//                Component comp = table.prepareRenderer(renderer, j, i);
//                preferredWidth = Math.max(comp.getPreferredSize().width, preferredWidth);
//            }
//
//            // Set the preferred width of the column to the maximum width of the text in the cells, plus some padding
//            column.setPreferredWidth(preferredWidth + 10); // You can adjust the padding value (10) as needed
//        }


        // Set custom TableCellRenderer for the first column to render images
        table.getColumnModel().getColumn(0).setCellRenderer(new ImageTableCellRenderer());


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
//        label.setWrapStyleWord(true);
//        label.setLineWrap(true);

//        JLabel labelHead = new JLabel(City.futureSevenDayCityData.get(0).getCityName());
//        labelHead.setFont(new Font("Arial", Font.PLAIN,20));
//        getContentPane().add(labelHead,BorderLayout.PAGE_START);

        JButton cmdClose = new JButton("Close");
        JButton cmdExport = new JButton("Save Data");
        JButton cmdLoad = new JButton("Load Data");


//        pnlDisplay = new JPanel();
        pnlCmd = new JPanel();




//        pnlDisplay.setSize(600, 600);
        pnlCmd.setSize(200, 200);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 400));

        // Create the pnlDisplay panel to hold the JScrollPane
        pnlDisplay = new JPanel(new BorderLayout());
        pnlDisplay.add(scrollPane, BorderLayout.CENTER);


        pnlCmd.add(cmdClose);
        pnlCmd.add(cmdExport);
        pnlCmd.add(cmdLoad);

        cmdClose.addActionListener(new CloseButtonListener());
        cmdExport.addActionListener(new ExportButtonListener());
        cmdLoad.addActionListener(new LoadButtonListener());


//        frame = new JFrame();
//        frame.setPreferredSize(new Dimension(600, 600));
//        frame.add(pnlDisplay, BorderLayout.CENTER);
//        frame.add(pnlCmd, BorderLayout.SOUTH);
//        frame.pack();
//        setSize(600, 600);
//        frame.setVisible(true);

        // Create the frame to hold the pnlDisplay panel
        frame = new JFrame();
        frame.add(pnlDisplay);
        frame.add(pnlCmd, BorderLayout.SOUTH);
        frame.setSize(1000, 600); // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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


    /**
     * exports data to a local file
     */
    private class ExportButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                displayWeatherData();
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "File saved to local directory");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
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
     * loads data from a file
     */
    private class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /**
     * saves current frame's weather data to a file
     * @throws IOException
     */
    public void displayWeatherData() throws IOException {
        StringBuilder strToSave = new StringBuilder();
        Path path = null;
        if(type.equals("Historic"))
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
            System.out.println(city.getCityID() + "\t\t" + city.getCityName() + "\t\t\t\t\t\t" + city.getTemp() + "\t" + city.getHumidity() + "\t" + city.getIcon() + "\t" + city.getDescription());
        }
        strToSave.setLength(0);
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



    private class sortByTempButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tableModel.setRowCount(0);
            Collections.sort(City.futureSevenDayCityData, new SortbyTemperature());
            showTable(City.futureSevenDayCityData);
        }
    }

    private class sortByHumButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tableModel.setRowCount(0);
            Collections.sort(City.futureSevenDayCityData, new SortByHumidity());

        }

    }

    private void showTable(ArrayList<City> cityList)
    {
        tableModel.setRowCount(0);
        for(City city : cityList) {
            addToTable(city);
        }

    }


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
        setTitle("Rain - 7 day Forecast");
    }


    /**
     * gets column names parsed from current date.
     * @param s
     * @throws ParseException
     */
    public void setColumnNames() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-EEE, MM, yyyy");
            Date today = new Date();
            Date prevDate;
            String stringDate = sdf.format(today);
            columnNames[0] = stringDate;
            for(int i=1; i<7; i++) {
                prevDate = findNextDay(today);
                stringDate = sdf.format(prevDate);
                columnNames[i] = stringDate;
                today = prevDate;
            }
    }

    /**
     * finds the next date from a given date
     * @param date
     * @return
     */
    private static Date findNextDay(Date date) {
        return new Date(date.getTime() + MILLIS_IN_A_DAY);
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