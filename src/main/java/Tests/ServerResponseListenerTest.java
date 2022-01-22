package Tests;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import Model.Message;

public class ServerResponseListenerTest extends Thread{

	private Socket socket;

	public ServerResponseListenerTest(Socket socket) {
		this.socket = socket;

	}

	public void run() {
		
		try {
			
			InputStream inputStream = socket.getInputStream();
			ObjectInputStream in = new ObjectInputStream(inputStream);
			MessageTest serverResponse = (MessageTest) in.readObject();

			while(serverResponse!=null) {
				System.out.println("[ServerResponseListenerTest] Query text: "+serverResponse.getText());
				System.out.println("[ServerResponseListenerTest] Query type: "+serverResponse.getType());
				serverResponse = (MessageTest) in.readObject();
			}

		} catch (IOException | ClassNotFoundException e) {e.printStackTrace();}



	}


}
