package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICityEntry extends JFrame {
    private JButton cmdAdd;
    private JButton cmdCancel;
    private JPanel pnlDisplay;
    private JPanel pnlCmd;
    private JTextField cityName;
    private GUICurrentWeatherList listFrame;

    public GUICityEntry() {


        setTitle("Rain - Add a City");

        pnlDisplay = new JPanel();
        pnlCmd = new JPanel();

        pnlDisplay.add(new JLabel("City Name: "));
        cityName = new JTextField(20);
        pnlDisplay.add(cityName);

        cmdAdd = new JButton("Add");
        cmdCancel = new JButton("Cancel");
        cmdAdd.addActionListener(new AddButtonListener());
        cmdCancel.addActionListener(new CancelButtonListener());

        pnlCmd.add(cmdAdd);
        pnlCmd.add(cmdCancel);

        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCmd, BorderLayout.SOUTH);
        pack();
        setVisible(true);

    }

    private class AddButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class CancelButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
