package Tests;

public class Client {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Client: "+Server.getClients());
		Thread.sleep(1000);
		System.out.println("Client: "+Server.getClients());
		
		while(true) {}
	}

}
