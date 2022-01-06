package Tests;

import java.util.ArrayList;

public class ClientHandler extends Thread {

	private static int clients;


	public ClientHandler(int clients) {
		this.clients=clients;
	}
	
	public void run() {
		System.out.println("ClientHandler: "+Server.clients);
		try {Thread.sleep(1000);}catch (InterruptedException e){e.printStackTrace();}
		System.out.println("ClientHandler: "+Server.clients);
	}


	public int getClients() {
		return this.clients;
	}

}
