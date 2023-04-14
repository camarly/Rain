package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class GUIWeekHistoryEntry extends JFrame {

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

    private String type = null;

    public GUIWeekHistoryEntry(String type) {
        this.type = type;

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

//        YEARS = new Integer[40];
//        int currentYear = 2023; //check on method to get the year of the present date.
//        for (int i = 0; i < YEARS.length; i++){
//            YEARS[i] = currentYear;
//            System.out.println(YEARS[i]);
//            currentYear --;
//        }

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

        pack();
        setVisible(true);
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String city = cityField.getText();
            String startTime = "1680256800";
            String endTime = "1680948000";
            GUIWeekHistory wkHistory = new GUIWeekHistory (city, startTime, endTime, getReportType());
            wkHistory.setVisible(true);
        }
    }

    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    //Convert Unix time to readable time
    public String unixToDate (long unix){
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

    public String getReportType() {
        return this.type;
    }
}