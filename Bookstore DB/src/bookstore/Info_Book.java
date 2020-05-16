package bookstore;

import java.awt.BorderLayout;
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

public class Info_Book extends JFrame {

	private JFrame mainFrame;
	private JTextField ISBN_text;
	private JTextField Title_text;
	private JTextField p_year_text;
	private JTextField publishername_text;
	private JTextField category_text;
	private JTextField price_text;
	private JTextField quantity_text;
		
	private JButton btnOK;
	/**
	 * Create the frame.
	 */
	public Info_Book() {
		mainFrame = new JFrame("Book information");
		mainFrame.setBounds(100, 100, 526, 526);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setVisible(true);
		
		JLabel lblCreateNewAcount = new JLabel("Information of selected book");
		lblCreateNewAcount.setForeground(Color.RED);
		lblCreateNewAcount.setFont(new Font("Segoe Script", Font.BOLD, 18));
		lblCreateNewAcount.setBounds(73, 50, 312, 45);
		mainFrame.getContentPane().add(lblCreateNewAcount);
		
		JLabel lblISBN = new JLabel("ISBN :");
		lblISBN.setForeground(Color.RED);
		lblISBN.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblISBN.setBounds(11, 96, 70, 24);
		mainFrame.getContentPane().add(lblISBN);
		
		JLabel lblTitle = new JLabel("Title :");
		lblTitle.setForeground(Color.RED);
		lblTitle.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblTitle.setBounds(10, 146, 100, 24);
		mainFrame.getContentPane().add(lblTitle);
		
		ISBN_text = new JTextField("");
		ISBN_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		ISBN_text.setBounds(157, 96, 250, 40);
		ISBN_text.setEnabled(false);
		mainFrame.getContentPane().add(ISBN_text);
		
		Title_text = new JTextField();
		Title_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		Title_text.setBounds(157, 146, 250, 40);
		Title_text.setDocument(new JTextFieldLimit(30));
		mainFrame.getContentPane().add(Title_text);
		
		JLabel lblp_year = new JLabel("Publisher year :");
		lblp_year.setForeground(Color.RED);
		lblp_year.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblp_year.setBounds(11, 196, 112, 24);
		mainFrame.getContentPane().add(lblp_year);
		
		JLabel lblp_name = new JLabel("Publisher name :");
		lblp_name.setForeground(Color.RED);
		lblp_name.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblp_name.setBounds(11, 246, 100, 24);
		mainFrame.getContentPane().add(lblp_name);
		
		p_year_text = new JTextField("");
		p_year_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		p_year_text.setDocument(new JTextFieldLimit(55));
		p_year_text.setBounds(157, 196, 250, 40);
		mainFrame.getContentPane().add(p_year_text);
		
		publishername_text = new JTextField();
		publishername_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		publishername_text.setBounds(157, 246, 250, 40);
		publishername_text.setDocument(new JTextFieldLimit(30));
		mainFrame.getContentPane().add(publishername_text);
		
		JLabel lblCatogry = new JLabel("Category :");
		lblCatogry.setForeground(Color.RED);
		lblCatogry.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblCatogry.setBounds(11, 296, 99, 24);
		mainFrame.getContentPane().add(lblCatogry);
		
		JLabel lblprice = new JLabel("Price :");
		lblprice.setForeground(Color.RED);
		lblprice.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblprice.setBounds(11, 346, 100, 24);
		mainFrame.getContentPane().add(lblprice);
		
		category_text = new JTextField("");
		category_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		category_text.setBounds(157, 296, 250, 40);
		category_text.setDocument(new JTextFieldLimit(30));
		mainFrame.getContentPane().add(category_text);
		
		price_text = new JTextField("");
		price_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		price_text.setBounds(157, 346, 250, 40);
		price_text.setDocument(new JTextFieldLimit(12));
		mainFrame.getContentPane().add(price_text);
		
		JLabel lblQuan = new JLabel("Available quantity :");
		lblQuan.setForeground(Color.RED);
		lblQuan.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblQuan.setBounds(10, 396, 143, 24);
		mainFrame.getContentPane().add(lblQuan);
		
		quantity_text = new JTextField("");
		quantity_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		quantity_text.setBounds(157, 396, 250, 40);
		quantity_text.setDocument(new JTextFieldLimit(50));
		mainFrame.getContentPane().add(quantity_text);
		   Start s = Start.getInstance();


		String sqlSelect = "SELECT * FROM users where email = '" + s.getUserEmail() + "';";
		try {
			
			ResultSet rs = MainFram.stmt.executeQuery(sqlSelect);
            while(rs.next()) {
            	ISBN_text.setText(rs.getString("email"));
			Title_text.setText(rs.getString("password_user"));
			p_year_text.setText(rs.getString("phone"));
			publishername_text.setText(rs.getString("user_name"));
			category_text.setText(rs.getString("first_name"));
			price_text.setText(rs.getString("last_name"));
			quantity_text.setText(rs.getString("shipping_address"));
            }
			 	  
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
			}
		});
		btnOK.setFont(new Font("Stencil", Font.BOLD, 16));
		btnOK.setForeground(Color.BLUE);
		btnOK.setBounds(141, 466, 129, 39);
		mainFrame.getContentPane().add(btnOK);
	}

}
