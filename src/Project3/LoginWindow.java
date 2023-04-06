

package Project3;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginWindow extends JFrame{

	private final JTextField usernameField;
	private final JPasswordField passwordField;

	public LoginWindow() {

		setTitle("User Login");

		JPanel loginPanel = new JPanel();
		JPanel commandsPanel = new JPanel();

		loginPanel.add(new JLabel("Username"));
		usernameField = new JTextField(20);
		loginPanel.add(usernameField);


		loginPanel.add(new JLabel("Password"));
		passwordField = new JPasswordField(20);
		loginPanel.add(passwordField);


		loginPanel.setLayout(new GridLayout(2, 3));

		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(this::loginEvent);
		commandsPanel.add(submitButton);

		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(e -> {
			usernameField.setText("");
			passwordField.setText("");
		});
		commandsPanel.add(clearButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> System.exit(0));
		commandsPanel.add(cancelButton);


		add(loginPanel, BorderLayout.CENTER);
        add(commandsPanel, BorderLayout.SOUTH);

		pack();
		setVisible(true);

	}


	public void loginEvent(ActionEvent e) {
		// TODO Auto-generated method stub
		UserAuthenticate thisUser = new UserAuthenticate(usernameField.getText(), String.valueOf(passwordField.getPassword()));
		if(thisUser.isValid)
			System.out.println("Valid");
		else
			System.out.println("Invalid");
	}


}
