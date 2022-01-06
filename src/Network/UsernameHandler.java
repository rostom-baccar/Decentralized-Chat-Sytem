package Network;

public class UsernameHandler extends Thread{
	private ClientHandler clientThread;

	public UsernameHandler(ClientHandler clientThread) {
		this.clientThread = clientThread;

	}

	public void run() {
		while (!clientThread.getCanBeAdded()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e){e.printStackTrace();
			}
		}
		Server.getClients().add(clientThread);
		try {
			Thread.sleep(5000); //to five time for the client handler thread to assign the username
		} catch (InterruptedException e){e.printStackTrace();}
		for (ClientHandler client : Server.getClients()) {
			System.out.println(client.getClientUsername());
		} 
	}
}
