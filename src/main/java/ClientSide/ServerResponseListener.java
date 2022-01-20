package ClientSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Interface.ChatWindow;
import Interface.LoginWindow;
import Interface.MainWindow;
import Model.ChatMessageType;
import Model.Message;

//Thread for each client which listens constantly to what the server broadcasts
//we only need an input attribute since we won't be sending the server any messages with these threads

public class ServerResponseListener extends Thread{

	private Socket clientSocket;
	private static Message message=null;
	private static boolean conversationInitiator=true;

	public ServerResponseListener(Socket clientSocket) throws IOException {
		this.clientSocket=clientSocket;


	}

	public void run() {
		try {
			
			InputStream inputStream = clientSocket.getInputStream();
			ObjectInputStream in = new ObjectInputStream(inputStream);
			Message serverResponse = (Message) in.readObject();
			
			while(serverResponse!=null) {
				System.out.println("[ServerResponseListener] Query type: "+serverResponse.getType());
				System.out.println("[ServerResponseListener] Query content: "+serverResponse.getContent());
				System.out.println("[ServerResponseListener] Query argument1: "+serverResponse.getArgument1());
				System.out.println("[ServerResponseListener] Query argument2: "+serverResponse.getArgument2());
				System.out.println();
				switch( (ChatMessageType) serverResponse.getType()) {
				
				case Notification:
					if (serverResponse.getContent().equals("You are connected")) {
						Client.setUniqueUsername(true);
					}
					
//					if (!serverResponse.getContent().equals("Someone is already connected with this username. Please choose another one")) {
//						MainWindow.setUniqueNewUsername(true);
//					}
					else {
						JOptionPane.showMessageDialog(null,serverResponse.getContent());
					}
					break;
					
				case UsersList:
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
					message=serverResponse;
					message=null;
					
				case UsernameChange :
					MainWindow.setUniqueNewUsername(true);
					
				default:
					break;
				}
				serverResponse = (Message) in.readObject();
			} 
		}
		catch (IOException e){e.printStackTrace();}
		catch (ClassNotFoundException e) {e.printStackTrace();}

	}
	
	//Setters and Getters

	public static void setConversationInitiator(boolean conversationInitiator) {
		ServerResponseListener.conversationInitiator = conversationInitiator;
	}

	public static Message getMessage() {
		return message;
	}

	public static void setMessage(Message message) {
		ServerResponseListener.message = message;
	}

	public static boolean isConversationInitiator() {
		return conversationInitiator;
	}
}