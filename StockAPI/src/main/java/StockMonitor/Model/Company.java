package StockMonitor.Model;

public class Company {
	private String symbol;
	private String name;
	private String currency;
	private double price;
	
	public Company(){
		
	}
	public Company(String symbol, String name, String currency, double price){
		this.symbol = symbol;
		this.name = name;
		this.currency = currency;
		this.price = price;
	}
	
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
