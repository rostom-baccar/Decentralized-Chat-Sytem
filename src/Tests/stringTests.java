package Tests;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class stringTests {

	public static void main(String[] args) throws SocketException {
	      InetAddress myIP = null;
		try {
			myIP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	      /* public String getHostAddress(): Returns the IP 
	       * address string in textual presentation.
	       */
	      System.out.println("My IP Address is:");
	      System.out.println(myIP.getHostAddress());
	}

}
