package Project3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIWeekHistory extends JFrame {

    private JButton cmdClose;
    private JButton cmdExport;

    private JPanel pnlDisplay;
    private JPanel pnlCmd;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    public GUIWeekHistory()  {

        setTitle("Rain - Prior Week History");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);

        String[] columnNames = {"Date", "City", "Temp", "Humidity", "Weather Condition", "Description", ""};
        model = new DefaultTableModel(columnNames, 0);

        table = new JTable(model);
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 400));

        cmdExport = new JButton("Export");
        cmdClose = new JButton("Close");
        cmdExport.addActionListener(new ExportButtonListener());
        cmdClose.addActionListener(new CloseButtonListener());

        pnlCmd = new JPanel();
        pnlCmd.add(cmdExport);
        pnlCmd.add(cmdClose);

        add(scrollPane, BorderLayout.CENTER);
        add(pnlCmd, BorderLayout.SOUTH);

        pack();
        setVisible(true);

    }

    private class ExportButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class CloseButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}
