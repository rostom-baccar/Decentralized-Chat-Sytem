package Interface ;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.*;

import Controller.Controller;
import Controller.Controller_Interface;
import Model.LocalUser;
import Network.TCP_Client;
import Network.TCP_Server;
import Tests.TCP_server;

public class Authentification extends JFrame implements ActionListener {
	private Controller_Interface controller;
	private LocalUser user1;
    JFrame loginFrame;
    JPanel loginPanel;
    JTextField pseudoField;
    JLabel pseudoLabel;
    JButton loginButton;

    public Authentification() {

        loginFrame = new JFrame("Authentification");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(new Dimension(2000, 500));
        loginPanel = new JPanel(new GridLayout(10,10));

        addComponents();

        loginFrame.getRootPane().setDefaultButton(loginButton);
        loginFrame.getContentPane().add(loginPanel, BorderLayout.CENTER);
        loginFrame.pack();
        loginFrame.setSize(300,300);
        loginFrame.setVisible(true);
        

    }

    
    private void addComponents() {

        pseudoField = new JTextField(40);
        pseudoLabel = new JLabel("Pseudo", SwingConstants.LEFT);
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        loginPanel.add(pseudoField);
        loginPanel.add(pseudoLabel);
        loginPanel.add(loginButton);
        
        pseudoLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    public void actionPerformed(ActionEvent event) {

        String pseudo = pseudoField.getText();
        Controller cont1 = null;
		//			user1 = new LocalUser("Default",false,null,0,0,0);
		//			cont1 = new Controller(user1);
		//			cont1.local_user.setPseudo(pseudo);
		//			System.out.println(cont1.local_user);
					loginFrame.setVisible(false);
					
					ActiveUsers window = new ActiveUsers();

    }

    
}