package bookstore;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;

public class HomeFrame extends JFrame {

	private JFrame mainFrame;
    private JTextField SearchtextField;
	private static Start s = Start.getInstance();
	private String search = "";
	private String category = "";
	private static HashMap<String, String[]> books = new HashMap<String, String[]>();
	private static HashMap<String, String[]> cart = s.getCart();
	

	/**
	 * Create the frame.
	 */
	public HomeFrame() {
		 s = Start.getInstance();
		  search = "";
		  category = "";
		  books = new HashMap<String, String[]>();
		cart = s.getCart();
		
		getContentPane().setLayout(null);
		mainFrame = new JFrame("Home");
		mainFrame.setName("Home");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(100, 100, 516, 403);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setVisible(true);
		
		s.Load_menubar(mainFrame);
		
		//Put the radio buttons in a column in a panel.
        JPanel radioPanel2 = new JPanel(new GridLayout(0, 1));
        radioPanel2.setSize(123, 176);
        radioPanel2.setLocation(312, 72);
		 
	        
	        JLabel lblSearchedKey = new JLabel("Searched key :");
	        lblSearchedKey.setForeground(Color.RED);
	        lblSearchedKey.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
	        lblSearchedKey.setBounds(42, 259, 118, 41);
	        lblSearchedKey.setVisible(false);
	        mainFrame.getContentPane().add(lblSearchedKey);
	        
	        JButton btnSearch = new JButton("Search");
	        btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					s.setcategory(category);
					s.setSearch(search);
					s.setkeySearch(SearchtextField.getText());
					doSearch();
					s.setPagNumSearch(1);
					mainFrame.hide();
					new SearchFrame();
				}
			});
	        btnSearch.setFont(new Font("Stencil", Font.BOLD, 16));
	        btnSearch.setForeground(Color.BLUE);
	        btnSearch.setBounds(130, 312, 137, 41);
	        btnSearch.setVisible(false);
	        mainFrame.getContentPane().add(btnSearch);
	        
	        SearchtextField = new JTextField("");
			 SearchtextField.addKeyListener(new KeyAdapter() {
			 	@Override
			 	public void keyTyped(KeyEvent e) {
			 		if(!SearchtextField.getText().equals("")) {
	     		        btnSearch.setVisible(true);
	     		   }else {
	     		        btnSearch.setVisible(false);
	     		   }
			 	}
			 });
		        SearchtextField.setBounds(170, 260, 259, 41);
		        SearchtextField.setVisible(false);
		        mainFrame.getContentPane().add(SearchtextField);
		        SearchtextField.setColumns(10);
	        
		 //Create the radio buttons.
        JRadioButton ISBNButton = new JRadioButton("ISBN");
        ISBNButton.setMnemonic(KeyEvent.VK_B);
 
        JRadioButton titleButton = new JRadioButton("Title");
        titleButton.setMnemonic(KeyEvent.VK_D);
 
        JRadioButton publisherButton = new JRadioButton("Publisher");
        publisherButton.setMnemonic(KeyEvent.VK_R);
 
        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(ISBNButton);
        group.add(titleButton);
        group.add(publisherButton);

        //Put the radio buttons in a column in a panel.
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.setSize(123, 176);
        radioPanel.setLocation(184, 72);
        radioPanel.add(ISBNButton);
        radioPanel.add(titleButton);
        
       
         
        
       JRadioButton authorButton = new JRadioButton("Author");
       authorButton.setMnemonic(KeyEvent.VK_P);
       group.add(authorButton);
       authorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search = "author";
				category = "";
				SearchtextField.setVisible(true);
				lblSearchedKey.setVisible(true);
		        radioPanel2.setVisible(false);
	     		   System.out.println ("enter2");
	     		   if(!SearchtextField.getText().equals("")) {
	     		        btnSearch.setVisible(true);
	     		   }else {
	     		        btnSearch.setVisible(false);
	     		   }
			}
		});
        radioPanel.add(authorButton);
        radioPanel.add(publisherButton);
 
        mainFrame.getContentPane().add(radioPanel);
        

        //Register a listener for the radio buttons.
        ISBNButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search = "ISBN";
				category = "";
     		   System.out.println ("enter1");
		        radioPanel2.setVisible(false);
     		  SearchtextField.setVisible(true);
				lblSearchedKey.setVisible(true);
				if(!SearchtextField.getText().equals("")) {
     		        btnSearch.setVisible(true);
     		   }else {
    		        btnSearch.setVisible(false);
    		   }
			}
		});
        titleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search = "title";
				category = "";
	     		   System.out.println ("enter3");
			        radioPanel2.setVisible(false);
	     		  SearchtextField.setVisible(true);
					lblSearchedKey.setVisible(true);
					if(!SearchtextField.getText().equals("")) {
	     		        btnSearch.setVisible(true);
	     		   } else {
	     		        btnSearch.setVisible(false);
	     		   }
			}
		});
        publisherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search = "publisher_name";
				category = "";
	     		   System.out.println ("enter4");
			        radioPanel2.setVisible(false);
	     		  SearchtextField.setVisible(true);
					lblSearchedKey.setVisible(true);
					if(!SearchtextField.getText().equals("")) {
	     		        btnSearch.setVisible(true);
	     		   }else {
	     		        btnSearch.setVisible(false);
	     		   }
			}
		});
 
        
        JLabel lblSelect = new JLabel("<html>Select attribute<br/>which you will<br/>search by:</html>");
        lblSelect.setForeground(Color.RED);
        lblSelect.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
        lblSelect.setBounds(38, 51, 159, 86);
        mainFrame.getContentPane().add(lblSelect);
        
       
        
        JRadioButton ScienceButton = new JRadioButton("Science");
        ScienceButton.setMnemonic(KeyEvent.VK_4);
 
        JRadioButton ArtButton = new JRadioButton("Art");
        ArtButton.setMnemonic(KeyEvent.VK_3);
 
        JRadioButton HistoryButton = new JRadioButton("History");
        HistoryButton.setMnemonic(KeyEvent.VK_2);
        
        JRadioButton ReligionButton = new JRadioButton("Religion");
        ReligionButton.setMnemonic(KeyEvent.VK_0);
 
        JRadioButton GeographyButton = new JRadioButton("Geography");
        GeographyButton.setMnemonic(KeyEvent.VK_1);
 
      //Group the radio buttons.
        ButtonGroup group2 = new ButtonGroup();
        group2.add(ScienceButton);
        group2.add(ArtButton);
        group2.add(HistoryButton);
        group2.add(ReligionButton);
        group2.add(GeographyButton);
        
        
        radioPanel2.add(ScienceButton);
        radioPanel2.add(ArtButton);
        radioPanel2.add(HistoryButton);
        radioPanel2.add(ReligionButton);
        radioPanel2.add(GeographyButton);
        radioPanel2.setVisible(false);
        mainFrame.getContentPane().add(radioPanel2);
        
        //Register a listener for the radio buttons.
        ScienceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				category = "0";
     		   System.out.println ("enter1");
		        btnSearch.setVisible(true);
			}
		});
        ArtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				category = "1";
	     		   System.out.println ("enter3");
			        btnSearch.setVisible(true);

			}
		});
        HistoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				category = "2";
	     		   System.out.println ("enter4");
			        btnSearch.setVisible(true);
			}
		});
        ReligionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				category = "3";
	     		   System.out.println ("enter3");
			        btnSearch.setVisible(true);
			}
		});
        GeographyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				category = "4";
	     		   System.out.println ("enter4");
			        btnSearch.setVisible(true);
			}
		});
        
        JRadioButton CategoryButton = new JRadioButton("<html>Specific<br/> Category</html>");
        CategoryButton.setMnemonic(KeyEvent.VK_C);
        group.add(CategoryButton);
        CategoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search = "Category";
				btnSearch.setVisible(false);
		        radioPanel2.setVisible(true);
		        SearchtextField.setVisible(false);
				lblSearchedKey.setVisible(false);
				System.out.println ("enter5");
			}
		});
        radioPanel.add(CategoryButton);

      
	}
	
	private void doSearch() {
		 String search = s.getSearch();
		 String category = s.getcategory();
		 String keySearch = s.getkeySearch();
		 String[] arrbook = new String[100000];
		 int i = 0;
		 String sqlSelect;
		 
		 //author ISBN title publisher_name category Science Art History Religion Geography
		 if(category.equals("")) {
			 if (!search.equals("author")) {
				 sqlSelect = "SELECT * FROM book where "+  search + " = '" + keySearch + "';";
			 } else {
				 //search in author table
				
				 sqlSelect = "SELECT * FROM book INNER JOIN author ON book.ISBN = author.book_ISBN where author.book_ISBN in (select book_ISBN FROM author where author_name = '"  + keySearch + "');";
			 }
		 } else {
			 sqlSelect = "SELECT * FROM book where "+  search + " = '" + category + "';";
		 }
		 ResultSet rs = null;
			try {
				rs = MainFram.stmt.executeQuery(sqlSelect);	
				while(rs.next()) {
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
					String[] attr = {rs.getString("title"), rs.getString("p_year"),
							rs.getString("publisher_name"), rs.getString("category"), rs.getString("price"),
							rs.getString("threshold"), rs.getString("quantity") , "0", "0"
					};
					if (cart != null) {
						if(cart.containsKey(rs.getString("ISBN"))) {
							attr[7] = "1";
						}
					}
					books.put(rs.getString("ISBN"), attr);
					arrbook[i] = rs.getString("ISBN");
					i++;
	            }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			s.searchBooks(books);
			s.setarrBooks(arrbook);
			s.setcategory("");
			s.setSearch("");
			s.setkeySearch("");
	}
	
}
