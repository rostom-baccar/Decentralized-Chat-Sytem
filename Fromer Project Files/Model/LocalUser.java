package Model;

import java.net.*;
import java.util.ArrayList;



public class LocalUser extends User {
	
	//chaque utilisateur local a un port UDP sur lequel il va �couter les
	//requ�tes broadcast, et un port UDP sur lequel il va envoyer ses requ�tes
	//broadcast. Ces attributs ne sont coh�rents qu'avec un user local car on n'a
	//pas besoin de connaitre les ports UDP d'un remote user
	
	private int UDPport_listening;
	private int UDPport_sending;
	private ArrayList<RemoteUser> remoteUsersList = new ArrayList<RemoteUser>();
	
	

	public LocalUser(String pseudo, boolean active, InetAddress addr, int TCPport,
			int UDPport_listening, int UDPport_sending) {
		super(pseudo, active, addr, TCPport);
		this.UDPport_listening=UDPport_listening;
		this.UDPport_sending=UDPport_sending;
		
	}
	
	// Getters //
	public int getUDPport_listening() {
		return UDPport_listening;
	}
	
	public int getUDPport_sending() {
		return UDPport_sending;
	}
	// Setters //
	public void setUDPport_listening(int UDPport_listening) {
		this.UDPport_listening = UDPport_listening;
	}

	public void setUDPport_sending(int UDPport_sending) {
		this.UDPport_sending = UDPport_sending;
	}
	
	
	
	public void addRemoteUser(String pseudo, boolean active, InetAddress addr, int TCPport) {
		RemoteUser rm = new RemoteUser ( pseudo, active, addr, TCPport) ;
		int index = this.remoteUsersList.indexOf(rm);
		if(index!=-1) {
			System.out.println("("+this.pseudo+") "+"MAJ, IP(port) : "+pseudo+" => "+addr+"("+TCPport+")");
			this.remoteUsersList.get(index).setAddr(addr);
			this.remoteUsersList.get(index).setTCPport(TCPport);
			this.remoteUsersList.get(index).setPseudo(pseudo);
		}
		else {
			System.out.println("("+this.pseudo+") "+"Add new user IP(port) : "+pseudo+" => "+addr+"("+TCPport+")");
			this.remoteUsersList.add(rm);
		}
	}

	@Override
	public String toString() {
		return "LocalUser [UDPport_listening=" + UDPport_listening + ", UDPport_sending=" + UDPport_sending
				+ ", remoteUsersList=" + remoteUsersList + "]";
	}
	
	

}
