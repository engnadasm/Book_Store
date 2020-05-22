package bookstore;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sign_upFrame extends JFrame {

	private JPanel contentPane;
	private JTextField email_text;
	private JTextField pass_text;
	private JTextField phone_text;
	private JTextField username_text;
	private JTextField first_name_text;
	private JTextField last_name_text;
	private JTextField shipping_address_text;
	
	private JButton btnCreate;

	/**
	 * Create the frame.
	 */
	public Sign_upFrame() {
		setTitle("Sign_up");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel lblCreateNewAcount = new JLabel("Create new Acount");
		lblCreateNewAcount.setForeground(Color.RED);
		lblCreateNewAcount.setFont(new Font("Segoe Script", Font.BOLD, 18));
		lblCreateNewAcount.setBounds(127, 3, 213, 45);
		contentPane.add(lblCreateNewAcount);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setForeground(Color.RED);
		lblEmail.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblEmail.setBounds(11, 46, 70, 24);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(Color.RED);
		lblPassword.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblPassword.setBounds(10, 96, 100, 24);
		contentPane.add(lblPassword);
		
		email_text = new JTextField("");
		email_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		email_text.setBounds(157, 46, 250, 40);
		email_text.setDocument(new JTextFieldLimit(30));
		contentPane.add(email_text);
		
		pass_text = new JTextField();
		pass_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		pass_text.setBounds(157, 96, 250, 40);
		pass_text.setDocument(new JTextFieldLimit(30));
		contentPane.add(pass_text);
		
		JLabel lblUserName = new JLabel("User_Name :");
		lblUserName.setForeground(Color.RED);
		lblUserName.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblUserName.setBounds(11, 146, 112, 24);
		contentPane.add(lblUserName);
		
		JLabel lblFirstName = new JLabel("First_Name :");
		lblFirstName.setForeground(Color.RED);
		lblFirstName.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblFirstName.setBounds(11, 196, 100, 24);
		contentPane.add(lblFirstName);
		
		username_text = new JTextField("");
		username_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		username_text.setBounds(157, 146, 250, 40);
		username_text.setDocument(new JTextFieldLimit(55));
		contentPane.add(username_text);
		
		first_name_text = new JTextField();
		first_name_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		first_name_text.setBounds(157, 196, 250, 40);
		first_name_text.setDocument(new JTextFieldLimit(30));
		contentPane.add(first_name_text);
		
		JLabel lblSecondName = new JLabel("Last_Name :");
		lblSecondName.setForeground(Color.RED);
		lblSecondName.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblSecondName.setBounds(11, 246, 99, 24);
		contentPane.add(lblSecondName);
		
		JLabel lblPhone = new JLabel("Phone :");
		lblPhone.setForeground(Color.RED);
		lblPhone.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblPhone.setBounds(11, 296, 100, 24);
		contentPane.add(lblPhone);
		
		last_name_text = new JTextField("");
		last_name_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		last_name_text.setBounds(157, 246, 250, 40);
		last_name_text.setDocument(new JTextFieldLimit(30));
		contentPane.add(last_name_text);
		
		phone_text = new JTextField("");
		phone_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		phone_text.setBounds(157, 296, 250, 40);
		phone_text.setDocument(new JTextFieldLimit(12));
		contentPane.add(phone_text);
		
		JLabel lbladdress = new JLabel("Shipping_Address :");
		lbladdress.setForeground(Color.RED);
		lbladdress.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lbladdress.setBounds(10, 346, 143, 24);
		contentPane.add(lbladdress);
		
		shipping_address_text = new JTextField("");
		shipping_address_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		shipping_address_text.setBounds(157, 346, 250, 40);
		shipping_address_text.setDocument(new JTextFieldLimit(50));
		contentPane.add(shipping_address_text);
		
		btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(username_text.getText().equals("")) {
					JOptionPane.showMessageDialog(Sign_upFrame.this,
						    "You should write your user name not be null to can sign_up.",
	   						   "error message",
						    JOptionPane.ERROR_MESSAGE);
				} 
				else if(pass_text.getText().equals("")) {
					JOptionPane.showMessageDialog(Sign_upFrame.this,
						    "You should write your password not be null to can sign_up.",
	   						   "error message",
						    JOptionPane.ERROR_MESSAGE);
				} else if(email_text.getText().equals("")) {
					JOptionPane.showMessageDialog(Sign_upFrame.this,
						    "You should write your email not be null to can sign_up.",
	   						   "error message",
						    JOptionPane.ERROR_MESSAGE);
				}
				else if(first_name_text.getText().equals("")) {
					JOptionPane.showMessageDialog(Sign_upFrame.this,
						    "You should write your first name not be null to can sign_up.",
	   						   "error message",
						    JOptionPane.ERROR_MESSAGE);
				} 
				else if(last_name_text.getText().equals("")) {
					JOptionPane.showMessageDialog(Sign_upFrame.this,
						    "You should write your last name not be null to can sign_up.",
	   						   "error message",
						    JOptionPane.ERROR_MESSAGE);
				} 
				else if(shipping_address_text.getText().equals("")) {
					JOptionPane.showMessageDialog(Sign_upFrame.this,
						    "You should write your shipping address not be null to can sign_up.",
	   						   "error message",
						    JOptionPane.ERROR_MESSAGE);
				} 
				else if(phone_text.getText().equals("")) {
					JOptionPane.showMessageDialog(Sign_upFrame.this,
						    "You should write your phone not be null to can sign_up.",
	   						   "error message",
						    JOptionPane.ERROR_MESSAGE);
				}
				else {
				
				String sql = "select sign_up('"+ email_text.getText() + "','" + 
						username_text.getText() + "','" + pass_text.getText() + "','" + first_name_text.getText() + "','"
						+ last_name_text.getText() + "','" +
						shipping_address_text.getText() + "','" +phone_text.getText() + "');";
	            try {
					
					ResultSet rs1 = MainFram.stmt.executeQuery(sql);
		               while(rs1.next()) {
		            	   String out = rs1.getString("sign_up('"+ email_text.getText() + "','"  + 
			            	   		username_text.getText() + "','" + pass_text.getText() + "','" + first_name_text.getText()
				            	   	 + "','" + last_name_text.getText() + "','" 
				            	   	+ shipping_address_text.getText() + "','" +phone_text.getText() + "')");
		            	   //System.out.println (out);
		            	   if(out.equals("You sign_up Successfully.....")) {
			            	   dispose();
		            	   } else {
		            		   JOptionPane.showMessageDialog(Sign_upFrame.this,
		   						   out,
		   	   						   "error message",
		   						    JOptionPane.ERROR_MESSAGE);
		            	   }
		            	   
		               }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btnCreate.setFont(new Font("Stencil", Font.BOLD, 16));
		btnCreate.setForeground(Color.BLUE);
		btnCreate.setBounds(125, 416, 129, 39);
		contentPane.add(btnCreate);
		
		
	}

}