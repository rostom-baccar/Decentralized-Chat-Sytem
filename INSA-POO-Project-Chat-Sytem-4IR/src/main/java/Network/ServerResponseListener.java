package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Interface.ChatWindow;
import Interface.MainWindow;

//Thread for each client which listens constantly to what the server broadcasts
//we only need an input attribute since we won't be sending the server any messages with these threads

public class ServerResponseListener extends Thread{

	private BufferedReader in;
	private Socket clientSocket;
	private ArrayList<String> localClients = new ArrayList<String>();
	private boolean firstWindow=true;
	private ChatWindow chatWindow = null;
	private static String message=null;
	private static boolean conversationInitiator=true;

	public ServerResponseListener(Socket clientSocket) throws IOException {
		this.clientSocket=clientSocket;
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

	public void run() {
		try {
			while(true) {

				String serverResponse = in.readLine();

				if (serverResponse==null) break;
				Thread.sleep(200); //to give time to the client handler to send its response
				
				if (!serverResponse.equals("Username already taken, please choose another one")) {
					Client.setUniqueUsername(true);
				}
				else {
					JOptionPane.showMessageDialog(null,serverResponse);
				}
				
				if (!serverResponse.equals("Someone is already connected with this username. Please choose another one")) {
					MainWindow.setUniqueNewUsername(true);
				}
				else {
					JOptionPane.showMessageDialog(null,serverResponse);
				}

				//Active Users refresh button
				if (serverResponse.equals("begin clients")) {
					String clientsResponse = in.readLine();
					while (!clientsResponse.equals("end clients")) {
						localClients.add(clientsResponse);
						MainWindow.getUsersList().addItem(clientsResponse);
						clientsResponse=in.readLine();
					}
				}

				//Broadcast
				if (serverResponse.contains("[BROADCAST]")) {
					MainWindow.getBroadArea().append(serverResponse+"\n");
				}

				if (serverResponse.contains(" wants to chat. Open a Chat Window with them!")) {
					conversationInitiator=false;
					JOptionPane.showMessageDialog(null,serverResponse);
				}

				if (serverResponse.equals("Sorry, the user you are trying to chat with is not connected!")) {
					JOptionPane.showMessageDialog(null,serverResponse);
				}
				
				if (serverResponse.contains(" has opened a chat. You can now chat with them!")) {
					JOptionPane.showMessageDialog(null,serverResponse);
				}
				if (serverResponse.contains("@")) {
					message=serverResponse;		
					message=null;
				}

				if (serverResponse.contains("//")) {
					MainWindow.getBroadArea().append(serverResponse+"\n");
				}
			} 
		}catch (IOException e){e.printStackTrace();} catch (InterruptedException e) {
			e.printStackTrace();
		}

		finally {
			try {
				in.close();
			} catch (IOException e){e.printStackTrace();
			}
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