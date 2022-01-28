package ServerSide;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import Model.MessageType;
import Model.Message;

//each client will have its own client handler with which it can communicate (like a server instance)
//in order to have multiple client we need multiple threads handling each client
//otherwise we would have blocking functions that would not allow the program to finish

public class ClientHandler extends Thread {

	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket clientSocket;
	private String clientUsername; 
	private boolean canBeAdded=false;
	private String ipAddress;

	public ClientHandler(Socket clientSocket) throws IOException{
		this.clientSocket=clientSocket;

		InputStream inputStream = clientSocket.getInputStream();
		in = new ObjectInputStream(inputStream);

		OutputStream outputStream = clientSocket.getOutputStream();
		out = new ObjectOutputStream(outputStream);
	}

	public void run() {
		Message request = null;
		try {
			request = (Message) in.readObject();
		}
		catch (ClassNotFoundException e2) {e2.printStackTrace();}
		catch (IOException e2) {e2.printStackTrace();}

		try {

			while(request!=null) {



				switch(request.getType()) {

				case Connect:

					if (!unique(request.getContent())) {
						out.writeObject(Message.buildMessage(MessageType.Notification,"Username already taken, please choose another one"));
						request = (Message) in.readObject();
					}
					else {
						out.writeObject(Message.buildMessage(MessageType.Notification,"You are connected"));
						clientUsername=request.getContent(); //we save it so that each client handler knows its primary client
						ipAddress = request.getArgument1();
						broadcast(MessageType.BroadConnect,clientUsername+" just connected",clientUsername,ipAddress,null);
						this.canBeAdded=true;
						request = (Message) in.readObject();


					}
					break;

				case Disconnect:

					broadcast(MessageType.BroadDisconnect,clientUsername+" disconnected",clientUsername,ipAddress,null);
					Server.getClients().remove(this);
					this.canBeAdded=false;
					this.clientSocket.close();
					request = (Message) in.readObject();
					break;

				case UsersList: 

					for (ClientHandler client : Server.getClients()) {
						if (client!=this) {
							//we do not show the user's own nickname
							out.writeObject(Message.buildMessage2(MessageType.UsersList,null,client.clientUsername,client.ipAddress));
							Thread.sleep(10);
						}
					}
					request = (Message) in.readObject();
					break;

				case PrivateMessage:

					String remoteUser=request.getArgument2();
					ClientHandler remoteClientHandler=findThread(remoteUser);
					remoteClientHandler.out.writeObject(request);
					request = (Message) in.readObject();

					break;

				case BroadMessage:

					broadcast(MessageType.BroadMessage,"["+clientUsername+"]: "+request.getContent(),null,null,null);
					request = (Message) in.readObject();
					break;

				case Initiator:

					String initiator=request.getArgument1();
					String remoteUser1=request.getArgument2();
					ClientHandler remoteClientHandler1=findThread(remoteUser1);

					remoteClientHandler1.out.writeObject(Message.buildMessage(MessageType.Initiator,initiator+" wants to chat. Open a Chat Window with them!"));
					request = (Message) in.readObject();
					break;

				case Recipient:

					String recipient=request.getArgument1();
					String remoteUser2=request.getArgument2();
					ClientHandler remoteClientHandler2=findThread(remoteUser2);
					remoteClientHandler2.out.writeObject(Message.buildMessage(MessageType.Recipient,recipient+" has opened a chat. You can now chat with them!"));
					request = (Message) in.readObject();
					break;

				case UsernameChange:

					String oldUsername=request.getArgument1();
					String newUsername=request.getArgument2();
					if (!unique(newUsername)) {
						out.writeObject(Message.buildMessage(MessageType.Notification,"Someone is already connected with this username. Please choose another one"));
					}
					else {
						ClientHandler targetThread=findThread(oldUsername);
						if (targetThread!=null) {
							Server.getClients().remove(targetThread);
							targetThread.setClientUsername(newUsername);
							Server.getClients().add(targetThread);
							broadcast(MessageType.BroadUsernameChange,oldUsername+" has changed their username to "+newUsername,oldUsername,newUsername,ipAddress);
							out.writeObject(Message.buildTypeMessage(MessageType.UsernameChange));
						}
					}

				default:
					request = (Message) in.readObject();
					break;


				}
			}
		} 
		catch (IOException | ClassNotFoundException | InterruptedException e) {} //to avoid errors when disconnecting
		finally {
			//When we manually break off the loop
			try {
				out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();}
		}

	}

	//Useful functions used above
	public static ClientHandler findThread(String remoteUser) {
		ClientHandler target = null;
		for (ClientHandler client : Server.getClients()) {
			if (remoteUser.equals(client.clientUsername)){
				target=client;
			}
		}
		return target;
	}

	//for changing username
	private void broadcast(MessageType type, String message, String argument1, String argument2, String argument3) throws IOException {
		//we send a message to all the clientHandlers that are active
		switch(type) {

		case BroadConnect : case BroadDisconnect :

			for (ClientHandler client : Server.getClients()) {
				client.out.writeObject(Message.buildMessage2(type,"**"+message+"**",argument1,argument2));
			}
			break;

		case BroadUsernameChange :
			for (ClientHandler client : Server.getClients()) {
				client.out.writeObject(Message.buildMessage3(type,"**"+message+"**",argument1,argument2, argument3));
			}
			break;

		case BroadMessage :
			for (ClientHandler client : Server.getClients()) {
				client.out.writeObject(Message.buildMessage2(type,message,argument1,argument2));
			}
		default:
			break;
		}
	}


	private static boolean unique(String username) {
		boolean unique=true;
		for (ClientHandler client : Server.getClients()) {
			if (client.clientUsername.equals(username)) {
				unique=false;
			}
		}
		return unique;
	}

	//Setters and Getters

	public String getClientUsername() {
		return clientUsername;
	}

	public void setClientUsername(String clientUsername) {
		this.clientUsername = clientUsername;
	}

	public boolean getCanBeAdded() {
		return canBeAdded;
	}

	public void setCanBeAdded(boolean canBeAdded) {
		this.canBeAdded = canBeAdded;
	}

}