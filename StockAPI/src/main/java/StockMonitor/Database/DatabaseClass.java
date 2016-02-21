package StockMonitor.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import StockMonitor.Model.*;

public class DatabaseClass {
	
	private Map<String, Company> companies = new HashMap<>();
	private Connection con;
	/*
	 * Insert the given stock to mysqldb stocks in table stockprice
	 */
	public Map<String, Company> getCompanies(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocks", "root", "logicmonitor");
			Statement insertStockStatement = con.createStatement();
			String sql = "select * from stockprice";
			ResultSet rs = insertStockStatement.executeQuery(sql);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return companies;
	}
	
	
	
	
}
