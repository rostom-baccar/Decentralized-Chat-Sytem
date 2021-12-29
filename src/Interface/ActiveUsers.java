
package Interface;

import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

import Controller.Controller;
import Network.Send;
import Network.TCP_Client;
import Network.TCP_Server;

public class ActiveUsers extends JFrame implements ActionListener {
    final static int START_INDEX = 0;
    public static boolean click = false;
    JPanel mainPanel;
    JPanel selectPanel;
    JComboBox<String> UsersList = null;
    JButton chatButton;

    public ActiveUsers() {
    	
        selectPanel = new JPanel();
        addWidgets();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(selectPanel);
        
		chatButton = new JButton("CHAT");
		chatButton.setBounds(10, 10, 100, 30);
		chatButton.addActionListener(this);
		mainPanel.add(chatButton);
		
		this.add(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		this.setVisible(true);
    }


    private void addWidgets() {

    	String[] init = {};
    	UsersList = new JComboBox<String>(init);
    	
    	//use addItem to add active user to the list
    	//UDP
        UsersList.addItem("192.168.1.15");
        UsersList.addItem("192.168.1.6");
        UsersList.addItem("192.168.1.22");
        UsersList.addItem("192.168.1.255");
        
        UsersList.setSelectedIndex(START_INDEX);

        selectPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Select user"), 
            BorderFactory.createEmptyBorder(10,10,10,10)));
        selectPanel.add(UsersList);
        UsersList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        if ("comboBoxChanged".equals(event.getActionCommand())) {
        	String s=String.valueOf(UsersList.getSelectedItem());
        }
        if(event.getSource() == chatButton) {
        	click=true;
        	String s=String.valueOf(UsersList.getSelectedItem());
	        ChatWindow window = null;
	        window = new ChatWindow();
	        
			Socket client_socket = null;
			try {
				client_socket = new Socket(s,Controller.TCPport_local);}
			catch(UnknownHostException e) {e.printStackTrace();
			}catch(IOException e){e.printStackTrace();}
			try {
				PrintWriter out = new PrintWriter(client_socket.getOutputStream());
			} catch(IOException e){e.printStackTrace();}
			
	        
	        //remarque importante:
	        //faut-il se connecter à tout le monde ?
	        //pour être toujours pret à recevoir des msg de la part de 
	        //tout le monde
//	        String message=null;
//			TCP_Server t1 = new TCP_Server(Controller.TCPport_local);
//			t1.start();
//			TCP_Client t2 = new TCP_Client(s,Controller.TCPport_local,message);
//			t2.start();
//	        TCP_Client.connect(s,Controller.TCPport_local);
			click=false;
        }
    }

    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        ActiveUsers window = new ActiveUsers();

        JFrame mainFrame = new JFrame("Active Users");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        mainFrame.setContentPane(window.mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

}