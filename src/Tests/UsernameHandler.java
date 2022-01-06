package Tests;

public class UsernameHandler extends Thread{
	private ClientHandler clientThread;

	public UsernameHandler(ClientHandler clientThread) {
		this.clientThread = clientThread;

	}

	public void run() {
		Server.setClients(5);
		System.out.println("UsernameHandler: "+Server.clients);
	}
}
