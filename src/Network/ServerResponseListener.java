package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JComboBox;

import Interface.MainWindow;

//Thread for each client which listens constantly to what the server broadcasts
//we only need an input attribute since we won't be sending the server any messages with these threads
public class ServerResponseListener extends Thread{

	private BufferedReader in;
	private Socket clientSocket;

	public ServerResponseListener(Socket clientSocket) throws IOException {
		this.clientSocket=clientSocket;
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

	public void run() {
		try {
			while(true) {
				String serverResponse = in.readLine();
				if (serverResponse==null) break;
				Thread.sleep(200); //to give time to the client handler to send its response
				if (!serverResponse.equals("Username already taken, please choose another one")) {
					Client.setUniqueUsername(true);
				}
				if (serverResponse.equals("begin clients")) {
					System.out.println("[DEBUG] server:"+  serverResponse);
					String clientsResponse = in.readLine();
					while (!clientsResponse.equals("end clients")) {

//						MainWindow.UsersList.setSelectedIndex(-1);  
//						MainWindow.UsersList.setSelectedItem(clientsResponse);  
//						System.out.println("[DEBUG] next client: "+clientsResponse);
//						if(MainWindow.UsersList.getSelectedIndex()==-1){
//							System.out.println("[DEBUG] client: "+clientsResponse+ " does not exist. Adding...");
							MainWindow.UsersList.addItem(clientsResponse);
//							System.out.println("[DEBUG] client: "+clientsResponse);
//						}
//						else {
//							System.out.println("[DEBUG] client: "+clientsResponse+ " exists");
//							if (ClientHandler.findThread(clientsResponse).getCanBeAdded()==false) {
//								System.out.println("[DEBUG] client: "+clientsResponse+ " disconnected");
//								MainWindow.UsersList.removeItem(clientsResponse);
//							}
//						}
						clientsResponse=in.readLine();
					}
					System.out.println("[DEBUG] server: "+clientsResponse);

				}
			} 
		}catch (IOException e){e.printStackTrace();} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
			} catch (IOException e){e.printStackTrace();
			}
		}
	}
}