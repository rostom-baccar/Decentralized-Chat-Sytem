package Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	final static int port = 5001;
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static ArrayList<ClientHandler> clients = new ArrayList<>();


	public static void main(String[] args) {


		//Sockets
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("1");
			e.printStackTrace();
		}
		while(true) {
//			System.out.println("[SERVER] Waiting for client connection");
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.out.println("2");
				e.printStackTrace();
			}
//			System.out.println("[SERVER] Connected to client");

			//Creation of the thread that's going to handle the client
			ClientHandler clientThread = null;
			try {
				clientThread = new ClientHandler(clientSocket,clients);
			} catch (IOException e) {
				System.out.println("3");
				e.printStackTrace();
			}
			clients.add(clientThread);
			clientThread.start();
		}
	}
}
