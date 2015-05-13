package requestObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import configXML.ConfigXML;
import connection.RequestConnector;
import requestAgents.RequestAgent;
import website.IWebsite;

public class SingleRequestMuliOutput implements RequestObject {

	private String websiteURL;
	private RequestAgent agent;
	double price = -1;
	HashMap<String,Double> priceResults = new HashMap<String,Double>();
	String name ="";
	String referer = "";
	Timestamp time = null;
	String kindOfSpecialImpl = "";
	public SingleRequestMuliOutput(String name, String URL, String kind, String Referer,RequestAgent agent) {
		
		websiteURL = URL;
		System.out.println("URL:"+websiteURL+":"+agent.getName());
		//this.agent = RequestAgent.factory.createAgent(agent);
		this.agent = agent;
		this.name = name;
		this.referer = Referer;
		this.kindOfSpecialImpl = kind;
	}
	
	public void perform() {
		RequestConnector re = new RequestConnector(this.websiteURL,"");
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
		
		HashMap<String, String> rawResults = null;
		if(this.kindOfSpecialImpl.equals(ConfigXML.ATTIBUTE_SPECIALIMPL_AMAZON)){
			rawResults = re.GetContentFromAmazon(this.websiteURL, this.referer, agent);
		}else{
			if(this.kindOfSpecialImpl.equals(ConfigXML.ATTIBUTE_SPECIALIMPL_ORBITZ)){
				rawResults = re.GetContentFromOrbitz(this.websiteURL, this.referer, agent);
			}else
				{if(this.kindOfSpecialImpl.equals(ConfigXML.ATTIBUTE_SPECIALIMPL_SWOODO)){
					rawResults = re.GetContentFromSwoodoo(this.websiteURL, this.referer, agent);
				}else{
					if(this.kindOfSpecialImpl.equals(ConfigXML.ATTIBUTE_SPECIALIMPL_MOBILEOTTO)){
						rawResults= re.GetContentFromOttoMobile(this.name, this.websiteURL, this.referer, agent);
					}
				}
			}
				
		}
		
			double sum = 0.0;
			for(Entry<String,String> e : rawResults.entrySet()){
				String OnlyNumber = e.getValue().replaceAll("[\\D&&[^,]&&[^.]]", "");
				if(OnlyNumber.contains(",")){
					OnlyNumber = OnlyNumber.replace(",", ".");
				}
				try{
					Double price = Double.parseDouble(OnlyNumber);
					sum += price;
					priceResults.put(e.getKey(), price);
				}catch (Exception e1){
					System.err.println(e1);
				}
			}
			sum = sum/priceResults.size()*1.0;
			if(!this.name.startsWith("Otto")){
				priceResults.put("average"+"_"+this.name+"_"+this.agent.getName(), sum);
			}
			
		
			
			
		}
		
		//System.out.println(OnlyNumber);
		//System.out.println(priceString);
		

	public double getPrice() {
			System.err.println("this is a mulitble class, there is no single price please use the getAllPrices Function");
		
		// TODO Auto-generated method stub
		return -1;
	}

	public String getURL() {
		return this.websiteURL;
	}

	public String getCssPath() {
		return "no css Path avaible";
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
		return priceResults;
	}

	@Override
	public boolean hasMulitblePrices() {
		return true;
	}


}
