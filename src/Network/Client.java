package Network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

	private static String username;

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
		ServerConnection serverConnection = new ServerConnection(socket);

		//Sending
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

		//Receiving
		serverConnection.start();

		System.out.println("Type username");
		Scanner s = new Scanner(System.in);
		username=s.nextLine();
		out.println(username);
		Thread.sleep(400); //to give time for ServerConnection to set uniqueUsername to true if it's unique
		System.out.println(uniqueUsername);
		while(!uniqueUsername) {
			username=s.nextLine();
			out.println(username);
		}

		System.out.println("Type @ + name of remote user + message to send a message to a remote user privately");
		System.out.println("Type broad + the message you want to broadcast to all active clients");
		System.out.println("Type disconnect to disconnect");
		System.out.println("Type active to see all active users");

		String query=s.nextLine();
		while(query!=null) {
			out.println(query);
			query=s.nextLine();
		}
	}

	public static void setUniqueUsername(boolean uniqueUsername) {
		Client.uniqueUsername = uniqueUsername;
	}

	public static String getUsername() {
		return username;
	}


}
