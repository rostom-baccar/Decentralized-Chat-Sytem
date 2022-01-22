package ClientSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import Interface.ChatWindow;
import Interface.MainWindow;
import Model.ChatMessageType;
import Model.Message;

//Thread for each client which listens constantly to what the server broadcasts
//we only need an input attribute since we won't be sending the server any messages with these threads

public class ServerResponseListener extends Thread{

	private Socket clientSocket;
	private static Message message=null;
	private static boolean conversationInitiator=true;
	private static ObjectInputStream in;

	public ServerResponseListener(ObjectInputStream in) throws IOException {
		this.in=in;

	}

	public void run() {
		try {

			Message serverResponse = (Message) in.readObject();

			while(serverResponse!=null) {
				System.out.println("[ServerResponseListener] Query type: "+serverResponse.getType());
				System.out.println("[ServerResponseListener] Query content: "+serverResponse.getContent());
				System.out.println("[ServerResponseListener] Query argument1: "+serverResponse.getArgument1());
				System.out.println("[ServerResponseListener] Query argument2: "+serverResponse.getArgument2());
				System.out.println();
				switch( (ChatMessageType) serverResponse.getType()) {

				case Notification:

					if (serverResponse.getContent().equals("You are connected")) {
						Client.setUniqueUsername(true);
					}

					else {
						JOptionPane.showMessageDialog(null,serverResponse.getContent());
					}
					break;

				case UsersList:

					MainWindow.getUsersList().addItem(serverResponse.getContent());
					break;

				case BroadMessage : 

					MainWindow.getBroadArea().append(serverResponse.getContent()+"\n");
					break;

				case BroadConnect :

					MainWindow.getBroadArea().append(serverResponse.getContent()+"\n");
					String connectedUser=serverResponse.getArgument1();
					MainWindow.getUsersList().addItem(connectedUser);

					break;

				case BroadDisconnect :

					MainWindow.getBroadArea().append(serverResponse.getContent()+"\n");
					String disconnectedUser=serverResponse.getArgument1();
					MainWindow.getUsersList().removeItem(disconnectedUser);

					break;

				case BroadUsernameChange :

					MainWindow.getBroadArea().append(serverResponse.getContent()+"\n");
					String oldUsername=serverResponse.getArgument1();
					String newUsername=serverResponse.getArgument2();
					MainWindow.getUsersList().removeItem(oldUsername);
					MainWindow.getUsersList().addItem(newUsername);



					break;

				case Recipient:

					JOptionPane.showMessageDialog(null,serverResponse.getContent());
					break;

				case Initiator:

					conversationInitiator=false;
					JOptionPane.showMessageDialog(null,serverResponse.getContent());
					break;

				case PrivateMessage :

					String sender = serverResponse.getArgument1();
					String message=serverResponse.getContent();
					ChatWindow chatWindowTarget=findChatWindow(sender);
					chatWindowTarget.getChatArea().append("["+sender+"] "+message+"\n");
					break;

				case UsernameChange :
					MainWindow.setUniqueNewUsername(true);
					break;

				default:
					break;

				}
				serverResponse = (Message) in.readObject();
			} 
		}
		catch (IOException e){e.printStackTrace();}
		catch (ClassNotFoundException e) {e.printStackTrace();}

	}

	//Setters and Getters

	private ChatWindow findChatWindow(String sender) {
		ChatWindow chatWindowTarget = null;
		for (ChatWindow chatWindow : MainWindow.getChatWindows()) {
			if (chatWindow.getRemoteUser().equals(sender)) {
				chatWindowTarget=chatWindow;
				break;
			}
		}
		return chatWindowTarget;
	}

	public static void setConversationInitiator(boolean conversationInitiator) {
		ServerResponseListener.conversationInitiator = conversationInitiator;
	}

	public static Message getMessage() {
		return message;
	}

	public static void setMessage(Message message) {
		ServerResponseListener.message = message;
	}

	public static boolean isConversationInitiator() {
		return conversationInitiator;
	}
}