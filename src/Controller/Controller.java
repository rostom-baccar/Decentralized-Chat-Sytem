package Controller;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import Interface.*;
import Model.*;
import javax.swing.*;

public class Controller {

private LocalUser local_user;
private Interface interf;

	public boolean pseudoValidity(String pseudo) throws SocketException {
		//constructeur permettant de renseigner le port UDP et l'adresse de l'utilisateur
		DatagramSocket dgramSocket = new DatagramSocket(this.local_user.getUDPport_sending(),this.local_user.getAddr());
		
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
	public void broadcast(DatagramSocket dgramSocket,String message, int UDPport) throws IOException {
		byte buffer[] = null;
		buffer = message.getBytes();
		InetAddress broadcast_IP = InetAddress.getByName("10.1.255.255"); //mettre l'adresse broad du réseau
		//test: pour l'instant on envoir sur le port 1234 mais normalement chaque utilisateur a son port
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, broadcast_IP, UDPport);
		dgramSocket.send(packet);	
		}
	}

	


