package Tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static Socket connect(int port) {
	ServerSocket server_socket = null;
	try {
		server_socket = new ServerSocket(port);
	}catch(IOException e1){e1.printStackTrace();}
	System.out.println("Waiting for connection...");
	Socket client_socket = null;
	try {
		client_socket = server_socket.accept();
	} catch(IOException e){e.printStackTrace();}
	System.out.println("Connected");
	return client_socket;
	}
	
	
	public static BufferedReader build_input(int port) {
		BufferedReader input = null;
		Socket client_socket=Server.connect(port);
		try {
			input = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
		} catch(IOException e1){e1.printStackTrace();
		}
		return input;
	}
	
	public static void recieve(int port) {
		
		String msg = null;
		BufferedReader input = Server.build_input(port);
		try {
			msg = input.readLine();
		}catch(IOException e1){e1.printStackTrace();}
		
        while (msg!=null) {
            System.out.println("Client: " + msg);
            try {
                msg = input.readLine();
            }catch(IOException e){e.printStackTrace();}
        }
	}
	
	public static void main(String[] args) {
		

		
		
		
		
		
		
		
		
		
	}

}
