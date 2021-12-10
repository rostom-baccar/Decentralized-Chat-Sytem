
package Interface;

/**
 * CelsiusConverter.java is a 1.4 application that 
 * demonstrates the use of JButton, JTextField and
 * JLabel.  It requires no other files.
 */

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.*;

import Controller.Controller;
import Controller.Controller_Interface;
import Model.LocalUser;

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
        //Create and set up the window.
        loginFrame = new JFrame("Authentification");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(new Dimension(1200, 400));

        //Create and set up the panel.
        loginPanel = new JPanel(new GridLayout(7, 7));

        //Add the widgets.
        addComponents();

        //Set the default button.
        loginFrame.getRootPane().setDefaultButton(loginButton);

        //Add the panel to the window.
        loginFrame.getContentPane().add(loginPanel, BorderLayout.CENTER);

        //Display the window.
        loginFrame.pack();
        loginFrame.setVisible(true);
    }

    /**
     * Create and add the widgets.
     */
    private void addComponents() {
        //Create widgets.
        pseudoField = new JTextField(20);
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
        //Parse degrees Celsius as a double and convert to Fahrenheit.
        String pseudo = pseudoField.getText();
        Controller cont1 = null;
		try {
			cont1 = new Controller(user1);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cont1.test();
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

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     * @throws UnknownHostException 
     */
    //public static Authentification createAndShowGUI(Controller_Interface controller) throws UnknownHostException {
    	public static void createAndShowGUI() throws UnknownHostException {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);
        LocalUser user1 = new LocalUser("Default",false,null,0,0,0);
        Controller_Interface controller = new Controller_Interface();
        
        Authentification auth = null;
        auth = new Authentification(controller);
        
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
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