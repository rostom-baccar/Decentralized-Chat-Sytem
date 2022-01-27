package Interface;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import ServerSide.ClientHandler;
import ServerSide.Server;

public class KillServerWindow extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static JFrame killFrame;
	private static JPanel killPanel;
    private JButton killButton;
    private JLabel killLabel;

    public KillServerWindow() {
    	
    	
    	killFrame=new JFrame ("Kill Server");
        killPanel = new JPanel(new GridLayout(10,10));
        killLabel = new JLabel("STOP SERVER");

		killButton = new JButton ("STOP");
        
        killButton.addActionListener(this);

        killButton.setBounds (77, 60, 85, 23);
        killLabel.setBounds (72, 25, 100, 25); 
       
        killFrame.add (killLabel);
        killFrame.add (killButton);
        
        killFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        killFrame.getRootPane().setDefaultButton(killButton);
        killFrame.setSize(new Dimension(2000, 500));
        killFrame.getContentPane().add (killPanel, BorderLayout.CENTER);
        killFrame.pack();
        killFrame.setLocationRelativeTo(null) ;
        killFrame.setSize(250,150);
        killFrame.setVisible (true);
        
    }


	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent event) {
		try {
			Server.getServerSocket().close();
			Server.setRunServer(false);
			for (ClientHandler clientHandler : Server.getClients()) clientHandler.stop();
			killFrame.setVisible(false);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}