package ServerSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	final static int port = 5001;
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();


	public static void main(String[] args) throws InterruptedException {
		
		//Sockets
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		while(true) {
			
			//Server accepts all connections 
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}

			//Creation of the thread that's going to handle the client
			ClientHandler clientThread = null;
			UsernameHandler usernameThread = null;
			try {
				clientThread = new ClientHandler(clientSocket);
			} catch (IOException e) {
				e.printStackTrace();
			}
			clientThread.start();

			//thread that will add the thread to clients as soon as its
			//username will be unique (while loop is a blocking process)
			usernameThread = new UsernameHandler(clientThread);
			usernameThread.start();
			
		}
	}
	
	//Setters and Getters

	public static ArrayList<ClientHandler> getClients(){
		return clients;
	}
}