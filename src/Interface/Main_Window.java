package Interface;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;
import javax.swing.*;
import Controller.Controller_Interface;
import Tests.TCP_client;
import Tests.TCP_server;

public class Main_Window extends JFrame implements ActionListener{
	
    private int width = 800;
    private int height = 600;
	private Controller_Interface controller;

    //JFrame mainFrame;
    JPanel mainPanel;
    JPanel centerPanel;
    JPanel bottomPanel;
    
    JTextField messageField;
	JTextArea convArea;
	JScrollPane scrollMessages;
    //JLabel messageLabel;
    JButton sendButton;
    

    public Main_Window(Controller_Interface controller) {
    	this.controller=controller;

/*
        mainFrame = new JFrame("Main Window");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(2000, 500));
        */
    	
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
        
        //mainFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        //mainFrame.pack();
        //mainFrame.setVisible(true);
    }

    

    public void actionPerformed(ActionEvent event) {
    	if(event.getSource() == sendButton){
        String message = messageField.getText();
        convArea.append(message+"\n");
        /*
        TCP_client t1;
		TCP_server t2;
		t1 = new TCP_client(message,"10.1.5.148");
		t2 = new TCP_server();
		t1.start();
		t2.start();
		*/
    	}
    }

    	public static void createAndShowGUI() throws UnknownHostException {

        JFrame.setDefaultLookAndFeelDecorated(true);
        Controller_Interface controller = new Controller_Interface();
        Main_Window window = null;
        window = new Main_Window(controller);
        
    }

    
}