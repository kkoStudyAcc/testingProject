package requestObject;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.XML;
import org.w3c.dom.Document;

import connection.RequestConnector;
import requestAgents.RequestAgent;
import requestAgents.RequestAgents;
import website.IWebsite;
import website.XPathValidator;

public class RequestObjectImpl implements RequestObject {

	private IWebsite website ;
	private RequestAgent agent;
	double price = -1;
	String name ="";
	String referer = "";
	Timestamp time = null;
	public RequestObjectImpl(String name,String uRL, ArrayList<String> csspaths, String Referer,RequestAgent agent) {
		website = IWebsite.factory.createWebsite(uRL, csspaths);
		//this.agent = RequestAgent.factory.createAgent(agent);
		this.agent = agent;
		this.name = name;
		this.referer = Referer;
		
	}
	
	public void perform() {
		RequestConnector re = new RequestConnector(website.getUrl(),website.getPricePath(agent.whichKindOfDevice()));
		//HashMap<String, Object> result = re.sendREQUEST(RequestConnector.KIND_GET, agent);
		/*
		if(result.containsKey("xml")){
			Object xmlObject = result.get("xml");
			if(xmlObject instanceof Document){
				Document XML = (Document) xmlObject;
				this.price = XPathValidator.getValue(XML, website.getxPath()) ;
			}else{
				System.err.println("Value to key 'xml' is no instance of Document");
			}
		}else{
			System.err.println("Request doesnt gave back a xml");
		}*/
		
		this.time = new Timestamp(new Date().getTime());
		String priceString = re.GetContent(website.getUrl(), website.getPricePath(agent.whichKindOfDevice()),this.referer,agent);
		
		System.out.println(priceString);
		String OnlyNumber = priceString.replaceAll("[\\D&&[^,]&&[^.]]", "");
		if(OnlyNumber.contains(",")){
			OnlyNumber = OnlyNumber.replace(".", "");
			OnlyNumber = OnlyNumber.replace(",", ".");
			
		}
		if(this.name.startsWith("bonprix")){
			OnlyNumber = OnlyNumber.substring(0, 2) + "."+OnlyNumber.substring(2);
		}
		
		try{
			this.price = Double.parseDouble(OnlyNumber);
		}catch (Exception e){
			System.err.println("error by parsing");
			System.err.println(e);
			for(String csspath : website.getAllPricePaths()){
				priceString = re.GetContent(website.getUrl(), csspath,this.referer,agent);
				
				System.out.println(priceString);
				OnlyNumber = priceString.replaceAll("[\\D&&[^,]&&[^.]]", "");
				if(OnlyNumber.contains(",")){
					OnlyNumber = OnlyNumber.replace(",", ".");
				}
				
				try{
					this.price = Double.parseDouble(OnlyNumber);
					break;
				}catch (Exception ee){
					System.err.println("alternetive also failed");
					this.price = -1;
				}
				}
			}
			
			
		}
		
		//System.out.println(OnlyNumber);
		//System.out.println(priceString);
		

	public double getPrice() {
		if(price == -1){
			System.err.println("something went wrong, the price variable was never touched. Do you call the method perform?");
		}
		// TODO Auto-generated method stub
		return this.price;
	}

	public String getURL() {
		return website.getUrl();
	}

	public String getCssPath() {
		return website.getPricePath(agent.whichKindOfDevice());
	}

	public String getName() {
		return this.name;
	}

	public RequestAgent getRequestAgent() {
		return this.agent;
	}


	public String getExecutionTime() {
		if(this.time == null){
			return "empty";
		}
		String DateTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(this.time);
		return DateTime;
	}

	@Override
	public String getReferer() {
		return this.referer;
	}

	@Override
	public HashMap<String, Double> getAllPriceResults() {
		return null;
	}

	@Override
	public boolean hasMulitblePrices() {

		return false;
	}

	

}
