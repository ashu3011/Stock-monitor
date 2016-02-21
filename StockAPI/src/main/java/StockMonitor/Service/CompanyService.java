package StockMonitor.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import StockMonitor.Database.*;
import StockMonitor.Model.Company;


public class CompanyService {
	private DatabaseClass dbObject = new DatabaseClass(); 
	
	private Map<String, Company> companies = dbObject.getCompanies();
	
	public List<Company> getAllCompanies(){
		return new ArrayList<Company>(companies.values());
	}
	
	/*
	 * Add remining functionalities like below
	 */
	public Company getCompany(String symbol){
		return companies.get(symbol);
	}

}
