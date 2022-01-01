package Model;

import java.net.InetAddress;

public class RemoteUser extends User{

	public RemoteUser(String pseudo, boolean active, InetAddress addr, int TCPport) {
		super(pseudo, active, addr, TCPport);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "RemoteUser [toString()=" + super.toString() + "]";
	}

	public void setPortTCP(int tCPport) {
		// TODO Auto-generated method stub
		
	}

}
