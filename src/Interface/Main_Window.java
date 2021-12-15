
package Interface;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.*;

import Controller.Controller;
import Controller.Controller_Interface;
import Model.LocalUser;
import Tests.TCP_client;
import Tests.TCP_server;

public class Main_Window implements ActionListener {
	private Controller_Interface controller;

    JFrame mainFrame;
    JPanel mainPanel;
    JTextField messageField;
	JTextArea convArea;
    JLabel messageLabel;
    JButton sendButton;

    public Main_Window(Controller_Interface controller) {
    	this.controller=controller;


        mainFrame = new JFrame("Main Window");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(2000, 500));
        mainPanel = new JPanel(new GridLayout(10,10));

        addComponents();

        mainFrame.getRootPane().setDefaultButton(sendButton);
        mainFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    
    private void addComponents() {

        messageField = new JTextField(40);
        convArea = new JTextArea(20, 20);
        messageLabel = new JLabel("Message", SwingConstants.LEFT);
        sendButton = new JButton("SEND");
        sendButton.addActionListener(this);

        convArea.setEditable(false);
        mainPanel.add(convArea);
        mainPanel.add(messageField);
        mainPanel.add(messageLabel);
        mainPanel.add(sendButton);
        
        messageLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    public void actionPerformed(ActionEvent event) {

        String message = messageField.getText()+"\r\n";
        convArea.setText(message+"\r\n");
        TCP_client t1;
		TCP_server t2;
		t1 = new TCP_client(message,"10.1.5.148");
		t2 = new TCP_server();
		t1.start();
		t2.start();
        
    }

    	public static void createAndShowGUI() throws UnknownHostException {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);
        //System.out.println(user1);
        Controller_Interface controller = new Controller_Interface();
        
        Main_Window window = null;
        window = new Main_Window(controller);
        
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                	//LocalUser user1 = new LocalUser("Default",false,null,0,0,0);
                	//Controller_Interface controller = new Controller_Interface();
                	//createAndShowGUI(controller);
					createAndShowGUI();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
    
}