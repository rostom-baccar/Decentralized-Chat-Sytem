package Tests;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import Model.ChatMessageType;

public class ServerTest {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {	
		
		ServerSocket ss = new ServerSocket(7777);
		System.out.println("ServerSocket awaiting connections...");
		Socket socket = ss.accept(); 
		System.out.println("Connection from " + socket + "!");

		//Receiving
		InputStream inputStream = socket.getInputStream();
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
		
		//Sending
		OutputStream outputStream = socket.getOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(outputStream);
		
		
		MessageTest msg2 = (MessageTest) objectInputStream.readObject();
		MessageTest resp;
		
		while(msg2!=null) {
			System.out.println("Text: " + msg2.getText());
			System.out.println("Type: " + msg2.getType());
			resp = new MessageTest("Received",ChatMessageType.Notification);
			out.writeObject(resp);
			msg2 = (MessageTest) objectInputStream.readObject();
		}
		ss.close();
		socket.close();
	}
}