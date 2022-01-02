package Network;

import java.util.ArrayList;

public class UsernameHandler extends Thread{
	private ClientHandler clientThread;
	private ArrayList<ClientHandler> clients;

	public UsernameHandler(ClientHandler clientThread, ArrayList<ClientHandler> clients) {
		this.clientThread = clientThread;
		this.clients=clients;
	}

	public void run() {
		while (!clientThread.canBeAdded()) {}
		clients.add(clientThread);
	}
}
