package ClientSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import Interface.ChatWindow;
import Interface.MainWindow;
import Model.ChatMessageType;
import Model.Message;
import Model.RemoteUser;

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
					
					String username = serverResponse.getArgument1();
					String ipAddress = serverResponse.getArgument2();
					RemoteUser remoteUser = new RemoteUser(username,ipAddress);
//					MainWindow.getUsersList().addItem(username+" "+ipAdress);
					MainWindow.getStringUsersList().addItem(username);
					MainWindow.getObjectUsersList().add(remoteUser);

					break;

				case BroadMessage : 

					MainWindow.getBroadArea().append(serverResponse.getContent()+"\n");
					break;

				case BroadConnect :

					MainWindow.getBroadArea().append(serverResponse.getContent()+"\n");
					String usernameConnected=serverResponse.getArgument1();
					String ipAddressConnected=serverResponse.getArgument2();
					RemoteUser connectedRemoteUser = new RemoteUser(usernameConnected,ipAddressConnected);
					MainWindow.getObjectUsersList().add(connectedRemoteUser);
//					MainWindow.getUsersList().addItem(usernameConnected+" "+ipAdressConnected);
					MainWindow.getStringUsersList().addItem(usernameConnected);

					break;

				case BroadDisconnect :

					MainWindow.getBroadArea().append(serverResponse.getContent()+"\n");
					String usernameDisconnected=serverResponse.getArgument1();
					String ipAddressDisconnected=serverResponse.getArgument2();
					RemoteUser disconnectedRemoteUser = new RemoteUser(usernameDisconnected,ipAddressDisconnected);
					MainWindow.getObjectUsersList().remove(disconnectedRemoteUser);
//					MainWindow.getUsersList().removeItem(usernameDisconnected+" "+ipAdressDisconnected);
					MainWindow.getStringUsersList().removeItem(usernameDisconnected);

					break;

				case BroadUsernameChange :

					MainWindow.getBroadArea().append(serverResponse.getContent()+"\n");
					String oldUsername=serverResponse.getArgument1();
					String newUsername=serverResponse.getArgument2();
					String ipAddress1=serverResponse.getArgument3();
					RemoteUser oldRemoteUser = new RemoteUser(oldUsername,ipAddress1);
					RemoteUser newRemoteUser = new RemoteUser(newUsername,ipAddress1);
					MainWindow.getStringUsersList().removeItem(oldUsername);
					MainWindow.getStringUsersList().addItem(newUsername);
					MainWindow.getObjectUsersList().remove(oldRemoteUser);
					MainWindow.getObjectUsersList().add(newRemoteUser);

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
					String privateMessage=serverResponse.getContent();
					ChatWindow chatWindowTarget=findChatWindow(sender);
					chatWindowTarget.getChatArea().append("["+sender+"] "+privateMessage+"\n");
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