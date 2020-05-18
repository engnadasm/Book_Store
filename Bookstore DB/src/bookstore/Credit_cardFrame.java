package bookstore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Date;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class Credit_cardFrame extends JFrame {

	private JFrame mainFrame;
	private static Start start = Start.getInstance();
	private JTextField textFieldCreditCard;

	/**
	 * Create the frame.
	 */
	public Credit_cardFrame() {
		getContentPane().setLayout(null);
		mainFrame = new JFrame("Credit_card Frame");
		mainFrame.setBounds(100, 100, 387, 255);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setVisible(true);
		
		JLabel lblCredit_card = new JLabel("Credit card number :");
		lblCredit_card.setForeground(Color.RED);
		lblCredit_card.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblCredit_card.setBounds(23, 11, 170, 29);
		mainFrame.getContentPane().add(lblCredit_card);
		
		JLabel lblexpiry_date = new JLabel(" Expiry date :");
		lblexpiry_date.setForeground(Color.RED);
		lblexpiry_date.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblexpiry_date.setBounds(23, 102, 99, 32);
		mainFrame.getContentPane().add(lblexpiry_date);
		
		textFieldCreditCard = new JTextField();
		textFieldCreditCard.setBounds(33, 51, 297, 29);
		textFieldCreditCard.setDocument(new JTextFieldLimit(16));
		PlainDocument doc = (PlainDocument) textFieldCreditCard.getDocument();
	    doc.setDocumentFilter(new MyIntFilter());
		mainFrame.getContentPane().add(textFieldCreditCard);
		
		Properties p = new Properties();
		p.put("text.today", "today");
		p.put("text.month", "month");
		p.put("text.year", "year");
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePicker.setBounds(132, 110, 198, 30);
		mainFrame.getContentPane().add(datePicker);
		
		JLabel lblNewLabel = new JLabel("*(Reqire 16 digit)");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(191, 19, 118, 14);
		mainFrame.getContentPane().add(lblNewLabel);
		
		JButton btnOrder = new JButton("Order ^_^");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date selectedDate = (Date) datePicker.getModel().getValue();
				if(selectedDate != null) {
					if(selectedDate.compareTo(new Date()) >= 0) {
						String[] books = start.getarrBooksCart();
						for(int i = 0; i < start.getCart().size(); i++) {
							String[] attr = start.getCart().get(books[i]);
							String  sqlSelect = "";
							//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

							SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
						    Date now = new Date();
						    String strDate = sdfDate.format(now);
						   sqlSelect = "call order_insertion('"+  books[i] + "', "+ attr[8] + ",  '" + start.getUserEmail() + "' , '"+ strDate+ "');";
						   try {
							MainFram.stmt.addBatch(sqlSelect);
							start.removeAllBookCart();
							mainFrame.hide();
							new Shopping_cartFrame();

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						}
						//System.out.println(sqlSelect);
							try {
								MainFram.stmt.executeBatch();
								/*ResultSet	rs = MainFram.stmt.executeQuery(sqlSelect);	
								while(rs.next()) {
									System.out.println("enter");
					            }*/
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
				}
				
			}
		});
		btnOrder.setFont(new Font("Snap ITC", Font.BOLD, 16));
		btnOrder.setForeground(Color.RED);
		btnOrder.setBounds(105, 168, 134, 23);
		mainFrame.getContentPane().add(btnOrder);
		
		
		
	}
}
