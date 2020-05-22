package bookstore;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

 class Start {
	private String userEmail;
	private HashMap<String, String[]> userCart = new HashMap<String, String[]>();
	private HashMap<String, String[]> SearchBooks;
	private String[] arrBooks;

	//private Stack<String[]> userCart;
	private static Start single_instance = null; 
	private static boolean isManager = false;
	private String search;
	private String category;
	private String keySearch;
	private String ISBNInfo_Book;
	private int pagNumSearch = 1;
	private boolean first = true;
	private int count = 0;
	private String[] arrBooksCart = new String[100000];
  
    // private constructor restricted to this class itself 
    private Start() 
    { 

    } 
    
    public void setStartToNull(Start n) {
    	removeAllBookCart();
		setBoolFirst(true);
		setUserEmail("");
		searchBooks(new HashMap<String, String[]>());
		isManager = false;
		search = "";
		category = "";
		keySearch = "";
		ISBNInfo_Book = "";
		pagNumSearch = 1;
		first = true;
		int count = 0;
		arrBooksCart = new String[100000];
    	this.single_instance = null;
    }
  
    // static method to create instance of Singleton class 
    public static Start getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new Start(); 
  
        return single_instance; 
    } 
    
    public void setBoolFirst(boolean first) {
    	this.first = first;
    }
    
    public boolean getBoolFirst() {
    	return this.first;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFram window = new MainFram();

	}
	
	public void setISBNInfo_Book(String ISBNInfo_Book) {
		this.ISBNInfo_Book = ISBNInfo_Book;
	}
	
	public String getISBNInfo_Book() {
		return ISBNInfo_Book;
	}
	
	public void setPagNumSearch(int pagNumSearch) {
		this.pagNumSearch = pagNumSearch;
	}
	
	public int getpagNumSearch() {
		return pagNumSearch;
	}
	
	public static void Load_menubar(JFrame mainFrame) {
		Start start = Start.getInstance();
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 500, 35);
		mainFrame.getContentPane().add(menuBar);
		
		JButton btnLog_out = new JButton("Log_out");
		btnLog_out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start.setStartToNull(null);
				mainFrame.hide();
				new MainFram();
			}
		});
		btnLog_out.setFont(new Font("Stencil", Font.BOLD, 16));
		btnLog_out.setForeground(Color.BLUE);
		btnLog_out.setBounds(147, 211, 135, 39);
		menuBar.add(btnLog_out);
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.hide();
				new Update_Info();
			}
		});
		btnProfile.setFont(new Font("Stencil", Font.BOLD, 16));
		btnProfile.setForeground(Color.BLUE);
		btnProfile.setBounds(147, 211, 135, 39);
		menuBar.add(btnProfile);
		
		JButton btnCart = new JButton("Cart");
		btnCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.hide();
				new Shopping_cartFrame();
			}
		});
		btnCart.setFont(new Font("Stencil", Font.BOLD, 16));
		btnCart.setForeground(Color.BLUE);
		btnCart.setBounds(147, 211, 135, 39);
		menuBar.add(btnCart);

		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.hide();
				new HomeFrame();
			}
		});
		btnHome.setFont(new Font("Stencil", Font.BOLD, 16));
		btnHome.setForeground(Color.BLUE);
		btnHome.setBounds(147, 211, 135, 39);
		menuBar.add(btnHome);
		
		JButton btnManage = new JButton("Manage");

		if(isManager) {
		btnManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.hide();
				new ManageFrame();
			}
		});
		btnManage.setFont(new Font("Stencil", Font.BOLD, 16));
		btnManage.setForeground(Color.BLUE);
		btnManage.setBounds(147, 211, 135, 39);
		menuBar.add(btnManage);
		}
		
		System.out.println(mainFrame.getName());
		if(mainFrame.getName().equals("Home")) {
			btnHome.setEnabled(false);
		} else if(mainFrame.getName().equals("Update")) {
			btnProfile.setEnabled(false);
		} else if(mainFrame.getName().equals("Cart")) {
			btnCart.setEnabled(false);
		} else if(mainFrame.getName().equals("Manage Frame")) {
			btnManage.setEnabled(false);
		} 
	}

	public void setUserEmail(String email) {
		userCart = new HashMap<String, String[]>();
		this.userEmail = email;
	}
	
	public String getUserEmail() {
		return this.userEmail;
	}
	
	public HashMap<String, String[]> getCart(){
		return userCart;
	}
	
	public void addBookIntoCart(String idBook, String[] bookInfo) {
			userCart.put(idBook, bookInfo);
			this.arrBooksCart[count] = idBook;
			count++;
	}
	
	public String[] getarrBooksCart() {
		return this.arrBooksCart;
	}
	
	public void setarrBooksCart(String[] arrBooksCart) {
		 this.arrBooksCart = arrBooksCart;
	}
	
	
	public void removeBookFromCart(String idbook) {
		userCart.remove(idbook);
		count--;
		//this.arrBooksCart = new String[this.arrBooksCart.length];
	}
	
	public void removeAllBookCart() {
		userCart.clear();
		count = 0;
	}
	
	
	public int numBookCart() {
		return userCart.size();
	}
	
	public void searchBooks(HashMap<String, String[]> books){
		this.SearchBooks = books;
	}
	
	public HashMap<String, String[]> getSearchBook(){
		return this.SearchBooks;
	}
		
	public void setarrBooks(String[] arrBooks){
		this.arrBooks = arrBooks;
	}
	
	public String[] getarrBooks(){
		return this.arrBooks;
	}
	
	public void setManager(boolean check) {
		this.isManager = check;
	}
	
	public boolean isManager() {
		return this.isManager;
	}
	
	public void setSearch(String search) {
		this.search = search;
	}
	
	public String getSearch() {
		return this.search;
	}
	
	public void setcategory(String category) {
		this.category = category;
	}
	
	public String getcategory() {
		return this.category;
	}
	
	public void setkeySearch(String keySearch) {
		this.keySearch = keySearch;
	}
	
	public String getkeySearch() {
		return this.keySearch;
	}
}
