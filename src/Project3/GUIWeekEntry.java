package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class GUIWeekEntry extends JFrame {

    private JTextField cityField;
    private JComboBox<String> dbMonth;
    private JComboBox<Integer> dbDay;
    private JComboBox<Integer> dbYear;

    private JButton cmdSubmit;
    private JButton cmdCancel;
    private JPanel pnlDisplay;
    private JPanel pnlCmd;
    private JPanel pnlDate;

    private String type;


    /**
     * constructor for generating entry window for city to get forecast weather data
     * @param type
     */
    public GUIWeekEntry(String type) {
        this.type = type;

        setTitle("Rain - Select city and date");

        pnlDisplay = new JPanel();
        pnlCmd = new JPanel();
        pnlDate = new JPanel();

        pnlDisplay.add(new JLabel("City Name"));
        cityField = new JTextField(20);
        pnlDisplay.add(cityField);

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

        pack();
        setVisible(true);
    }



    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String city = cityField.getText();
            String startTime = "1680256800";
            String endTime = "1680948000";
            try {
                if(type.equals("Historic")) {
                    new GUIWeekHistory(city, getReportType());
                    dispose();
                }
                else {
                    new GUIWeekForecast(city, getReportType());
                    dispose();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }



    /**
     * Convert Unix time to readable time
     * @param unix
     * @return date in string format
     */
    public static String unixToDate (long unix){
        Date date = new Date (unix * 1000);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        dateFormat.setTimeZone((TimeZone.getTimeZone("UTC-5")));
        String readableDT = dateFormat.format(date);
        return readableDT;
    }

    /**
     * Convert readable time to Unix
     * @param readableDT
     * @return String with data i unixdatetime format
     */
    public static String dateToUnix (String readableDT){

        try{
//            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-EEE, MM, yyyy");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC-5"));
            Date date = dateFormat.parse(readableDT);
            String unixDT = String.valueOf(date.toInstant().getEpochSecond());
            return unixDT;
        }catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * returns the type of data requested "Current", "Historic" or "Forecast".
     * @return
     */
    public String getReportType() {
        return this.type;
    }
}