package com.logicmonitor.stockdownload;
/*
 * The standalone program to download stock price from Yahoo Finance given the symbol
 */
import java.sql.*;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 * Conatins members to download a stock and all its information based on Symbol
 * @author Ashutosh
 *
 */
public class StockDownloader {
	private Connection con;
	private final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/stocks";
	private final String USER = "root";
	private final String PASSWORD = "logicmonitor";
	/*
	 * Insert the given stock to mysqldb stocks in table stockprice
	 */
	/**
	 * Insert the given stock to mysqldb stocks in table stockprice
	 * @param stock
	 * @throws SQLException
	 */
	public void insertStockToDb(Stock stock, String name){
		try {
			//Get the connection
			con = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
			Statement insertStockStatement = con.createStatement();
			
			//sql to get all the current stocks
			String sql = "select * from stockprice";
			ResultSet rs = insertStockStatement.executeQuery(sql);
			
			//if stock already exists do not download
			while(rs.next()){
				if (rs.getString("SYMBOL").equals(name)){
					System.out.println("Stock already Exists");
					return;
				}
			}
			
			// Else download the stock and add to the database
			sql = "Insert into stockprice (SYMBOL, SNAME, CURRENCY, PRICE)"
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
	public void downloadStockInfo(String stockName){
		try {
			//get stock from yahoo finance and save in Stock object
			Stock stock = YahooFinance.get(stockName);
			insertStockToDb(stock, stockName);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
