package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JButton cmdCurrent;
    private JButton cmdPastWeek;
    private JButton cmdForecast;
    private JButton cmdTempMap;
    private JButton cmdSuggest;
    private JButton cmdAbout;


    public MainMenu() {

        setTitle("Rain - Main Menu");
        setIconImage(new ImageIcon("./resources/images/frameIcon.png").getImage());
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

        cmdCurrent.setIcon(new ImageIcon("./resources/images/current.png"));
        cmdCurrent.setBackground(new Color(2,200,152));
        cmdPastWeek.setIcon(new ImageIcon("./resources/images/past.png"));
        cmdPastWeek.setBackground(new Color(62,220,247));
        cmdForecast.setIcon(new ImageIcon("./resources/images/future.png"));
        cmdForecast.setBackground(new Color(128,84,247));
        cmdTempMap.setIcon(new ImageIcon("./resources/images/tempmap.png"));
        cmdTempMap.setBackground(new Color(247,142,62));
        cmdSuggest.setIcon(new ImageIcon("./resources/images/suggest.png"));
        cmdSuggest.setBackground(new Color(195,62,247));
        cmdAbout.setIcon(new ImageIcon("./resources/images/about.png"));
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

        cmdCurrent.setOpaque(true);
        cmdPastWeek.setOpaque(true);
        cmdForecast.setOpaque(true);
        cmdTempMap.setOpaque(true);
        cmdSuggest.setOpaque(true);
        cmdAbout.setOpaque(true);

        cmdCurrent.setBorderPainted(false);
        cmdPastWeek.setBorderPainted(false);
        cmdForecast.setBorderPainted(false);
        cmdTempMap.setBorderPainted(false);
        cmdSuggest.setBorderPainted(false);
        cmdAbout.setBorderPainted(false);

        setVisible(true);
    }

    private class CurrentWeatherButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new GUICurrentWeatherList();
        }
    }
    private class PrevWeekButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new GUIWeekEntry("Historic");
        }
    }



    private class ForecastButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new GUIWeekEntry("Future");
        }
    }

    private class TempMapButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class AboutUsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                    """
                            PANDA
                            
                            The better Solution

                            Computer  and Engineering Systems designers providing SMEs and  Enterprises with custom solutions for any devOPs, design and technology needs.

                            Founded 2023.
                            All Rights Reserved.""", "About Us", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class SuggestionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}