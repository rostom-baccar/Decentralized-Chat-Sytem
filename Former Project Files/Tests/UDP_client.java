package Tests;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDP_client extends Thread
{
	public static void main (String arg[]) {
	
		//Message entre par l'utilisateur
		Scanner s = new Scanner(System.in);

		//Creation datagram object
		DatagramSocket dgramSocket = null;
		try {
			dgramSocket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Recuperation adresse de broadcast de la salle (GEI 207)
		InetAddress broadcast_ip = null;
		try {
			broadcast_ip = InetAddress.getByName("10.1.255.255");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		byte buffer[] = null;

		//Envoi de messages en continue: while true
		while (true)
		{
			//Stockage du scanner 
			//String msg = s.nextLine();
			String msg = "test_broad";
			//Buffer for incoming datagrams
			buffer = msg.getBytes();

			//datagram Packet pour envoi
			DatagramPacket DpSend = new DatagramPacket(buffer, buffer.length, broadcast_ip, 1234);
			try {
				dgramSocket.send(DpSend);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}