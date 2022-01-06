package Model;

import java.net.*;

public class User {

	protected String pseudo;
	protected boolean active;
	protected InetAddress addr;
	protected int TCPport;

	public User(String pseudo, boolean active, InetAddress addr, int TCPport){
		this.pseudo=pseudo;
		this.active=false;
		this.addr=addr;
		this.TCPport=TCPport;
	}


	// Getters //
	public String getPseudo() {
		return this.pseudo;
	}

	public int getTCPport() {
		return this.TCPport;
	}

	public boolean isActive() {
		return this.active;
	}

	public InetAddress getAddr() {
		return this.addr;
	}

	// Setters //
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setAddr(InetAddress addr) {
		this.addr = addr;
	}

	public void setTCPport(int TCPport) {
		this.TCPport = TCPport;
	}


	@Override
	public String toString() {
		return "User [pseudo=" + pseudo + ", active=" + active + ", addr=" + addr + ", TCPport=" + TCPport + "]";
	}


	

}
