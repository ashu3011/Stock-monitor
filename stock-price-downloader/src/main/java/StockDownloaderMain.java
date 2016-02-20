/*
 * The standalone program to download stock price from Yahoo Finance given the symbol
 */
import java.sql.*;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class StockDownloaderMain {
	
	/*
	 * Insert the given stock to mysqldb stocks in table stockprice
	 */
	public void insertStockToDb(Stock stock){
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocks", "root", "logicmonitor");
			Statement insertStockStatement = con.createStatement();
			String sql = "Insert into stockprice (SYMBOL, SNAME, CURRENCY, PRICE)"
					+ "values ('" + stock.getSymbol() + "','" + stock.getName()+ "','" 
					+ stock.getCurrency() + "','" + stock.getQuote().getPrice().toString() + "')";
			insertStockStatement.executeUpdate(sql);
			System.out.println("Insert Complete");
			
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	/*
	 * Using Yahoo Finance API we download the stock info, it takes name as input
	 */
	public static Stock downloadStockInfo(String StockName){
		Stock stock = YahooFinance.get(StockName);
		return stock;
	}

}
