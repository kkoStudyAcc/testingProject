package main;

import persist.IPersits;
import requestAgents.RequestAgent;
import requestAgents.RequestAgents;
import requestObject.RequestObject;
import website.IWebsite;
import website.XPathValidator;

public class Start {

	public static void main(String[] args) {
		Start s = new Start();
		s.startTestSequence();
		

	}
	public void startTestSequence(){
		//TODO: Pizza service, Taxi Klamotten, Musik, APPS
		IPersits logging = IPersits.factory.getFileLogging();
		
			//RequestObject request = RequestObject.factory.createRequestObjecti("http://www.flug24.de/flight/encodes/sFlightInput/48c829e54fb2094ea5f55029b8f06ee9/","//*[@id='itinerary_list']/div[1]/form/div[1]/span", RequestAgents.CHROME);
		//RequestObject request = RequestObject.factory.createRequestObjecti("http://www.alternate.de/Crucial/MX100-2-5-SSD-256-GB/html/product/1142839?","#buyProduct > div.productShort > p.price > span", RequestAgents.CHROME);
		//RequestObject request = RequestObject.factory.createRequestObjecti("http://www.amazon.de/Lenovo-Notebook-Hybrid-Graphics-schwarz/dp/B00FBD4YZI", "#priceblock_ourprice ", RequestAgents.CHROME);
		//RequestObject request = RequestObject.factory.createRequestObjecti("test Request","http://play.google.com/store/apps/details?id=ch.threema.app&hl=de","#body-content > div:nth-child(4) > div > div.details-info > div.info-container > div.details-actions > span > button > span:nth-child(2)", RequestAgents.SAFARI);
		
		
		
			//RequestObject request = RequestObject.factory.createRequestObjecti("https://itunes.apple.com/de/app/threema/id578665578?mt=8","#left-stack > div.lockup.product.application > ul > li:nth-child(1) > div", RequestAgents.CHROME);
		//request.perform();
		//System.out.println(request.getPrice() +"");
	}
	
}
