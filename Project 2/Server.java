import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	final static int port = 5001;
	private static ServerSocket server_socket;
	private static Socket clientSocket;
	private static ArrayList<ClientHandler> clients = new ArrayList<>();


	public static void main(String[] args) throws IOException {


		//Sockets
		server_socket = new ServerSocket(port);
		while(true) {
			System.out.println("[SERVER] Waiting for client connection");
			clientSocket = server_socket.accept();
			System.out.println("[SERVER] Connected to client");

			//Creation of the thread that's going to handle the client
			ClientHandler clientThread = new ClientHandler(clientSocket);
			clients.add(clientThread);
			clientThread.start();
		}
	}
}


