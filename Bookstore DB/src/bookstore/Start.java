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
	private HashMap<String, String[]> userCart;
	private HashMap<String, String[]> SearchBooks;
	//private Stack<String[]> userCart;
	private static Start single_instance = null; 
	  
  
    // private constructor restricted to this class itself 
    private Start() 
    { 

    } 
  
    // static method to create instance of Singleton class 
    public static Start getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new Start(); 
  
        return single_instance; 
    } 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFram window = new MainFram();

	}
	
	public static void Load_menubar(JFrame mainFrame) {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 450, 35);
		mainFrame.getContentPane().add(menuBar);
		
		
		JButton btnLog_out = new JButton("Log_out");
		btnLog_out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
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
				mainFrame.dispose();
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
				mainFrame.dispose();
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
				mainFrame.dispose();
				new HomeFrame();
			}
		});
		btnHome.setFont(new Font("Stencil", Font.BOLD, 16));
		btnHome.setForeground(Color.BLUE);
		btnHome.setBounds(147, 211, 135, 39);
		menuBar.add(btnHome);
		
		System.out.println(mainFrame.getName());
		if(mainFrame.getName().equals("Home")) {
			btnHome.setEnabled(false);
		} else if(mainFrame.getName().equals("Update")) {
			btnProfile.setEnabled(false);
		} else if(mainFrame.getName().equals("Cart")) {
			btnCart.setEnabled(false);
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
		if ( userCart.containsKey(idBook) ) {
			
			modifyBookCart(idBook, bookInfo);
		}else {
			userCart.put(idBook, bookInfo);
		}
	}
	
	private void modifyBookCart(String idBook, String[] bookInfo) {
		
	}
	
	public void removeBookFromCart(String idbook) {
		userCart.remove(idbook);
	}
	
	public void removeAllBookCart() {
		userCart.clear();
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
}
