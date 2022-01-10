package Network;

//Thread that adds the client thread to the list of threads as soon as its 
//username becomes unique

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
	}
}