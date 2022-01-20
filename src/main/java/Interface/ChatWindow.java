package Interface;

import java.awt.*;
import java.awt.event.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.event.*;

import Network.MessageListener;
import Network.ServerResponseListener;
import Network.ClientHandler;

import Database.LocalDatabase;

public class ChatWindow extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private String username;
	private String remoteUser;
    private JButton sendButton;
    private JTextField chatField;
    private JTextArea chatArea;
    

	private JFrame chatFrame;
    private JPanel chatPanel;
    private String query;
    

    public ChatWindow(String username, String remoteUser) {
    	
    	this.username=username;
    	this.remoteUser=remoteUser;
    	
        chatFrame = new JFrame ("[PRIVATE CHAT] "+username+" - "+remoteUser);
		chatPanel = new JPanel(new GridLayout(10,10));

        sendButton = new JButton ("SEND");
        chatField = new JTextField (5);
        chatArea = new JTextArea (5, 5);
        
		sendButton.addActionListener(this);

        sendButton.setBounds (180, 215, 70, 25);
        chatField.setBounds (15, 215, 150, 25);
        chatArea.setBounds (15, 15, 235, 185);
        
        chatFrame.add (sendButton);
        chatFrame.add (chatField);
        chatFrame.add (chatArea);
        
		chatFrame.setSize(new Dimension(2000, 500));
        chatFrame.getContentPane().add (chatPanel, BorderLayout.CENTER);
        chatFrame.pack();
		chatFrame.setSize(370,290);
        chatFrame.setVisible (true);
        
        //Thread that listens to incoming messages from remote user and 
        //displays them on the chat area
        MessageListener messageThread = new MessageListener(chatArea,remoteUser);
        messageThread.start();
        
    }

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sendButton) {
			String message = chatField.getText();
			chatArea.append("["+username+"]: "+message+"\n");
			//
			// clientHandler.getIpAddress()
			// Add message to bdd , initiator=1 ///////////////////////////////////////////////////////////////
			//
			//String ip = ClientHandler.getIpAddress() ;
			try {
				String RemoteipAddress = ServerResponseListener.getRemoteIpAddress();
				String LocalipAddress = InetAddress.getLocalHost().getHostAddress();
			
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();  
				String tstamp = dtf.format(now);  
				
				int num = MainWindow.getLocaldb().insertLine(LocalipAddress, RemoteipAddress, message, tstamp);
			} catch (UnknownHostException e2) {e2.printStackTrace();}
			
			
			MainWindow.setQuery("@"+remoteUser+" "+message);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1){e1.printStackTrace();
			}
			MainWindow.setQuery(null);
		}
	}

	//Setters and Getters

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	public JTextArea getChatArea() {
		return chatArea;
	}
}