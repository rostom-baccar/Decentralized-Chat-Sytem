package Tests;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerTest {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket ss = new ServerSocket(7777);
		System.out.println("ServerSocket awaiting connections...");
		Socket socket = ss.accept(); 
		System.out.println("Connection from " + socket + "!");

		InputStream inputStream = socket.getInputStream();
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		MessageTest msg2 = (MessageTest) objectInputStream.readObject();
		
		while(msg2!=null) {
			System.out.println("Text: " + msg2.getText());
			System.out.println("Type: " + msg2.getType());
			msg2 = (MessageTest) objectInputStream.readObject();
		}
		ss.close();
		socket.close();
	}
}