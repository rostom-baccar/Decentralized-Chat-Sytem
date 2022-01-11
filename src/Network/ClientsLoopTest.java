package Network;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Debug thread that print the content of clients every 5 seconds
//Thread started by the Server, gets the list from Server class

public class ClientsLoopTest extends Thread implements ActionListener{

	private static int i=0;
	private static JFrame testFrame;
	private JButton toggleButton;
	private JPanel testPanel;

	public void run() {
		
    	testFrame=new JFrame ("TOGGLE");
        testPanel = new JPanel(new GridLayout(10,10));
		toggleButton = new JButton ("TOGGLE");
        toggleButton.addActionListener(this);
        toggleButton.setBounds (1,1,85, 23);
        testFrame.add(toggleButton);
        testFrame.getRootPane().setDefaultButton(toggleButton);
        testFrame.getContentPane().add (testPanel, BorderLayout.CENTER);
        testFrame.pack();
        testFrame.setSize(200,75);
        testFrame.setVisible (true);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		i++;
		System.out.println("Iteration "+i);
		for (ClientHandler client : Server.getClients()){
			System.out.println("[DEBUG] Clients: "+client.getClientUsername());
		}
		System.out.println();
		}
		
	
	}


