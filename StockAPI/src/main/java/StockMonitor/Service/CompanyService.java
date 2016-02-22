package StockMonitor.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import StockMonitor.Database.*;
import StockMonitor.Model.Company;

/**
 * Performs operations on the model and returns the results.
 * @author Ashutosh
 *
 */
public class CompanyService {
	private DatabaseClass dbObject = new DatabaseClass(); 
	
	/**
	 * Gets all the companies from the database and returns the List of comapnies
	 * @return
	 */
	public List<Company> getAllCompanies(){
		Map<String, Company> companies = dbObject.runQuery("getAllCompanies", "");
		return new ArrayList<Company>(companies.values());
	}
	
	/**
	 * Delete a company from the database given the stock symbol
	 * @param symbol
	 */
	public void deleteCompany(String symbol){
		dbObject.runQuery("deleteCompany", symbol);
	}

}
