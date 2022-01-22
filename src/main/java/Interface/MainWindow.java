package Interface;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.*;
import ClientSide.ServerResponseListener;
import Model.ChatMessageType;
import Model.Message;
import Model.RemoteUser;

public class MainWindow extends JPanel implements ActionListener {

	private static ArrayList<ChatWindow> chatWindows = new ArrayList<ChatWindow>();
	private static final long serialVersionUID = 1L;
	private static boolean uniqueNewUsername=false;
	private static JComboBox<String> UsersList = null;
	private static JTextArea broadArea;
	private static JFrame mainFrame;
	private static String username;
	private static String newUsername;
	private JButton disconnectButton;
	private JButton chatButton;
	private JTextField broadField;
	private JButton sendButton;
	private JLabel groupChatLabel;
	private JPanel mainPanel;
	private ChatWindow chatWindow=null;
	private JButton changeUsernameButton;
	private JTextField newUsernameField;
	private ObjectOutputStream out;



	public MainWindow(String username, ObjectOutputStream out) throws IOException {

		MainWindow.username=username;
		this.out=out;  	

		String[] init= {};
		UsersList = new JComboBox<String>(init);
		mainFrame = new JFrame (username);
		mainPanel = new JPanel(new GridLayout(10,10));
		broadArea = new JTextArea (5, 5);
		broadArea.setEditable(false);
		newUsernameField = new JTextField (5);
		groupChatLabel = new JLabel ("GROUP CHAT");
		disconnectButton = new JButton ("Disconnect");
		chatButton = new JButton ("Chat");
		sendButton = new JButton ("Send");
		changeUsernameButton = new JButton ("Change Username");
		broadField = new JTextField (5);

		changeUsernameButton.addActionListener(this);
		disconnectButton.addActionListener(this);
		chatButton.addActionListener(this);
		sendButton.addActionListener(this);

		changeUsernameButton.setBounds (355, 20, 150, 25);
		chatButton.setBounds (390, 135, 115, 25);
		disconnectButton.setBounds (390, 415, 115, 25);
		sendButton.setBounds (390, 455, 115, 25);
		broadField.setBounds (25, 455, 345, 25);
		newUsernameField.setBounds (25, 20, 305, 25);
		UsersList.setBounds (390, 95, 115, 25);
		broadArea.setBounds (30, 95, 340, 340);
		groupChatLabel.setBounds (160, 65, 100, 25);

		mainFrame.add (changeUsernameButton);
		mainFrame.add (chatButton);
		mainFrame.add (disconnectButton);
		mainFrame.add (sendButton);
		mainFrame.add (broadField);
		mainFrame.add (newUsernameField);
		mainFrame.add (UsersList);
		mainFrame.add (broadArea);
		mainFrame.add (groupChatLabel);

		mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(new Dimension(2000, 500));
		mainFrame.getContentPane().add (mainPanel, BorderLayout.CENTER);
		mainFrame.pack();
		mainFrame.setSize(540,540);
		mainFrame.setVisible (true);

		
		//When the window is close a disconnect query is sent 
		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					out.writeObject(Message.buildTypeMessage(ChatMessageType.Disconnect));
				} catch (IOException e1) {e1.printStackTrace();}			}
		});

		//When someone new connects we update their users list
		out.writeObject(Message.buildTypeMessage(ChatMessageType.UsersList));


	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == disconnectButton) {
			try {
				out.writeObject(Message.buildTypeMessage(ChatMessageType.Disconnect));
			} catch (IOException e1) {e1.printStackTrace();}
			mainFrame.setVisible (false);
		}

		if(e.getSource() == sendButton) {
			String message = broadField.getText();
			try {
				out.writeObject(Message.buildMessage(ChatMessageType.BroadMessage,message));
			} catch (IOException e1) {e1.printStackTrace();}

		}

		if(e.getSource() == chatButton) {
			String remoteUser=(String) UsersList.getSelectedItem();
			chatWindow = new ChatWindow(username, remoteUser, out);
			chatWindows.add(chatWindow);

			if (ServerResponseListener.isConversationInitiator())
			{
				try {
					out.writeObject(Message.buildMessage2(ChatMessageType.Initiator,null,username,remoteUser));
				} catch (IOException e1) {e1.printStackTrace();}
			}
			else {
				try {
					out.writeObject(Message.buildMessage2(ChatMessageType.Recipient,null,username,remoteUser));
				} catch (IOException e1) {e1.printStackTrace();}
			}
			ServerResponseListener.setConversationInitiator(true);
		}

		if(e.getSource() == changeUsernameButton) {
			newUsername = newUsernameField.getText();
			try {
				out.writeObject(Message.buildMessage2(ChatMessageType.UsernameChange,null,username,newUsername));
			} catch (IOException e1) {e1.printStackTrace();}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {e1.printStackTrace();}

			if (uniqueNewUsername) {
				mainFrame.setTitle(newUsername);
				JOptionPane.showMessageDialog(null,"Username changed from "+username+" to "+newUsername);
				username=newUsername;
			}
			uniqueNewUsername=false;
		}
	}

	//Getters and Setters


	public static ArrayList<ChatWindow> getChatWindows() {
		return chatWindows;
	}

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

}