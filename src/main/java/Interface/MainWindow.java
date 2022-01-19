package Interface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import Model.ChatMessageType;
import Model.Message;
import NetworkListeners.ServerResponseListener;
import NetworkManagers.ClientHandler;
import NetworkManagers.Server;

public class MainWindow extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static JComboBox<String> UsersList = null;
	private static JTextArea broadArea;
	private static JFrame mainFrame;
	private static Message queryToSend;
	private static String username;
	private static String newUsername;
	private static boolean uniqueNewUsername=false;
	private JButton refreshButton;
	private JButton disconnectButton;
	private JButton chatButton;
	private JTextField broadField;
	private JButton sendButton;
	private JLabel groupChatLabel;
	private JPanel mainPanel;
	private ChatWindow chatWindow=null;
	private JButton changeUsernameButton;
	private JTextField newUsernameField;

	public MainWindow(String username) {

		this.username=username;

		String[] init= {};
		UsersList = new JComboBox<String>(init);
		mainFrame = new JFrame (username);
		mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel(new GridLayout(10,10));
		broadArea = new JTextArea (5, 5);
		newUsernameField = new JTextField (5);
		groupChatLabel = new JLabel ("GROUP CHAT");
		refreshButton = new JButton ("Refresh List");
		disconnectButton = new JButton ("Disconnect");
		chatButton = new JButton ("Chat");
		sendButton = new JButton ("Send");
		changeUsernameButton = new JButton ("Change Username");
		broadField = new JTextField (5);

		changeUsernameButton.addActionListener(this);
		refreshButton.addActionListener(this);
		disconnectButton.addActionListener(this);
		chatButton.addActionListener(this);
		sendButton.addActionListener(this);

		changeUsernameButton.setBounds (355, 20, 150, 25);
		chatButton.setBounds (390, 175, 115, 25);
		refreshButton.setBounds (390, 95, 115, 25);
		disconnectButton.setBounds (390, 415, 115, 25);
		sendButton.setBounds (390, 455, 115, 25);
		broadField.setBounds (25, 455, 345, 25);
		newUsernameField.setBounds (25, 20, 305, 25);
		UsersList.setBounds (390, 135, 115, 25);
		broadArea.setBounds (30, 95, 340, 340);
		groupChatLabel.setBounds (160, 65, 100, 25);

		mainFrame.add (changeUsernameButton);
		mainFrame.add (chatButton);
		mainFrame.add (refreshButton);
		mainFrame.add (disconnectButton);
		mainFrame.add (sendButton);
		mainFrame.add (broadField);
		mainFrame.add (newUsernameField);
		mainFrame.add (getUsersList());
		mainFrame.add (getBroadArea());
		mainFrame.add (groupChatLabel);

		mainFrame.setSize(new Dimension(2000, 500));
		mainFrame.getContentPane().add (mainPanel, BorderLayout.CENTER);
		mainFrame.pack();
		mainFrame.setSize(540,540);
		mainFrame.setVisible (true);

	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == disconnectButton) {
			Message query = new Message(ChatMessageType.Disconnect);		
			queryToSend=query;
			queryToSend=null;
		}

		if(e.getSource() == refreshButton) {
			Message query = new Message(ChatMessageType.UsersList);		
			UsersList.removeAllItems();
			queryToSend=query;
			queryToSend=null;
		}

		if(e.getSource() == sendButton) {
			String message = broadField.getText();
			Message query = new Message(ChatMessageType.BroadMessage,message);
			queryToSend=query;
			queryToSend=null;
		}

		if(e.getSource() == chatButton) {
			String remoteUser=(String) UsersList.getSelectedItem();
			chatWindow = new ChatWindow(username, remoteUser);

			if (ServerResponseListener.isConversationInitiator())
			{
				Message query = new Message(ChatMessageType.Initiator,null,username,remoteUser);
				queryToSend=query;
			}
			else {
				Message query = new Message(ChatMessageType.Recipient,null,username,remoteUser);
				queryToSend=query;
			}
			ServerResponseListener.setConversationInitiator(true);
			queryToSend=null;
		}

		if(e.getSource() == changeUsernameButton) {
			newUsername = newUsernameField.getText();
			Message query = new Message(ChatMessageType.UsernameChange,null,username,newUsername);

			uniqueNewUsername=false;
			queryToSend=query;
			queryToSend=null;
			if (uniqueNewUsername) {
				mainFrame.setTitle(newUsername);
				username=newUsername;
				JOptionPane.showMessageDialog(null,"Username changed from "+username+" to "+newUsername);

			}
		}
	}

	//Getters and Setters


	public static void setUniqueNewUsername(boolean uniqueNewUsername) {
		MainWindow.uniqueNewUsername = uniqueNewUsername;
	}

	public static JComboBox<String> getUsersList() {
		return UsersList;
	}

	public static JTextArea getBroadArea() {
		return broadArea;
	}

	public static JFrame getMainFrame() {
		return mainFrame;
	}

	public static Message getQuery() {
		return queryToSend;
	}

	public static void setQuery(Message query) {
		MainWindow.queryToSend = query;
	}

}