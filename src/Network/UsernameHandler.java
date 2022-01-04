package Network;

import java.util.ArrayList;

public class UsernameHandler extends Thread{
	private ClientHandler clientThread;

	public UsernameHandler(ClientHandler clientThread) {
		this.clientThread = clientThread;

	}

	public void run() {
//		System.out.println("Waiting for unique username");
		while (!clientThread.getCanBeAdded()) {
//			System.out.println("*");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e){e.printStackTrace();
			}
		}
//		System.out.println("Thread ready to be added");
		Server.getClients().add(clientThread);
//		System.out.println("Thread added");
//		System.out.println(Server.getClients().size());
	}
}
