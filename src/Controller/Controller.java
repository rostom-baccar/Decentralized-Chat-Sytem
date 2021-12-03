package Controller;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import Interface.*;
import Model.*;
import javax.swing.*;

public class Controller {

private LocalUser mounir;
private Interface interf;

	public boolean pseudoValidity(String pseudo) {
		
		
		return false;
	}
	
	public String initPseudo() throws IOException {
		String pseudo;
		Scanner s = new Scanner(System.in);
	    System.out.println("Enter nickname");
	    pseudo=s.nextLine();
		while(!(this.pseudoValidity(pseudo))) { 
			System.out.println("Nickname taken: Enter another nockname");
			pseudo=s.nextLine();  
		}
		System.out.println("The nickname is valid");
		return pseudo;
	}
	
	
	public void broadcast(DatagramSocket dgramSocket,String toSend) throws IOException {
		InetAddress broadcastIP = InetAddress.getLocalHost(); 
		DatagramPacket outPacket = null;
		
			
		}
	}

	


