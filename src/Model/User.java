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
	return this.pseudo;
}

public void setPseudo(String pseudo) {
	this.pseudo = pseudo;
}

public boolean isActive() {
	return this.active;
}

public void setActive(boolean active) {
	this.active = active;
}

public InetAddress getAddr() {
	return this.addr;
}

public void setAddr(InetAddress addr) {
	this.addr = addr;
}

public int getTCPport() {
	return this.TCPport;
}

public void setTCPport(int TCPport) {
	this.TCPport = TCPport;
}


}
