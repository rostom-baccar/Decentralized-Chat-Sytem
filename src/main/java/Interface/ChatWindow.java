package Interface;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import ClientSide.Client;
import Model.ChatMessageType;
import Model.LocalIpAddress;
import Model.Message;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class ChatWindow {

	private JFrame chatFrame;
	private ObjectOutputStream out;
	private JTextField chatField;
	private String username;
	private String remoteUser;
	private String ipAddress;
	private JTextArea chatArea;
	private JLabel privateChatLabel;
	private JScrollPane scrollPane;
	private JButton sendButton;

	public ChatWindow(String username, String remoteUser, String ipAddress, ObjectOutputStream out) {
		//	public ChatWindow(String username, String remoteUser, ObjectOutputStream out) {

		this.username=username;
		this.remoteUser=remoteUser;
		this.out=out;
		this.ipAddress=ipAddress;
		initialize();
	}


	private void initialize() {
		chatFrame = new JFrame(username+" - "+remoteUser);
		chatFrame.setBounds(100, 100, 326, 393);
		chatFrame.getContentPane().setLayout(null);

		privateChatLabel = new JLabel("[PRIVATE CHAT]  "+remoteUser);
		privateChatLabel.setBounds(20, 11, 300, 25);
		chatFrame.getContentPane().add(privateChatLabel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 35, 274, 266);
		chatFrame.getContentPane().add(scrollPane);

		chatArea = new JTextArea();
		chatArea.setEditable(false);
		scrollPane.setViewportView(chatArea);

		chatField = new JTextField();
		chatField.setBounds(20, 312, 189, 25);
		chatFrame.getContentPane().add(chatField);
		chatField.setColumns(10);

		sendButton = new JButton("Send");
		sendButton.setBounds(219, 312, 75, 25);
		chatFrame.getContentPane().add(sendButton);
		chatFrame.getRootPane().setDefaultButton(sendButton);


		chatFrame.setVisible(true);

		chatFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainWindow.getChatWindows().remove(ChatWindow.this);
			}
		});

		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == sendButton) { 
					String message = chatField.getText();
					
					try {
						String RemoteipAddress = ipAddress;
						String LocalipAddress = LocalIpAddress.getLocalAddress().getHostAddress();			
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
						LocalDateTime now = LocalDateTime.now();  
						String tstamp = dtf.format(now);  
						

						int num = Client.getClientdb().insertRow(LocalipAddress, RemoteipAddress, message, tstamp);
						
						chatArea.append("                                                     "+tstamp.substring(0,16)+"\n");
						chatArea.append("["+username+"]: "+message+"\n");
					} catch (Exception e2) {e2.printStackTrace();}
					
					

					try {
						out.writeObject(Message.buildMessage2(ChatMessageType.PrivateMessage,message,username,remoteUser));
					} catch (IOException e1) {e1.printStackTrace();}
				}
				chatField.setText("");
			}
		});


	}

	public static void main (String[] a) {
		new ChatWindow("Rostom", "Wissem", null,null);
		//		new ChatWindow("Rostom", "Wissem", null);

	}

	//Setters and Getters

	public JTextArea getChatArea() {
		return chatArea;
	}

	public String getUsername() {
		return username;
	}

	public String getRemoteUser() {
		return remoteUser;
	}

	public void setChatArea(JTextArea chatArea) {
		this.chatArea = chatArea;
	}

	public ChatWindow getChatWindow() {
		return this;
	}

	public String getIpAddress() {
		return ipAddress;
	}
	
	public void setRemoteUsername(String remoteUser) {
		this.remoteUser = remoteUser;
	}


	public void setPrivateChatLabel(String newUsername) {
		JLabel newChatLabel = new JLabel("[PRIVATE CHAT]  "+newUsername);
		this.privateChatLabel = newChatLabel;
	}

}
