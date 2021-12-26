package Tests;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDP_server extends Thread
{
	public static void main (String arg[]) {
	

		DatagramSocket dgramSocket = null;
		try {
			dgramSocket = new DatagramSocket(1234);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] buffer = new byte[65535];

		DatagramPacket inPacket = null;
		while (true)
		{

			inPacket = new DatagramPacket(buffer, buffer.length);

			try {
				dgramSocket.receive(inPacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Client:-" + data(buffer));

			buffer = new byte[65535];
		}
	}

	public static StringBuilder data(byte[] a)
	{
		if (a == null)
			return null;
		StringBuilder ret = new StringBuilder();
		int i = 0;
		while (a[i] != 0)
		{
			ret.append((char) a[i]);
			i++;
		}
		return ret;
	}
}