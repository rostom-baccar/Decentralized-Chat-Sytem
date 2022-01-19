package Model;
import java.io.Serializable;


public class Message  implements Serializable {
	private static final long serialVersionUID = 1L;
	private ChatMessageType type;
	private String message;

	public Message(ChatMessageType type, String message) {
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
