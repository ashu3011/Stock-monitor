package StockMonitor.StockAPI;
import StockMonitor.Model.Company;
import StockMonitor.Service.CompanyService;


import java.util.List;

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
        	responseString.append("<tr><td>"+cp.getSymbol() + "<td>" + cp.getName() + "<td>" + cp.getCurrency() + "<td>" 
        						+ Double.toString(cp.getPrice()) + "</tr>");
        }
        return responseString.toString();
    }
    
    @POST
    @Path("/add/{companyName}")
    @Produces(MediaType.TEXT_HTML)
    public String addCompany(@PathParam("companyName") String name){
    	
    	return "<h3> Started monitoring" + name + "</h3>";
    }
    
}
