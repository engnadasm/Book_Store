package bookstore;
import java.sql.ResultSet;
public interface IManager {

	/**
	 * the user enters the properties of the new book
	 * along with a threshold (the minimum quantity in
	 * stock to be maintained for that book).
	 */
	public boolean addBook(String ISBN, String title, String price,
							String pname,String paddress, String ptelephone,
							String pyear, String category,
							String threshold,String quantity,String[] authors);
	/**
	 * For updating an existing book, the user first searches 
	 * for the book then he does the required update
	 * @param attr searched attribute
	 * @param val searched value
	 * @return true if operation is executed successfully
	 */
	
	public boolean searchToModify(String attr, String val);
	/**
	 * modify existing book
	 * @param attributes to be updated
	 * @param newValues new values of the attributes
	 * @param searchAttr attribute of condition 
	 * @param searchVal value of condition 
	 * @return true if at least one book has the value val of the attribute attr
	 */
	
	public boolean modifyBook(String[] attributes, String[] newValues, String searchAttr, String searchVal );
	
	/**
	 * order quantity of a new book or an existing book
	 * @param ISBN of book
	 * @param quantity of book
	 * @return true if operation is executed successfully
	 */
	public boolean placeOrder(String ISBN, String quantity);
	
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
	public boolean confirmOrder(String ISBN);
	
	/**
	 * Promote registered customers to have managers credentials
	 * @param email customer email
	 * @return true if operation is executed successfully
	 */
	public boolean promoteCustomer(String email);
	
	/**
	 * The total sales for books in the previous month
	 * @return
	 */
	public ResultSet totalBookSale();
	
	/**
	 * The top 5 customers who purchase the most purchase 
	 * amount in descending order for the last three months
	 * @return
	 */
	public ResultSet top5Custmors();
	/**
	 * The top 10 selling books for the last three months
	 * @return
	 */
	public ResultSet top10Books();
}
