package Tests;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//public class TCP_server extends Thread {
public class TCP_server{

	//public void run() {
	public static void main (String arg[]) {
	//public static void server() {
		//declarations
		int port = 5000;


		//Cote serveur
		//on cree un socket server qui va ecouter en donnant le port


		ServerSocket server_socket = null;
		try {

			server_socket = new ServerSocket(port);
			System.out.println("Waiting for connection...");
			Socket client_socket = null;
			client_socket = server_socket.accept();
			System.out.println("Connected");
			
			BufferedReader input = null;
			input = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
			String msg = null;
			msg = input.readLine();
 //on le decare a l'exterieur du loop car on en a besoin de la verification
		
        while (msg!=null) {
            System.out.println("Client: " + msg);
            try {
                msg = input.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
		 

		//si la connexion est perdue, on sort du while et on ferme tout
		
       
            client_socket.close();
            server_socket.close();
            input.close();
		}
         catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		 

	}

}

