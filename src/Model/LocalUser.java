package Model;

import java.net.*;

public class LocalUser extends User {
	
	private int UDPport;

	public LocalUser(String pseudo, boolean active, InetAddress addr, int TCPport,
			int UDPport) {
		super(pseudo, active, addr, TCPport);
		this.UDPport=UDPport;
		
	}

}
