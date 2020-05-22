package bookstore;


import java.sql.ResultSet;
import java.sql.SQLException;
public interface IManager {

	/**
	 * the user enters the properties of the new book
	 * along with a threshold (the minimum quantity in
	 * stock to be maintained for that book).
	 */
	public static boolean addBook(String ISBN, String title, String price,
							String pname,String paddress, String ptelephone,
							String pyear, String category,
							String threshold,String quantity,String[] authors){
		// TODO Auto-generated method stub	       
        try {
        	ResultSet rs1 = MainFram.stmt.executeQuery("SELECT ISBN FROM BOOK WHERE ISBN = '" + ISBN +"';");
        	if(rs1.next()) return false;
 	       	String sql1 = "INSERT IGNORE INTO PUBLISHER VALUES('" + pname +"', '"+ paddress +"', '"+ ptelephone +  "');";
 	       	String sql2 = "INSERT INTO BOOK VALUES('" +ISBN +"', '"+ title+"', '"+ pyear+"', '"+ pname+"', '"
        		   + category + "', " + price + ", '" + threshold + "', "+ quantity +");";
 	      MainFram.stmt.executeUpdate(sql1);
 	     MainFram.stmt.executeUpdate(sql2);
			for(int i = 0; i < authors.length; i++) {
				String sql3 = "INSERT INTO Author VALUES('" +authors[i] +"', '"+ISBN+ "');";
				MainFram.stmt.executeUpdate(sql3);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	return true;
}

	/**
	 * modify existing book
	 * @param attributes to be updated
	 * @param newValues new values of the attributes
	 * @param searchAttr attribute of condition 
	 * @param searchVal value of condition 
	 * @return true if at least one book has the value val of the attribute attr
	 */
	
	public static boolean modifyBook(String oldISBN,String ISBN, String title, String price,String pname,
									String pyear, String category, String quantity) {
		
		String sql = "UPDATE BOOK SET ISBN = '" + ISBN + "', Title = '" + title + "', price = " + price + ", publisher_name = '"+
		pname + "', category = '" + category + "', quantity = " + quantity + ", p_year = '"+ pyear + "' where ISBN = '" + oldISBN + "';";
			try {

				MainFram.stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		
		return true;
	}

	
	/**
	 * order quantity of a new book or an existing book
	 * @param ISBN of book
	 * @param quantity of book
	 * @return true if operation is executed successfully
	 */
	public static boolean placeOrder(String ISBN, String quantity) {try {
		//search ISBN in orders
		String sql1 = "SELECT ISBN FROM ORDERS WHERE ISBN = "+ ISBN + ";";
		ResultSet rs = MainFram.stmt.executeQuery(sql1);
		String sql2;
		//if ISBN exists update quantity
		if(rs.next()) {
			sql2 = "UPDATE ORDERS SET QUANTITY = QUANTITY + " + quantity + "WHERE ISBN = '" + ISBN + "';";
			
		} else {
			sql2 = "INSERT INTO ORDERS VALUES('" + ISBN + "'," + quantity + ");";
		}
		MainFram.stmt.executeUpdate(sql2);
		}		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * The user can confirm an order when receiving 
	 * the ordered quantity from the book’s publisher;
	 * the quantity of the book in store automatically
	 * increases with the quantity specified in the order.
	 * Assume that deleting the order means that the order 
	 * is received from publisher. (hint: trigger before
	 * deletion from orders table).
	 * @param ISBN
	 * @return true if operation is executed successfully
	 */
	public static boolean confirmOrder(String ISBN) {
		try {
			String sql = "DELETE FROM ORDERS WHERE ISBN = '" + ISBN + "';";
            MainFram.stmt.executeUpdate(sql);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	
	}
	
	/**
	 * Promote registered customers to have managers credentials
	 * @param email customer email
	 * @return true if operation is executed successfully
	 */
	public static boolean promoteCustomer(String email) {
		try {
			//search email in users
			String sql1 = "SELECT EMAIL FROM USERS WHERE EMAIL = '"+ email + "';";
			ResultSet rs = MainFram.stmt.executeQuery(sql1);
			//if email exists promote
			if(rs.next()) {
				String sql = "UPDATE USERS SET IS_MANAGER = 1 WHERE EMAIL = '" + email +"';";
				MainFram.stmt.executeUpdate(sql);				
			} else {// error
				return false;
			}
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	return true;
}

	
	/**
	 * The total sales for books in the previous month
	 * @return
	 */
	public static ResultSet totalBookSale() {
		try {
			String sql = "SELECT ISBN, sum(no_of_copies) FROM BOOK_SALES WHERE date >= DATE_ADD(NOW(),INTERVAL -30 DAY) "
							+" GROUP BY ISBN;";
            ResultSet rs = MainFram.stmt.executeQuery(sql);
            return rs;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * The top 5 customers who purchase the most purchase 
	 * amount in descending order for the last three months
	 * @return
	 */
	public static ResultSet top5Custmors() {

	try {
		String sql = "SELECT first_name,last_name,sum(totalprice)as purchaseAmount, email "
				+ "from book_sales natural join users WHERE date >= DATE_ADD(NOW(),INTERVAL -90 DAY)"
				+ " group by email order by purchaseAmount desc limit 5;";
        ResultSet rs = MainFram.stmt.executeQuery(sql);
        return rs;
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}

	/**
	 * The top 10 selling books for the last three months
	 * @return
	 */
	public static ResultSet top10Books() {

		try {
			String sql = "SELECT ISBN,title,sum(totalprice)as purchaseAmount "
					+ "from book_sales natural join book WHERE date >= DATE_ADD(NOW(),INTERVAL -90 DAY) "
					+ "group by isbn order by purchaseAmount desc limit 10;";
            ResultSet rs = MainFram.stmt.executeQuery(sql);
            return rs;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
		
	
}
