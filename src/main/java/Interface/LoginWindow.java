package Interface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class LoginWindow extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static String username;
	private static JFrame loginFrame;
    private JButton loginButton;
    private JTextField usernameField;
    private JPanel loginPanel;
    private JLabel typeUsernameLabel;

    public LoginWindow() {
    	
    	loginFrame=new JFrame ("Login Window");
        loginPanel = new JPanel(new GridLayout(10,10));
        typeUsernameLabel = new JLabel("Type Username");

		loginButton = new JButton ("SEND");
		usernameField = new JTextField (5);
        
        loginButton.addActionListener(this);

        loginButton.setBounds (77, 75, 85, 23);
        usernameField.setBounds (22, 45, 190, 23);
        typeUsernameLabel.setBounds (72, 10, 100, 25); 
       
        loginFrame.add (typeUsernameLabel);
        loginFrame.add (usernameField);
        loginFrame.add (loginButton);
        
        loginFrame.getRootPane().setDefaultButton(loginButton);
        loginFrame.setSize(new Dimension(2000, 500));
        loginFrame.getContentPane().add (loginPanel, BorderLayout.CENTER);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null) ;
        loginFrame.setSize(250,150);
        loginFrame.setVisible (true);
        
    }


	public void actionPerformed(ActionEvent event) {

		username=usernameField.getText();
		username=null;
		try {
			Thread.sleep(500);
		} catch (InterruptedException e){e.printStackTrace();
		}
		
	}


	public static JFrame getLoginFrame() {
		return loginFrame;
	}


	public static void setLoginFrame(JFrame loginFrame) {
		LoginWindow.loginFrame = loginFrame;
	}


	public static String getUsername() {
		return username;
	}


	public static void setUsername(String username) {
		LoginWindow.username = username;
	}





}