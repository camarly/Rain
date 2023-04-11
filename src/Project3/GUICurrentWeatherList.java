package Project3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICurrentWeatherList extends JFrame {

    private JButton cmdAddCity;
    private JButton cmdRemoveCity;
    private JButton cmdEditCity;
    private JButton cmdRefresh;
    private JButton cmdPrevFive;
    private JButton cmdTmpMap;

    private JPanel pnlDisplay;
    private JPanel pnlCrudCmd;
    private JPanel pnlSearchCmd;

    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    private GUICurrentWeatherList thisFrame;

    public GUICurrentWeatherList() {
        super("Rain - Current Weather Listing");

        thisFrame = this;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        String[] columnNames = {"City", "Temp", "Humidity", "Weather Condition", "Description", ""};

        model = new DefaultTableModel(columnNames, 0);

        table = new JTable(model);
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        //getContentPane().add(scrollPane, BorderLayout.NORTH);

        pnlCrudCmd = new JPanel();
        pnlSearchCmd = new JPanel();

        cmdAddCity = new JButton("Add City");
        cmdEditCity = new JButton("Edit City");
        cmdRemoveCity = new JButton("Remove City");
        cmdRefresh = new JButton("Refresh");
        cmdPrevFive = new JButton("Previous 5 Days");
        cmdTmpMap = new JButton("Temperature Map");

        cmdAddCity.addActionListener(new AddCityButtonListener());
        cmdEditCity.addActionListener(new EditCityButtonListener());
        cmdRemoveCity.addActionListener(new RemoveCityButtonListener());
        cmdRefresh.addActionListener(new RefreshButtonListener());
        cmdPrevFive.addActionListener(new FiveDayButtonListener());
        cmdTmpMap.addActionListener(new TempMapButtonListener());

        pnlCrudCmd.add(cmdAddCity);
        pnlCrudCmd.add(cmdEditCity);
        pnlCrudCmd.add(cmdRemoveCity);
        pnlSearchCmd.add(cmdRefresh);
        pnlSearchCmd.add(cmdPrevFive);
        pnlSearchCmd.add(cmdTmpMap);

        add(scrollPane, BorderLayout.NORTH);
        add(pnlCrudCmd, BorderLayout.CENTER);
        add(pnlSearchCmd, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private class AddCityButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class EditCityButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class RemoveCityButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class RefreshButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class FiveDayButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class TempMapButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}
