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
		
		Scanner s = new Scanner(System.in);
		System.out.println("Type username");
		username=s.nextLine();
		
		//Socket
		Socket socket = new Socket(Server_IP, port);
		
		//Sending
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		
		//Receiving
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out.println(username);
		
		String response = in.readLine();
		System.out.println(response);
		
		socket.close();
		
	}

	public static String getUsername() {
		return username;
	}

	
}
