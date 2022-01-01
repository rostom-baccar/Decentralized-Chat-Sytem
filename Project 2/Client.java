import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private static String username;

	public Client(String username) {
		this.username=username;
	}


	private final static int port = 5001;
	private final static String Server_IP="127.0.0.1";

	public static void main(String[] args) throws IOException {

		//Socket
		Socket socket = new Socket(Server_IP, port);
		ServerConnection serverConnection = new ServerConnection(socket);

		//Sending
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

		//Receiving
		serverConnection.start();
		
		System.out.println("Type username");
		Scanner s = new Scanner(System.in);
		username=s.nextLine();
		out.println(username);

		System.out.println("Type request + the name of the client you want to connect with");
		System.out.println("Type broad + the message you want to broadcast to all active clients");

		String query=s.nextLine();
		while(query!=null) {
			out.println(query);
			query=s.nextLine();
		}

		socket.close();

	}

	public static String getUsername() {
		return username;
	}


}
