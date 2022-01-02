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
	private Socket clientSocket;
	private ArrayList<ClientHandler> clients;
	private String clientUsername; 
	private boolean firstConnection=true;

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
			System.out.println("6");
			e1.printStackTrace();}
		try {
			while(request!=null) {
				if (request.contains("request")) {
//					out.println("Contains request");
					//request part

				}
				else if (request.contains("broad")) {
//					out.println("Contains broad");
					int spaceIndex = request.indexOf(" ");
					if (spaceIndex!=-1) { //if it exists
						//						System.out.println("Broadcasting "+request.substring(spaceIndex+1)+"...");
						broadcast(request.substring(spaceIndex+1),clientUsername);
						//						System.out.println("Broadcast done");
						request = in.readLine();
					}
				}

				else if (firstConnection){ //first connection
					System.out.println(request + " just connected");
					out.println("You are connected");
					clientUsername=request; //we save it so that each client handler knows its primary client
					request = in.readLine();
					firstConnection=false;
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


}