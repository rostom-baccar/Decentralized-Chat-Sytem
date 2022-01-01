import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	final static int port = 5001;
	private static ServerSocket server_socket;
	private static Socket client_socket;
	public static void main(String[] args) throws IOException {


		//Sockets
		server_socket = new ServerSocket(port);
		System.out.println("Waiting for client connection");
		client_socket = server_socket.accept();
		System.out.println("Connected to client");
		//Sending 
		PrintWriter out = new PrintWriter(client_socket.getOutputStream(), true);

		//Receiving
		BufferedReader in = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
		String connection = in.readLine();
		try {
			while(connection!=null) {				
					System.out.println(connection + " has connected");
					out.println("Server: You are connected");
					connection = in.readLine();
			}
		}
		finally {
			//When we manually break off the loop
			out.close();
			in.close();
		}
	}


}


