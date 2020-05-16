package bookstore;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;

public class AddBookToCartFram extends JFrame {

	private JFrame mainFrame;
	private JTextField Title_text;
	private JTextField price_text;
	private JTextField quantity_text;
	private JButton btnOK;

	/**
	 * Create the frame.
	 */
	public AddBookToCartFram() {
		getContentPane().setLayout(null);
		mainFrame = new JFrame("Add Book To Cart");
		mainFrame.setBounds(100, 100, 500, 321);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setVisible(true);
		
		JLabel lblCreateNewAcount = new JLabel("Add Book To Cart");
		lblCreateNewAcount.setForeground(Color.RED);
		lblCreateNewAcount.setFont(new Font("Segoe Script", Font.BOLD, 18));
		lblCreateNewAcount.setBounds(133, 11, 312, 45);
		mainFrame.getContentPane().add(lblCreateNewAcount);
		
		JLabel lblTitle = new JLabel("Title :");
		lblTitle.setForeground(Color.RED);
		lblTitle.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblTitle.setBounds(20, 50, 154, 24);
		mainFrame.getContentPane().add(lblTitle);
		
		Title_text = new JTextField();
		Title_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		Title_text.setBounds(204, 50, 250, 40);
		Title_text.setEnabled(false);
		mainFrame.getContentPane().add(Title_text);
		
		JLabel lblprice = new JLabel("Price :");
		lblprice.setForeground(Color.RED);
		lblprice.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblprice.setBounds(21, 100, 153, 24);
		mainFrame.getContentPane().add(lblprice);
		
		price_text = new JTextField("");
		price_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		price_text.setBounds(204, 100, 250, 40);
		price_text.setEnabled(false);
		mainFrame.getContentPane().add(price_text);
		
		JLabel lblQuan = new JLabel("Available quantity :");
		lblQuan.setForeground(Color.RED);
		lblQuan.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblQuan.setBounds(20, 150, 154, 24);
		mainFrame.getContentPane().add(lblQuan);
		
		quantity_text = new JTextField("");
		quantity_text.setFont(new Font("Tahoma", Font.BOLD, 16));
		quantity_text.setBounds(204, 150, 250, 40);
		quantity_text.setEnabled(false);
		mainFrame.getContentPane().add(quantity_text);
		
		JLabel lblselectQuan = new JLabel("<html>Select quantity<br/>you want to add:</html>");
		lblselectQuan.setForeground(Color.RED);
		lblselectQuan.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblselectQuan.setBounds(20, 200, 154, 70);
		mainFrame.getContentPane().add(lblselectQuan);
		
		SpinnerNumberModel model1 = new SpinnerNumberModel(1.0, 0.0, 9.0, 1.0);  
		JSpinner spinner = new JSpinner(model1);
		spinner.setBounds(160, 210, 50, 40);
		mainFrame.getContentPane().add(spinner);
		
		
		btnOK = new JButton("ADD");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    spinner.commitEdit();
				} catch ( java.text.ParseException e1 ) {
					
				}
				int value = (Integer) spinner.getValue();
				mainFrame.dispose();
			}
		});
		btnOK.setFont(new Font("Stencil", Font.BOLD, 16));
		btnOK.setForeground(Color.BLUE);
		btnOK.setBounds(300, 232, 129, 39);
		mainFrame.getContentPane().add(btnOK);
		
		
	}
}
