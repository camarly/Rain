package Project3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUITemperatureMap extends JFrame {

    private String imagePath = "./heatmap.png";
    private JLabel heatMap;
    private JButton cmdClose;
    private JPanel pnlCmd;

    public GUITemperatureMap() {

        setTitle("Rain - Temperature Map");
        setIconImage(new ImageIcon("frameIcon.png").getImage());
        setSize(900, 700);

        ImageIcon image = new ImageIcon(imagePath);
        ImageIcon resizedImage = new ImageIcon(image.getImage().getScaledInstance(900,600,Image.SCALE_SMOOTH));

        heatMap = new JLabel(resizedImage);
        heatMap.setHorizontalAlignment(JLabel.CENTER);


        pnlCmd = new JPanel();
        cmdClose = new JButton("Close");
        cmdClose.addActionListener(new CloseButtonListener());
        pnlCmd.add(cmdClose, BorderLayout.CENTER);

        add(heatMap, BorderLayout.CENTER);
        add(pnlCmd, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private class CloseButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}