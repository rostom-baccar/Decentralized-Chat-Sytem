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
	public Client(String username) {
		this.username=username;
	}

	InetAddress myIP = null;


	private final static int port = 5001;
	private final static String Server_IP="127.0.0.1";
	private static boolean uniqueUsername=false;

	public static void main(String[] args) throws IOException, InterruptedException {

		//Socket
		Socket socket = new Socket(Server_IP, port);
		ServerResponseListener serverConnection = new ServerResponseListener(socket);

		//Sending
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

		//Receiving
		serverConnection.start();
		LoginWindow loginWindow = new LoginWindow();
		System.out.println("Type username");
		//		Scanner s = new Scanner(System.in);
		//		username=s.nextLine();
		while(username==null) { //waiting for user to type username in text field
			username=LoginWindow.username;
		}
		System.out.println("[DEBUG] client "+username);
		out.println(username);
		Thread.sleep(400); //to give time for ServerConnection to set uniqueUsername to true if it's unique
		while(!uniqueUsername) {
			//			username=s.nextLine();
			System.out.println("[DEBUG] username not unique");
			username=null;
			while(username==null) { 
				username=LoginWindow.username;
			}
			out.println(username);
		}
		LoginWindow.loginFrame.setVisible(false);
		JOptionPane.showMessageDialog(null,"You are connected");
		MainWindow mainWindow = new MainWindow(username);
		System.out.println("Type @ + name of remote user + message to send a message to a remote user privately");
		System.out.println("Type broad + the message you want to broadcast to all active clients");
		System.out.println("Type disconnect to disconnect");
		System.out.println("Type active to see all active users");

		//		Scanner s = new Scanner(System.in);
		//		String query=s.nextLine();

		while(true) {
			Thread.sleep(1000);
			System.out.println("[DEBUG] WAITING FOR QUERY");
			System.out.println("[DEBUG] "+query);
			while (query==null) {
				query=MainWindow.query;
			}
			System.out.println("[DEBUG] query recieved: "+query);

			//		}
			//		while(query!=null) {
			//			out.println(query);
			//			query=s.nextLine();
			//		}


			out.println(query);
			if (query.equals("disconnect")) {
				MainWindow.mainFrame.setVisible(false);
				JOptionPane.showMessageDialog(null,"You are disconnected");
			}
			query=null;
		}
	}


	public static void setUniqueUsername(boolean uniqueUsername) {
		Client.uniqueUsername = uniqueUsername;
	}

	public static String getUsername() {
		return username;
	}


}