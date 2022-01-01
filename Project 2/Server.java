import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	final static int port = 5001;
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static ArrayList<ClientHandler> clients = new ArrayList<>();


	public static void main(String[] args) throws IOException {


		//Sockets
		serverSocket = new ServerSocket(port);
		while(true) {
//			System.out.println("[SERVER] Waiting for client connection");
			clientSocket = serverSocket.accept();
//			System.out.println("[SERVER] Connected to client");

			//Creation of the thread that's going to handle the client
			ClientHandler clientThread = new ClientHandler(clientSocket,clients);
			clients.add(clientThread);
			clientThread.start();
		}
	}
}


