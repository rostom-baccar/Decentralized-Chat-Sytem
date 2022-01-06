package Interface ;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LoginWindow extends JFrame implements ActionListener {
	public static String username;
	public static JFrame loginFrame;
	public static JPanel loginPanel;
	public static JTextField pseudoField;
	JLabel pseudoLabel;
	JButton loginButton;

	public LoginWindow() {

		loginFrame = new JFrame("Login Window");
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setSize(new Dimension(2000, 500));
		loginPanel = new JPanel(new GridLayout(10,10));
		
		pseudoField = new JTextField(40);
		pseudoLabel = new JLabel("Pseudo", SwingConstants.LEFT);
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);

		loginPanel.add(pseudoField);
		loginPanel.add(pseudoLabel);
		loginPanel.add(loginButton);

		pseudoLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		loginFrame.getRootPane().setDefaultButton(loginButton);
		loginFrame.getContentPane().add(loginPanel, BorderLayout.CENTER);
		loginFrame.pack();
		//		loginFrame.setLocationRelativeTo(null) ;
		loginFrame.setSize(300,300);
		loginFrame.setVisible(true);


	}


	public void actionPerformed(ActionEvent event) {

		username = pseudoField.getText();
		username=null;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e){e.printStackTrace();
		}
		
		//		loginFrame.setVisible(false);



	}
	
	public static void main(String [] argv) {
		LoginWindow loginWindow = new LoginWindow();

	}


}