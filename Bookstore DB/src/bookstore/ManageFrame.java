package bookstore;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ManageFrame extends JFrame {

	private JFrame mainFrame;
	private static Start start = Start.getInstance();

	/**
	 * Create the frame.
	 */
	public ManageFrame() {
		getContentPane().setLayout(null);
		mainFrame = new JFrame("Manage Frame");
		mainFrame.setName("Manage Frame");
		mainFrame.setBounds(100, 100, 455, 403);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setVisible(true);
		
		Start.Load_menubar(mainFrame);
		
	}

}
