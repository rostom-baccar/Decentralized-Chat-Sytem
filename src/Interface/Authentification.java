package Interface ;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.*;

import Controller.Controller;
import Controller.Controller_Interface;
import Model.LocalUser;
import Tests.TCP_server;

public class Authentification implements ActionListener {
	private Controller_Interface controller;
	private LocalUser user1;
    JFrame loginFrame;
    JPanel loginPanel;
    JTextField pseudoField;
    JLabel pseudoLabel;
    JButton loginButton;

    public Authentification(Controller_Interface controller) {
    	this.controller=controller;

        loginFrame = new JFrame("Authentification");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(new Dimension(2000, 500));
        loginPanel = new JPanel(new GridLayout(10,10));

        addComponents();

        loginFrame.getRootPane().setDefaultButton(loginButton);
        loginFrame.getContentPane().add(loginPanel, BorderLayout.CENTER);
        loginFrame.pack();
        loginFrame.setVisible(true);
    }

    
    private void addComponents() {

        pseudoField = new JTextField(40);
        pseudoLabel = new JLabel("Pseudo", SwingConstants.LEFT);
        loginButton = new JButton("Login");

        //Listen to events from the Convert button.
        loginButton.addActionListener(this);

        //Add the widgets to the container.
        loginPanel.add(pseudoField);
        loginPanel.add(pseudoLabel);
        loginPanel.add(loginButton);
        
        pseudoLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    public void actionPerformed(ActionEvent event) {

        String pseudo = pseudoField.getText();
        Controller cont1 = null;
		try {
			user1 = new LocalUser("Default",false,null,0,0,0);
			cont1 = new Controller(user1);
			cont1.local_user.setPseudo(pseudo);
			System.out.println(cont1.local_user);
			loginFrame.setVisible(false);
			
	        Main_Window window = null;
	        window = new Main_Window(controller);
	        //TCP_server.server();
	        
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		//user1.setPseudo(pseudo);
		//System.out.println(user1);
		/*
        try {
        	cont1.pseudoValidity(pseudo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
        //System.out.println(pseudo);
    }

    	public static void createAndShowGUI() throws UnknownHostException {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);
        LocalUser user1 = new LocalUser("Default",false,null,0,0,0);
        //System.out.println(user1);
        Controller_Interface controller = new Controller_Interface();
        
        Authentification auth = null;
        auth = new Authentification(controller);
        
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