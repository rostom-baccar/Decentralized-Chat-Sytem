package Network;

import javax.swing.JTextArea;

//Thread thzt listens to the server response in case of direct message and
//appends the message to the corresponding chat area

public class MessageListener extends Thread {

	private JTextArea chatArea;
	private String remoteUser;
	private String incomingMessage=null;

	public MessageListener(JTextArea chatArea, String remoteUser) {
		this.chatArea=chatArea;
		this.remoteUser=remoteUser;
	}

	public void run() {
		while(true) {
			while (incomingMessage==null) {
				incomingMessage=ServerResponseListener.message;
			}
			System.out.println("[DEBUG] Message Listener: "+incomingMessage);
			chatArea.append("["+remoteUser+"] "+incomingMessage+"\n");
			incomingMessage=null;
		}
	}
}


