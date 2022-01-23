package Tests;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class ScrollTest {

	private JPanel middlePanel;
	private JTextArea display;
	private JScrollPane scroll;
	private JFrame frame;
	
	public ScrollTest(){

		middlePanel = new JPanel ();
		middlePanel.setBorder ( new TitledBorder ( new EtchedBorder (), "Display Area" ) );

		// create the middle panel components

		display = new JTextArea ( 16, 58 );
		display.setEditable ( false ); // set textArea non-editable
		scroll = new JScrollPane ( display );
		scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

		//Add Textarea in to middle panel
		middlePanel.add ( scroll );

		// My code
		frame = new JFrame ();
		frame.add ( middlePanel );
		frame.pack ();
		frame.setLocationRelativeTo ( null );
		frame.setVisible ( true );

	}

	public static void main ( String[] args )
	{
		ScrollTest scrollTest = new ScrollTest();
	}

}
