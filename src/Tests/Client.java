package Tests;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	static PrintWriter out = null;

	public static void connect(String host, int port) {
		Socket client_socket = null;
		try {
			client_socket = new Socket(host,port);}
		catch(UnknownHostException e) {e.printStackTrace();
		}catch(IOException e){e.printStackTrace();}
		try {
			out = new PrintWriter(client_socket.getOutputStream());
		} catch(IOException e){e.printStackTrace();}
		
	}


	public static void send(String message) {
		//Scanner s = new Scanner(System.in);
		//String msg;
		//while(true) { 
			//msg=s.nextLine();
			out.println(message);
			out.flush(); 
		//}

	}
	
	public static void main(String[] args) {
		
		String message;
		Client.connect("192.168.1.15",5000);
		Client.send("test");
	}
	
	
	
	
}