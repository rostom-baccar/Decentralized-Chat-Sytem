package Tests;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDP_client
{
	public static void main(String args[]) throws IOException
	{
		//Message entré par l'utilisateur
		Scanner s = new Scanner(System.in);

		//Création datagram object
		DatagramSocket dgramSocket = new DatagramSocket();

		//Récupération adresse de broadcast de la salle (GEI 207)
		InetAddress broadcast_ip = InetAddress.getByName("10.1.255.255");
		byte buffer[] = null;

		//Envoi de messages en continue: while true
		while (true)
		{
			//Stockage du scanner 
			String msg = s.nextLine();

			//Buffer for incoming datagrams
			buffer = msg.getBytes();

			//datagram Packet pour envoi
			DatagramPacket DpSend = new DatagramPacket(buffer, buffer.length, broadcast_ip, 1234);
			dgramSocket.send(DpSend);
		}
	}
}