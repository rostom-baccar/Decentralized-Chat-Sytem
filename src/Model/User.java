package Model;

import java.net.*;

public class User {
	
private String pseudo;
private boolean active;
private InetAddress addr;
private int TCPport;

public User(String pseudo, boolean active, InetAddress addr, int TCPport){
	this.pseudo=pseudo;
	this.active=active;
	this.addr=addr;
	this.TCPport=TCPport;
}

public String getPseudo() {
	return pseudo;
}

public void setPseudo(String pseudo) {
	this.pseudo = pseudo;
}

public boolean isActive() {
	return active;
}

public void setActive(boolean active) {
	this.active = active;
}

public InetAddress getAddr() {
	return addr;
}

public void setAddr(InetAddress addr) {
	this.addr = addr;
}

public int getTCPport() {
	return TCPport;
}

public void setTCPport(int tCPport) {
	TCPport = tCPport;
}


}
