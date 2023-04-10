package Project3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GUICurrentWeatherList extends JFrame {

    private JButton cmdAddCity;
    private JButton cmdRemoveCity;
    private JButton cmdEditCity;
    private JButton cmdRefreshCurrent;
    private JButton cmdSearchPast;
    private JButton cmdViewPrevSearch;

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
        cmdRefreshCurrent = new JButton("Refresh");
        cmdSearchPast = new JButton("Weather History Lookup");
        cmdViewPrevSearch = new JButton("Search History");

        pnlCrudCmd.add(cmdAddCity);
        pnlCrudCmd.add(cmdEditCity);
        pnlCrudCmd.add(cmdRemoveCity);
        pnlSearchCmd.add(cmdRefreshCurrent);
        pnlSearchCmd.add(cmdSearchPast);
        pnlSearchCmd.add(cmdViewPrevSearch);

        add(scrollPane, BorderLayout.NORTH);
        add(pnlCrudCmd, BorderLayout.CENTER);
        add(pnlSearchCmd, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }


}
