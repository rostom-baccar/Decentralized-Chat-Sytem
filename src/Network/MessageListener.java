package Network;

import javax.swing.JTextArea;

public class MessageListener extends Thread {

	private JTextArea chatArea;
	private String remoteUser;
	private String incomingMessage;

	public MessageListener(JTextArea chatArea, String remoteUser) {
		this.chatArea=chatArea;
		this.remoteUser=remoteUser;
	}

	public void run() {
//		while(true) {
			while (ServerResponseListener.message==null) {
				incomingMessage=ServerResponseListener.message;
			}
			chatArea.append("["+remoteUser+"] "+incomingMessage+"\n");
//		}
	}
}


