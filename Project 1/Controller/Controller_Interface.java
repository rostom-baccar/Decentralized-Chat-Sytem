package Controller;

import java.net.UnknownHostException;

import Interface.ActiveUsers;
import Interface.Authentification;
import Network.TCP_Server;

public class Controller_Interface {
	static String msg;
	protected Authentification view;

	public Controller_Interface() throws UnknownHostException {
	}

	public static void main(String[] args) throws UnknownHostException {
		//Controller_Interface interf = new Controller_Interface();
		Authentification window = new Authentification();


	}

}