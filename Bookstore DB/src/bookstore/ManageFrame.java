package bookstore;



import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

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
		mainFrame.setBounds(100, 100, 516, 403);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setVisible(true);
		
		Start.Load_menubar(mainFrame);
		
		
		JButton btnNewButton = new JButton("Manage Database");
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookOpFrame();
			}
		});
		btnNewButton.setBounds(122, 61, 192, 48);
		mainFrame.getContentPane().add(btnNewButton);
		
		JButton btnPromoteUsers = new JButton("Promote Users");
		btnPromoteUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PromoteUserFrame();
			}
		});
		btnPromoteUsers.setForeground(Color.RED);
		btnPromoteUsers.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPromoteUsers.setBounds(122, 120, 192, 48);
		mainFrame.getContentPane().add(btnPromoteUsers);
		
		JButton btnManageSales = new JButton("Manage Sales");
		btnManageSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SalesFrame();
			}
		});
		btnManageSales.setForeground(Color.RED);
		btnManageSales.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnManageSales.setBounds(122, 179, 192, 48);
		mainFrame.getContentPane().add(btnManageSales);
	}

}
