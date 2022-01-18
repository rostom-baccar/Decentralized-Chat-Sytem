package Model;
import java.io.Serializable;

enum ChatMessageType {
	Connect,Disconnect,Broad,Private,UsersList,UsernameChange
}
public class MessageType  implements Serializable {
	private static final long serialVersionUID = 1L;
	private ChatMessageType type;
	private String message;

	public MessageType(ChatMessageType type, String message) {
		this.type = type;
		this.message = message;
	}

	public ChatMessageType getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}
}
