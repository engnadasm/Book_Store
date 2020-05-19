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
	private Start s = Start.getInstance();

	private JButton btnOK;
	private JTextField textField_msg;
	/**
	 * Create the frame.
	 */
	public Info_Book() {
		mainFrame = new JFrame("Book information");
		mainFrame.setBounds(100, 100, 526, 526);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setVisible(true);
		
		JLabel lblCreateNewAcount = new JLabel("Information of selected book");
		lblCreateNewAcount.setForeground(Color.RED);
		lblCreateNewAcount.setFont(new Font("Segoe Script", Font.BOLD, 18));
		lblCreateNewAcount.setBounds(120, 11, 312, 45);
		mainFrame.getContentPane().add(lblCreateNewAcount);
		
		JLabel lblISBN = new JLabel("ISBN :");
		lblISBN.setForeground(Color.RED);
		lblISBN.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblISBN.setBounds(21, 57, 185, 24);
		mainFrame.getContentPane().add(lblISBN);
		
		JLabel lblTitle = new JLabel("Title :");
		lblTitle.setForeground(Color.RED);
		lblTitle.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblTitle.setBounds(20, 107, 186, 24);
		mainFrame.getContentPane().add(lblTitle);
		
		ISBN_text = new JTextField("");
		ISBN_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		ISBN_text.setBounds(204, 57, 250, 40);
		if(!s.isManager())
			ISBN_text.setEnabled(false);
		mainFrame.getContentPane().add(ISBN_text);
		
		Title_text = new JTextField();
		Title_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		Title_text.setBounds(204, 107, 250, 40);
		if(!s.isManager())
		Title_text.setEnabled(false);
		mainFrame.getContentPane().add(Title_text);
		
		JLabel lblp_year = new JLabel("Publisher year :");
		lblp_year.setForeground(Color.RED);
		lblp_year.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblp_year.setBounds(21, 157, 185, 24);
		mainFrame.getContentPane().add(lblp_year);
		
		JLabel lblp_name = new JLabel("Publisher name :");
		lblp_name.setForeground(Color.RED);
		lblp_name.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblp_name.setBounds(21, 207, 185, 24);
		mainFrame.getContentPane().add(lblp_name);
		
		p_year_text = new JTextField("");
		p_year_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		if(!s.isManager())
		p_year_text.setEnabled(false);
		p_year_text.setBounds(204, 157, 250, 40);
		mainFrame.getContentPane().add(p_year_text);
		
		publishername_text = new JTextField();
		publishername_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		publishername_text.setBounds(204, 207, 250, 40);
		if(!s.isManager())
		publishername_text.setEnabled(false);
		mainFrame.getContentPane().add(publishername_text);
		
		JLabel lblCatogry = new JLabel("Category :");
		lblCatogry.setForeground(Color.RED);
		lblCatogry.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblCatogry.setBounds(21, 257, 185, 24);
		mainFrame.getContentPane().add(lblCatogry);
		
		JLabel lblprice = new JLabel("Price :");
		lblprice.setForeground(Color.RED);
		lblprice.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblprice.setBounds(21, 307, 185, 24);
		mainFrame.getContentPane().add(lblprice);
		
		category_text = new JTextField("");
		category_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		category_text.setBounds(204, 257, 250, 40);
		if(!s.isManager())
		category_text.setEnabled(false);
		mainFrame.getContentPane().add(category_text);
		
		price_text = new JTextField("");
		price_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		price_text.setBounds(204, 307, 250, 40);
		if(!s.isManager())
		price_text.setEnabled(false);
		mainFrame.getContentPane().add(price_text);
		
		JLabel lblQuan = new JLabel("Available quantity :");
		lblQuan.setForeground(Color.RED);
		lblQuan.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblQuan.setBounds(20, 357, 186, 24);
		mainFrame.getContentPane().add(lblQuan);
		
		quantity_text = new JTextField("");
		quantity_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		quantity_text.setBounds(204, 357, 250, 40);
		if(!s.isManager())
		quantity_text.setEnabled(false);
		mainFrame.getContentPane().add(quantity_text);
		
		
		String sqlSelect = "SELECT * FROM Book where ISBN = '" + s.getISBNInfo_Book() + "';";
		try {
			
			ResultSet rs = MainFram.stmt.executeQuery(sqlSelect);
            while(rs.next()) {
            ISBN_text.setText(rs.getString("ISBN"));
			Title_text.setText(rs.getString("title"));
			p_year_text.setText(rs.getString("p_year"));
			publishername_text.setText(rs.getString("publisher_name"));
			category_text.setText(rs.getString("category"));
			price_text.setText(rs.getString("price"));
			quantity_text.setText(rs.getString("quantity"));
            }
			 	  
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(s.isManager()) {
					
					boolean noerror = IManager.modifyBook(s.getISBNInfo_Book(),ISBN_text.getText(), Title_text.getText(),
														price_text.getText(),publishername_text.getText(),
														p_year_text.getText(), category_text.getText(), quantity_text.getText());
					if(noerror) {
						textField_msg.setForeground(Color.GREEN);
						textField_msg.setText("Operation is successfully completed");
					}else {
						textField_msg.setForeground(Color.RED);
						textField_msg.setText("Error occured while execution");
					}
				}

				//mainFrame.hide();
			}
		});
		btnOK.setFont(new Font("Stencil", Font.BOLD, 16));
		btnOK.setForeground(Color.BLUE);
		btnOK.setBounds(186, 408, 129, 39);
		mainFrame.getContentPane().add(btnOK);
		
		textField_msg = new JTextField();
		textField_msg.setEditable(false);
		textField_msg.setBounds(78, 458, 354, 20);
		mainFrame.getContentPane().add(textField_msg);
		textField_msg.setColumns(10);
	}

}
