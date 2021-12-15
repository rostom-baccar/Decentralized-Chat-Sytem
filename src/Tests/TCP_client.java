package Tests;


import java.net.Socket;
import java.io.*;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.*;

public class TCP_client extends Thread {
	String test;
	String addr;
	public TCP_client(String test, String addr) {
		this.test=test;
		this.addr=addr;
	}

	public void run() {
		//Cote client
		//declarations
		//String host = "10.1.5.154";
		String host = addr;
		int port = 5000;
		//creation du message a envoyer
		Scanner s = new Scanner(System.in);
		Socket client_socket;
		String msg;
		PrintWriter out = null;

		//creation du socket

		//declaration d'un socket de type TCP (autre synthaxe pour un socket UDP)
		//ecrire un message dans le client socket, dans le outputstream du clinet_socket


		try {
			client_socket = new Socket(host,port);
			out = new PrintWriter(client_socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		while(true) { //loop pour creer des messages a envoyer a chaque fois
			//msg=s.nextLine();
			msg=this.test;
			//on place msg dans out
			out.println(msg);
			out.flush(); //


		}

	}
}