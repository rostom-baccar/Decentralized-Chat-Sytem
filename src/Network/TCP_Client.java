package Network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCP_Client extends Thread{

	static PrintWriter out = null;
	static int count = 0;
	int port;
	static String host;
	
	public TCP_Client(String host,int port) {
		this.host=host;
		this.port=port;
	}


	public void run() {
		count++;
		System.out.println(count);
		Socket client_socket = null;
		try {
			client_socket = new Socket(host,port);}
		catch(UnknownHostException e) {e.printStackTrace();
		}catch(IOException e){e.printStackTrace();}
		try {
			out = new PrintWriter(client_socket.getOutputStream());
		} catch(IOException e){e.printStackTrace();}

	}

//	public static void send(String message) {
//		out.println(message);
//		out.flush(); 


//	}

//	public static void send2() {
//		Scanner s = new Scanner(System.in);
//		String msg;
//		while(true) { 
//			msg=s.nextLine();
//			out.println(msg);
//			out.flush(); 
//		}
//
//	}
//
//	public static void main(String[] args) {
//
//		String message;
//		TCP_Client.connect("192.168.1.15",5000);
//		TCP_Client.send2();
//	}




}