package Interface;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import Model.ChatMessageType;
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
	JTextArea chatArea;



	/**
	 * Create the application.
	 */
	public ChatWindow(String username, String remoteUser, ObjectOutputStream out) {
		this.username=username;
		this.remoteUser=remoteUser;
		this.out=out;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		chatFrame = new JFrame(username+" - "+remoteUser);
		chatFrame.setBounds(100, 100, 326, 393);
		chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chatFrame.getContentPane().setLayout(null);
		
		JLabel privateChatLabel = new JLabel("[PRIVATE CHAT]  "+remoteUser);
		privateChatLabel.setBounds(20, 11, 300, 25);
		chatFrame.getContentPane().add(privateChatLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 35, 274, 266);
		chatFrame.getContentPane().add(scrollPane);
		
		chatArea = new JTextArea();
		scrollPane.setViewportView(chatArea);
		
		chatField = new JTextField();
		chatField.setBounds(20, 312, 189, 25);
		chatFrame.getContentPane().add(chatField);
		chatField.setColumns(10);
		
		JButton sendButton = new JButton("Send");
		sendButton.setBounds(219, 312, 75, 25);
		chatFrame.getContentPane().add(sendButton);
				
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
					chatArea.append("["+username+"]: "+message+"\n");

					try {
						out.writeObject(Message.buildMessage2(ChatMessageType.PrivateMessage,message,username,remoteUser));

					} catch (IOException e1) {e1.printStackTrace();}
				}
			}
		});
		
	}
	
	public static void main (String[] a) {
		new ChatWindow("Rostom", "Wissem", null);
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
}
