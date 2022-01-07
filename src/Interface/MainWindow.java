package Interface ;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MainWindow extends JPanel implements ActionListener {
	private JButton refreshButton;
	private JButton disconnectButton;
	private JButton chatButton;
	public static JComboBox<String> UsersList = null;
	public static String[] init= {};
	private JTextArea TextAreaMain;
	private JTextField TextFieldMain;
	private JButton sendButton;
	private JLabel Label;
	public static JFrame mainFrame;
	private JPanel mainPanel;
	public static String query;
	private String username;

	public MainWindow(String username) {

		this.username=username;
		UsersList = new JComboBox<String>(init);
		mainFrame = new JFrame (username);
		mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel(new GridLayout(10,10));

		TextAreaMain = new JTextArea (5, 5);
		TextFieldMain = new JTextField (5);
		Label = new JLabel ("GROUP CHAT");
		refreshButton = new JButton ("Refresh list");
		disconnectButton = new JButton ("Disconnect");
		chatButton = new JButton ("Chat");
		sendButton = new JButton ("Send");
		refreshButton.addActionListener(this);
		disconnectButton.addActionListener(this);
		chatButton.addActionListener(this);
		sendButton.addActionListener(this);

		refreshButton.setBounds (350, 20, 130, 25);
		disconnectButton.setBounds (350, 250, 130, 25);
		chatButton.setBounds (350, 105, 130, 25);
		UsersList.setBounds (350, 60, 130, 25);
		TextAreaMain.setBounds (20, 60, 310, 215);
		TextFieldMain.setBounds (20, 295, 310, 25);
		sendButton.setBounds (350, 295, 130, 25);
		Label.setBounds (135, 25, 80, 25);

		mainFrame.add (refreshButton);
		mainFrame.add (disconnectButton);
		mainFrame.add (chatButton);
		mainFrame.add (UsersList);
		mainFrame.add (TextAreaMain);
		mainFrame.add (TextFieldMain);
		mainFrame.add (sendButton);
		mainFrame.add (Label);

		mainFrame.setSize(new Dimension(2000, 500));
		mainFrame.getContentPane().add (mainPanel, BorderLayout.CENTER);
		mainFrame.pack();
		mainFrame.setSize(500,400);
		mainFrame.setVisible (true);


	}

	public static void main(String [] argv) {
		String testUsername="test username";
		MainWindow mainWindow = new MainWindow(testUsername);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == disconnectButton) {
			query="disconnect";
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1){e1.printStackTrace();
			}
			query=null;
		}

		if(e.getSource() == refreshButton) {
//			String[] init = {};
//			UsersList = new JComboBox<String>(init);
			UsersList.removeAllItems();
			query="active";
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1){e1.printStackTrace();
			}
			query=null;
			try {
				Thread.sleep(250); //to give time for the server response
				//listener to add the clients to the table
			}
			catch (InterruptedException e1) {e1.printStackTrace();}
		}

	}
}
