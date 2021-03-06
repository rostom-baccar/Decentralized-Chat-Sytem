package ClientSide;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import Database.Database;
import Interface.LoginWindow;
import Interface.MainWindow;
import Interface.ServerIPAddressWindow;

public class Client {

	private final static int port = 5001;
	private static String Server_IP = null; //Put Server IP here
	private static boolean uniqueUsername=false;
	private static String username=null;
	private static Socket socket;
	private static Database clientdb;

	public Client(String username) {
		Client.username=username;
	}

	//Main throws exceptions because the functions that throw these exceptions rarely cause any problems
	//Adding a try-catch clause for each line would make the code very hard to read
	public static void main(String[] args) throws IOException, InterruptedException {
		
		new ServerIPAddressWindow();
		while (Server_IP == null) {
			Server_IP=ServerIPAddressWindow.getServerIP();
		}
		
		//Creating socket client from port and the Server IP. Note: The server uses the same port. 
		socket = new Socket(Server_IP, port);

		//Sending
		OutputStream outputStream = socket.getOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(outputStream);
		
		//Receiving
		InputStream inputStream = socket.getInputStream();
		ObjectInputStream in = new ObjectInputStream(inputStream);

		//Receiving: we use a thread because we need a while loop in order to receive information
		ServerResponseListener serverConnection = new ServerResponseListener(in);
		serverConnection.start();
		
		//Login Window
		new LoginWindow(out);

		while (!uniqueUsername) {Thread.sleep(1);}
		username=LoginWindow.getUsername();
		LoginWindow.getLoginFrame().setVisible(false);
		
		//MainWindow
		new MainWindow(username,out);
		
		//Database
		clientdb = new Database ();
	}

	//Setters and Getters

	public static void setUniqueUsername(boolean uniqueUsername) {
		Client.uniqueUsername = uniqueUsername;
	}

	public static String getUsername() {
		return username;
	}

	public static Socket getSocket() {
		return socket;
	}
	public static Database getClientdb() {
		return clientdb;
	}

	public static void setServer_IP(String server_IP) {
		Server_IP = server_IP;
	}

}