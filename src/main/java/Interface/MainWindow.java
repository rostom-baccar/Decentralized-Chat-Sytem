package Interface;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import ClientSide.Client;
import ClientSide.ServerResponseListener;
import Database.Database;
import Model.MessageType;
import Model.LocalIpAddress;
import Model.Message;
import Model.RemoteUser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MainWindow {

	private static JFrame mainFrame;
	private JTextField newUsernameField;
	private JTextField broadField;
	private static boolean uniqueNewUsername=false;
	private static ArrayList<ChatWindow> chatWindows = new ArrayList<ChatWindow>();
	private static String username;
	private static String newUsername;
	private ChatWindow chatWindow=null;
	private ObjectOutputStream out;
	private static String[] stringInit= {};
	private static ArrayList<RemoteUser> ObjectUsersList = new ArrayList<RemoteUser>();
	private static JComboBox<String> stringUsersList = new JComboBox<String>(stringInit);
	private static JTextArea broadArea;

	public static void main(String[] args) throws IOException {
		new MainWindow("rostom",null);
	}

	public MainWindow(String username, ObjectOutputStream out) throws IOException {
		MainWindow.username=username;
		this.out=out;
		initialize();
	}


	private void initialize() throws IOException {
		mainFrame = new JFrame(username);
		mainFrame.setBounds(100, 100, 511, 474);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);

		JButton changeUsernameButton = new JButton("Change Username");

		changeUsernameButton.setBounds(338, 12, 151, 25);
		mainFrame.getContentPane().add(changeUsernameButton);

		newUsernameField = new JTextField();
		newUsernameField.setBounds(20, 12, 308, 25);
		mainFrame.getContentPane().add(newUsernameField);
		newUsernameField.setColumns(10);


		stringUsersList.setBounds(358, 73, 131, 25);
		mainFrame.getContentPane().add(stringUsersList);

		JButton chatButton = new JButton("Chat");

		chatButton.setBounds(358, 109, 131, 25);
		mainFrame.getContentPane().add(chatButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 73, 328, 317);
		mainFrame.getContentPane().add(scrollPane);

		broadArea = new JTextArea();
		broadArea.setEditable(false);
		scrollPane.setViewportView(broadArea);

		broadField = new JTextField();
		broadField.setColumns(10);
		broadField.setBounds(20, 401, 328, 25);
		mainFrame.getContentPane().add(broadField);

		JButton sendButton = new JButton("Send");
		sendButton.setBounds(358, 401, 131, 25);
		mainFrame.getContentPane().add(sendButton);

		JButton disconnectButton = new JButton("Disconnect");
		disconnectButton.setBounds(358, 366, 131, 25);
		mainFrame.getContentPane().add(disconnectButton);

		JLabel groupChatLabel = new JLabel("GROUP CHAT");
		groupChatLabel.setBounds(140, 48, 112, 14);
		mainFrame.getContentPane().add(groupChatLabel);
		mainFrame.getRootPane().setDefaultButton(sendButton);
		mainFrame.setVisible(true);

		//When the window is close a disconnect query is sent 
		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					getMainWindow().out.writeObject(Message.buildTypeMessage(MessageType.Disconnect));
				} catch (IOException e1) {e1.printStackTrace();}			}
		});

		//When someone new connects we update their users list
		this.out.writeObject(Message.buildTypeMessage(MessageType.UsersList));

		changeUsernameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newUsername = newUsernameField.getText();
				try {
					out.writeObject(Message.buildMessage2(MessageType.UsernameChange,null,username,newUsername));
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
						
				for (ChatWindow c : chatWindows) {
					
					c.getChatArea().setText("");
					c.setUsername(newUsername);
					
					try {
						String RemoteipAddress = c.getIpAddress();
						String LocalipAddress = LocalIpAddress.getLocalAddress().getHostAddress();
						
						
						Database.LoadChatHistory(Client.getClientdb(), c , LocalipAddress, RemoteipAddress, newUsername, c.getRemoteUser());

					} catch (Exception ee) {
						System.out.print("Error while loading History ! \n");
						ee.printStackTrace();
					}
					stringUsersList.removeItem(username);
					
				}

				
				newUsernameField.setText("");
			}
		});

		chatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String remoteUser=(String) stringUsersList.getSelectedItem();
				RemoteUser targetRemoteUser = findRemoteUser(remoteUser);
				String ipAddress = targetRemoteUser.getIpAdress();
				chatWindow = new ChatWindow(username, remoteUser,ipAddress, out);
				chatWindows.add(chatWindow);
				//Loading Chat History

				try {
					String RemoteipAddress = ipAddress;
					String LocalipAddress = LocalIpAddress.getLocalAddress().getHostAddress();
					Database.LoadChatHistory(Client.getClientdb(),chatWindow, LocalipAddress, RemoteipAddress,username, remoteUser);

				} catch (Exception ee) {
					System.out.print("Error while loading History ! \n");
					ee.printStackTrace();

				}

				if (ServerResponseListener.isConversationInitiator())
				{
					try {
						out.writeObject(Message.buildMessage2(MessageType.Initiator,null,username,remoteUser));
					} catch (IOException e1) {e1.printStackTrace();}
				}
				else {
					try {
						out.writeObject(Message.buildMessage2(MessageType.Recipient,null,username,remoteUser));
					} catch (IOException e1) {e1.printStackTrace();}
				}
				ServerResponseListener.setConversationInitiator(true);
			}

		});
		
		


		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					out.writeObject(Message.buildTypeMessage(MessageType.Disconnect));
				} catch (IOException e1) {e1.printStackTrace();}
				mainFrame.setVisible (false);
			}
		});

		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = broadField.getText();
				try {
					out.writeObject(Message.buildMessage(MessageType.BroadMessage,message));
				} catch (IOException e1) {e1.printStackTrace();}
				broadField.setText("");
			}
			
		});

	}

	private RemoteUser findRemoteUser(String remoteUser) {
		RemoteUser target = null;
		for (RemoteUser it : ObjectUsersList) {
			if (it.getUsername().equals(remoteUser)) {
				target = it;
				break;
			}
		}
		return target;
	}

	//Getters and Setters

	public static ArrayList<RemoteUser> getObjectUsersList() {
		return ObjectUsersList;
	}

	public MainWindow getMainWindow() {
		return this;
	}

	public static ArrayList<ChatWindow> getChatWindows() {
		return chatWindows;
	}

	public static void setUniqueNewUsername(boolean uniqueNewUsername) {
		MainWindow.uniqueNewUsername = uniqueNewUsername;
	}

	public static JComboBox<String> getStringUsersList() {
		return stringUsersList;
	}

	public static JTextArea getBroadArea() {
		return broadArea;
	}

	public static JFrame getMainFrame() {
		return mainFrame;
	}
	
	public static String getUsername() {
		return username;
	}

}
