package Interface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ChatWindow extends JPanel implements ActionListener {
	private String username;
	private String remoteUser;
    private JButton sendButton;
    private JTextField chatField;
    private JTextArea chatArea;
    private JFrame chatFrame;
    private JPanel chatPanel;
    public String query;

    public ChatWindow(String username, String remoteUser) {
    	
    	this.username=username;
    	this.remoteUser=remoteUser;
    	
        chatFrame = new JFrame ("[PRIVATE CHAT] "+username+" - "+remoteUser);
		chatPanel = new JPanel(new GridLayout(10,10));


        sendButton = new JButton ("SEND");
        chatField = new JTextField (5);
        chatArea = new JTextArea (5, 5);
		sendButton.addActionListener(this);


        sendButton.setBounds (180, 215, 70, 25);
        chatField.setBounds (15, 215, 150, 25);
        chatArea.setBounds (15, 15, 235, 185);
        
        chatFrame.add (sendButton);
        chatFrame.add (chatField);
        chatFrame.add (chatArea);
        
		chatFrame.setSize(new Dimension(2000, 500));
        chatFrame.getContentPane().add (chatPanel, BorderLayout.CENTER);
        chatFrame.pack();
		chatFrame.setSize(370,290);
        chatFrame.setVisible (true);
        
    }


    public static void main (String[] args) {
    	String user1 = "user1";
    	String user2 = "user2";
		ChatWindow chatWindow = new ChatWindow(user1,user2);

    }


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sendButton) {
			String message = chatField.getText();
			chatArea.append("["+username+"]: "+message+"\n");
			MainWindow.query="@"+remoteUser+" "+message;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1){e1.printStackTrace();
			}
			MainWindow.query=null;
		}

	}


	public String getQuery() {
		return query;
	}


	public void setQuery(String query) {
		this.query = query;
	}
}