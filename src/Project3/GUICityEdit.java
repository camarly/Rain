package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICityEdit extends JFrame {

    private JButton cmdSave;
    private JButton cmdCancel;
    private JPanel pnlDisplay;
    private JPanel pnlCmd;
    private JTextField cityName;

    private String cityEdit;
    private GUICurrentWeatherList listFrame;

    public GUICityEdit(GUICurrentWeatherList listFrame, String cityEdit) {

        setTitle("Rain - Edit City");
        this.cityEdit = cityEdit;

        this.listFrame = listFrame;


        pnlDisplay = new JPanel();
        pnlCmd = new JPanel();

        pnlDisplay.add(new JLabel("New City Name: "));
        cityName = new JTextField(20);
        pnlDisplay.add(cityName);

        cmdSave = new JButton("Save");
        cmdCancel = new JButton("Cancel");

        cmdSave.addActionListener(new SaveButtonListener());
        cmdCancel.addActionListener(new CancelButtonListener());

        pnlCmd.add(cmdSave);
        pnlCmd.add(cmdCancel);

        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCmd, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = cityName.getText();

            for (City city: listFrame.getCityList()){
                if (city.getCityName().equals(cityEdit)){
                    city.setCityName(name);
                    city.setCurrentWeather();
                    listFrame.showTable(listFrame.getCityList());
                    dispose();
                }
            }
        }


    }

    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }


}