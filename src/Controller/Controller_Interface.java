package Controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import Interface.Authentification;
import Model.LocalUser;

public class Controller_Interface {
	
	protected Authentification view;
	
	public Controller_Interface() throws UnknownHostException {
	}

	public static void main(String[] args) throws UnknownHostException {
		Controller_Interface interf = new Controller_Interface();
		Authentification.createAndShowGUI();
	}
	
}

