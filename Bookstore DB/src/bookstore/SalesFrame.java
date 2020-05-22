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
import javax.swing.JTextField;

public class SalesFrame {

	private JFrame frame;
	private static ResultSet rs;
	private JTextField txtAttr;

	public SalesFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);;
		frame.getContentPane().setLayout(null);
		DefaultListModel<String> l = new DefaultListModel<>(); 
		JList <String>list = new JList<>(l);
		list.setBounds(28, 214, 363, 221);
		JScrollPane scrollpane = new JScrollPane(list);
		scrollpane.setSize(400, 200);
		scrollpane.setLocation(19, 200);
		frame.getContentPane().add(scrollpane,BorderLayout.CENTER);
		
		JRadioButton rdbtnTotalBooksales = new JRadioButton("The total sales for books in the previous month");
		rdbtnTotalBooksales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAttr.setText("ISBN    Total Sales");
				rs = IManager.totalBookSale();
				l.removeAllElements();
				try {
					while(rs.next()) {
						l.addElement(rs.getString(1)+ " = "+rs.getString(2));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		rdbtnTotalBooksales.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		rdbtnTotalBooksales.setBounds(19, 77, 358, 23);
		frame.getContentPane().add(rdbtnTotalBooksales);
		
		JRadioButton rdbtnTopCustomers = new JRadioButton("The top 5 customers for the last three months");
		rdbtnTopCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAttr.setText("first name   last name    purchase amount   email");
				rs = IManager.top5Custmors();
				l.removeAllElements(); 
				try {
					while(rs.next()) {
						l.addElement(rs.getString(1)+ " , "+rs.getString(2)+ " , "+rs.getString(3)+ " , "+rs.getString(4));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		rdbtnTopCustomers.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		rdbtnTopCustomers.setBounds(18, 115, 359, 23);
		frame.getContentPane().add(rdbtnTopCustomers);
		
		JRadioButton rdbtnTopBookSales = new JRadioButton("The top 10 selling books for the last three months");
		rdbtnTopBookSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAttr.setText("ISBN     Title     Purchase Amount");
				rs = IManager.top10Books();
				l.removeAllElements();
				try {
					while(rs.next()) {
						l.addElement(rs.getString(1)+ " , "+rs.getString(2)+ " , "+rs.getString(3));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		rdbtnTopBookSales.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		rdbtnTopBookSales.setBounds(19, 151, 359, 23);
		frame.getContentPane().add(rdbtnTopBookSales);
		
		ButtonGroup bgroupOptions = new ButtonGroup();
		bgroupOptions.add(rdbtnTotalBooksales);
		bgroupOptions.add(rdbtnTopCustomers);
		bgroupOptions.add(rdbtnTopBookSales);
		
		txtAttr = new JTextField();
		txtAttr.setEditable(false);
		txtAttr.setBounds(19, 179, 400, 20);
		frame.getContentPane().add(txtAttr);
		txtAttr.setColumns(10);

		frame.setVisible(true);
	}
}
