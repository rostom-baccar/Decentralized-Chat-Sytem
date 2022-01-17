package Network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Interface.LoginWindow;
import Interface.MainWindow;

public class Client {

	private static String username=null;
	private static String query;
	private final static int port = 5001;
	private final static String Server_IP="127.0.0.1"; //Put Server IP here
	private static boolean uniqueUsername=false;

	public Client(String username) {
		Client.username=username;
	}


	public static void main(String[] args) throws IOException, InterruptedException {

		//Creating socket client from port and the Server IP. Note: The server uses the same port. 
		//The whole app runs on one port only
		Socket socket = new Socket(Server_IP, port);

		//Sending
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

		//Receiving: we use a thread because we need a while loop in order to recieve information, which is a blocking process
		ServerResponseListener serverConnection = new ServerResponseListener(socket);
		serverConnection.start();

		
		//Login Window
		LoginWindow loginWindow = new LoginWindow();

		while(username==null) { //waiting for user to type username in text field
			username=LoginWindow.getUsername();
		}
		out.println(username);
		Thread.sleep(400); //to give time for ServerConnection to set uniqueUsername to true if it's unique

		while(!uniqueUsername) {
			username=null;
			while(username==null) { 
				username=LoginWindow.getUsername();
			}
			out.println(username);
		}

		//Login Window closes and Main Window opens if username is unique
		LoginWindow.getLoginFrame().setVisible(false);
		JOptionPane.showMessageDialog(null,"You are connected");
		MainWindow mainWindow = new MainWindow(username);


		while(true) {
			Thread.sleep(1000);
			while (query==null) {
				query=MainWindow.getQuery(); //Waiting for the user to send a query via the Main Window (through the buttons)
			}
			out.println(query);

			if (query.equals("disconnect")) {
				MainWindow.getMainFrame().setVisible(false);
				JOptionPane.showMessageDialog(null,"You are disconnected");
			}
			query=null;
		}
	}


	//Setters and Getters
	public static void setUniqueUsername(boolean uniqueUsername) {
		Client.uniqueUsername = uniqueUsername;
	}

	public static String getUsername() {
		return username;
	}

}