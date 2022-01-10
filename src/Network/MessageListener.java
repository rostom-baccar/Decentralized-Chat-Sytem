package Network;

import javax.swing.JTextArea;

//Thread that listens to the server response in case of direct message and
//appends the message to the "corresponding" chat area

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
				incomingRequest=ServerResponseListener.message;
			}
			
			int spaceIndex=incomingRequest.indexOf(" ");
			String potentialRemoteUser=incomingRequest.substring(1,spaceIndex);
			message=incomingRequest.substring(spaceIndex+1);
			if (remoteUser.equals(potentialRemoteUser)) {
//			System.out.println("[DEBUG] Message Listener: "+incomingMessage);
			chatArea.append("["+remoteUser+"] "+message+"\n");
			}
			incomingRequest=null;
		}
	}
}


