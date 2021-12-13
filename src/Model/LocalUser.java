package Model;

import java.net.*;

public class LocalUser extends User {
	
	//chaque utilisateur local a un port UDP sur lequel il va écouter les
	//requêtes broadcast, et un port UDP sur lequel il va envoyer ses requêtes
	//broadcast. Ces attributs ne sont cohérents qu'avec un user local car on n'a
	//pas besoin de connaitre les ports UDP d'un remote user
	private int UDPport_listening;
	private int UDPport_sending;

	public LocalUser(String pseudo, boolean active, InetAddress addr, int TCPport,
			int UDPport_listening, int UDPport_sending) {
		super(pseudo, active, addr, TCPport);
		this.UDPport_listening=UDPport_listening;
		this.UDPport_sending=UDPport_sending;
		
	}

	public int getUDPport_listening() {
		return UDPport_listening;
	}

	public void setUDPport_listening(int UDPport_listening) {
		this.UDPport_listening = UDPport_listening;
	}

	public int getUDPport_sending() {
		return UDPport_sending;
	}

	public void setUDPport_sending(int UDPport_sending) {
		this.UDPport_sending = UDPport_sending;
	}
	
	@Override
	public String toString() {
		return "User "+ this.pseudo + ", addIP= " + this.addr + ", TCPport= " + this.TCPport +
				", active: " + this.isActive() + ", UDPport listening= "+ this.UDPport_listening +
				", UDPport sending= "+ this.UDPport_sending;
				
	}

}
