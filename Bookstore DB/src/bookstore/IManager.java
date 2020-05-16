package bookstore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
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
           String sql1 = "INSERT INTO BOOK VALUES(" +ISBN +","+ title+","+ pyear+","+ pname+","
        		   + category + "," + price + "," + threshold + ","+ quantity +");";
 	       String sql2 = "INSERT INTO PUBLISHER VALUES(" + pname +","+ paddress +","+ ptelephone +  ");";
 	       
 	      MainFram.stmt.executeUpdate(sql1);
 	     MainFram.stmt.executeUpdate(sql2);
			for(int i = 0; i < authors.length; i++) {
				String sql3 = "INSERT INTO Author VALUES(" +authors[i] +","+ISBN+ ");";
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
	 * For updating an existing book, the user first searches 
	 * for the book then he does the required update
	 * @param attr searched attribute
	 * @param val searched value
	 * @return true if operation is executed successfully
	 */
	
	public static boolean searchToModify(String attr, String val){
		// TODO Auto-generated method stub
		try {
			String sql;
		if(attr.equalsIgnoreCase("author")) {
			sql = "SELECT ISBN FROM Author WHERE " + attr +" = "+ val + ");";
		}else {
			sql = "SELECT ISBN FROM BOOK WHERE " + attr +" = "+ val + ");";
		}
		ResultSet rs = MainFram.stmt.executeQuery(sql);
		return rs.next();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * modify existing book
	 * @param attributes to be updated
	 * @param newValues new values of the attributes
	 * @param searchAttr attribute of condition 
	 * @param searchVal value of condition 
	 * @return true if at least one book has the value val of the attribute attr
	 */
	
	public static boolean modifyBook(String[] attributes, String[] newValues, String searchAttr, String searchVal ) {
		
		 StringBuilder str = new StringBuilder();  
		 str.append("UPDATE BOOK SET ");
		 int i = 0;
			for(i = 0; i < attributes.length - 1; i++) {
				str.append(attributes[i] + " = " + newValues[i] + ", ");
			}
			str.append(attributes[i] + " = " + newValues[i] + "WHERE " );
			
		if(searchAttr.equalsIgnoreCase("author")) {
			String s = "SELECT ISBN FROM Author WHERE " + searchAttr +" = "+ searchVal + ");";
			str.append("ISBN in (" + s + ");");
		}
		else { 
			str.append(searchAttr + " = " + searchVal + ";");
		}
			try {

				String sql = str.toString();
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
			sql2 = "UPDATE ORDERS SET QUANTITY = QUANTITY + " + quantity + "WHERE ISBN = " + ISBN + ";";
			
		} else {
			sql2 = "INSERT INTO ORDERS VALUES(" + ISBN + "," + quantity + ");";
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
			String sql = "DELETE FROM ORDERS WHERE ISBN =" + ISBN + ";";
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
			String sql1 = "SELECT EMAIL FROM USERS WHERE EMAIL = "+ email + ";";
			ResultSet rs = MainFram.stmt.executeQuery(sql1);
			//if email exists promote
			if(rs.next()) {
				String sql = "UPDATE USERS SET IS_MANAGER = 1 WHERE EMAIL = " + email +";";
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
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		currentMonth -= 1;
		try {
			String sql = "SELECT ISBN, sum(no_of_copies) FROM BOOK_SALES WHERE month(DATE) >= "
				+currentMonth+ " GROUP BY ISBN;";
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
		
	int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
	currentMonth -= 3;
	try {
		String sql = "SELECT first_name,last_name,sum(totalprice)as purchaseAmount, email "
				+ "from book_sales natural join users where month(DATE) >= "+ currentMonth
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
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		currentMonth -= 3;
		try {
			String sql = "SELECT ISBN,title,sum(totalprice)as purchaseAmount "
					+ "from book_sales natural join book where month(date) >= " + currentMonth
					+ " order by purchaseAmount desc limit 10;";
            ResultSet rs = MainFram.stmt.executeQuery(sql);
            return rs;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
		
	
}
