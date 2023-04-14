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
    private GUICurrentWeatherList listFrame;

    public GUICityEdit(GUICurrentWeatherList listFrame) {

        this.listFrame = listFrame;

        setTitle("Rain - Edit City");
        setIconImage(new ImageIcon("frameIcon.png").getImage());

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

    private class SaveButtonListener implements ActionListener{
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