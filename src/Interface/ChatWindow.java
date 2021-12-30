package Interface;


import java.awt.event.*;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import javax.swing.*;

import Controller.Controller;
import Network.Send;
import Network.TCP_Client;
import Network.TCP_Server;

public class ChatWindow extends JFrame implements ActionListener{

	JPanel mainPanel;
	JPanel centerPanel;
	JPanel bottomPanel;

	public static JTextField messageField;
	public static JTextArea convArea;
	JScrollPane scrollMessages;
	JButton sendButton;


	public ChatWindow() {

		mainPanel = new JPanel(null);

		centerPanel = new JPanel(null);
		centerPanel.setBounds(0, 0, 400, 400);

		bottomPanel = new JPanel(null);
		bottomPanel.setBounds(0, centerPanel.getHeight(), 400, 100);

		convArea = new JTextArea();
		scrollMessages = new JScrollPane(convArea);
		scrollMessages.setBounds(5, 5, centerPanel.getWidth() - 20, centerPanel.getHeight() - 20);
		centerPanel.add(scrollMessages);

		messageField = new JTextField();
		messageField.setBounds(10, 10, 250, 30);
		bottomPanel.add(messageField);

		sendButton = new JButton("SEND");
		sendButton.setBounds(messageField.getWidth() + 10, 10, 100, 30);
		sendButton.addActionListener(this);
		bottomPanel.add(sendButton);

		mainPanel.add(centerPanel);
		mainPanel.add(bottomPanel);

		this.add(mainPanel);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == sendButton){
			String message = messageField.getText();
			convArea.append("Client :" +message+"\n");
			
			TCP_Client.send(message);
			
			
		}
	}







}