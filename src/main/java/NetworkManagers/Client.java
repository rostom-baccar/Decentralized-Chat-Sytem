package NetworkManagers;

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
import NetworkListeners.ServerResponseListener;

public class Client {

	private final static int port = 5001;
	private final static String Server_IP="127.0.0.1"; //Put Server IP here
	private static boolean uniqueUsername=false;
	private static String username=null;
	private static Message query;

	public Client(String username) {
		Client.username=username;
	}

	//Main throws exceptions because the functions that throw these exceptions rarely cause any problems
	//Adding a try-catch clause for each line would make the code very hard to read
	public static void main(String[] args) throws IOException, InterruptedException {

		//Creating socket client from port and the Server IP. Note: The server uses the same port. 
		Socket socket = new Socket(Server_IP, port);
		
		//Sending
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
		
		//Receiving: we use a thread because we need a while loop in order to receive information, which is a blocking process
		ServerResponseListener serverConnection = new ServerResponseListener(socket);
		serverConnection.start();

		//Login Window
		LoginWindow loginWindow = new LoginWindow();

		//waiting for user to type username in text field
		while(username==null) {
			username=LoginWindow.getUsername();
		}
		System.out.println("[Client] Username: "+username);
		

		out.writeObject(Message.buildMessage(ChatMessageType.Connect,username));

		Thread.sleep(100); //to give time for ServerConnection to set uniqueUsername to true if it's unique

		while(!uniqueUsername) {
			username=null;
			while(username==null) { 
				username=LoginWindow.getUsername();
			}
			System.out.println("[Client] Username: "+username);
			out.writeObject(Message.buildMessage(ChatMessageType.Connect,username));
		}

		//Login Window closes and Main Window opens if username is unique
		LoginWindow.getLoginFrame().setVisible(false);
//		JOptionPane.showMessageDialog(null,"You are connected");
		MainWindow mainWindow = new MainWindow(username);


		while(true) {
			
			while (query==null) {
				query=MainWindow.getQuery(); //Waiting for the user to send a query via the Main Window (through the buttons)
			}
			
			System.out.println("[Client] Query type: "+query.getType());
			System.out.println("[Client] Query content: "+query.getContent());
			System.out.println("[Client] Query argument1: "+query.getArgument1());
			System.out.println("[Client] Query argument2: "+query.getArgument2());
			System.out.println();
			
//			out.writeObject(query);
//			if (query.getType()==ChatMessageType.Disconnect) {
//				MainWindow.getMainFrame().setVisible(false);
//				JOptionPane.showMessageDialog(null,"You are disconnected");
//			}
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