package bookstore;


import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesFrame {

	private JFrame frame;
	private static ResultSet rs;

	public SalesFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JRadioButton rdbtnTotalBooksales = new JRadioButton("The total sales for books in the previous month");
		rdbtnTotalBooksales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rs = IManager.totalBookSale();
				 DefaultListModel<String> l = new DefaultListModel<>(); 
				try {
					while(rs.next()) {
						l.addElement(rs.getString(1)+ " , "+rs.getString(2));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JList <String>list = new JList<>(l);
				list.setBounds(28, 214, 363, 221);
				frame.getContentPane().add(new JScrollPane(list),BorderLayout.CENTER);
			}
		});
		rdbtnTotalBooksales.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		rdbtnTotalBooksales.setBounds(19, 77, 358, 23);
		frame.getContentPane().add(rdbtnTotalBooksales);
		
		JRadioButton rdbtnTopCustomers = new JRadioButton("The top 5 customers for the last three months");
		rdbtnTopCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rs = IManager.top5Custmors();
				 DefaultListModel<String> l = new DefaultListModel<>(); 
				try {
					while(rs.next()) {
						l.addElement(rs.getString(1)+ " , "+rs.getString(2)+ " , "+rs.getString(3)+ " , "+rs.getString(4));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JList <String>list = new JList<>(l);
				list.setBounds(28, 214, 363, 221);
				frame.getContentPane().add(new JScrollPane(list),BorderLayout.CENTER);
			}
		});
		rdbtnTopCustomers.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		rdbtnTopCustomers.setBounds(18, 115, 359, 23);
		frame.getContentPane().add(rdbtnTopCustomers);
		
		JRadioButton rdbtnTopBookSales = new JRadioButton("The top 10 selling books for the last three months");
		rdbtnTopBookSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rs = IManager.top10Books();
				 DefaultListModel<String> l = new DefaultListModel<>(); 
				try {
					while(rs.next()) {
						l.addElement(rs.getString(1)+ " , "+rs.getString(2)+ " , "+rs.getString(3));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JList <String>list = new JList<>(l);
				list.setBounds(28, 214, 363, 221);
				frame.getContentPane().add(new JScrollPane(list),BorderLayout.CENTER);
			}
			
		});
		rdbtnTopBookSales.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		rdbtnTopBookSales.setBounds(19, 151, 359, 23);
		frame.getContentPane().add(rdbtnTopBookSales);
		
		ButtonGroup bgroupOptions = new ButtonGroup();
		bgroupOptions.add(rdbtnTotalBooksales);
		bgroupOptions.add(rdbtnTopCustomers);
		bgroupOptions.add(rdbtnTopBookSales);

		frame.setVisible(true);
	}

}
