package Tests;

import java.util.ArrayList;

public class Server {

	static int clients = 0;

	public static void setClients(int clients) {
		Server.clients = clients;
	}

	public static void main(String[] args) throws InterruptedException {
		
		ClientHandler clientThread = new ClientHandler(clients);
		clientThread.start();
		
		UsernameHandler usernameThread = new UsernameHandler(clientThread);
		usernameThread.start();
		System.out.println("Server: "+clients);
		Thread.sleep(1000);
		System.out.println("Server: "+clients);

		while(true) {}
	}

	public static int getClients(){
		return clients;
	}

}
