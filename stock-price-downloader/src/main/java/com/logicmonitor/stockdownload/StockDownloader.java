package com.logicmonitor.stockdownload;
/*
 * The standalone program to download stock price from Yahoo Finance given the symbol
 */
import java.sql.*;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class StockDownloader {
	private Connection con;
	/*
	 * Insert the given stock to mysqldb stocks in table stockprice
	 */
	/**
	 * Insert the given stock to mysqldb stocks in table stockprice
	 * @param stock
	 * @throws SQLException
	 */
	public void insertStockToDb(Stock stock){
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocks", "root", "logicmonitor");
			Statement insertStockStatement = con.createStatement();
			String sql = "Insert into stockprice (SYMBOL, SNAME, CURRENCY, PRICE)"
					+ "values ('" + stock.getSymbol() + "','" + stock.getName()+ "','" 
					+ stock.getCurrency() + "','" + stock.getQuote().getPrice().toString() + "')";
			insertStockStatement.executeUpdate(sql);
			System.out.println("Insert Complete");
			con.close();
			
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    
		}
	}
	
	
	/**
	 * Using Yahoo Finance, downloads the stock info, it takes Symbol as input
	 * @param StockName
	 */
	public void downloadStockInfo(String StockName){
		try {
			Stock stock = YahooFinance.get(StockName);
			insertStockToDb(stock);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
