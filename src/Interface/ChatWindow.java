package Interface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ChatWindow extends JPanel implements ActionListener {
    private JButton sendButton;
    private JTextField chatField;
    private JTextArea chatArea;
    private JFrame chatFrame;
    private JPanel chatPanel;

    public ChatWindow() {
    	
        chatFrame = new JFrame ("MyPanel");
        chatFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		chatPanel = new JPanel(new GridLayout(10,10));


        sendButton = new JButton ("SEND");
        chatField = new JTextField (5);
        chatArea = new JTextArea (5, 5);

        sendButton.setBounds (180, 215, 70, 25);
        chatField.setBounds (15, 215, 150, 25);
        chatArea.setBounds (15, 15, 235, 185);
        
        chatFrame.add (sendButton);
        chatFrame.add (chatField);
        chatFrame.add (chatArea);
        
		chatFrame.setSize(new Dimension(2000, 500));
        chatFrame.getContentPane().add (chatPanel, BorderLayout.CENTER);
        chatFrame.pack();
		chatFrame.setSize(280,290);
        chatFrame.setVisible (true);
        
    }


    public static void main (String[] args) {
		ChatWindow chatWindow = new ChatWindow();

    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}