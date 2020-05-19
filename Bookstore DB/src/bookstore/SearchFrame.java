package bookstore;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class SearchFrame extends JFrame {
	private static Start start = Start.getInstance();
	
	private JFrame mainFrame;
	private static HashMap<String, String[]> books = start.getSearchBook();
	private static String[] arrBooks = start.getarrBooks();
	private static int noBooks = books.size();
	private static int noPags = noBooks/10;
	private static int currPag = start.getpagNumSearch();
	
	private static HashMap<String, String[]> cart = start.getCart();
	private String[] attr;
	private boolean first = start.getBoolFirst();
	private int[] all_price = new int[noBooks + 1];

	public JTextField[] all_JTextField = new JTextField[]{new JTextField(), new JTextField(),new JTextField(),
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
    JButton[] all_Btn = new JButton[21];
    
	JSpinner[] all_spinner = new JSpinner[11];

	/**
	 * Create the frame.
	 */
	public SearchFrame() {
		start = Start.getInstance();
		books = start.getSearchBook();
		arrBooks = start.getarrBooks();
		noBooks = books.size();
		noPags = noBooks/10;
	    currPag = start.getpagNumSearch();
		 cart = start.getCart();
		 first = start.getBoolFirst();
		all_price = new int[noBooks + 1];

		if((noBooks%10 )!= 0 && first) {
			noPags++;
			start.setBoolFirst(first);
		}
		mainFrame = new JFrame("Search");
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setBounds(100, 100, 526, 550);
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
		
		JLabel lblBookprice = new JLabel("Book_Price");
		lblBookprice.setForeground(Color.RED);
		lblBookprice.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblBookprice.setBounds(190, 50, 90, 15);
		mainFrame.getContentPane().add(lblBookprice);
		
		JLabel lblPagenum = new JLabel("Page number " + currPag + " of total "+ noPags);
		lblPagenum.setForeground(Color.RED);
		lblPagenum.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblPagenum.setBounds(300, 50, 200, 15);
		mainFrame.getContentPane().add(lblPagenum);
		
		start.Load_menubar(mainFrame);
				
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
							new SearchFrame();
 
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
							new SearchFrame();
						}
					}
				});
				initialize();

	}
	
	private void function() {
		cart = start.getCart();
		 int noCurrBook = noBooks-(10*(currPag-1));
		 if(noCurrBook > 10) {
			 noCurrBook = 10;
		 }

			
		 for (int y = 1; y <= noCurrBook; y++) {
			 all_JPanel[y].setVisible(true);
			 arrBooks = start.getarrBooks();
			 attr = books.get(arrBooks[(y - 1) + 10 * (currPag - 1)]);
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
		     all_JTextField[3 * y - 1].setText(attr[4]);
		     all_price[y + (currPag - 1) * 10] = Integer.parseInt(attr[6]);
		     
		     if(attr[7].equals("1")) {
		    	 System.out.println("enter false2");
				//all_spinner[y].setVisible(false);
		    	 all_Btn[2 * y - 1].setEnabled(false);
				all_Btn[2 * y - 1].setText("In cart"); 
				
		     } else {
	    		
				 all_Btn[2 * y - 1].setEnabled(true);
			 		all_Btn[2 * y - 1].setText("Add to Cart");
			 		//PageActionListener1 n =  new PageActionListener1(y , all_Btn[2 * y - 1]);
			    	 all_Btn[2 * y - 1].addActionListener(new PageActionListener1(y));
			    	 if (cart != null) {
							if(!cart.containsKey(arrBooks[(y - 1) + 10 * (currPag - 1)])) {
					    		 System.out.println("enter tue");
							//	all_spinner[y].setVisible(true);
								 all_Btn[2 * y - 1].setEnabled(true);
								all_Btn[2 * y - 1].setText("Add to Cart");
									 
							} else {
					    		 System.out.println("enter false1");
								//	all_spinner[y].setVisible(false);
								 all_Btn[2 * y - 1].setEnabled(false);
								all_Btn[2 * y - 1].setText("In cart"); 
							}
					}
		     }
			 all_Btn[2 * y].addActionListener(new PageActionListener2(y));
		 }
	}
	
	private void check() {
		cart = start.getCart();
		 int noCurrBook = noBooks-(10*(currPag-1));
		 if(noCurrBook > 10) {
			 noCurrBook = 10;
		 }
	
		for (int y = 1; y <= noCurrBook; y++) {
			 attr = books.get(arrBooks[(y - 1) + 10 * (currPag - 1)]);
			 /*
				 * title
					p_year
					publisher_name
					category
					price
					threshold
					quantity
				 */
		     if(attr[7].equals("1")) {
		    	 System.out.println("enter false2");
				all_spinner[y].setVisible(false);
		    	
		     } else {
			    	 if (cart != null) {
							if(!cart.containsKey(arrBooks[(y - 1) + 10 * (currPag - 1)])) {
					    		 System.out.println("enter tue");
								all_spinner[y].setVisible(true); 
							} else {
					    		 System.out.println("enter false1");
									all_spinner[y].setVisible(false);
							
							}
					}
		     }
	}
	}
	
	private class PageActionListener1 implements ActionListener {
	    private int y;
	    
	    public PageActionListener1(int y) {
	        this.y = y;
	    }

	    public void actionPerformed(ActionEvent e) {
			cart = start.getCart();
			attr = books.get(arrBooks[(y - 1) + 10 * (currPag - 1)]);
	    	if (cart != null) {
	    		// System.out.println("enter cart");
	    		try {
					all_spinner[y].commitEdit();
				} catch ( java.text.ParseException e1 ) {
					
				}
				//y + (currPag - 1) * 10
				Object value =  all_spinner[y].getValue();
				if(!cart.containsKey(arrBooks[(y - 1) + 10 * (currPag - 1)])) {
					attr[7] = "1";
					attr[8] = value.toString();
					books.put(arrBooks[(y - 1) + 10 * (currPag - 1)], attr);
					start.searchBooks(books);	
					start.addBookIntoCart(arrBooks[(y - 1) + 10 * (currPag - 1)], attr);
				}
				
				System.out.println(value.toString());
				all_spinner[y].setVisible(false);
	    		 System.out.println("enter false3");
				all_Btn[2 * y - 1].setEnabled(false);
				all_Btn[2 * y - 1].setText("In cart");
	    	}
	    	mainFrame.hide();
			new SearchFrame();
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
		all_JTextField[2].setBounds(190, 0, 70, 30);
		all_JPanel[1].add(all_JTextField[2]);
		
		 all_Btn[1] = new JButton("Add to Cart");
		all_Btn[1].setBounds(290, 6, 89, 23);
		all_Btn[1].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[1].setForeground(Color.BLUE);
		all_JPanel[1].add(all_Btn[1]);
		
		 all_Btn[2] = new JButton("Info. of book");
		all_Btn[2].setBounds(389, 6, 89, 23);
		all_Btn[2].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[2].setForeground(Color.BLUE);
		all_JPanel[1].add(all_Btn[2]);		

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
		all_JTextField[5].setBounds(190, 0, 70, 30);
		all_JPanel[2].add(all_JTextField[5]);
		
		all_Btn[3] = new JButton("Add to Cart");
		all_Btn[3].setBounds(290, 6, 89, 23);
		all_Btn[3].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[3].setForeground(Color.BLUE);
		all_JPanel[2].add(all_Btn[3]);
		//all_Btn[3].setEnabled(true);
		//all_Btn[3].setText("Add to Cart");
		
		all_Btn[4] = new JButton("Info. of book");
		all_Btn[4].setBounds(389, 6, 89, 23);
		all_Btn[4].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[4].setForeground(Color.BLUE);
		all_JPanel[2].add(all_Btn[4]);
		
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
		all_JTextField[8].setBounds(190, 0, 70, 30);
		all_JPanel[3].add(all_JTextField[8]);
		
		all_Btn[5] = new JButton("Add to Cart");
		all_Btn[5].setBounds(290, 6, 89, 23);
		all_Btn[5].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[5].setForeground(Color.BLUE);
		all_JPanel[3].add(all_Btn[5]);
		//all_Btn[5].setEnabled(true);
	//	all_Btn[5].setText("Add to Cart");
		
		all_Btn[6] = new JButton("Info. of book");
		all_Btn[6].setBounds(389, 6, 89, 23);
		all_Btn[6].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[6].setForeground(Color.BLUE);
		all_JPanel[3].add(all_Btn[6]);
		
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
		all_JTextField[11].setBounds(190, 0, 70, 30);
		all_JPanel[4].add(all_JTextField[11]);
		
		all_Btn[7] = new JButton("Add to Cart");
		all_Btn[7].setBounds(290, 6, 89, 23);
		all_Btn[7].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[7].setForeground(Color.BLUE);
		all_JPanel[4].add(all_Btn[7]);
		//all_Btn[7].setEnabled(true);
		//all_Btn[7].setText("Add to Cart");
		
		all_Btn[8] = new JButton("Info. of book");
		all_Btn[8].setBounds(389, 6, 89, 23);
		all_Btn[8].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[8].setForeground(Color.BLUE);
		all_JPanel[4].add(all_Btn[8]);
		
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
		all_JTextField[14].setBounds(190, 0, 70, 30);
		all_JPanel[5].add(all_JTextField[14]);
		
		all_Btn[9] = new JButton("Add to Cart");
		all_Btn[9].setBounds(290, 6, 89, 23);
		all_Btn[9].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[9].setForeground(Color.BLUE);
		all_JPanel[5].add(all_Btn[9]);
	//	all_Btn[9].setEnabled(true);
		//all_Btn[9].setText("Add to Cart");
		
		all_Btn[10] = new JButton("Info. of book");
		all_Btn[10].setBounds(389, 6, 89, 23);
		all_Btn[10].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[10].setForeground(Color.BLUE);
		all_JPanel[5].add(all_Btn[10]);
		
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
		all_JTextField[17].setBounds(190, 0, 70, 30);
		all_JPanel[6].add(all_JTextField[17]);
		
		all_Btn[11] = new JButton("Add to Cart");
		all_Btn[11].setBounds(290, 6, 89, 23);
		all_Btn[11].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[11].setForeground(Color.BLUE);
		all_JPanel[6].add(all_Btn[11]);
	//	all_Btn[11].setEnabled(true);
		//all_Btn[11].setText("Add to Cart");
		
		all_Btn[12] = new JButton("Info. of book");
		all_Btn[12].setBounds(389, 6, 89, 23);
		all_Btn[12].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[12].setForeground(Color.BLUE);
		all_JPanel[6].add(all_Btn[12]);
	
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
		all_JTextField[20].setBounds(190, 0, 70, 30);
		all_JPanel[7].add(all_JTextField[20]);
		
		all_Btn[13] = new JButton("Add to Cart");
		all_Btn[13].setBounds(290, 6, 89, 23);
		all_Btn[13].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[13].setForeground(Color.BLUE);
		all_JPanel[7].add(all_Btn[13]);
	//	all_Btn[13].setEnabled(true);
		//all_Btn[13].setText("Add to Cart");
		
		all_Btn[14] = new JButton("Info. of book");
		all_Btn[14].setBounds(389, 6, 89, 23);
		all_Btn[14].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[14].setForeground(Color.BLUE);
		all_JPanel[7].add(all_Btn[14]);
	
		
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
		all_JTextField[23].setBounds(190, 0, 70, 30);
		all_JPanel[8].add(all_JTextField[23]);
		
		all_Btn[15] = new JButton("Add to Cart");
		all_Btn[15].setBounds(290, 6, 89, 23);
		all_Btn[15].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[15].setForeground(Color.BLUE);
		all_JPanel[8].add(all_Btn[15]);
//		all_Btn[15].setEnabled(true);
	//	all_Btn[15].setText("Add to Cart");
		
		all_Btn[16] = new JButton("Info. of book");
		all_Btn[16].setBounds(389, 6, 89, 23);
		all_Btn[16].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[16].setForeground(Color.BLUE);
		all_JPanel[8].add(all_Btn[16]);
		
		
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
		all_JTextField[26].setBounds(190, 0, 70, 30);
		all_JPanel[9].add(all_JTextField[26]);
		
		all_Btn[17] = new JButton("Add to Cart");
		all_Btn[17].setBounds(290, 6, 89, 23);
		all_Btn[17].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[17].setForeground(Color.BLUE);
//		all_Btn[17].setEnabled(true);
	//	all_Btn[17].setText("Add to Cart");
		all_JPanel[9].add(all_Btn[17]);
		
		all_Btn[18] = new JButton("Info. of book");
		all_Btn[18].setBounds(389, 6, 89, 23);
		all_Btn[18].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[18].setForeground(Color.BLUE);
		all_JPanel[9].add(all_Btn[18]);
		
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
		all_JTextField[29].setBounds(190, 0, 70, 30);
		all_JPanel[10].add(all_JTextField[29]);
		
		all_Btn[19] = new JButton("Add to Cart");
		all_Btn[19].setBounds(290, 6, 89, 23);
		all_Btn[19].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[19].setForeground(Color.BLUE);
		all_JPanel[10].add(all_Btn[19]);
	//	all_Btn[19].setEnabled(true);
		//all_Btn[19].setText("Add to Cart");
		
		all_Btn[20] = new JButton("Info. of book");
		all_Btn[20].setBounds(389, 6, 89, 23);
		all_Btn[20].setFont(new Font("Tahoma", Font.BOLD, 8));
		all_Btn[20].setForeground(Color.BLUE);
		all_JPanel[10].add(all_Btn[20]);

		function();
		
		
		if(noBooks >= (1 + (currPag - 1) * 10)) {
		SpinnerNumberModel model1 = new SpinnerNumberModel(1, 1, all_price[1 + (currPag - 1) * 10], 1);  
		all_spinner[1] = new JSpinner(model1);
		all_spinner[1].setBounds(260, 6, 30, 20);
		all_JPanel[1].add(all_spinner[1]);
		}
		
		if(noBooks >= (2 + (currPag - 1) * 10)) {
	SpinnerNumberModel model2 = new SpinnerNumberModel(1, 1, all_price[2 + (currPag - 1) * 10], 1);  
		all_spinner[2] = new JSpinner(model2);
		all_spinner[2].setBounds(260, 6, 30, 20);
		all_JPanel[2].add(all_spinner[2]);
		}
		
		if(noBooks >= (3 + (currPag - 1) * 10)) {
	SpinnerNumberModel model3 = new SpinnerNumberModel(1, 1, all_price[3 + (currPag - 1) * 10], 1);  
		all_spinner[3] = new JSpinner(model3);
		all_spinner[3].setBounds(260, 6, 30, 20);
		all_JPanel[3].add(all_spinner[3]);
		}
		
		if(noBooks >= (4 + (currPag - 1) * 10)) {
		SpinnerNumberModel model4 = new SpinnerNumberModel(1, 1, all_price[4 + (currPag - 1) * 10], 1);  
		all_spinner[4] = new JSpinner(model4);
		all_spinner[4].setBounds(260, 6, 30, 20);
		all_JPanel[4].add(all_spinner[4]);
		}
		
		if(noBooks >= (5 + (currPag - 1) * 10)) {
		SpinnerNumberModel model5 = new SpinnerNumberModel(1, 1, all_price[5 + (currPag - 1) * 10], 1);  
		all_spinner[5] = new JSpinner(model5);
		all_spinner[5].setBounds(260, 6, 30, 20);
		all_JPanel[5].add(all_spinner[5]);
		}
		
		if(noBooks >= (6 + (currPag - 1) * 10)) {
		SpinnerNumberModel model6 = new SpinnerNumberModel(1, 1, all_price[6 + (currPag - 1) * 10], 1);  
		all_spinner[6] = new JSpinner(model6);
		all_spinner[6].setBounds(260, 6, 30, 20);
		all_JPanel[6].add(all_spinner[6]);
		}
		
		if(noBooks >= (7 + (currPag - 1) * 10)) {
		SpinnerNumberModel model7 = new SpinnerNumberModel(1, 1, all_price[7 + (currPag - 1) * 10], 1);  
		all_spinner[7] = new JSpinner(model7);
		all_spinner[7].setBounds(260, 6, 30, 20);
		all_JPanel[7].add(all_spinner[7]);
		}
		
		if(noBooks >= (8 + (currPag - 1) * 10)) {
	SpinnerNumberModel model8 = new SpinnerNumberModel(1, 1, all_price[8 + (currPag - 1) * 10], 1);  
		all_spinner[8] = new JSpinner(model8);
		all_spinner[8].setBounds(260, 6, 30, 20);
		all_JPanel[8].add(all_spinner[8]);
		}
		
		if(noBooks >= (9 + (currPag - 1) * 10)) {
		SpinnerNumberModel model9 = new SpinnerNumberModel(1, 1, all_price[9 + (currPag - 1) * 10], 1);  
		all_spinner[9] = new JSpinner(model9);
		all_spinner[9].setBounds(260, 6, 30, 20);
		all_JPanel[9].add(all_spinner[9]);
		}
		
		if(noBooks >= (10 + (currPag - 1) * 10)) {
		SpinnerNumberModel model10 = new SpinnerNumberModel(1, 1, all_price[10 + (currPag - 1) * 10], 1);  
		all_spinner[10] = new JSpinner(model10);
		all_spinner[10].setBounds(260, 6, 30, 20);
		all_JPanel[10].add(all_spinner[10]);
		}
		check();

	}
}
