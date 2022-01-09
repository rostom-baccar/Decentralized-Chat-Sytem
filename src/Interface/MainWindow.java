package Interface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MainWindow extends JPanel implements ActionListener {
	private JButton refreshButton;
	private JButton disconnectButton;
	private JButton chatButton;
	public static JComboBox<String> UsersList = null;
	public static JTextArea broadArea;
	private JTextField broadField;
	private JButton sendButton;
	private JLabel groupChatLabel;
	public static JFrame mainFrame;
	private JPanel mainPanel;
	public static String query;
	private static String username;
	private ChatWindow chatWindow=null;
	private JButton changeUsernameButton;
	private JTextField newUsernameField;
	private static String newUsername;
	private static boolean uniqueNewUsername=false;
	public static boolean chatInitiator=false;

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
		mainFrame. add (sendButton);
		mainFrame.add (broadField);
		mainFrame.add (newUsernameField);
		mainFrame.add (UsersList);
		mainFrame.add (broadArea);
		mainFrame. add (groupChatLabel);

		mainFrame.setSize(new Dimension(2000, 500));
		mainFrame.getContentPane().add (mainPanel, BorderLayout.CENTER);
		mainFrame.pack();
		mainFrame.setSize(540,540);
		mainFrame.setVisible (true);

	}


	public static void main (String[] args) {
		String testUsername="test username";
		MainWindow mainWindow = new MainWindow(testUsername);
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == disconnectButton) {
			query="disconnect";
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1){e1.printStackTrace();
			}
			query=null;
		}

		if(e.getSource() == refreshButton) {
			//			String[] init = {};
			//			UsersList = new JComboBox<String>(init);
			UsersList.removeAllItems();
			query="active";
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1){e1.printStackTrace();
			}
			query=null;
			try {
				Thread.sleep(250); //to give time for the server response
				//listener to add the clients to the table
			}
			catch (InterruptedException e1) {e1.printStackTrace();}
		}

		if(e.getSource() == sendButton) {
			String message = broadField.getText();
			query="broad "+message;
			//broadArea.append(username+": "+message);
			query=null;

		}

		if(e.getSource() == chatButton) {
			String remoteUser=(String) UsersList.getSelectedItem();
//			System.out.println("[DEBUG] Chatting with "+remoteUser);
			chatWindow = new ChatWindow(username, remoteUser);
//			if (ServerResponseListener.chatInitiator) {
			query="chat "+username;
			query=null;
//			}
		}

		if(e.getSource() == changeUsernameButton) {
			newUsername = newUsernameField.getText();
			query="#"+username+" "+newUsername;
			//System.out.println("[DEBUG] query: "+query);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1){e1.printStackTrace();
			}
			query=null;
			try {
				Thread.sleep(500);//To give time for the server to check the
				//validity of the username
			} catch (InterruptedException e1){e1.printStackTrace();
			}
			if (uniqueNewUsername) {
				mainFrame.setTitle(newUsername);
//				mainFrame.setVisible (false);
				JOptionPane.showMessageDialog(null,"Username changed from "+username+" to "+newUsername);
//				MainWindow mainWindow = new MainWindow(newUsername);
				
				username=newUsername;
				//query=null;
			}
			uniqueNewUsername=false;
		}
	}

	public static void setUniqueNewUsername(boolean uniqueNewUsername) {
		MainWindow.uniqueNewUsername = uniqueNewUsername;
	}
}

