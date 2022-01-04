package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

//each client will have its own client handler with which it can communicate (like a server instance)
//in order to have multiple client we need multiple threads handling each client
//otherwise we would have blocking functions that would not allow the program to finish

public class ClientHandler extends Thread {

	private BufferedReader in;
	private PrintWriter out;

	public String getClientUsername() {
		return clientUsername;
	}

	private Socket clientSocket;
	private ArrayList<ClientHandler> clients;
	private String clientUsername; 
	private boolean firstConnection=true;
	private boolean canBeAdded=false;

	public boolean getCanBeAdded() {
		return canBeAdded;
	}


	//Every handler of a certain client will have access to the other handlers of the other clients
	public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException{
		this.clientSocket=clientSocket;
		this.clients=clients;
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out = new PrintWriter(clientSocket.getOutputStream(),true);
	}

	public void run() {
		String request = null;
		try {
			request = in.readLine();
		} catch (IOException e1) {
//			System.out.println("6");
			e1.printStackTrace();}
		try {
			while(request!=null) {
				if (request.contains("@")) {
					out.println("Contains request");
					//request part
					int spaceIndex=request.indexOf(" ");
//					out.println("1");
					String remoteUser=request.substring(1,spaceIndex);
					out.println("remoteUser: "+remoteUser);
					String message=request.substring(spaceIndex);
					out.println(message);
					//					System.out.println(this.clientUsername+" wants to send "+remoteUser+" this message: "+message);
					ClientHandler remoteClientHandler=findThread(remoteUser);
//					out.println("4");
//					String test = this.clientUsername;
//					out.println("5");
//					out.println(test);
//					out.println("6");
//					out.println(message);
//					out.println("7");
					remoteClientHandler.out.println("[PRIVATE CHAT] |"+this.clientUsername+"| "+message);
//					out.println("8");
					request = in.readLine();
//					out.println("9");

				}
				else if (request.contains("broad")) {
					//out.println("Contains broad");
					int spaceIndex = request.indexOf(" ");
					if (spaceIndex!=-1) { //if it exists
						//System.out.println("Broadcasting "+request.substring(spaceIndex+1)+"...");
						broadcast(request.substring(spaceIndex+1),clientUsername);
						//System.out.println("Broadcast done");
						request = in.readLine();
					}
				}

				else if (firstConnection){ //first connection
					if (!unique(request)) {
						out.println("Username already taken, please choose another one");
						request = in.readLine();

					}

					else {
						this.canBeAdded=true;
						System.out.println(request + " just connected");
						out.println("You are connected");
						clientUsername=request; //we save it so that each client handler knows its primary client
						request = in.readLine();
						firstConnection=false;
//						out.println(client.size());

					}

				}
				else if (request.contains("disconnect")) {
					System.out.println(clientUsername + " just disconnected");
					broadcast(clientUsername+" disconnected");
					clients.remove(this);
					this.clientSocket.close();
					request = in.readLine();
				}
				else if (request.contains("active")) {
					for (ClientHandler client : clients) {
						out.println(client.clientUsername);
					}
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
				System.out.println("4");
				e.printStackTrace();}
		}

	}


	public void setCanBeAdded(boolean canBeAdded) {
		this.canBeAdded = canBeAdded;
	}


	public ArrayList<ClientHandler> getClients() {
		return this.clients;
	}

	private ClientHandler findThread(String remoteUser) {
		ClientHandler target = null;
		for (int i=0; i<=10; i++) {
			for (ClientHandler client : clients) {
				if (remoteUser.equals(client.clientUsername)){
					target=client;
				}
			}
		}
		return target;
	}

	//for disconnecting: custom message without the | | 
	private void broadcast(String message) {
		//we send a message to all the clientHandlers that are active
		for (ClientHandler client : clients) {
			client.out.println(message);
		}
	}

	private void broadcast(String message, String clientUsername) {
		//we send a message to all the clientHandlers that are active
		for (ClientHandler client : clients) {
			client.out.println("|"+clientUsername+"| "+message);
		}
	}

	private static boolean unique(String username) {
		boolean unique=true;
		ArrayList<ClientHandler> clients = Server.getClients();
		for (ClientHandler client : clients) {
			if ((client.clientUsername!=null) && (client.clientUsername.equals(username))) unique=false;
		}
		return unique;
	}

}