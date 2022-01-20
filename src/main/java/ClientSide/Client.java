package ClientSide;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

import Interface.LoginWindow;
import Interface.MainWindow;
import Model.ChatMessageType;
import Model.Message;

public class Client {

	private final static int port = 5001;
	private final static String Server_IP="127.0.0.1"; //Put Server IP here
	private static boolean uniqueUsername=false;
	private static String username=null;
	private static Message query;
	private static Socket socket;

	public Client(String username) {
		Client.username=username;
	}

	//Main throws exceptions because the functions that throw these exceptions rarely cause any problems
	//Adding a try-catch clause for each line would make the code very hard to read
	public static void main(String[] args) throws IOException, InterruptedException {

		//Creating socket client from port and the Server IP. Note: The server uses the same port. 
		socket = new Socket(Server_IP, port);

		//Sending
		OutputStream outputStream = socket.getOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(outputStream);

		//Receiving: we use a thread because we need a while loop in order to receive information, which is a blocking process
		ServerResponseListener serverConnection = new ServerResponseListener(socket);
		serverConnection.start();

		//Login Window
		LoginWindow loginWindow = new LoginWindow(out);
		
		System.out.println("[Client] Checking if username is unique");
		while (!uniqueUsername) {Thread.sleep(1);}
		username=LoginWindow.getUsername();
		System.out.println("[Client] Username: "+username);
		LoginWindow.getLoginFrame().setVisible(false);
		System.out.println("[Client] Username is unique");
		MainWindow mainWindow = new MainWindow(username,out);
		

		while(true) {
//
//			while (query==null) {
//				query=MainWindow.getQuery(); //Waiting for the user to send a query via the Main Window (through the buttons)
//			}
//
//			System.out.println("[Client] Query type: "+query.getType());
//			System.out.println("[Client] Query content: "+query.getContent());
//			System.out.println("[Client] Query argument1: "+query.getArgument1());
//			System.out.println("[Client] Query argument2: "+query.getArgument2());
//			System.out.println();
//
//			out.writeObject(query);
//			if (query.getType()==ChatMessageType.Disconnect) {
//				MainWindow.getMainFrame().setVisible(false);
//				JOptionPane.showMessageDialog(null,"You are disconnected");
//			}
//			query=null;
		}
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
}