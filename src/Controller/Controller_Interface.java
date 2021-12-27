package Controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import Interface.Authentification;
import Interface.MainWindow;
import Model.LocalUser;
import Tests.Server;
import Tests.TCP_client;
import Tests.TCP_server;

public class Controller_Interface {
	static String msg;
	protected Authentification view;
	
	public Controller_Interface() throws UnknownHostException {
	}
	
	public static void main(String[] args) throws UnknownHostException {
		//Scanner s = new Scanner(System.in);
		//msg=s.nextLine();
		//TCP_client.client(msg);
		Controller_Interface interf = new Controller_Interface();
		Authentification.createAndShowGUI();
		Server.connect(5000);
		Server.recieve(5000);

	}
	
}
