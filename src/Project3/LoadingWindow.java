package Project3;

import javax.swing.*;

public class LoadingWindow extends JDialog {
    private JProgressBar progressBar;

    public LoadingWindow(JFrame parent) {
        super(parent, "Getting resources", true);
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        JPanel panel = new JPanel();
        panel.add(progressBar);
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(parent);
    }

    public void showDialog() {
        setVisible(true);
    }

    public void hideDialog() {
        setVisible(false);
    }
}
