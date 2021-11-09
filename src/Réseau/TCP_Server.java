package Réseau;

import java.net.*;

public class TCP_Server {
	
	ServerSocket server_socket;
	InetAddress address;
	int port;
	
	
	
	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}
	


}
