package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICityEntry extends JFrame {
    private JButton cmdAdd;
    private JButton cmdClose;
    private JPanel pnlDisplay;
    private JPanel pnlCmd;
    private JTextField cityName;
    private GUICurrentWeatherList listFrame;

    public GUICityEntry(GUICurrentWeatherList listFrame) {

        this.listFrame = listFrame;

        setTitle("Rain - Add a City");
        setIconImage(new ImageIcon("frameIcon.png").getImage());

        pnlDisplay = new JPanel();
        pnlCmd = new JPanel();

        pnlDisplay.add(new JLabel("City Name: "));
        cityName = new JTextField(20);
        pnlDisplay.add(cityName);

        cmdAdd = new JButton("Add");
        cmdClose = new JButton("Close");
        cmdAdd.addActionListener(new AddButtonListener());
        cmdClose.addActionListener(new CloseButtonListener());

        pnlCmd.add(cmdAdd);
        pnlCmd.add(cmdClose);

        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCmd, BorderLayout.SOUTH);
        pack();
        setVisible(true);

    }

    private class AddButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = null;
            String lat = null;
            String lon = null;


            name = cityName.getText();
            City city = new City ();
            city.setCityName(name);

            city.setCurrentWeather();
            listFrame.addCity(city);
            listFrame.saveCities(listFrame.getCityList());

            cityName.setText("");

        }
    }

    private class CloseButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();

        }
    }
}