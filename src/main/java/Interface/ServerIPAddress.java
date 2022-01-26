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

public class ServerIPAddress extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static String ServerIP;
	private static JFrame ServerIPFrame;
    private JButton ServerIPButton;
    private JTextField ServerIPField;
    private JPanel ServerIPPanel;
    private JLabel typeUsernameLabel;

    public ServerIPAddress() throws SocketException {
    	
    	
    	ServerIPFrame=new JFrame ("Server IP");
    	ServerIPPanel = new JPanel(new GridLayout(10,10));
        typeUsernameLabel = new JLabel("Type Server IP Address");

        ServerIPButton = new JButton ("SEND");
		ServerIPField = new JTextField (5);
        
		ServerIPButton.addActionListener(this);

		ServerIPButton.setBounds (77, 75, 85, 23);
        ServerIPField.setBounds (22, 45, 190, 23);
        typeUsernameLabel.setBounds (72, 10, 100, 25); 
       
        ServerIPFrame.add (typeUsernameLabel);
        ServerIPFrame.add (ServerIPField);
        ServerIPFrame.add (ServerIPButton);
        
        ServerIPFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        ServerIPFrame.getRootPane().setDefaultButton(ServerIPButton);
        ServerIPFrame.setSize(new Dimension(2000, 500));
        ServerIPFrame.getContentPane().add (ServerIPPanel, BorderLayout.CENTER);
        ServerIPFrame.pack();
        ServerIPFrame.setLocationRelativeTo(null) ;
        ServerIPFrame.setSize(250,150);
        ServerIPFrame.setVisible (true);
        
    }


	public void actionPerformed(ActionEvent event) {

		ServerIP=ServerIPField.getText();
		ServerIPFrame.setVisible (false);
				
	}


	public static String getServerIP() {
		return ServerIP;
	}


	public static void setServerIP(String username) {
		ServerIPAddress.ServerIP = username;
	}

}