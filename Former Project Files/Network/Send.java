package Network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Send extends Thread{

	public String message;
	public PrintWriter out;

	public Send(String message, PrintWriter out) {
		this.message=message;
		this.out=out;
	}

	public void run() {
		out.println(message);
		out.flush(); 
	}

}
