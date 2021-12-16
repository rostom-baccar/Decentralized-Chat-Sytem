package Tests;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//public class TCP_server extends Thread {
public class TCP_server{

    //public void run() {
	public static void main(String[] args) {
        //declarations
        int port = 5000;


        //Cote serveur
        //on cree un socket server qui va ecouter en donnant le port


        ServerSocket server_socket = null;
        try {
            server_socket = new ServerSocket(port);
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        System.out.println("Waiting for connection...");
        Socket client_socket = null;
        try {
            client_socket = server_socket.accept();
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        System.out.println("Connected");
        BufferedReader input = null;
        try {
            input = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        //inputstreamreader donne le msg en bytes
        //bufferedreader le convertit en caracteres

        //Affichage du message
        String msg = null;
        try {
            msg = input.readLine();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } //on le decare a l'exterieur du loop car on en a besoin de la verification
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
        try {
            client_socket.close();
            server_socket.close();
            input.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}

 