import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
	
	private BufferedReader in;
	private PrintWriter out;
	private Socket client_socket;
	
	public ClientHandler(Socket client_socket) throws IOException{
		this.client_socket=client_socket;
		in = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
		out = new PrintWriter(client_socket.getOutputStream(),true);
	}
	
	public void run() {
		String connection = null;
		try {
			connection = in.readLine();
		} catch (IOException e1) {e1.printStackTrace();}
		try {
			while(connection!=null) {				
					System.out.println(connection + " just connected");
					out.println("Server: You are connected");
					connection = in.readLine();
			}
		} catch (IOException e) {e.printStackTrace();}
		finally {
			//When we manually break off the loop
			out.close();
			try {
				in.close();
			} catch (IOException e) {e.printStackTrace();}
		}
		
	}
	
}
