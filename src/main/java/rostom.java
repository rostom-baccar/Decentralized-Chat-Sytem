import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import Interface.ChatWindow;
import Interface.MainWindow;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class rostom {

	private JFrame mainFrame;
	private JTextField newUsernameField;
	private JTextField broadField;
	private static boolean uniqueNewUsername=false;
	private static ArrayList<ChatWindow> chatWindows = new ArrayList<ChatWindow>();
	private static String username;
	private static String newUsername;
	private ChatWindow chatWindow=null;
	private ObjectOutputStream out;



	public static void main(String[] args) throws IOException {
					rostom window = new rostom("rostom",null);
	}

	/**
	 * Create the application.
	 */
	public rostom(String username, ObjectOutputStream out) throws IOException {
		rostom.username=username;
		this.out=out;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setBounds(100, 100, 511, 474);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		JButton changeUsernameButton = new JButton("Change Username");

		changeUsernameButton.setBounds(338, 12, 151, 25);
		mainFrame.getContentPane().add(changeUsernameButton);
		
		newUsernameField = new JTextField();
		newUsernameField.setBounds(20, 12, 308, 25);
		mainFrame.getContentPane().add(newUsernameField);
		newUsernameField.setColumns(10);
		
		String[] init= {};
//		JComboBox UsersList = new JComboBox<String>(init);
		JComboBox UsersList = new JComboBox(init);
		UsersList.setBounds(358, 73, 131, 25);
		mainFrame.getContentPane().add(UsersList);
		
		JButton chatButton = new JButton("Chat");

		chatButton.setBounds(358, 109, 131, 25);
		mainFrame.getContentPane().add(chatButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 73, 328, 317);
		mainFrame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		broadField = new JTextField();
		broadField.setColumns(10);
		broadField.setBounds(20, 401, 328, 25);
		mainFrame.getContentPane().add(broadField);
		
		JButton sendButton = new JButton("Send");
		sendButton.setBounds(358, 401, 131, 25);
		mainFrame.getContentPane().add(sendButton);
		
		JButton disconnectButton = new JButton("Disconnect");
		disconnectButton.setBounds(358, 366, 131, 25);
		mainFrame.getContentPane().add(disconnectButton);
		
		JLabel groupChatLabel = new JLabel("GROUP CHAT");
		groupChatLabel.setBounds(140, 48, 106, 14);
		mainFrame.getContentPane().add(groupChatLabel);
		
		changeUsernameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		chatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		
	}
}
