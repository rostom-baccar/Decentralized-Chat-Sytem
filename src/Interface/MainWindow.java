package Interface;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.*;
import Controller.Controller_Interface;
import Tests.Client;
import Tests.Server;
import Tests.TCP_client;
import Tests.TCP_server;

public class MainWindow extends JFrame implements ActionListener{

	private int width = 800;
	private int height = 600;
	private Controller_Interface controller;

	//JFrame mainFrame;
	JPanel mainPanel;
	JPanel centerPanel;
	JPanel bottomPanel;

	JTextField messageField;
	public static JTextArea convArea;
	JScrollPane scrollMessages;
	JButton sendButton;


	public MainWindow(Controller_Interface controller) {

		this.controller=controller;
		mainPanel = new JPanel(null);

		centerPanel = new JPanel(null);
		centerPanel.setBounds(0, 0, width, height - 100);

		bottomPanel = new JPanel(null);
		bottomPanel.setBounds(0, centerPanel.getHeight(), width, 100);

		convArea = new JTextArea();
		scrollMessages = new JScrollPane(convArea);
		scrollMessages.setBounds(5, 5, centerPanel.getWidth() - 20, centerPanel.getHeight() - 20);
		centerPanel.add(scrollMessages);

		messageField = new JTextField();
		messageField.setBounds(10, 10, 450, 30);
		bottomPanel.add(messageField);

		//mainFrame.getRootPane().setDefaultButton(sendButton);
		sendButton = new JButton("SEND");
		sendButton.setBounds(messageField.getWidth() + 10, 10, 100, 30);
		sendButton.addActionListener(this);
		bottomPanel.add(sendButton);

		mainPanel.add(centerPanel);
		mainPanel.add(bottomPanel);

		this.add(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == sendButton){
			String message = messageField.getText();
			convArea.append("Client :" +message+"\n");
			Client.send(message);
		}
	}


	public static void createAndShowGUI() throws UnknownHostException {

		JFrame.setDefaultLookAndFeelDecorated(true);
		Controller_Interface controller = new Controller_Interface();
		MainWindow window = null;
		window = new MainWindow(controller);
		
	}





}