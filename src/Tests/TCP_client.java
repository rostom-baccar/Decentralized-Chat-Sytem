package Tests;


import java.net.Socket;
import java.io.*;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.*;
public class TCP_client{
	String test;
	String addr;
	Scanner s = new Scanner(System.in);


		public static void main(String[] args) {

		String host = "192.168.1.15";
		int port = 5000;
		Scanner s = new Scanner(System.in);
		Socket client_socket;
		String msg;
		PrintWriter out = null;

		
		try {
			client_socket = new Socket(host,port);
			out = new PrintWriter(client_socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		while(true) { 
		msg=s.nextLine();

			out.println(msg);
			out.flush(); //
		}

	}
}