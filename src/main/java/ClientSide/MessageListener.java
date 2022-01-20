package ClientSide;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import javax.swing.JTextArea;

import Model.ChatMessageType;
import Model.Message;

//Thread that listens to the server response in case of direct message and
//appends the message to the corresponding chat area

public class MessageListener extends Thread {

	private JTextArea chatArea;
	private String remoteUser;
	private Message incomingRequest=null;
	private String message;
	private String username;


	public MessageListener(JTextArea chatArea, String remoteUser, String username) {
		this.chatArea=chatArea;
		this.remoteUser=remoteUser;
		this.username=username;
	}

	public void run() {
		
		try {
			InputStream inputStream = Client.getSocket().getInputStream();
			ObjectInputStream in = new ObjectInputStream(inputStream);
			Message serverResponse = (Message) in.readObject();

			while(serverResponse!=null) {
				switch( (ChatMessageType) serverResponse.getType()) {

				case PrivateMessage:
					if (username.equals(serverResponse.getArgument2())) {
						chatArea.append("["+serverResponse.getArgument1()+"] "+message+"\n");
					}
					break;
					
				default:
					break;

				}
			}
		} catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
	}
}