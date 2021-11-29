package Tests;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDP_server
{
	public static void main(String[] args) throws IOException
	{

		DatagramSocket dgramSocket = new DatagramSocket(1234);
		byte[] buffer = new byte[65535];

		DatagramPacket inPacket = null;
		while (true)
		{

			inPacket = new DatagramPacket(buffer, buffer.length);

			dgramSocket.receive(inPacket);

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