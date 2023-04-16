package Project3;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;


public class GUILoginWindow extends JFrame {

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private JButton submitButton;
    private JButton clearButton;
    private JButton cancelButton;
    private JLabel rainIcon;
    private JPanel loginPanel;
    private JPanel commandsPanel;
    private GUILoginWindow thisFrame;

    public GUILoginWindow() {

        thisFrame = this;
        setTitle("Rain - User Login");

        JPanel loginPanel = new JPanel();
        JPanel commandsPanel = new JPanel();
        loginPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        commandsPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        loginPanel.setBackground(Color.LIGHT_GRAY);
        commandsPanel.setBackground(Color.LIGHT_GRAY);

        //JPanel imgContainer = new JPanel(new ImageIcon("./resources/images/frameIcon.png"));
        ImageIcon image = new ImageIcon("./resources/images/frameIcon.png");

        ImageIcon resizedImage = new ImageIcon(image.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH));
        rainIcon = new JLabel(resizedImage);
        rainIcon.setHorizontalAlignment(JLabel.CENTER);
        add(rainIcon, BorderLayout.NORTH);

        // Set font and color for labels and text fields
        Font labelFont = new Font("Arial", Font.PLAIN, 18);
        Color labelColor = new Color(52, 73, 94);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 14);
        Color textFieldColor = new Color(52, 73, 94);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(labelFont);
        usernameLabel.setForeground(labelColor);
        loginPanel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setFont(textFieldFont);
        usernameField.setForeground(textFieldColor);
        loginPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(labelColor);
        loginPanel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setFont(textFieldFont);
        passwordField.setForeground(textFieldColor);
        loginPanel.add(passwordField);

        loginPanel.setLayout(new GridLayout(2, 1));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        submitButton = new JButton("Submit");
        submitButton.setFont(labelFont);
        submitButton.setBackground(new Color(0, 0, 139));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this::loginEvent);
        commandsPanel.add(submitButton);

        clearButton = new JButton("Clear");
        clearButton.setFont(labelFont);
        clearButton.setBackground(new Color(57, 152, 174));
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(this::clearEvent);
        commandsPanel.add(clearButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 18));
        cancelButton.setBackground(new Color(231, 76, 60));
        cancelButton.addActionListener(this::cancelEvent);
        commandsPanel.add(cancelButton);

        add(loginPanel, BorderLayout.CENTER);
        add(commandsPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void loginEvent(ActionEvent e) {
        // TODO Auto-generated method stub
        UserAuthenticate thisUser = new UserAuthenticate(usernameField.getText(), String.valueOf(passwordField.getPassword()));
        if(thisUser.isValid){
            thisFrame.setVisible(false);
            new MainMenu();
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