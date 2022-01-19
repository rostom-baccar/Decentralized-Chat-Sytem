package Tests;

import java.io.Serializable;

import Model.ChatMessageType;

// must implement Serializable in order to be sent
public class MessageTest implements Serializable{
    private final String text;
    private final ChatMessageType type;


    public MessageTest(String text, ChatMessageType type) {
        this.text = text;
    	this.type=type;

    }

    public String getText() {
        return text;
    }
    
    public ChatMessageType getType() {
        return type;
    }
}