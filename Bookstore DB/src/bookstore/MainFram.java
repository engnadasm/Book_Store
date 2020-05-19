package bookstore;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.TextField;


public class MainFram extends JFrame{

	private JFrame frame;
	private TextField email_text;
	private TextField pass_text;
	private static Connection conn = null;
	public static Statement stmt;

	/**
	 * Create the application.
	 */
	public MainFram() {
		try
        {
			String userName = "root";
			String password = "1234abcd";
			String url = "jdbc:MySQL://localhost:3306/bookstore";        
			conn = DriverManager.getConnection (url, userName, password);
			System.out.println ("\nDatabase Connection Established...");
			stmt = conn.createStatement();
        }
        catch (Exception ex)
         {
		       System.err.println ("Cannot connect to database server");
			   ex.printStackTrace();
         }      
		   
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Log_in");
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		Label lblWelcome = new Label("Welcome");
		lblWelcome.setForeground(Color.RED);
		lblWelcome.setFont(new Font("Segoe Script", Font.BOLD, 18));
		lblWelcome.setBounds(127, 3, 166, 45);
		frame.getContentPane().add(lblWelcome);
		
		
		JButton btnLogin = new JButton("Log_in");
		btnLogin.setFont(new Font("Stencil", Font.BOLD, 16));
		btnLogin.setForeground(Color.BLUE);
		btnLogin.setBounds(147, 155, 129, 39);
		frame.getContentPane().add(btnLogin);
		
		JButton btnSignup = new JButton("Sign_up");
		btnSignup.setFont(new Font("Stencil", Font.BOLD, 16));
		btnSignup.setForeground(Color.BLUE);
		btnSignup.setBounds(147, 211, 129, 39);
		frame.getContentPane().add(btnSignup);
		
		JLabel lblEmail = new JLabel("email :");
		lblEmail.setForeground(Color.RED);
		lblEmail.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblEmail.setBounds(21, 62, 70, 24);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("password :");
		lblPassword.setForeground(Color.RED);
		lblPassword.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblPassword.setBounds(10, 103, 100, 24);
		frame.getContentPane().add(lblPassword);
		
		
		email_text = new TextField();
		//email_text.setDocument(new JTextFieldLimit(30));
		email_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		email_text.setBounds(134, 50, 250, 40);
		frame.getContentPane().add(email_text);
		
		pass_text = new TextField();
		//pass_text.setDocument(new JTextFieldLimit(30));
		pass_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		pass_text.setBounds(134, 100, 250, 40);
		frame.getContentPane().add(pass_text);
		
		 Start s = Start.getInstance();
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(email_text.getText().equals("")) {
         		   JOptionPane.showMessageDialog(MainFram.this,
						    "Please, write your email to can log_in.....",
	   						   "error message",
						    JOptionPane.ERROR_MESSAGE);
				} 
				else if(pass_text.getText().equals("")) {
         		   JOptionPane.showMessageDialog(MainFram.this,
						    "Please, write your password to can log_in.....",
	   						   "error message",
						    JOptionPane.ERROR_MESSAGE);
				} else {
				String sql = "select log_in('"+ email_text.getText() + "','" + pass_text.getText() + "');";
	            try {
					
					ResultSet rs1 = stmt.executeQuery(sql);
		               while(rs1.next()) {
		            	   String out = rs1.getString("log_in('"+ email_text.getText() + "','" + pass_text.getText() + "')");
		            	   if (out.equals("You log_in Successfully.....")) {
		            		   s.setUserEmail(email_text.getText());
		            	   
		
		            			   String sql2 = "SELECT * FROM users where email = '" + email_text.getText() + "';";
		            			    rs1 = stmt.executeQuery(sql2);
		    		               while(rs1.next()) {
		    		            	   System.out.println(rs1.getString("is_manager"));
				            		   s.setManager(rs1.getString("is_manager").equals("1"));
		    		               }
		            		   
			            	   frame.dispose();
		            		   new HomeFrame();
		            	   } else {
		            		   JOptionPane.showMessageDialog(MainFram.this,
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
	
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Sign_upFrame();
			}
		});
	}
}
