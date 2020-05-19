package bookstore;



import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;

public class BookOpFrame {

	private JFrame frame;
	private String[] authors;
	//add book
	private JTextField txtIsbn;
	private JTextField txtTitle;
	private JTextField txtCategory;
	private JTextField txtPrice;
	private JTextField txtThreshold;
	private JTextField txtQuantity;
	private JTextField txtPublisher;
	private JTextField txtName;
	private JTextField txtBook;
	private JTextField txtAddress;
	private JTextField txtTelephone;
	private JTextField txtAuthors;
	private JTextField textField_Author;
	private JList<String> list;
	private JScrollPane scrollPane;
	private JButton btnDelete;
	private JTextField textField_ISBN;
	private JTextField textField_Title;
	private JTextField textField_Category;
	private JTextField textField_Price;
	private JTextField textField_Threshold;
	private JTextField textField_Quantity;
	private JTextField textField_Name;
	private JTextField textField_Address;
	private JTextField textField_phone;
	private JTextField textField_pyear;
	private JTextField txtPublicationYear;
	private JButton btnAddName;
	private JButton btnAdd;
	private DefaultListModel<String> l;
	//confirm order
	private JTextField textField_confirm;
	private JTextField confrimISBN;
	private JButton btnConfirm;
	//order book
	private JTextField textField_I;
	private JTextField textlabelIsbn;
	private JTextField textField_Q;
	private JTextField orderQuantity;
	private JButton btnOrder;
	//message
	private JTextField textField_msg;


	public BookOpFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 370);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JRadioButton rdbtnAddBook = new JRadioButton("Add book");
		rdbtnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAddBookVisibilty(true);
				setConfirmVisibilty(false);
				setOrderVisibilty(false);
				textField_msg.setText("");
			}
		});
		rdbtnAddBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnAddBook.setBounds(6, 76, 120, 23);
		frame.getContentPane().add(rdbtnAddBook);
		
		JRadioButton rdbtnModifyBook = new JRadioButton("Modify book");
		rdbtnModifyBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnModifyBook.setBounds(6, 123, 120, 23);
		frame.getContentPane().add(rdbtnModifyBook);
		
		JRadioButton rdbtnOrderBook = new JRadioButton("Order book");
		rdbtnOrderBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAddBookVisibilty(false);
				setConfirmVisibilty(false);
				setOrderVisibilty(true);
				textField_msg.setText("");
			}
		});
		rdbtnOrderBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnOrderBook.setBounds(6, 237, 120, 23);
		frame.getContentPane().add(rdbtnOrderBook);
		
		JRadioButton rdbtnConfirmOrder = new JRadioButton("Confirm order");
		rdbtnConfirmOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAddBookVisibilty(false);
				setConfirmVisibilty(true);
				setOrderVisibilty(false);
				textField_msg.setText("");
			}
		});
		rdbtnConfirmOrder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnConfirmOrder.setBounds(6, 170, 120, 23);
		frame.getContentPane().add(rdbtnConfirmOrder);
		
		ButtonGroup bgroupOptions = new ButtonGroup();
		bgroupOptions.add(rdbtnAddBook);
		bgroupOptions.add(rdbtnModifyBook);
		bgroupOptions.add(rdbtnOrderBook);
		bgroupOptions.add(rdbtnConfirmOrder);
		
		txtIsbn = new JTextField();
		txtIsbn.setText("ISBN");
		txtIsbn.setEditable(false);
		txtIsbn.setBounds(151, 80, 41, 20);
		frame.getContentPane().add(txtIsbn);
		txtIsbn.setColumns(10);
		
		txtTitle = new JTextField();
		txtTitle.setText("Title");
		txtTitle.setEditable(false);
		txtTitle.setColumns(10);
		txtTitle.setBounds(151, 111, 41, 20);
		frame.getContentPane().add(txtTitle);
		
		txtCategory = new JTextField();
		txtCategory.setText("Category");
		txtCategory.setEditable(false);
		txtCategory.setColumns(10);
		txtCategory.setBounds(132, 142, 60, 20);
		frame.getContentPane().add(txtCategory);
		
		txtPrice = new JTextField();
		txtPrice.setText("price");
		txtPrice.setEditable(false);
		txtPrice.setColumns(10);
		txtPrice.setBounds(151, 173, 41, 20);
		frame.getContentPane().add(txtPrice);
		
		txtThreshold = new JTextField();
		txtThreshold.setText("Threshold");
		txtThreshold.setEditable(false);
		txtThreshold.setColumns(10);
		txtThreshold.setBounds(132, 209, 60, 20);
		frame.getContentPane().add(txtThreshold);
		
		txtQuantity = new JTextField();
		txtQuantity.setText("Quantity");
		txtQuantity.setEditable(false);
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(132, 240, 60, 20);
		frame.getContentPane().add(txtQuantity);
		
		txtPublisher = new JTextField();
		txtPublisher.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		txtPublisher.setText("publisher");
		txtPublisher.setEditable(false);
		txtPublisher.setColumns(10);
		txtPublisher.setBounds(408, 46, 102, 26);
		frame.getContentPane().add(txtPublisher);
		
		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setEditable(false);
		txtName.setColumns(10);
		txtName.setBounds(342, 80, 60, 20);
		frame.getContentPane().add(txtName);
		
		txtBook = new JTextField();
		txtBook.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		txtBook.setText("book");
		txtBook.setEditable(false);
		txtBook.setColumns(10);
		txtBook.setBounds(194, 49, 60, 20);
		frame.getContentPane().add(txtBook);
		
		txtAddress = new JTextField();
		txtAddress.setText("Address");
		txtAddress.setEditable(false);
		txtAddress.setColumns(10);
		txtAddress.setBounds(342, 111, 60, 20);
		frame.getContentPane().add(txtAddress);
		
		txtTelephone = new JTextField();
		txtTelephone.setText("Telephone");
		txtTelephone.setEditable(false);
		txtTelephone.setColumns(10);
		txtTelephone.setBounds(329, 142, 73, 20);
		frame.getContentPane().add(txtTelephone);
		
		txtAuthors = new JTextField();
		txtAuthors.setText("Authors");
		txtAuthors.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		txtAuthors.setEditable(false);
		txtAuthors.setColumns(10);
		txtAuthors.setBounds(408, 177, 102, 20);
		frame.getContentPane().add(txtAuthors);
		l = new DefaultListModel<>(); 
		list = new JList<>(l);
		list.setBounds(320, 200, 82, 60);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(list);
        scrollPane.setSize(82, 60);
        scrollPane.setLocation(320, 200);
        frame.getContentPane().add(scrollPane,BorderLayout.CENTER);
		list.setVisible(true);
		scrollPane.setVisible(true);
		
		textField_Author = new JTextField();
		textField_Author.setBounds(320, 271, 82, 20);
		frame.getContentPane().add(textField_Author);
		textField_Author.setColumns(10);
		
		btnAddName = new JButton("Add name");
		btnAddName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField_Author.getText().equals("")) {
				l.addElement(textField_Author.getText());
				list.setVisible(true);
				textField_Author.setText("");
				System.out.println(l.getSize());
				}
			}
		});
		btnAddName.setBounds(408, 270, 104, 23);
		frame.getContentPane().add(btnAddName);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print("before = "+ l.getSize());
				if(list.getSelectedIndex() != -1){
				l.removeElementAt(list.getSelectedIndex());
				System.out.print(l.getSize());
				}
			}
		});
		btnDelete.setBounds(421, 209, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		textField_ISBN = new JTextField();
		textField_ISBN.setColumns(10);
		textField_ISBN.setBounds(194, 80, 100, 20);
		frame.getContentPane().add(textField_ISBN);
		
		textField_Title = new JTextField();
		textField_Title.setColumns(10);
		textField_Title.setBounds(194, 111, 100, 20);
		frame.getContentPane().add(textField_Title);
		
		textField_Category = new JTextField();
		textField_Category.setColumns(10);
		textField_Category.setBounds(194, 142, 100, 20);
		frame.getContentPane().add(textField_Category);
		
		textField_Price = new JTextField();
		textField_Price.setColumns(10);
		textField_Price.setBounds(194, 173, 100, 20);
		frame.getContentPane().add(textField_Price);
		
		textField_Threshold = new JTextField();
		textField_Threshold.setColumns(10);
		textField_Threshold.setBounds(194, 209, 100, 20);
		frame.getContentPane().add(textField_Threshold);
		
		textField_Quantity = new JTextField();
		textField_Quantity.setColumns(10);
		textField_Quantity.setBounds(194, 240, 100, 20);
		frame.getContentPane().add(textField_Quantity);
		
		textField_Name = new JTextField();
		textField_Name.setColumns(10);
		textField_Name.setBounds(408, 80, 106, 20);
		frame.getContentPane().add(textField_Name);
		
		textField_Address = new JTextField();
		textField_Address.setColumns(10);
		textField_Address.setBounds(408, 111, 106, 20);
		frame.getContentPane().add(textField_Address);
		
		textField_phone = new JTextField();
		textField_phone.setColumns(10);
		textField_phone.setBounds(408, 142, 106, 20);
		frame.getContentPane().add(textField_phone);
		
		btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				authors = new String[l.getSize()];
				for(int i = 0; i < l.getSize(); i++) {
					authors[i]=l.getElementAt(i);
				}
				boolean noerror = IManager.addBook(textField_ISBN.getText(), textField_Title.getText(), textField_Price.getText(),
						textField_Name.getText(), textField_Address.getText(), textField_phone.getText(), textField_pyear.getText(), textField_Category.getText(),
						textField_Threshold.getText(), textField_Quantity.getText(), authors);
				if(noerror) {
					textField_msg.setForeground(Color.GREEN);
					textField_msg.setText("Operation is successfully completed");
					clearTextField();
				}else {
					textField_msg.setForeground(Color.RED);
					textField_msg.setText("Error occured while execution");
				}
			}
		});
		btnAdd.setForeground(Color.RED);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd.setBounds(171, 295, 112, 29);
		frame.getContentPane().add(btnAdd);
		
		textField_confirm = new JTextField();
		textField_confirm.setBounds(271, 173, 86, 20);
		frame.getContentPane().add(textField_confirm);
		textField_confirm.setColumns(10);
		
		confrimISBN = new JTextField();
		confrimISBN.setEditable(false);
		confrimISBN.setText("ISBN");
		confrimISBN.setColumns(10);
		confrimISBN.setBounds(179, 173, 86, 20);
		frame.getContentPane().add(confrimISBN);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setForeground(Color.RED);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean noerror = IManager.confirmOrder(textField_confirm.getText());
				if(noerror) {
					textField_msg.setForeground(Color.GREEN);
					textField_msg.setText("Operation is successfully completed");
					textField_confirm.setText("");
				}else {
					textField_msg.setForeground(Color.RED);
					textField_msg.setText("Error occured while execution");
				}
			
			}
		});
		btnConfirm.setBounds(236, 208, 89, 23);
		frame.getContentPane().add(btnConfirm);
		
		textField_I = new JTextField();
		textField_I.setColumns(10);
		textField_I.setBounds(217, 226, 86, 20);
		frame.getContentPane().add(textField_I);
		
		textlabelIsbn = new JTextField();
		textlabelIsbn.setEditable(false);
		textlabelIsbn.setText("ISBN");
		textlabelIsbn.setColumns(10);
		textlabelIsbn.setBounds(160, 226, 86, 20);
		frame.getContentPane().add(textlabelIsbn);
		
		btnOrder = new JButton("Order");
		btnOrder.setForeground(Color.RED);
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean noerror = IManager.placeOrder(textField_I.getText(), textField_Q.getText());
				if(noerror) {
					textField_msg.setForeground(Color.GREEN);
					textField_msg.setText("Operation is successfully completed");
					textField_I.setText("");
					textField_Q.setText("");
				}else {
					textField_msg.setForeground(Color.RED);
					textField_msg.setText("Error occured while execution");
				}
			}
		});
		btnOrder.setBounds(217, 261, 89, 23);
		frame.getContentPane().add(btnOrder);
		
		textField_Q = new JTextField();
		textField_Q.setColumns(10);
		textField_Q.setBounds(377, 226, 86, 20);
		frame.getContentPane().add(textField_Q);
		
		orderQuantity = new JTextField();
		orderQuantity.setText("Quantity");
		orderQuantity.setEditable(false);
		orderQuantity.setColumns(10);
		orderQuantity.setBounds(320, 226, 86, 20);
		frame.getContentPane().add(orderQuantity);
		
		textField_pyear = new JTextField();
		textField_pyear.setBounds(194, 271, 100, 20);
		frame.getContentPane().add(textField_pyear);
		textField_pyear.setColumns(10);
		
		txtPublicationYear = new JTextField();
		txtPublicationYear.setEditable(false);
		txtPublicationYear.setEnabled(true);
		txtPublicationYear.setText("Publication year");
		txtPublicationYear.setBounds(95, 271, 95, 20);
		frame.getContentPane().add(txtPublicationYear);
		txtPublicationYear.setColumns(10);
		
		textField_msg = new JTextField();
		textField_msg.setEditable(false);
		textField_msg.setBounds(293, 301, 231, 20);
		frame.getContentPane().add(textField_msg);
		textField_msg.setColumns(10);
		
		
		//set all to invisible
		setAddBookVisibilty(false);
		setConfirmVisibilty(false);
		setOrderVisibilty(false);
		frame.setVisible(true);
		
	}
	
	
	private void setAddBookVisibilty(boolean v ) {
		txtIsbn.setVisible(v);
		txtTitle.setVisible(v);
		txtCategory.setVisible(v);
		txtPrice.setVisible(v);
		txtThreshold.setVisible(v);
		txtQuantity.setVisible(v);
		txtPublisher.setVisible(v);
		txtName.setVisible(v);
		txtBook.setVisible(v);
		txtAddress.setVisible(v);
		txtTelephone.setVisible(v);
		txtAuthors.setVisible(v);
		textField_Author.setVisible(v);
		btnDelete.setVisible(v);
		textField_ISBN.setVisible(v);
		textField_Title.setVisible(v);
		textField_Category.setVisible(v);
		textField_Price.setVisible(v);
		textField_Threshold.setVisible(v);
		textField_Quantity.setVisible(v);
		textField_Name.setVisible(v);
		textField_Address.setVisible(v);
		textField_phone.setVisible(v);
		textField_pyear.setVisible(v);
		txtPublicationYear.setVisible(v);
		btnAddName.setVisible(v);
		btnAdd.setVisible(v);
		scrollPane.setVisible(v);
		list.setVisible(v);
	}
	private void setConfirmVisibilty(boolean v ) {

		textField_confirm.setVisible(v);
		 confrimISBN.setVisible(v);
		btnConfirm.setVisible(v);
		
	}
	private void setOrderVisibilty(boolean v ) {
		textField_I.setVisible(v);
		textlabelIsbn.setVisible(v);
		textField_Q.setVisible(v);
		orderQuantity.setVisible(v);
		btnOrder.setVisible(v);
	}
	private void clearTextField() {
		textField_Author.setText("");
		textField_ISBN.setText("");
		textField_Title.setText("");
		textField_Category.setText("");
		textField_Price.setText("");
		textField_Threshold.setText("");
		textField_Quantity.setText("");
		textField_Name.setText("");
		textField_Address.setText("");
		textField_phone.setText("");
		textField_pyear.setText("");
		l.removeAllElements();
	}
}
