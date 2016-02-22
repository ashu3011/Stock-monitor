package StockMonitor.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import StockMonitor.Model.*;


/**
 * Consists of database class that performs operation in database and returns values if required.
 * @author Ashutosh
 *
 */
public class DatabaseClass {
	
	private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/stocks";
	private final String USER = "root";
	private final String PASSWORD = "logicmonitor";
	
	private Map<String, Company> companies = new HashMap<>();
	private Connection con;
	/**
	 * Insert or deletes the given stock to mysqldb stocks in table stockprice
	 * @param queryType type of query if Insert or Delete
	 * @param name Stock Symbol
	 * @return Companies map
	 */
	public Map<String, Company> runQuery(String queryType, String name){
		try {
			//Connect to database
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
			Statement StockStatement = con.createStatement();
			
			//get sql according to the query type (get or Delete)
			String sql = getSql(queryType, name);
			
			//If delete query
			if(queryType == "deleteCompany"){
				StockStatement.execute(sql);
				return companies;
			}
			
			//If get query
			ResultSet rs = StockStatement.executeQuery(sql);
			while(rs.next()){
				Company comp = new Company(rs.getString("SYMBOL"), rs.getString("SNAME"), rs.getString("CURRENCY"), rs.getDouble("PRICE"));
				companies.put(rs.getString("SYMBOL"), comp);
			}
			con.close();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
  
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return companies;
	}
	
	//returns sql based in query required (Insert or Delete)
	private String getSql(String queryType, String name) {
		String query = "";
		if(queryType == "getAllCompanies"){
			query = "select * from stockprice";
		}
		else if(queryType == "deleteCompany"){
			query = "DELETE FROM stockprice WHERE SYMBOL = '" + name + "'";
		}
		return query;
	}
	
	
	
	
}
