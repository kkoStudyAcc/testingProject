package website;

import java.util.ArrayList;

public class WebsiteImpl implements IWebsite {

	private String URL = "";
	private String xPath = "";
	private String normalPricePath = "";
	private String mobilePricePath = "";
	private double price  = 0.0;
	public static int typeXPath =0;
	ArrayList<String> csspaths = new ArrayList<String>();
	public WebsiteImpl(String uRL, ArrayList<String> csspathsInput, int type) {
		if(type == typeXPath){
		this.URL = uRL.replaceAll("&amp;", "&");
		for(String e : csspathsInput){
			csspaths.add(e.replaceAll("&gt;",">"));
		}
		}
		
	}
	public String getPricePath (int kindOfDevice){
		if(kindOfDevice < csspaths.size() )
			return csspaths.get(kindOfDevice);
		else
			return csspaths.get(csspaths.size()-1);
	}
	public String getUrl () {return this.URL;}
	@Override
	public ArrayList<String> getAllPricePaths() {
		return this.csspaths;
	}

}
