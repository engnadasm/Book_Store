package bookstore;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class Shopping_cartFrame extends JFrame {

private static Start start = Start.getInstance();
	
	private JFrame mainFrame;
	private static HashMap<String, String[]> cart = start.getCart();
	private static String[] arrBooks = start.getarrBooksCart();
	private static int noBooks = cart.size();
	private static int noPags = noBooks/10;
	private static int currPag = start.getpagNumSearch();
	private int tatal_price = 0;
	
	private static HashMap<String, String[]> books = start.getSearchBook();
	private String[] attr;
	private boolean first = start.getBoolFirst();
	private int[] all_price = new int[noBooks + 1];
	
	JTextField[] all_JTextField = new JTextField[]{new JTextField(), new JTextField(),new JTextField(),
			new JTextField(), new JTextField(),new JTextField(),
			new JTextField(), new JTextField(),new JTextField(),
			new JTextField(), new JTextField(),new JTextField(),
			new JTextField(), new JTextField(),new JTextField(),
			new JTextField(), new JTextField(),new JTextField(),
			new JTextField(), new JTextField(),new JTextField(),
			new JTextField(), new JTextField(),new JTextField(),
			new JTextField(), new JTextField(),new JTextField(),
			new JTextField(), new JTextField(),new JTextField()};
	
	JPanel[] all_JPanel = new JPanel[]{new JPanel(), new JPanel(),new JPanel(),
			new JPanel(), new JPanel(),new JPanel(),
			new JPanel(), new JPanel(),new JPanel(),
			new JPanel(),new JPanel()};
    JButton[] all_Btn = new JButton[31];
    
    private JTextField textField;
	/**
	 * Create the frame.
	 */
	public Shopping_cartFrame() {
		start = Start.getInstance();
		books = start.getSearchBook();
		cart = start.getCart();
		arrBooks = start.getarrBooksCart();
		noBooks = cart.size();
		noPags = noBooks/10;
	    currPag = start.getpagNumSearch();
		 first = start.getBoolFirst();
		 tatal_price = 0;
		all_price = new int[noBooks + 1];
		for(int k = 0; k < noBooks; k++) {
			 attr = cart.get(arrBooks[k]);
		     all_price[k] = Integer.parseInt(attr[8]) * Integer.parseInt(attr[4]);
		     tatal_price = tatal_price + all_price[k];
		}

		if((noBooks%10 )!= 0 && first) {
			noPags++;
			start.setBoolFirst(first);
		}
		
		mainFrame = new JFrame("Cart");
		mainFrame.setName("Cart");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(100, 100, 543, 601);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setVisible(true);
		
		JLabel lblBookid = new JLabel("Book_ID");
		lblBookid.setForeground(Color.RED);
		lblBookid.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblBookid.setBounds(10, 50, 80, 15);
		mainFrame.getContentPane().add(lblBookid);
		
		JLabel lblBooktitle = new JLabel("Book_Title");
		lblBooktitle.setForeground(Color.RED);
		lblBooktitle.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblBooktitle.setBounds(100, 50, 80, 15);
		mainFrame.getContentPane().add(lblBooktitle);
		
		JLabel lblBookprice = new JLabel("Order_Price");
		lblBookprice.setForeground(Color.RED);
		lblBookprice.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblBookprice.setBounds(190, 50, 90, 15);
		mainFrame.getContentPane().add(lblBookprice);
		
		JLabel lblPagenum = new JLabel("Page number " + currPag + " of total "+ noPags);
		lblPagenum.setForeground(Color.RED);
		lblPagenum.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblPagenum.setBounds(300, 50, 200, 15);
		mainFrame.getContentPane().add(lblPagenum);
		Start.Load_menubar(mainFrame);

		initialize();
				
				JLabel lblTotalprice = new JLabel("Total_Price : ");
				lblTotalprice.setForeground(Color.RED);
				lblTotalprice.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
				lblTotalprice.setBounds(0, 504, 98, 23);
				mainFrame.getContentPane().add(lblTotalprice);
				
				textField = new JTextField();
				textField.setText("" + tatal_price);
				textField.setEditable(false);
				textField.setBounds(100, 503, 59, 24);
				textField.setFont(new Font("Tahoma", Font.BOLD, 16));
				mainFrame.getContentPane().add(textField);
				
				JButton btnConfirm_all = new JButton("Confirm_all");
				btnConfirm_all.setFont(new Font("Stencil", Font.BOLD, 14));
				btnConfirm_all.setForeground(Color.BLUE);
				btnConfirm_all.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
				    	mainFrame.hide();
						new Credit_cardFrame();
						
					}
				});
				btnConfirm_all.setBounds(178, 504, 149, 24);
				mainFrame.getContentPane().add(btnConfirm_all);
				
				JButton btnRemoveall = new JButton("Remove_all");
				btnRemoveall.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						start.removeAllBookCart();
						mainFrame.hide();
						new Shopping_cartFrame();
					}
				});
				btnRemoveall.setFont(new Font("Stencil", Font.BOLD, 14));
				btnRemoveall.setForeground(Color.BLUE);
				btnRemoveall.setBounds(369, 505, 141, 23);
				mainFrame.getContentPane().add(btnRemoveall);
				
				JLabel lblOr = new JLabel("OR");
				lblOr.setForeground(Color.RED);
				lblOr.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
				lblOr.setBounds(337, 508, 22, 14);
				mainFrame.getContentPane().add(lblOr);
				
				JButton btnNewButtonN = new JButton("Next");
				btnNewButtonN.setBounds(384, 76, 80, 20);
				mainFrame.getContentPane().add(btnNewButtonN);
				
				JButton btnNewButtonP = new JButton("Previous");
				btnNewButtonP.setBounds(284, 76, 90, 20);
				mainFrame.getContentPane().add(btnNewButtonP);
				/**
				 * action of button go to get number of page.
				 */
				btnNewButtonN.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) { 
						if (currPag < noPags) {
							currPag++;
							System.out.println(currPag);
							lblPagenum.setText("Page number " + currPag + " of total "+ noPags);
					    	start.setPagNumSearch(currPag);
					    	mainFrame.hide();
							new Shopping_cartFrame();
 
						}
					}
				});
				
				btnNewButtonP.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (currPag > 1) {
							currPag--;
							System.out.println(currPag);
							lblPagenum.setText("Page number " + currPag + " of total "+ noPags);
					    	start.setPagNumSearch(currPag);
					    	mainFrame.hide();
							new Shopping_cartFrame();
						}
					}
				});
				
	}
	
	private void function() {
		cart = start.getCart();
		books = start.getSearchBook();
        arrBooks = start.getarrBooksCart();

		 int noCurrBook = noBooks-(10*(currPag-1));
		 if(noCurrBook > 10) {
			 noCurrBook = 10;
		 }
		 		 
		 for (int y = 1; y <= noCurrBook; y++) {
			 all_JPanel[y].setVisible(true);
			 attr = cart.get(arrBooks[(y - 1) + 10 * (currPag - 1)]);
			 /*
				 * 0- title
					1- p_year
					2- publisher_name
				3-	category
				4-	price
					5- threshold
				6- 	quantity
					7- added to cart 1 or not 0
					8- in cart
				 */
		     all_JTextField[3 * y - 3].setText(arrBooks[(y - 1) + 10 * (currPag - 1)]);
		     all_JTextField[3 * y - 2].setText(attr[0]);
		     int allPrice = Integer.parseInt(attr[4]) * Integer.parseInt(attr[8]);  
		     all_JTextField[3 * y - 1].setText(attr[8] + " * " + attr[4] + " = " + allPrice);
		     
			 all_Btn[2 * y - 1].addActionListener(new PageActionListene(y));
			 
			 all_Btn[2 * y].addActionListener(new PageActionListener2(y));
		 
			 //all_Btn[(3 * y) % 10 + ].addActionListener(new PageActionListener2(y));

		 }
	}
	private class PageActionListener2 implements ActionListener {
	    private int y;

	    public PageActionListener2(int y) {
	        this.y = y;
	    }

	    public void actionPerformed(ActionEvent e) {
	    	start.setISBNInfo_Book(arrBooks[(y - 1) + 10 * (currPag - 1)]);
			new Info_Book();
		}
	}
	
	private class PageActionListene implements ActionListener {
	    private int y;

	    public PageActionListene(int y) {
	        this.y = y;
	    }

	    public void actionPerformed(ActionEvent e) {
	    	attr[7] = "0";
	    	//attr[8] = "0";
			books.put(arrBooks[(y - 1) + 10 * (currPag - 1)], attr);
			start.searchBooks(books);
	    	start.removeBookFromCart(arrBooks[(y - 1) + 10 * (currPag - 1)]);
	    	
	        String[] proxyArray = new String[arrBooks.length - 1]; 
	        // copy all the elements in the original to proxy array except the one at index 
	        for (int i = 0, k = 0; i < arrBooks.length; i++) { 
	            // check if index is crossed, continue without copying 
	            if (i == ((y - 1) + 10 * (currPag - 1))) { 
	                continue; 
	            } 
	 
	            // else copy the element
	            proxyArray[k++] = arrBooks[i]; 
	        }
	        start.setarrBooksCart(proxyArray);
	        arrBooks = start.getarrBooksCart();
			cart = start.getCart();
			books = start.getSearchBook();
			mainFrame.hide();
			new Shopping_cartFrame();
		}
	}

	private void initialize() {
		//related to 1_book
		//JPanel all_JPanel[1 = new JPanel();
		all_JPanel[1].setLayout(null);
		all_JPanel[1].setBounds(0, 100, 500, 30);
		all_JPanel[1].setVisible(false);
		mainFrame.getContentPane().add(all_JPanel[1]);		
		
		all_JTextField[0] = new JTextField();
		all_JTextField[0].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[0].setEditable(false);
		all_JTextField[0].setBounds(10, 0, 80, 30);
		all_JPanel[1].add(all_JTextField[0]);
		
		all_JTextField[1] = new JTextField();
		all_JTextField[1].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[1].setEditable(false);
		all_JTextField[1].setBounds(100, 0, 80, 30);
		all_JPanel[1].add(all_JTextField[1]);
		
		all_JTextField[2] = new JTextField();
		all_JTextField[2].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[2].setEditable(false);
		all_JTextField[2].setBounds(190, 0, 90, 30);
		all_JPanel[1].add(all_JTextField[2]);
		
		 all_Btn[1] = new JButton("Remove");
		all_Btn[1].setBounds(290, 6, 89, 23);
		all_Btn[1].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[1].setForeground(Color.BLUE);
		all_JPanel[1].add(all_Btn[1]);
		
		 all_Btn[2] = new JButton("Info. of book");
		all_Btn[2].setBounds(389, 6, 89, 23);
		all_Btn[2].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[2].setForeground(Color.BLUE);
		all_JPanel[1].add(all_Btn[2]);
		
		all_Btn[21] = new JButton("Confirm");
		all_Btn[21].setBounds(490, 6, 89, 23);
		all_Btn[21].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[21].setForeground(Color.BLUE);
	//	all_JPanel[1].add(all_Btn[21]);

//related to 2_book
		//JPanel all_JPanel[2 = new JPanel();
		all_JPanel[2].setLayout(null);
		all_JPanel[2].setBounds(0, 140, 500, 30);
		all_JPanel[2].setVisible(false);
		mainFrame.getContentPane().add(all_JPanel[2]);		
		
		all_JTextField[3] = new JTextField();
		all_JTextField[3].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[3].setEditable(false);
		all_JTextField[3].setBounds(10, 0, 80, 30);
		all_JPanel[2].add(all_JTextField[3]);
		
		all_JTextField[4] = new JTextField();
		all_JTextField[4].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[4].setEditable(false);
		all_JTextField[4].setBounds(100, 0, 80, 30);
		all_JPanel[2].add(all_JTextField[4]);
		
		all_JTextField[5] = new JTextField();
		all_JTextField[5].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[5].setEditable(false);
		all_JTextField[5].setBounds(190, 0, 90, 30);
		all_JPanel[2].add(all_JTextField[5]);
		
		all_Btn[3] = new JButton("Remove");
		all_Btn[3].setBounds(290, 6, 89, 23);
		all_Btn[3].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[3].setForeground(Color.BLUE);
		all_JPanel[2].add(all_Btn[3]);
		
		all_Btn[4] = new JButton("Info. of book");
		all_Btn[4].setBounds(389, 6, 89, 23);
		all_Btn[4].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[4].setForeground(Color.BLUE);
		all_JPanel[2].add(all_Btn[4]);
		
		all_Btn[22] = new JButton("Confirm");
		all_Btn[22].setBounds(490, 6, 89, 23);
		all_Btn[22].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[22].setForeground(Color.BLUE);
		//all_JPanel[2].add(all_Btn[22]);
		
//related to 3_book
		//JPanel all_JPanel[3 = new JPanel();
		all_JPanel[3].setLayout(null);
		all_JPanel[3].setBounds(0, 180, 500, 30);
		all_JPanel[3].setVisible(false);
		mainFrame.getContentPane().add(all_JPanel[3]);		
		
		all_JTextField[6] = new JTextField();
		all_JTextField[6].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[6].setEditable(false);
		all_JTextField[6].setBounds(10, 0, 80, 30);
		all_JPanel[3].add(all_JTextField[6]);
		
		all_JTextField[7] = new JTextField();
		all_JTextField[7].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[7].setEditable(false);
		all_JTextField[7].setBounds(100, 0, 80, 30);
		all_JPanel[3].add(all_JTextField[7]);
		
		all_JTextField[8] = new JTextField();
		all_JTextField[8].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[8].setEditable(false);
		all_JTextField[8].setBounds(190, 0, 90, 30);
		all_JPanel[3].add(all_JTextField[8]);
		
		all_Btn[5] = new JButton("Remove");
		all_Btn[5].setBounds(290, 6, 89, 23);
		all_Btn[5].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[5].setForeground(Color.BLUE);
		all_JPanel[3].add(all_Btn[5]);
		
		all_Btn[6] = new JButton("Info. of book");
		all_Btn[6].setBounds(389, 6, 89, 23);
		all_Btn[6].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[6].setForeground(Color.BLUE);
		all_JPanel[3].add(all_Btn[6]);
		
		all_Btn[23] = new JButton("Confirm");
		all_Btn[23].setBounds(490, 6, 89, 23);
		all_Btn[23].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[23].setForeground(Color.BLUE);
	//	all_JPanel[3].add(all_Btn[23]);
		
//related to 4_book
		//JPanel all_JPanel[4 = new JPanel();
		all_JPanel[4].setLayout(null);
		all_JPanel[4].setBounds(0, 220, 500, 30);
		all_JPanel[4].setVisible(false);
		mainFrame.getContentPane().add(all_JPanel[4]);		
		
		all_JTextField[9] = new JTextField();
		all_JTextField[9].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[9].setEditable(false);
		all_JTextField[9].setBounds(10, 0, 80, 30);
		all_JPanel[4].add(all_JTextField[9]);
		
		all_JTextField[10] = new JTextField();
		all_JTextField[10].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[10].setEditable(false);
		all_JTextField[10].setBounds(100, 0, 80, 30);
		all_JPanel[4].add(all_JTextField[10]);
		
		all_JTextField[11] = new JTextField();
		all_JTextField[11].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[11].setEditable(false);
		all_JTextField[11].setBounds(190, 0, 90, 30);
		all_JPanel[4].add(all_JTextField[11]);
		
		all_Btn[7] = new JButton("Remove");
		all_Btn[7].setBounds(290, 6, 89, 23);
		all_Btn[7].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[7].setForeground(Color.BLUE);
		all_JPanel[4].add(all_Btn[7]);
		
		all_Btn[8] = new JButton("Info. of book");
		all_Btn[8].setBounds(389, 6, 89, 23);
		all_Btn[8].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[8].setForeground(Color.BLUE);
		all_JPanel[4].add(all_Btn[8]);
		
		
		all_Btn[24] = new JButton("Confirm");
		all_Btn[24].setBounds(490, 6, 89, 23);
		all_Btn[24].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[24].setForeground(Color.BLUE);
	//	all_JPanel[4].add(all_Btn[24]);
//related to 5_book
		//JPanel all_JPanel[5 = new JPanel();
		all_JPanel[5].setLayout(null);
		all_JPanel[5].setBounds(0, 260, 500, 30);
		all_JPanel[5].setVisible(false);
		mainFrame.getContentPane().add(all_JPanel[5]);		
		
		all_JTextField[12] = new JTextField();
		all_JTextField[12].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[12].setEditable(false);
		all_JTextField[12].setBounds(10, 0, 80, 30);
		all_JPanel[5].add(all_JTextField[12]);
		
		all_JTextField[13] = new JTextField();
		all_JTextField[13].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[13].setEditable(false);
		all_JTextField[13].setBounds(100, 0, 80, 30);
		all_JPanel[5].add(all_JTextField[13]);
		
		all_JTextField[14] = new JTextField();
		all_JTextField[14].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[14].setEditable(false);
		all_JTextField[14].setBounds(190, 0, 90, 30);
		all_JPanel[5].add(all_JTextField[14]);
		
		all_Btn[9] = new JButton("Remove");
		all_Btn[9].setBounds(290, 6, 89, 23);
		all_Btn[9].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[9].setForeground(Color.BLUE);
		all_JPanel[5].add(all_Btn[9]);
		
		all_Btn[10] = new JButton("Info. of book");
		all_Btn[10].setBounds(389, 6, 89, 23);
		all_Btn[10].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[10].setForeground(Color.BLUE);
		all_JPanel[5].add(all_Btn[10]);
		
		all_Btn[25] = new JButton("Confirm");
		all_Btn[25].setBounds(490, 6, 89, 23);
		all_Btn[25].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[25].setForeground(Color.BLUE);
	//	all_JPanel[5].add(all_Btn[25]);
		
//related to 6_book
		//JPanel all_JPanel[6 = new JPanel();
		all_JPanel[6].setLayout(null);
		all_JPanel[6].setBounds(0, 300, 500, 30);
		all_JPanel[6].setVisible(false);
		mainFrame.getContentPane().add(all_JPanel[6]);		
		
		all_JTextField[15] = new JTextField();
		all_JTextField[15].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[15].setEditable(false);
		all_JTextField[15].setBounds(10, 0, 80, 30);
		all_JPanel[6].add(all_JTextField[15]);
		
		all_JTextField[16] = new JTextField();
		all_JTextField[16].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[16].setEditable(false);
		all_JTextField[16].setBounds(100, 0, 80, 30);
		all_JPanel[6].add(all_JTextField[16]);
		
		all_JTextField[17] = new JTextField();
		all_JTextField[17].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[17].setEditable(false);
		all_JTextField[17].setBounds(190, 0, 90, 30);
		all_JPanel[6].add(all_JTextField[17]);
		
		all_Btn[11] = new JButton("Remove");
		all_Btn[11].setBounds(290, 6, 89, 23);
		all_Btn[11].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[11].setForeground(Color.BLUE);
		all_JPanel[6].add(all_Btn[11]);
		
		all_Btn[12] = new JButton("Info. of book");
		all_Btn[12].setBounds(389, 6, 89, 23);
		all_Btn[12].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[12].setForeground(Color.BLUE);
		all_JPanel[6].add(all_Btn[12]);
		
		all_Btn[26] = new JButton("Confirm");
		all_Btn[26].setBounds(490, 6, 89, 23);
		all_Btn[26].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[26].setForeground(Color.BLUE);
	//	all_JPanel[6].add(all_Btn[26]);
		
//related to 7_book
		//JPanel all_JPanel[7 = new JPanel();
		all_JPanel[7].setLayout(null);
		all_JPanel[7].setBounds(0, 340, 500, 30);
		all_JPanel[7].setVisible(false);
		mainFrame.getContentPane().add(all_JPanel[7]);		
		
		all_JTextField[18] = new JTextField();
		all_JTextField[18].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[18].setEditable(false);
		all_JTextField[18].setBounds(10, 0, 80, 30);
		all_JPanel[7].add(all_JTextField[18]);
		
		all_JTextField[19] = new JTextField();
		all_JTextField[19].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[19].setEditable(false);
		all_JTextField[19].setBounds(100, 0, 80, 30);
		all_JPanel[7].add(all_JTextField[19]);
		
		all_JTextField[20] = new JTextField();
		all_JTextField[20].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[20].setEditable(false);
		all_JTextField[20].setBounds(190, 0, 90, 30);
		all_JPanel[7].add(all_JTextField[20]);
		
		all_Btn[13] = new JButton("Remove");
		all_Btn[13].setBounds(290, 6, 89, 23);
		all_Btn[13].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[13].setForeground(Color.BLUE);
		all_JPanel[7].add(all_Btn[13]);
		
		all_Btn[14] = new JButton("Info. of book");
		all_Btn[14].setBounds(389, 6, 89, 23);
		all_Btn[14].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[14].setForeground(Color.BLUE);
		all_JPanel[7].add(all_Btn[14]);
		
		all_Btn[27] = new JButton("Confirm");
		all_Btn[27].setBounds(490, 6, 89, 23);
		all_Btn[27].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[27].setForeground(Color.BLUE);
//		all_JPanel[7].add(all_Btn[27]);
		
//related to 8_book
		//JPanel all_JPanel[8 = new JPanel();
		all_JPanel[8].setLayout(null);
		all_JPanel[8].setBounds(0, 380, 500, 30);
		all_JPanel[8].setVisible(false);
		mainFrame.getContentPane().add(all_JPanel[8]);		
		
		all_JTextField[21] = new JTextField();
		all_JTextField[21].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[21].setEditable(false);
		all_JTextField[21].setBounds(10, 0, 80, 30);
		all_JPanel[8].add(all_JTextField[21]);
		
		all_JTextField[22] = new JTextField();
		all_JTextField[22].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[22].setEditable(false);
		all_JTextField[22].setBounds(100, 0, 80, 30);
		all_JPanel[8].add(all_JTextField[22]);
		
		all_JTextField[23] = new JTextField();
		all_JTextField[23].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[23].setEditable(false);
		all_JTextField[23].setBounds(190, 0, 90, 30);
		all_JPanel[8].add(all_JTextField[23]);
		
		all_Btn[15] = new JButton("Remove");
		all_Btn[15].setBounds(290, 6, 89, 23);
		all_Btn[15].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[15].setForeground(Color.BLUE);
		all_JPanel[8].add(all_Btn[15]);
		
		all_Btn[16] = new JButton("Info. of book");
		all_Btn[16].setBounds(389, 6, 89, 23);
		all_Btn[16].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[16].setForeground(Color.BLUE);
		all_JPanel[8].add(all_Btn[16]);
		
		all_Btn[28] = new JButton("Confirm");
		all_Btn[28].setBounds(490, 6, 89, 23);
		all_Btn[28].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[28].setForeground(Color.BLUE);
	//	all_JPanel[8].add(all_Btn[28]);
		
//related to 9_book
		//JPanel all_JPanel[9 = new JPanel();
		all_JPanel[9].setLayout(null);
		all_JPanel[9].setBounds(0, 420, 500, 30);
		all_JPanel[9].setVisible(false);
		mainFrame.getContentPane().add(all_JPanel[9]);		
		
		all_JTextField[24] = new JTextField();
		all_JTextField[24].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[24].setEditable(false);
		all_JTextField[24].setBounds(10, 0, 80, 30);
		all_JPanel[9].add(all_JTextField[24]);
		
		all_JTextField[25] = new JTextField();
		all_JTextField[25].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[25].setEditable(false);
		all_JTextField[25].setBounds(100, 0, 80, 30);
		all_JPanel[9].add(all_JTextField[25]);
		
		all_JTextField[26] = new JTextField();
		all_JTextField[26].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[26].setEditable(false);
		all_JTextField[26].setBounds(190, 0, 90, 30);
		all_JPanel[9].add(all_JTextField[26]);
		
		all_Btn[17] = new JButton("Remove");
		all_Btn[17].setBounds(290, 6, 89, 23);
		all_Btn[17].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[17].setForeground(Color.BLUE);
		all_JPanel[9].add(all_Btn[17]);
		
		all_Btn[18] = new JButton("Info. of book");
		all_Btn[18].setBounds(389, 6, 89, 23);
		all_Btn[18].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[18].setForeground(Color.BLUE);
		all_JPanel[9].add(all_Btn[18]);
		
		all_Btn[29] = new JButton("Confirm");
		all_Btn[29].setBounds(490, 6, 89, 23);
		all_Btn[29].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[29].setForeground(Color.BLUE);
	//	all_JPanel[9].add(all_Btn[29]);
		
//related to 10_book
		//JPanel all_JPanel[10 = new JPanel();
		all_JPanel[10].setLayout(null);
		all_JPanel[10].setBounds(0, 460, 500, 30);
		all_JPanel[10].setVisible(false);
		mainFrame.getContentPane().add(all_JPanel[10]);			
		
		all_JTextField[27] = new JTextField();
		all_JTextField[27].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[27].setEditable(false);
		all_JTextField[27].setBounds(10, 0, 80, 30);
		all_JPanel[10].add(all_JTextField[27]);
		
		all_JTextField[28] = new JTextField();
		all_JTextField[28].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[28].setEditable(false);
		all_JTextField[28].setBounds(100, 0, 80, 30);
		all_JPanel[10].add(all_JTextField[28]);
		
		all_JTextField[29] = new JTextField();
		all_JTextField[29].setFont(new Font("Tahoma", Font.PLAIN, 16));
		all_JTextField[29].setEditable(false);
		all_JTextField[29].setBounds(190, 0, 90, 30);
		all_JPanel[10].add(all_JTextField[29]);
		
		all_Btn[19] = new JButton("Confirm");
		all_Btn[19].setBounds(290, 6, 89, 23);
		all_Btn[19].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[19].setForeground(Color.BLUE);
		all_JPanel[10].add(all_Btn[19]);
		
		all_Btn[20] = new JButton("Remove");
		all_Btn[20].setBounds(389, 6, 89, 23);
		all_Btn[20].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[20].setForeground(Color.BLUE);
		all_JPanel[10].add(all_Btn[20]);
		
		all_Btn[30] = new JButton("Confirm");
		all_Btn[30].setBounds(490, 6, 89, 23);
		all_Btn[30].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[30].setForeground(Color.BLUE);
	//	all_JPanel[10].add(all_Btn[30]);

		function();
	}
}
