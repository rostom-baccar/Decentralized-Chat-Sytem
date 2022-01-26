package Interface;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketException;

import javax.swing.*;
import Model.ChatMessageType;
import Model.LocalIpAddress;
import Model.Message;

public class ServerIpAddress extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static String ServerIP;
	private static JFrame loginFrame;
    private JButton loginButton;
    private JTextField ServerIPField;
    private JPanel loginPanel;
    private JLabel typeUsernameLabel;

    public ServerIpAddress() throws SocketException {
    	
    	
    	loginFrame=new JFrame ("Server IP");
        loginPanel = new JPanel(new GridLayout(10,10));
        typeUsernameLabel = new JLabel("Type Server IP Address");

		loginButton = new JButton ("SEND");
		ServerIPField = new JTextField (5);
        
        loginButton.addActionListener(this);

        loginButton.setBounds (77, 75, 85, 23);
        ServerIPField.setBounds (22, 45, 190, 23);
        typeUsernameLabel.setBounds (72, 10, 100, 25); 
       
        loginFrame.add (typeUsernameLabel);
        loginFrame.add (ServerIPField);
        loginFrame.add (loginButton);
        
        loginFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        loginFrame.getRootPane().setDefaultButton(loginButton);
        loginFrame.setSize(new Dimension(2000, 500));
        loginFrame.getContentPane().add (loginPanel, BorderLayout.CENTER);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null) ;
        loginFrame.setSize(250,150);
        loginFrame.setVisible (true);
        
    }


	public void actionPerformed(ActionEvent event) {

		ServerIP=ServerIPField.getText();
		loginFrame.setVisible (false);
				
	}


	public static String getServerIP() {
		return ServerIP;
	}


	public static void setServerIP(String username) {
		ServerIpAddress.ServerIP = username;
	}

}