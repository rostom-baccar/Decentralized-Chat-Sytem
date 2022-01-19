package Tests;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClassB {

	public static void main(String[] args) throws UnknownHostException {
		System.out.println("IPV4 Localhost adress: "+InetAddress.getLocalHost().getHostAddress());
	}

}
