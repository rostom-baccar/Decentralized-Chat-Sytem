package ClientSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import Database.Database;
import Interface.ChatWindow;
import Interface.MainWindow;
import Model.ChatMessageType;
import Model.LocalIpAddress;
import Model.Message;
import Model.RemoteUser;

//Thread for each client which listens constantly to what the server broadcasts
//we only need an input attribute since we won't be sending the server any messages with these threads

public class ServerResponseListener extends Thread{

	private static boolean conversationInitiator=true;
	private static ObjectInputStream in;

	public ServerResponseListener(ObjectInputStream in) throws IOException {
		ServerResponseListener.in=in;

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

					LocalDateTime now = LocalDateTime.now();  
					DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");   
					String tstamp1 = dtf1.format(now);  					
					MainWindow.getBroadArea().append("                                                     "+tstamp1.substring(0,16)+"\n");
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


					ChatWindow chatWindowTarget = findRemoteChatWindow(oldUsername);
					if (chatWindowTarget != null ) {
						chatWindowTarget.setRemoteUsername(newUsername);
						chatWindowTarget.setPrivateChatLabel(newUsername);
						chatWindowTarget.getChatArea().setText("");

						try {
							String LocalipAddress = LocalIpAddress.getLocalAddress().getHostAddress();
							Database.LoadChatHistory(Client.getClientdb(),chatWindowTarget, LocalipAddress, ipAddress1,MainWindow.getUsername(), newUsername);

							//							ResultSet rs = Client.getClientdb().getHistory(LocalipAddress,ipAddress1);
							//							while (rs.next()){
							//								if (rs.getString(1).equals(LocalipAddress)) {
							//									chatWindowTarget.getChatArea().append("["+MainWindow.getUsername()+"]: "+rs.getString(3)+"\n");
							//								}else {
							//									chatWindowTarget.getChatArea().append("["+newUsername+"]: "+rs.getString(3)+"\n");
							//								}
							//							}
						} catch (Exception ee) {
							System.out.print("Error while loading History ! \n");
							ee.printStackTrace();
						}

					}
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
					ChatWindow chatWindowTarget1=findRemoteChatWindow(sender);


					LocalDateTime now1 = LocalDateTime.now();  
					DateTimeFormatter dtf11 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");   
					String tstamp11 = dtf11.format(now1);  

					chatWindowTarget1.getChatArea().append("                                                     "+tstamp11.substring(0,16)+"\n");
					chatWindowTarget1.getChatArea().append("["+sender+"] "+privateMessage+"\n");

					// chatWindowTarget.getChatArea().append("["+sender+"] "+privateMessage+"\n");
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




	private ChatWindow findRemoteChatWindow(String sender) {
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


	public static boolean isConversationInitiator() {
		return conversationInitiator;
	}
}