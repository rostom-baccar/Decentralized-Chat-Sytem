package NetworkListeners;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Interface.ChatWindow;
import Interface.MainWindow;
import Model.Message;
import NetworkManagers.Client;

//Thread for each client which listens constantly to what the server broadcasts
//we only need an input attribute since we won't be sending the server any messages with these threads

public class ServerResponseListener extends Thread{

	private ObjectInputStream in;
	private Socket clientSocket;
	private ArrayList<String> localClients = new ArrayList<String>();
	private static String message=null;
	private static boolean conversationInitiator=true;

	public ServerResponseListener(Socket clientSocket) throws IOException {
		this.clientSocket=clientSocket;
		in = new ObjectInputStream(clientSocket.getInputStream());
	}

	public void run() {
		try {
			while(true) {

				Message serverResponse = (Message) in.readObject();
				switch(serverResponse.getType()) {
				
				case Notification:
					if (!serverResponse.getContent().equals("Username already taken, please choose another one")) {
						Client.setUniqueUsername(true);
					}
					
					if (!serverResponse.getContent().equals("Someone is already connected with this username. Please choose another one")) {
						MainWindow.setUniqueNewUsername(true);
					}
					
					JOptionPane.showMessageDialog(null,serverResponse.getContent());
					break;
					
				case UsersList:
					localClients.add(serverResponse.getContent());
					MainWindow.getUsersList().addItem(serverResponse.getContent());
					break;
					
					
				case BroadMessage:
					MainWindow.getBroadArea().append(serverResponse.getContent()+"\n");
					break;
					
				case Recipient:
					conversationInitiator=false;
					JOptionPane.showMessageDialog(null,serverResponse.getContent());
					break;
					
					
				case Initiator:
					JOptionPane.showMessageDialog(null,serverResponse.getContent());
					break;
					
					
				case UserInexistant	:
					JOptionPane.showMessageDialog(null,serverResponse.getContent());
					break;
					
				case PrivateMessage :
					message=serverResponse.getContent();
					message=null;
					
				default:
					break;
				}

				

//				if (serverResponse.contains("//")) {
//					MainWindow.getBroadArea().append(serverResponse+"\n");
//				}
				
			} 
		}
		catch (IOException e){e.printStackTrace();}
		catch (ClassNotFoundException e) {e.printStackTrace();}

		finally {
			try {
				in.close();
			} catch (IOException e){e.printStackTrace();}
		}
	}
	
	//Setters and Getters

	public static void setConversationInitiator(boolean conversationInitiator) {
		ServerResponseListener.conversationInitiator = conversationInitiator;
	}

	public static String getMessage() {
		return message;
	}

	public static void setMessage(String message) {
		ServerResponseListener.message = message;
	}

	public static boolean isConversationInitiator() {
		return conversationInitiator;
	}
}