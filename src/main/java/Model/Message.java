package Model;
import java.io.Serializable;


public class Message implements Serializable {


	private static final long serialVersionUID = 1L;
	private ChatMessageType type;
	private String content;
	private String argument1;
	private String argument2;

	public Message(ChatMessageType type) {
		this.type = type;
		this.content = null;
		this.argument1=null;
		this.argument2=null;
	}

	public Message(ChatMessageType type, String content) {
		this.type = type;
		this.content = content;
		this.argument1=null;
		this.argument2=null;
	}
	
	public Message(ChatMessageType type, String content, String argument1) {
		this.type = type;
		this.content = content;
		this.argument1=argument1;
		this.argument2=null;
	}
	
	public Message(ChatMessageType type, String content, String argument1, String argument2) {
		this.type = type;
		this.content = content;
		this.argument1=argument1;
		this.argument2=argument2;
	}

	public static Message buildMessage(ChatMessageType type, String content) {
		Message message = new Message(type,content);
		return message;
	}
	
	public static Message buildMessageWithArgument(ChatMessageType type, String content, String argument1) {
		Message message = new Message(type,content,argument1);
		return message;
	}
	
	//Getters and Setters

	public ChatMessageType getType() {
		return type;
	}

	public void setType(ChatMessageType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getArgument1() {
		return argument1;
	}

	public void setArgument1(String username) {
		this.argument1 = username;
	}
	
	public String getArgument2() {
		return argument2;
	}

	public void setArgument2(String remoteUser) {
		this.argument2 = remoteUser;
	}
	
	public static String toString2(Message msg) {
		return "Message [type=" + msg.type + ", content=" + msg.content + ", argument1=" + msg.argument1 + ", argument2="
				+ msg.argument2 + "]";
	}
	
}
