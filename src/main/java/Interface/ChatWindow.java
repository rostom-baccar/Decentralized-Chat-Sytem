package Interface;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import javax.swing.*;
import Model.ChatMessageType;
import Model.Message;

public class ChatWindow extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton sendButton;
	private JTextField chatField;
	private JTextArea chatArea;
	private JFrame chatFrame;
	private JPanel chatPanel;
	private ObjectOutputStream out;

	private String username;
	private String remoteUser;

	public ChatWindow(String username, String remoteUser, ObjectOutputStream out) {

		this.username=username;
		this.remoteUser=remoteUser;
		this.out=out;

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

//		chatFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		chatFrame.setSize(new Dimension(2000, 500));
		chatFrame.getContentPane().add (chatPanel, BorderLayout.CENTER);
		chatFrame.pack();
		chatFrame.setSize(370,290);
		chatFrame.setVisible (true);

		chatFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainWindow.getChatWindows().remove(ChatWindow.this);
			}
		});
	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sendButton) {
			String message = chatField.getText();
			chatArea.append("["+username+"]: "+message+"\n");

			try {
				out.writeObject(Message.buildMessage2(ChatMessageType.PrivateMessage,message,username,remoteUser));

			} catch (IOException e1) {e1.printStackTrace();}
		}
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