package Model;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	private MessageType type;
	private String content;
	private String argument1;
	private String argument2;
	private String argument3;

	public Message(MessageType type) {
		this.type = type;
		this.content = null;
		this.argument1=null;
		this.argument2=null;
		this.argument3=null;
	}

	public Message(MessageType type, String content) {
		this.type = type;
		this.content = content;
		this.argument1=null;
		this.argument2=null;
		this.argument3=null;
	}
	
	public Message(MessageType type, String content, String argument1) {
		this.type = type;
		this.content = content;
		this.argument1=argument1;
		this.argument2=null;
		this.argument3=null;
	}
	
	public Message(MessageType type, String content, String argument1, String argument2) {
		this.type = type;
		this.content = content;
		this.argument1=argument1;
		this.argument2=argument2;
		this.argument3=null;
	}

	public Message(MessageType type, String content, String argument1, String argument2, String argument3) {
		this.type = type;
		this.content = content;
		this.argument1=argument1;
		this.argument2=argument2;
		this.argument3=argument3;
	}
		
	public static Message buildTypeMessage(MessageType type) {
		Message message = new Message(type);
		return message;
	}
	
	public static Message buildMessage(MessageType type, String content) {
		Message message = new Message(type,content);
		return message;
	}
	
	public static Message buildMessage1(MessageType type, String content, String argument1) {
		Message message = new Message(type,content,argument1);
		return message;
	}
	
	public static Message buildMessage2(MessageType type, String content, String argument1, String argument2) {
		Message message = new Message(type,content,argument1,argument2);
		return message;
	}
	
	public static Message buildMessage3(MessageType type, String content, String argument1, String argument2, String argument3) {
		Message message = new Message(type,content,argument1,argument2,argument3);
		return message;
	}
	
	//Getters and Setters

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
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
	
	public String getArgument3() {
		return argument3;
	}

	public void setArgument3(String argument3) {
		this.argument3 = argument3;
	}

	
	@Override
	public String toString() {
		return "Message [type=" + type + ", content=" + content + ", argument1=" + argument1 + ", argument2="
				+ argument2 + "]";
	}
	
}
