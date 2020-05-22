package bookstore;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;


public class ManagerFrame extends JFrame {

	private JFrame frame;

	public ManagerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		Start.Load_menubar(frame);
		
		JButton btnNewButton = new JButton("Manage Database");
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookOpFrame();
			}
		});
		btnNewButton.setBounds(122, 61, 192, 48);
		getContentPane().add(btnNewButton);
		
		JButton btnPromoteUsers = new JButton("Promote Users");
		btnPromoteUsers.setForeground(Color.RED);
		btnPromoteUsers.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPromoteUsers.setBounds(122, 120, 192, 48);
		getContentPane().add(btnPromoteUsers);
		
		JButton btnManageSales = new JButton("Manage Sales");
		btnManageSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PromoteUserFrame();
			}
		});
		btnManageSales.setForeground(Color.RED);
		btnManageSales.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnManageSales.setBounds(122, 179, 192, 48);
		getContentPane().add(btnManageSales);
		frame = new JFrame("Manage");
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		initialize();
	}
	private void initialize() {
		
	}
}
