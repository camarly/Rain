package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIHomeScreen extends JFrame {

    private GUIHomeScreen thisFrame;

    private JButton cmdCurrentWther;
    private JButton cmdPrevHistory;
    private JButton cmdTempMap;
    private JButton cmdAboutUs;

    private JLabel mainLabel;
    private JPanel  pnlDisplay;
    private JPanel pnlCmd;


    public GUIHomeScreen() {

        this.thisFrame = this;
        setTitle("Rain - Main Menu");
        setSize(400, 500);

        ImageIcon image = new ImageIcon("./main.jpg");
        ImageIcon resizedImage = new ImageIcon(image.getImage().getScaledInstance(900,600,Image.SCALE_SMOOTH));
        mainLabel = new JLabel(resizedImage);
        mainLabel.setHorizontalAlignment(JLabel.CENTER);


        pnlDisplay = new JPanel();
        pnlDisplay.add(mainLabel);

        pnlCmd = new JPanel();
        cmdCurrentWther = new JButton("Current Weather");
        cmdPrevHistory = new JButton("Prior Week History");
        cmdTempMap = new JButton("Temperature Map");
        cmdAboutUs = new JButton("About Us");

        cmdCurrentWther.addActionListener(new CurrentWeatherButtonListener());
        cmdPrevHistory.addActionListener(new PrevWeekButtonListener());
        cmdTempMap.addActionListener(new TempMapButtonListener());
        cmdAboutUs.addActionListener(new AboutUsButtonListener());

        pnlCmd.add(cmdCurrentWther);
        pnlCmd.add(cmdPrevHistory);
        pnlCmd.add(cmdTempMap);
        pnlCmd.add(cmdAboutUs);

        add(pnlDisplay,BorderLayout.CENTER);
        add(pnlCmd,BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private class CurrentWeatherButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    private class PrevWeekButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new GUIWeekHistoryEntry();

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

        }
    }
}