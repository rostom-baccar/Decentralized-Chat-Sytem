package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

//each client will have its own client handler with which it can communicate (like a server instance)
//in order to have multiple client we need multiple threads handling each client
//otherwise we would have blocking functions that would not allow the program to finish

public class ClientHandler extends Thread {

	private BufferedReader in;
	private PrintWriter out;
	private Socket clientSocket;
	private String clientUsername; 
	private boolean firstConnection=true;
	private boolean canBeAdded=false;
	private String ipAdress;


	//Every handler of a certain client will have access to the other handlers of the other clients
	public ClientHandler(Socket clientSocket) throws IOException{
		this.clientSocket=clientSocket;
		this.ipAdress=InetAddress.getLocalHost().getHostAddress();
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out = new PrintWriter(clientSocket.getOutputStream(),true);
	}

	public void run() {
		String request = null;
		try {
			request = in.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();}
		try {
			while(request!=null) {
				if (request.contains("@")) {
					int spaceIndex=request.indexOf(" ");
					String remoteUser=request.substring(1,spaceIndex);
					if (among(remoteUser)) {
						String message=request.substring(spaceIndex+1);
						ClientHandler remoteClientHandler=findThread(remoteUser);
						remoteClientHandler.out.println("@"+this.clientUsername+" "+message);
						//we do not send ourself the message to avoid opening an extra window
						//we simply append the message we send to the chat area
						request = in.readLine();

					}
					else {
						this.out.println("Sorry, this user is not connected");
						request = in.readLine();

					}
				}
				else if (request.contains("broad")) {
					int spaceIndex = request.indexOf(" ");
					if (spaceIndex!=-1) { //if it exists
						broadcast(request.substring(spaceIndex+1),clientUsername);
						request = in.readLine();
					}
				}
				else if (request.contains("*")) {
					int spaceIndex=request.indexOf(" ");
					String initiator = request.substring(1,spaceIndex);
					String remoteUser=request.substring(spaceIndex+1);
					ClientHandler remoteClientHandler=findThread(remoteUser);
					if (remoteClientHandler!=null) {
					remoteClientHandler.out.println(initiator+" wants to chat. Open a Chat Window with them!");
					}
					else {
					out.println("Sorry, the user you are trying to chat with is not connected!");
					}
					request = in.readLine();
				}
				else if (request.contains("$")) {
					int spaceIndex=request.indexOf(" ");
					String recipient = request.substring(1,spaceIndex);
					String remoteUser=request.substring(spaceIndex+1);
					ClientHandler remoteClientHandler=findThread(remoteUser);
					remoteClientHandler.out.println(recipient+" has opened a chat. You can now chat with them!");
					request = in.readLine();
				}
				else if (request.contains("#")) {
					int spaceIndex=request.indexOf(" ");
					String oldUsername=request.substring(1,spaceIndex);
					String newUsername=request.substring(spaceIndex+1);
					if (!unique(newUsername)) {
						out.println("Someone is already connected with this username. Please choose another one");
					}
					else {
						ClientHandler targetThread=findThread(oldUsername);
						targetThread.setClientUsername(newUsername);
						broadcast(oldUsername+" has changed their username to "+newUsername);
					}
					request = in.readLine();
				}

				else if (firstConnection){ //first connection
					if (!unique(request)) {
						out.println("Username already taken, please choose another one");
						request = in.readLine();
					}

					else {
						this.canBeAdded=true;
						out.println("You are connected");
						clientUsername=request; //we save it so that each client handler knows its primary client
						broadcast(clientUsername+" just connected");
						request = in.readLine();
						firstConnection=false;
					}

				}
				else if (request.contains("disconnect")) {
					broadcast(clientUsername+" disconnected");
					Server.getClients().remove(this);
					this.canBeAdded=false;
					this.clientSocket.close();
					request = in.readLine();
				}
				else if (request.contains("active")) {
					out.println("begin clients");
					for (ClientHandler client : Server.getClients()) {
						if (client!=this) { //we do not show the user's own nickname
							out.println(client.clientUsername);
						}
					}
					out.println("end clients");
					request = in.readLine();
				}
				else {
					out.println("Request unknown");
					request = in.readLine();

				}
			}
		} catch (IOException e) {} //to avoid errors when disconnecting
		finally {
			//When we manually break off the loop
			out.close();
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();}
		}

	}

	//Useful functions used above
	public static ClientHandler findThread(String remoteUser) {
		ClientHandler target = null;
		for (ClientHandler client : Server.getClients()) {
			if (remoteUser.equals(client.clientUsername)){
				target=client;
			}
		}
		return target;
	}

	//for changing username
	private void broadcast(String message) {
		//we send a message to all the clientHandlers that are active
		for (ClientHandler client : Server.getClients()) {
			client.out.println("//"+message+"//");
		}
	}

	private void broadcast(String message, String clientUsername) {
		//we send a message to all the clientHandlers that are active
		for (ClientHandler client : Server.getClients()) {
			client.out.println("[BROADCAST] |"+clientUsername+"| "+message);
		}
	}


	private static boolean unique(String username) {
		boolean unique=true;
		for (ClientHandler client : Server.getClients()) {
			if (client.clientUsername.equals(username)) {
				unique=false;
			}
		}
		return unique;
	}

	public static boolean among(String remoteUser) {
		boolean contain=false;
		for (ClientHandler client : Server.getClients()) {
			if (remoteUser.equals(client.clientUsername)){
				contain=true;
			}
		}
		return contain;
	}
	
	//Setters and Getters
	
	public String getClientUsername() {
		return clientUsername;
	}

	public void setClientUsername(String clientUsername) {
		this.clientUsername = clientUsername;
	}
	
	public boolean getCanBeAdded() {
		return canBeAdded;
	}
	
	public void setCanBeAdded(boolean canBeAdded) {
		this.canBeAdded = canBeAdded;
	}

}