package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIMainMenu extends JFrame {
    private JButton cmdCurrent;
    private JButton cmdPastWeek;
    private JButton cmdForecast;
    private JButton cmdTempMap;
    private JButton cmdSuggest;
    private JButton cmdAbout;
    private GUIMainMenu thisFrame;
    private GUICurrentWeatherList currentWeatherList;


    public GUIMainMenu() {

        thisFrame = this;

        setTitle("Rain - Main Menu");
        setIconImage(new ImageIcon("frameIcon.png").getImage());
        setSize(600,600);

        cmdCurrent = new JButton("Current Weather");
        cmdPastWeek = new JButton("7-Day History");
        cmdForecast = new JButton("7-Day Forecast");
        cmdTempMap = new JButton("Temperature Map");
        cmdSuggest = new JButton("Suggestions");
        cmdAbout = new JButton("About Us");

        cmdCurrent.setBorder(BorderFactory.createEtchedBorder());
        cmdPastWeek.setBorder(BorderFactory.createEtchedBorder());
        cmdForecast.setBorder(BorderFactory.createEtchedBorder());
        cmdTempMap.setBorder(BorderFactory.createEtchedBorder());
        cmdSuggest.setBorder(BorderFactory.createEtchedBorder());
        cmdAbout.setBorder(BorderFactory.createEtchedBorder());

        cmdCurrent.setIcon(new ImageIcon("current.png"));
        cmdCurrent.setBackground(new Color(2,200,152));
        cmdPastWeek.setIcon(new ImageIcon("past.png"));
        cmdPastWeek.setBackground(new Color(62,220,247));
        cmdForecast.setIcon(new ImageIcon("future.png"));
        cmdForecast.setBackground(new Color(128,84,247));
        cmdTempMap.setIcon(new ImageIcon("tempmap.png"));
        cmdTempMap.setBackground(new Color(247,142,62));
        cmdSuggest.setIcon(new ImageIcon("suggest.png"));
        cmdSuggest.setBackground(new Color(195,62,247));
        cmdAbout.setIcon(new ImageIcon("about.png"));
        cmdAbout.setBackground(new Color(159,159,159));

        cmdCurrent.setVerticalAlignment(SwingConstants.CENTER);
        cmdPastWeek.setVerticalAlignment(SwingConstants.CENTER);
        cmdForecast.setVerticalAlignment(SwingConstants.CENTER);
        cmdTempMap.setVerticalAlignment(SwingConstants.CENTER);
        cmdSuggest.setVerticalAlignment(SwingConstants.CENTER);
        cmdAbout.setVerticalAlignment(SwingConstants.CENTER);

        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        cmdCurrent.setFont(buttonFont);
        cmdPastWeek.setFont(buttonFont);
        cmdForecast.setFont(buttonFont);
        cmdTempMap.setFont(buttonFont);
        cmdSuggest.setFont(buttonFont);
        cmdAbout.setFont(buttonFont);

        cmdCurrent.setSize(200, 200);
        cmdPastWeek.setSize(200, 200);
        cmdForecast.setSize(200, 200);
        cmdTempMap.setSize(200, 200);
        cmdSuggest.setSize(200, 200);
        cmdAbout.setSize(200, 200);

        cmdCurrent.addActionListener(new CurrentWeatherButtonListener());
        cmdPastWeek.addActionListener(new PrevWeekButtonListener());
        cmdForecast.addActionListener(new ForecastButtonListener());
        cmdTempMap.addActionListener(new TempMapButtonListener());
        cmdSuggest.addActionListener(new SuggestionButtonListener());
        cmdAbout.addActionListener(new AboutUsButtonListener());

        setLayout(new GridLayout(3,2));
        add(cmdCurrent);
        add(cmdTempMap);
        add(cmdPastWeek);
        add(cmdForecast);
        add(cmdSuggest);
        add(cmdAbout);


        setVisible(true);
    }

    private class CurrentWeatherButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //thisFrame.setVisible(false);
            if (currentWeatherList == null)
                currentWeatherList = new GUICurrentWeatherList();
            else
                currentWeatherList.setVisible(true);

        }
    }

    private class TempMapButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //thisFrame.setVisible(false);
            new GUITemperatureMap();

        }
    }

    private class PrevWeekButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //new GUIWeekEntry(new GUIWeekHistory());

        }
    }

    private class ForecastButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           // new GUIWeekEntry(new GUIWeekForecast());

        }
    }

    private class SuggestionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new GUISuggestions();

        }
    }
    private class AboutUsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new GUIAboutUs();

        }
    }



}
