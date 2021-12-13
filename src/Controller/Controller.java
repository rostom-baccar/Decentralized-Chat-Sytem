package Controller;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import Interface.*;
import Model.*;
import javax.swing.*;

public class Controller {
	
final static int TCPport_local = 4001;
final static int UDPport_listening_local = 5001;
final static int UDPport_sending_local = 6001;

private LocalUser local_user;
private Interface interf;



	public Controller(LocalUser local_user) throws UnknownHostException {
		InetAddress addrIP = InetAddress.getLocalHost();
		this.local_user = new LocalUser("Default",true,addrIP,
				TCPport_local,UDPport_listening_local,UDPport_sending_local);
	}


	public boolean pseudoValidity(String pseudo) throws IOException {
		boolean valid = true ;
		int timeout = 3000;
		
		//constructeur permettant de renseigner le port UDP et l'adresse de l'utilisateur
		DatagramSocket dgramSocket = new DatagramSocket(this.local_user.getUDPport_sending(),this.local_user.getAddr());
		String message = "pseudo_validity"+"*"+pseudo+"*"+this.local_user.getAddr();
		//on n'a pas besoin d'envoyer le port udp listening car tous les utilisateurs ont le même
		broadcast(dgramSocket,message);
		
		//traitement de la réponse
		byte buffer[] = new byte[65535];
		String response = null;
		
		/*DatagramPacket inPacket= new DatagramPacket(buffer, buffer.length);
		dgramSocket.receive(inPacket);
		buffer = inPacket.getData();
		response = new String(buffer);*/
		DatagramPacket inPacket = null;
		
		
		while (valid && timeout>0)
		{
			
			inPacket = new DatagramPacket(buffer, buffer.length);
			
			try {
				dgramSocket.receive(inPacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response = new String(inPacket.getData(), 0, inPacket.getLength());
			
			
			buffer = new byte[65535];
		}
		
		return false;
	}
	
	public String initPseudo() throws IOException {
		String pseudo;
		Scanner s = new Scanner(System.in);
	    System.out.println("Enter nickname");
	    pseudo=s.nextLine();
		while(!(this.pseudoValidity(pseudo))) { 
			System.out.println("Nickname taken: Enter another nickname");
			pseudo=s.nextLine();  
		}
		System.out.println("The nickname is valid");
		return pseudo;
	}
	
	//broadcast est une fonction qui envoie à tous les utilisateur "message" en utilisant UDPport de l'utilisateur
	public void broadcast(DatagramSocket dgramSocket,String message) throws IOException {
		byte buffer[] = null;
		buffer = message.getBytes();
		InetAddress broadcast_IP = InetAddress.getByName("10.1.255.255"); //mettre l'adresse broad du réseau
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, broadcast_IP, this.local_user.getUDPport_listening() );
		dgramSocket.send(packet);	
			
		
	}
	
	
	
	
	

}

	


