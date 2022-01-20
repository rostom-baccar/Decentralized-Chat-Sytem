package ClientSide;

import javax.swing.JTextArea;

import Model.Message;

//Thread that listens to the server response in case of direct message and
//appends the message to the corresponding chat area

public class MessageListener extends Thread {

	private JTextArea chatArea;
	private String remoteUser;
	private Message incomingRequest=null;
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
			
			String potentialRemoteUser=incomingRequest.getArgument1();
			message=incomingRequest.getContent();
			if (remoteUser.equals(potentialRemoteUser)) {
				chatArea.append("["+remoteUser+"] "+message+"\n");
			}
			incomingRequest=null;
		}
	}
}