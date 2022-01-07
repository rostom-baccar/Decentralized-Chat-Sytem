package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JComboBox;

import Interface.ChatWindow;
import Interface.MainWindow;

//Thread for each client which listens constantly to what the server broadcasts
//we only need an input attribute since we won't be sending the server any messages with these threads
public class ServerResponseListener extends Thread{

	private BufferedReader in;
	private Socket clientSocket;
	private ArrayList<String> localClients = new ArrayList<>();
	private boolean firstWindow=true;
	private ChatWindow chatWindow = null;

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

				//Active Users refresh button
				if (serverResponse.equals("begin clients")) {
					System.out.println("[DEBUG] server:"+  serverResponse);
					String clientsResponse = in.readLine();
					while (!clientsResponse.equals("end clients")) {
						localClients.add(clientsResponse);
						MainWindow.UsersList.addItem(clientsResponse);
						clientsResponse=in.readLine();
					}
					System.out.println("[DEBUG] server: "+clientsResponse);
					for (int i=0; i<=localClients.size()-1; i++) {
						System.out.println("Local Client: "+localClients.get(i));
					}
				}
				//problème: cette liste sera dépendante de si on appuie sur
				//le bouton refresh ou non

				//localClients n'est pour l'instant pas utilisée

				//Broadcast
				if (serverResponse.contains("[BROADCAST]")) {
					MainWindow.broadArea.append(serverResponse+"\n");
				}
				if (serverResponse.contains("@")) {
					int spaceIndex=serverResponse.indexOf(" ");
					String remoteUser=serverResponse.substring(1,spaceIndex);
					if (firstWindow) {
						chatWindow = new ChatWindow(Client.getUsername(),remoteUser);
					}
					firstWindow=false;
					String message=serverResponse.substring(spaceIndex);
					chatWindow.chatArea.append("["+remoteUser+"] "+message+"\n");

				}



				System.out.println(serverResponse);
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
}