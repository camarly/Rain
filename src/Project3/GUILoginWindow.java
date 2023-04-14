package Project3;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;


public class GUILoginWindow extends JFrame{

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private JButton submitButton;
    private JButton clearButton;
    private JButton cancelButton;
    private GUILoginWindow thisFrame;

    public GUILoginWindow() {

        thisFrame = this;


        setTitle("Rain - User Login");
        setIconImage(new ImageIcon("frameIcon.png").getImage());

        JPanel loginPanel = new JPanel();
        JPanel commandsPanel = new JPanel();

        loginPanel.add(new JLabel("Username"));
        usernameField = new JTextField(20);
        loginPanel.add(usernameField);


        loginPanel.add(new JLabel("Password"));
        passwordField = new JPasswordField(20);
        loginPanel.add(passwordField);


        loginPanel.setLayout(new GridLayout(2, 3));

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this::loginEvent);
        commandsPanel.add(submitButton);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this::clearEvent);
        commandsPanel.add(clearButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this::cancelEvent);
        commandsPanel.add(cancelButton);


        add(loginPanel, BorderLayout.CENTER);
        add(commandsPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);

    }


    public void loginEvent(ActionEvent e) {
        // TODO Auto-generated method stub
        UserAuthenticate thisUser = new UserAuthenticate(usernameField.getText(), String.valueOf(passwordField.getPassword()));
        if(thisUser.isValid){
            this.setVisible(false);
            new GUIMainMenu();
            this.dispose();
        } else {
            showMessageDialog(null, "The password and/or username entered is incorrect.\nPlease try again.", "Invalid credentials!", ERROR_MESSAGE);
        }

    }

    public void clearEvent(ActionEvent e) {
        // TODO Auto-generated method stub
        usernameField.setText("");
        passwordField.setText("");
    }
    public void cancelEvent(ActionEvent e) {
        // TODO Auto-generated method stub
        System.exit(0);
    }

}