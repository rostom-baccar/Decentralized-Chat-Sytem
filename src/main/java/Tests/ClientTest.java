package Tests;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.ChatMessageType;

public class ClientTest {

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("localhost", 7777);
		System.out.println("Connected!");

		OutputStream outputStream = socket.getOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(outputStream);
		
		ServerResponseListenerTest serverConnection = new ServerResponseListenerTest(socket);
		serverConnection.start();
		
		
		Scanner s = new Scanner(System.in);
		String msg=s.nextLine();
		MessageTest msg1;
		while (msg!=null) {
			if (msg.equals("hey")) {
				msg1 = new MessageTest("Hey",ChatMessageType.Connect);
			} else {
				msg1 = new MessageTest("Bye",ChatMessageType.Disconnect);
			}
			out.writeObject(msg1);
			msg=s.nextLine();
		}
	}
}