package Network;

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
				int num = MainWindow.getLocaldb().insertLine("RemoteipAddress", "LocalipAddress", message, "2022-01-01 00:00:01");
				
			}
			incomingRequest=null;
		}
	}
}