package Tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import Interface.MainWindow;

public class Server {
	public static String msg;
	static BufferedReader input = null;
	
	public static void connect(int port) {
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
		try {
			input = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
		} catch(IOException e1){e1.printStackTrace();
		}
	}

	public static void recieve(int port) {

		//
		//String msg = null;
		try {
			msg = input.readLine();
		}catch(IOException e1){e1.printStackTrace();}

		while (msg!=null) {
			MainWindow.convArea.append("Server :" +msg+"\n");
			System.out.println("Client: " + msg);
			
			try {
				msg = input.readLine();
			}catch(IOException e){e.printStackTrace();}
		}
	}

	public static void main(String[] args) {
		Server.connect(5000);
		Server.recieve(5000);

	}

}
