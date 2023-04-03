

package Project3;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginWindow extends JFrame{
	
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton submitButton; 
	private JButton cancelButton;
	private JButton clearButton;
	private JPanel loginPanel;
	private JPanel commandsPanel;
	
	public LoginWindow() {
		
		setTitle("User Login");
		
		loginPanel = new JPanel();
		commandsPanel = new JPanel();
		
		loginPanel.add(new JLabel("Username"));
		usernameField = new JTextField(20);
		loginPanel.add(usernameField);
		
		
		loginPanel.add(new JLabel("Password"));
		passwordField = new JPasswordField(20);
		loginPanel.add(passwordField);

		
		loginPanel.setLayout(new GridLayout(2, 3));
		
		submitButton = new JButton("Submit");
		commandsPanel.add(submitButton);
		
		clearButton = new JButton("Clear");
		commandsPanel.add(clearButton);
		
		cancelButton = new JButton("Cancel");
		commandsPanel.add(cancelButton);
		

//		this.add(loginPanel);
//		this.add(commandsPanel);
		
        add(loginPanel, BorderLayout.CENTER);
        add(commandsPanel, BorderLayout.SOUTH);
        
		pack();
		setVisible(true);
		
		
	}
	
}
