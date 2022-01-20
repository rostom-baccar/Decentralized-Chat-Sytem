package Network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextArea;

import Interface.MainWindow;

//Thread that listens to the server response in case of direct message and
//appends the message to the corresponding chat area

public class MessageListener extends Thread {

	private JTextArea chatArea;
	private String remoteUser;
	private String incomingRequest=null;
	private String message;

	public MessageListener(JTextArea chatArea, String remoteUser) {
		this.chatArea=chatArea;
		this.remoteUser=remoteUser;
	}

	public void run() {
		while(true) {
			
			while (incomingRequest==null) {
				incomingRequest=ServerResponseListener.getMessage();
			}
			
			int spaceIndex=incomingRequest.indexOf(" ");
			String potentialRemoteUser=incomingRequest.substring(1,spaceIndex);
			message=incomingRequest.substring(spaceIndex+1);
			if (remoteUser.equals(potentialRemoteUser)) {
				chatArea.append("["+remoteUser+"] "+message+"\n");
				//
				// Add message Initiator=0 /////////////////////////////////////////////////////////////////
				//
				
				try {
					String RemoteipAddress = ServerResponseListener.getRemoteIpAddress();
					String LocalipAddress = InetAddress.getLocalHost().getHostAddress();
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
					LocalDateTime now = LocalDateTime.now();  
					String tstamp = dtf.format(now);  
					int num = MainWindow.getLocaldb().insertLine(RemoteipAddress, LocalipAddress, message, tstamp);
				
				} catch (UnknownHostException e) {e.printStackTrace();}
			}
			incomingRequest=null;
		}
	}
}