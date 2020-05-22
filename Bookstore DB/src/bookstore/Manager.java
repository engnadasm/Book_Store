package bookstore;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
public class Manager extends User implements IManager{
	
	private Connection conn = null;
	private Statement stmt = null;
	
public Manager() {
	super();
	try
    {
        String userName = "root";
        String password = "1234abcd";
        String url = "jdbc:MySQL://localhost:3306/library1";        
        conn = DriverManager.getConnection (url, userName, password);
        stmt = conn.createStatement();
        

    }catch (Exception ex)
    {
	       System.err.println ("Cannot connect to database server");
		   ex.printStackTrace();
    }
    }
	public boolean addBook(String ISBN, String title, String price,
			String pname,String paddress, String ptelephone,
			String pyear, String category,
			String threshold,String quantity,String[] authors){
		// TODO Auto-generated method stub	       
	        try {
	           String sql1 = "INSERT INTO BOOK VALUES(" + ");";
	 	       String sql2 = "INSERT INTO PUBLISHER VALUES(" + pname +","+ paddress +","+ ptelephone +  ");";
	 	       
				stmt.executeUpdate(sql1);
				stmt.executeUpdate(sql2);
				for(int i = 0; i < authors.length; i++) {
					String sql3 = "INSERT INTO BOOK_AUTHORS VALUES(" +ISBN +","+authors[i]+ ");";
					stmt.executeUpdate(sql3);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		return true;
	}

	public boolean searchToModify(String attr, String val) {
		// TODO Auto-generated method stub
		try {
			String sql;
		if(attr.equalsIgnoreCase("author")) {
			sql = "SELECT ISBN FROM BOOK_AUTHORS WHERE " + attr +" = "+ val + ");";
		}else {
			sql = "SELECT ISBN FROM BOOK WHERE " + attr +" = "+ val + ");";
		}
		ResultSet rs = stmt.executeQuery(sql);
		return rs.next();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean modifyBook(String[] attributes, String[] newValues, String searchAttr, String searchVal) {
		// TODO Auto-generated method stub		 
		 StringBuilder str = new StringBuilder();  
		 str.append("UPDATE BOOK SET ");
		 int i = 0;
			for(i = 0; i < attributes.length - 1; i++) {
				str.append(attributes[i] + " = " + newValues[i] + ", ");
			}
			str.append(attributes[i] + " = " + newValues[i] + "WHERE " );
			
		if(searchAttr.equalsIgnoreCase("author")) {
			String s = "SELECT ISBN FROM BOOK_AUTHORS WHERE " + searchAttr +" = "+ searchVal + ");";
			str.append("ISBN in (" + s + ");");
		}
		else { 
			str.append(searchAttr + " = " + searchVal + ";");
		}
			try {

				String sql = str.toString();
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		
		return true;
	}


	public boolean placeOrder(String ISBN, String quantity) {
		// TODO Auto-generated method stub
		try {
		//search ISBN in orders
		String sql1 = "SELECT ISBN FROM ORDER WHERE ISBN = "+ ISBN + ";";
		ResultSet rs = stmt.executeQuery(sql1);
		String sql2;
		//if ISBN exists update quantity
		if(rs.next()) {
			sql2 = "UPDATE ORDER SET QUANTITY = QUANTITY + " + quantity + "WHERE ISBN = " + ISBN + ";";
			
		} else {
			sql2 = "INSERT INTO ORDER VALUES(" + ISBN + "," + quantity + ");";
		}
		stmt.executeUpdate(sql2);
		}		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public boolean confirmOrder(String ISBN) {
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM ORDER WHERE ISBN =" + ISBN + ";";
            stmt.executeUpdate(sql);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public boolean promoteCustomer(String email) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE USER SET ISMANAGER = TRUE WHERE EMAIL = " + email +";";
            stmt.executeUpdate(sql);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public ResultSet totalBookSale() {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT ISBN, COUNT(*) FROM BOOK_SALES WHERE DATE >= GROUP BY ISBN;";
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}


	public ResultSet top5Custmors() {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT fname,lname,sum(totalprice)as purchaseAmount, email "
					+ "from book_sales natural join user"
					+ "group by email order by purchaseAmount desc limit 5;";
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	public ResultSet top10Books() {
		// TODO Auto-generated method stub
		try {
			String sql = "SELECT fname,lname,sum(totalprice)as purchaseAmount, email "
					+ "from book_sales natural join book where month(date) >= "
					+ "order by purchaseAmount desc limit 10;";
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
