package Tests;

import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
 
/**
 *
 * @author
 */
 
 
public class Enterkey {
 
    public static void main(String[] args) {
 
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
 
        // set this panel's layout to null for absolute positioning of components
        panel.setLayout(null);
 
        JButton button = new JButton();
 
        button.registerKeyboardAction(button.getActionForKeyStroke(
                                      KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                                      KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                                      JComponent.WHEN_FOCUSED);
 
        button.registerKeyboardAction(button.getActionForKeyStroke(
                                      KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                                      KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                                      JComponent.WHEN_FOCUSED);
 
        button.setText("Press The Enter Key");
        button.setBounds(0, 0, 200, 120);
        panel.add(button);
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setSize(200, 120);
    }
}