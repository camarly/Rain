package Project3;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GUIAboutUs extends JFrame {

    private JButton cmdClose;

    public GUIAboutUs() {

        setTitle("Rain - About Us");
        setIconImage(new ImageIcon("frameIcon.png").getImage());

        ArrayList<String> lines = loadAboutUs("./AboutUs.dat");
        JPanel pnlDisplay = new JPanel();
        for(String line : lines) {
            JLabel liine = new JLabel(line);
            JLabel newLine = new JLabel("                              \n");
            pnlDisplay.add(Box.createHorizontalStrut(10));
            pnlDisplay.add(newLine);
            pnlDisplay.add(Box.createVerticalGlue());
            pnlDisplay.add(liine);

        }

        JFrame frame = new JFrame();
        frame.add(pnlDisplay);
        frame.pack();
        frame.setSize(500,500);
        frame.setVisible(true);
    }


    private ArrayList<String> loadAboutUs(String aBfile)
    {
        ArrayList<String> lines =new ArrayList<>();
        Scanner fscan = null;
        try
        {
            fscan  = new Scanner(new File(aBfile));
            while(fscan.hasNext())
            {
                lines.add(fscan.nextLine());

            }

            fscan.close();
        }
        catch(IOException e)
        {}
        return lines;
    }
}