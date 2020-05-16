package bookstore;


import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PromoteUserFrame {

	private JFrame frame;
	private JTextField txtUsers;
	private JTextField textField_Email;
	private JTextField textField_msg;
	private JButton btnPromote;

	public PromoteUserFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 270, 130);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		txtUsers = new JTextField();
		txtUsers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUsers.setText("User's Email");
		txtUsers.setEditable(false);
		txtUsers.setBounds(6, 32, 86, 20);
		frame.getContentPane().add(txtUsers);
		txtUsers.setColumns(10);
		
		textField_Email = new JTextField();
		textField_Email.setBounds(95, 32, 155, 20);
		frame.getContentPane().add(textField_Email);
		textField_Email.setColumns(10);
		
		textField_msg = new JTextField();
		textField_msg.setEditable(false);
		textField_msg.setBounds(0, -1, 216, 20);
		frame.getContentPane().add(textField_msg);
		textField_msg.setColumns(10);
		
		btnPromote = new JButton("Promote");
		btnPromote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean noerror = IManager.promoteCustomer(textField_Email.getText());
				if (noerror) {
					textField_msg.setForeground(Color.GREEN);
					textField_msg.setText("Action is successfully done");
				}else {
					textField_msg.setForeground(Color.RED);
					textField_msg.setText("Error occured while execution");
				}
			}
		});
		btnPromote.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		btnPromote.setBounds(76, 63, 89, 23);
		frame.getContentPane().add(btnPromote);
		frame.setVisible(true);
	}

}
