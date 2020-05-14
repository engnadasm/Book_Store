package online_bookstore;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class Update_Info extends JFrame {

	private JFrame mainFrame;
	private JTextField email_text;
	private JTextField pass_text;
	private JTextField phone_text;
	private JTextField username_text;
	private JTextField first_name_text;
	private JTextField last_name_text;
	private JTextField shipping_address_text;
		
	private JButton btnUpdate;


	/**
	 * Create the frame.
	 */
	public Update_Info() {
		mainFrame = new JFrame("Update");
		mainFrame.setName("Update");
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setBounds(100, 100, 450, 555);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setVisible(true);
		
		Start.Load_menubar(mainFrame);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		JLabel lblCreateNewAcount = new JLabel("Edit my personal information");
		lblCreateNewAcount.setForeground(Color.RED);
		lblCreateNewAcount.setFont(new Font("Segoe Script", Font.BOLD, 18));
		lblCreateNewAcount.setBounds(73, 50, 312, 45);
		mainFrame.getContentPane().add(lblCreateNewAcount);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setForeground(Color.RED);
		lblEmail.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblEmail.setBounds(11, 96, 70, 24);
		mainFrame.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(Color.RED);
		lblPassword.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblPassword.setBounds(10, 146, 100, 24);
		mainFrame.getContentPane().add(lblPassword);
		
		email_text = new JTextField("");
		email_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		email_text.setBounds(157, 96, 250, 40);
		email_text.setEnabled(false);
		mainFrame.getContentPane().add(email_text);
		
		pass_text = new JTextField();
		pass_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		pass_text.setBounds(157, 146, 250, 40);
		pass_text.setDocument(new JTextFieldLimit(30));
		mainFrame.getContentPane().add(pass_text);
		
		JLabel lblUserName = new JLabel("User_Name :");
		lblUserName.setForeground(Color.RED);
		lblUserName.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblUserName.setBounds(11, 196, 112, 24);
		mainFrame.getContentPane().add(lblUserName);
		
		JLabel lblFirstName = new JLabel("First_Name :");
		lblFirstName.setForeground(Color.RED);
		lblFirstName.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblFirstName.setBounds(11, 246, 100, 24);
		mainFrame.getContentPane().add(lblFirstName);
		
		username_text = new JTextField("");
		username_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		username_text.setDocument(new JTextFieldLimit(55));
		username_text.setBounds(157, 196, 250, 40);
		mainFrame.getContentPane().add(username_text);
		
		first_name_text = new JTextField();
		first_name_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		first_name_text.setBounds(157, 246, 250, 40);
		first_name_text.setDocument(new JTextFieldLimit(30));
		mainFrame.getContentPane().add(first_name_text);
		
		JLabel lblSecondName = new JLabel("Last_Name :");
		lblSecondName.setForeground(Color.RED);
		lblSecondName.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblSecondName.setBounds(11, 296, 99, 24);
		mainFrame.getContentPane().add(lblSecondName);
		
		JLabel lblPhone = new JLabel("Phone :");
		lblPhone.setForeground(Color.RED);
		lblPhone.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblPhone.setBounds(11, 346, 100, 24);
		mainFrame.getContentPane().add(lblPhone);
		
		last_name_text = new JTextField("");
		last_name_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		last_name_text.setBounds(157, 296, 250, 40);
		last_name_text.setDocument(new JTextFieldLimit(30));
		mainFrame.getContentPane().add(last_name_text);
		
		phone_text = new JTextField("");
		phone_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		phone_text.setBounds(157, 346, 250, 40);
		phone_text.setDocument(new JTextFieldLimit(12));
		mainFrame.getContentPane().add(phone_text);
		
		JLabel lbladdress = new JLabel("Shipping_Address :");
		lbladdress.setForeground(Color.RED);
		lbladdress.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lbladdress.setBounds(10, 396, 143, 24);
		mainFrame.getContentPane().add(lbladdress);
		
		shipping_address_text = new JTextField("");
		shipping_address_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		shipping_address_text.setBounds(157, 396, 250, 40);
		shipping_address_text.setDocument(new JTextFieldLimit(50));
		mainFrame.getContentPane().add(shipping_address_text);
		   Start s = Start.getInstance();

		String sqlSelect = "SELECT * FROM users where email = '" + s.getUserEmail() + "';";
		try {
			
			ResultSet rs = MainFram.stmt.executeQuery(sqlSelect);
            while(rs.next()) {
			email_text.setText(rs.getString("email"));
			pass_text.setText(rs.getString("password_user"));
			phone_text.setText(rs.getString("phone"));
			username_text.setText(rs.getString("user_name"));
			first_name_text.setText(rs.getString("first_name"));
			last_name_text.setText(rs.getString("last_name"));
			shipping_address_text.setText(rs.getString("shipping_address"));
            }
			 	  
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(username_text.getText().equals("")) {
					JOptionPane.showMessageDialog(Update_Info.this,
						    "You check write your new user name not be null to can update it.",
						    "update error",
						    JOptionPane.ERROR_MESSAGE);
				} 
				else if(pass_text.getText().equals("")) {
					JOptionPane.showMessageDialog(Update_Info.this,
						    "You check write your new password not be null to can update it.",
						    "update error",
						    JOptionPane.ERROR_MESSAGE);
				} 
				else if(first_name_text.getText().equals("")) {
					JOptionPane.showMessageDialog(Update_Info.this,
						    "You check write your new first name not be null to can update it.",
						    "update error",
						    JOptionPane.ERROR_MESSAGE);
				} 
				else if(last_name_text.getText().equals("")) {
					JOptionPane.showMessageDialog(Update_Info.this,
						    "You check write your new last name not be null to can update it.",
						    "update error",
						    JOptionPane.ERROR_MESSAGE);
				} 
				else if(shipping_address_text.getText().equals("")) {
					JOptionPane.showMessageDialog(Update_Info.this,
						    "You check write your new shipping address not be null to can update it.",
						    "update error",
						    JOptionPane.ERROR_MESSAGE);
				} 
				else if(phone_text.getText().equals("")) {
					JOptionPane.showMessageDialog(Update_Info.this,
						    "You check write your new phone not be null to can update it.",
						    "update error",
						    JOptionPane.ERROR_MESSAGE);
				}
				else {
				String sql = "select update_user_info('"+ email_text.getText() + "','" + 
						username_text.getText() + "','" + pass_text.getText() + "','" + first_name_text.getText() + "','"
						+ last_name_text.getText() + "','" +
						shipping_address_text.getText() + "','" +phone_text.getText() + "');";
	            try {
					
					ResultSet rs1 = MainFram.stmt.executeQuery(sql);
		               while(rs1.next()) {
		            	   String out = rs1.getString("update_user_info('"+ email_text.getText() + "','"  + 
			            	   		username_text.getText() + "','" + pass_text.getText() + "','" + first_name_text.getText()
				            	   	 + "','" + last_name_text.getText() + "','" 
				            	   	+ shipping_address_text.getText() + "','" +phone_text.getText() + "')");
		            	   System.out.println (out);
		   		        }
	            	   dispose();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btnUpdate.setFont(new Font("Stencil", Font.BOLD, 16));
		btnUpdate.setForeground(Color.BLUE);
		btnUpdate.setBounds(141, 466, 129, 39);
		mainFrame.getContentPane().add(btnUpdate);
		
		
	}

}
