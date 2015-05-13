package website;

import java.util.ArrayList;

public class WebsiteFactory {

	public IWebsite createWebsite(String URL, ArrayList<String> csspaths){
		return new WebsiteImpl(URL, csspaths,WebsiteImpl.typeXPath);
	}
	//public IWebsite createWebsiteOutOfType(String URL, String typeOfWebSite){
	//	return new WebsiteImpl(URL, typeOfWebSite,122);
	//}
}
