package website;

import java.util.ArrayList;

public interface IWebsite {
	WebsiteFactory factory = new WebsiteFactory();
	XPathValidator validator = new XPathValidator();
	
	public String getPricePath (int kindOfDevice);
	public String getUrl();
	public ArrayList<String> getAllPricePaths();
		
}
