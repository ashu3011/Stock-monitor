package StockMonitor.StockAPI;
import StockMonitor.Model.Company;
import StockMonitor.Service.CompanyService;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import com.logicmonitor.stockdownload.*;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * Root resource (exposed at "companies" path)
 */
@Path("/companies")
public class CompanyResource {
	
	CompanyService apiService = new CompanyService();

	
    /**
     * Method handling HTTP GET requests for all companies. returns all companies
     * to the client as "text/HTML" media type.
     * @return HTML that will be returned as a text/plain response.
     */
    @GET
    @Path("/list")
    @Produces(MediaType.TEXT_HTML)
    public String listCompanies() {
    	StringBuilder responseString = new StringBuilder();
    	responseString.append("<table><tr><th>Symbol</th><th>Name</th><th>Currency</th><th>Price</th></tr>");
        List<Company> companyList =  apiService.getAllCompanies();
        for(Company cp: companyList){
        	Stock stock = YahooFinance.get(cp.getSymbol());
        	responseString.append("<tr><td>"+cp.getSymbol() + "<td>" + cp.getName() + "<td>" + cp.getCurrency() + "<td>" 
        						+ stock.getQuote().getPrice().toString() + "</tr>");
        }
        responseString.append("<br><a href='../../'> Back to Home </a> ");
        return responseString.toString();
    }
    
    /**
     * Method to add company to the database for monitoring
     * @param name The company name take from the url after ../add/
     * @return Success page
     */
    @POST
    @Path("/add/{companyName}")
    @Produces(MediaType.TEXT_HTML)
    public String addCompany(@PathParam("companyName") String name){
    	StockDownloader stockDownloader = new StockDownloader();
    	stockDownloader.downloadStockInfo(name);
    	/* add handler if user puts wrong symbol name, Also when company exists */
    	return "<h3>Started monitoring " + name + "</h3><br><a href='../../../'> Back to Home </a> ";
    }
    
    /**
     * Method to delete company from the database
     * @param name The company name take from the url after ../delete/
     * @return Success page
     */
    @DELETE
    @Path("/delete/{companyName}")
    @Produces(MediaType.TEXT_HTML)
    public String deleteCompany(@PathParam("companyName") String name){
    	apiService.deleteCompany(name);
    	return "<h3>Stopped monitoring " + name + "</h3><br><a href='../../../'> Back to Home </a> ";
    }
    
    /**
     * Delete API end point for the HTML UI
     * @param name 
     * @return
     */
    @POST
    @Path("/delete/{companyName}")
    @Produces(MediaType.TEXT_HTML)
    public String deleteCompanyUI(@PathParam("companyName") String name){
    	apiService.deleteCompany(name);
    	return "<h3>Stopped monitoring " + name + "</h3><br><a href='../../../'> Back to Home </a> ";
    }
    
    /**
     * Gets the historical performance of the stock
     * @param name company Symbol after ./history/
     * @return Historical performance of the stock
     */
    @GET
    @Path("/history/{companyName}")
    @Produces(MediaType.TEXT_HTML)
    public String getHistory(@PathParam("companyName") String name){
    	return "<br><a href='../../../'> Back to Home </a><br><img src='http://chart.finance.yahoo.com/z?s=" + name + "&t=6m&q=l&l=on&z=s&p=m50,m200'/>"; 
    } 
}
