package connection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;




import requestAgents.GenericAgent;
import requestAgents.RequestAgent;

public class RequestConnector {
	private String APIURL = "";
	private String TOKEN = "";
	private String tokenKind = "Bearer ";
	private String RESSOURCE ="";
	private String ROOTNODEFORJSON = "Response";
	public static final int KIND_DELETE = 0;
	public static final int KIND_PUT = 1;
	public static final int KIND_POST = 2;
	public static final int KIND_GET = 3;
	private static int counter = 0;
	private String XPATH;
	public static void main(String[] args){
		RequestConnector con = new RequestConnector();
		//con.getContent();
		con.doAmazonRequest();
		//con.doOrbitzRequest();
	}
	public RequestConnector(){}
	public RequestConnector(String WebsiteURL,String XPATH){
		this.APIURL = WebsiteURL;
		this.XPATH = XPATH;
	}
	public RequestConnector(String TOKEN,String APIURL, String RESSOURCE) {
		super();
		this.APIURL = APIURL;
		this.TOKEN = TOKEN;
		this.RESSOURCE = RESSOURCE;
	}
	public RequestConnector(String APIURL) {
		super();
		this.APIURL = APIURL;
	}
	

	
	//------------------------------------------------------------------------------------------------SEND POST
	
	
	public HashMap<String,String> GetContentFromSwoodoo(String URL,String referer,RequestAgent reguestAgent){
		return new PhantomJsConnector().doSwoodooRequest(URL, referer, reguestAgent);
	}
	public HashMap<String,String> GetContentFromOttoMobile(String id,String URL,String referer,RequestAgent reguestAgent){
		return new PhantomJsConnector().doOttoMobile(id,URL, referer, reguestAgent);
	}
	
	public String GetContent(String URL, String specialXpath,String referer,RequestAgent reguestAgent){
		try {
			
			Response response= Jsoup.connect(URL)
			           .ignoreContentType(true)
			           .userAgent(reguestAgent.httpString())  
			           .referrer(referer)
			           .header("Connection", "close")
			           .timeout(12000) 
			           .followRedirects(true)
			           .execute();
			

			org.jsoup.nodes.Document doc = response.parse();
			
			//schreiben(response.body());
			//org.jsoup.nodes.Document doc = Jsoup.connect(URL).get();
			//Connection con = Jsoup.connect(URL);
			//con.userAgent(reguestAgent.httpString());
			Elements e = doc.select(specialXpath);
			//System.out.println(doc);
			//return e.attr("content");
			return e.text();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
	}
	public HashMap<String,String> GetContentFromOrbitz(String URL,String referer,RequestAgent requestAgent){
		HashMap<String,String> toReturn = new HashMap<String,String>();
		try {
			Response response= Jsoup.connect(URL)
			           .ignoreContentType(true)
			           .userAgent(requestAgent.httpString())  
			           .referrer(referer)
			           .header("Connection", "close")
			           .timeout(30000)
			           .followRedirects(true)
			           .execute();
				schreiben(response.body());
				org.jsoup.nodes.Document doc = response.parse();
				String priceSelector ="";
				String nameSelector = "";
				String NameofNameSelectorAttribute = "";
				if(requestAgent.whichKindOfDevice()==RequestAgent.normal){
					priceSelector = "hotelPriceInfo";
					nameSelector= "hotelNameLink link";
					NameofNameSelectorAttribute = "class";
				}else{
					priceSelector = "rate";
					nameSelector= "hotelname";
					NameofNameSelectorAttribute = "data-context";
				}
				Elements prices = doc.getElementsByAttributeValue("class", priceSelector);
				//System.out.println(prices);
				Elements names = doc.getElementsByAttributeValueContaining(NameofNameSelectorAttribute, nameSelector);
				//System.out.println(names);
				if(prices.size() == names.size()){
						for(int i =0;i<prices.size();++i){
							Elements temp = prices.get(i).getElementsByClass("leadPrice");
							if(temp.size() == 0){
								//System.out.println("0Size:"+names.get(i).text()+":"+prices.get(i).text());
								toReturn.put(names.get(i).text(), prices.get(i).text());
							}
							else{
								//System.out.println("NSize:"+names.get(i).text()+":"+temp.get(0).text());
								toReturn.put(names.get(i).text(), temp.get(0).text());
							}
						}
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return toReturn;
	}
	public HashMap<String,String> GetContentFromAmazon(String URL,String referer,RequestAgent requestAgent){
		HashMap<String,String> toReturn = new HashMap<String,String>();
		try {
		Response response= Jsoup.connect(URL)
		           .ignoreContentType(true)
		           .userAgent(requestAgent.httpString())  
		           .referrer("bla")
		           .header("Connection", "close")
		           .timeout(12000)
		           .followRedirects(true)
		           .execute();
			org.jsoup.nodes.Document doc = response.parse();
			String productSelector = "";
			String priceSelector = "";
			String productNameSelector ="";
			if(requestAgent.whichKindOfDevice() == RequestAgent.normal){
				productSelector = "s-item-container";
				priceSelector = "a-size-base a-color-price s-price a-text-bold";
				productNameSelector = "a-size-medium a-color-null s-inline s-access-title a-text-normal";
			}else{
				productSelector = "productContainer";
				priceSelector ="dpOurPrice";
				productNameSelector = "productTitle";		
			}
			
			Elements product = doc.getElementsByAttributeValueContaining("class", productSelector);
			for(Element e : product){
				Element outprice = e.getElementsByAttributeValueContaining("class", priceSelector).first();
				//Element outprice = e.getElementsByAttributeValueContaining("class", "dpOurPrice").first();
				if(outprice != null)
				{
					//System.out.println(e.getElementsByAttributeValueContaining("class", productNameSelector).);
					toReturn.put(e.getElementsByAttributeValueContaining("class", productNameSelector).first().text(), outprice.text());
					//System.out.println(e.getElementsByAttributeValueContaining("class", productNameSelector).first().text()+":"+outprice.text());
				}
			}
			return toReturn;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	public void doOrbitzRequest(){
		//String URL = "http://www.orbitz.com/shop/home?type=hotel&hotel.type=keyword&hotel.coord=&hotel.keyword.key=Los+Angeles%2C+CA%2C+United+States&hotel.locId=loc.id%3A4309&hotel.chkin=04%2F02%2F15&hotel.chkout=04%2F03%2F15&hotel.rooms[0].adlts=2&hotel.rooms[0].chlds=0&hotel.rooms[0].chldAge[0]=&hotel.rooms[0].chldAge[1]=&hotel.rooms[0].chldAge[2]=&hotel.rooms[0].chldAge[3]=&hotel.rooms[0].chldAge[4]=&hotel.rating=&hotel.hname=&hotel.couponCode=&search=Search";
		String URL = "http://www.orbitz.com/shop/home?hotel.keyword.key=New+York%2C+NY%2C+United+States&hotel.locId=loc.id%3A17796&hotel.chkin=4%2F2%2F15&hotel.chkout=4%2F3%2F15&hotel.keyword.dl=&hsv.locs=&hsv.location=&hsv.mnScore=1.0&hsv.mxScore=5.0&hsv.page=1&hotel.couponCode=&hotel.hname=&hotel.rooms[0].chlds=0&type=hotel&hotel.rooms[0].chldAge[3]=&hotel.type=keyword&hotel.rooms[0].adlts=2&hotel.rooms[0].chldAge[1]=&hotel.rooms[0].chldAge[0]=&hotel.coord=&hotel.rooms[0].chldAge[2]=&hotel.rating=&hotel.rooms[0].chldAge[4]=&search=Search";
		String useragent = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5";
		//String useragent ="Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:32.0) Gecko/20100101 Firefox/32.0";
		int kindOFDevice = RequestAgent.mobile;
	//	int kindOFDevice = RequestAgent.normal;
		HashMap<String,String> result = this.GetContentFromOrbitz(URL, "bla", new GenericAgent("bla",useragent , kindOFDevice,1920,1080));
		for(Entry<String,String> e: result.entrySet()){
			System.out.println(e.getKey()+":"+e.getValue());
		}
		
	}
	public void doAmazonRequest(){
		//String URL = "http://www.amazon.de/s/ref=nb_sb_noss_2/275-2451850-7312117?__mk_de_DE=%C3%85M%C3%85%C5%BD%C3%95%C3%91&url=search-alias%3Daps&field-keywords=Spiegelreflex";
		String URL = "http://sqat.eu-gb.mybluemix.net/";
		//String useragent = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5";
		String useragent ="Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:32.0) Gecko/20100101 Firefox/32.0";
		//int kindOFDevice = RequestAgent.mobile;
		int kindOFDevice = RequestAgent.normal;
		HashMap<String,String> result = this.GetContentFromAmazon(URL, "", new GenericAgent("bla",useragent , kindOFDevice,1920,1080));
		for(Entry<String,String> e: result.entrySet()){
			System.out.println(e.getKey()+":"+e.getValue());
		}
		
	}
	public String getContent(){
		try {
			
			String URL = "http://m.notebooksbilliger.de/tablets/site/0/limit/6";
			Response response= Jsoup.connect(URL)
			           .ignoreContentType(true)
			           .userAgent("Mozilla/5.0 (Linux; U; Android-4.0.3; en-us; Galaxy Nexus Build/IML74K) AppleWebKit/535.7 (KHTML, like Gecko) CrMo/16.0.912.75 Mobile Safari/535.7")  
			           .referrer("bla")
			           .header("Connection", "close")
			           .timeout(12000)
			           .followRedirects(true)
			           .execute();
			
			System.out.println("Reponse:"+response.body());
			org.jsoup.nodes.Document doc = response.parse();
			
			schreiben(response.body());
			//Elements prices = doc.getElementsByAttributeValueContaining("class", "dpOurPrice");
		
			/*
			Elements product = doc.getElementsByAttributeValueContaining("class", "productContainer");
			//Elements product = doc.getElementsByAttributeValueContaining("class", "s-item-container");
			for(Element e : product){
				//Element outprice = e.getElementsByAttributeValueContaining("class", "a-size-base a-color-price s-price a-text-bold").first();
				Element outprice = e.getElementsByAttributeValueContaining("class", "dpOurPrice").first();
				if(outprice != null)
				{
					System.out.println(e.getElementsByAttributeValueContaining("class", "a-size-medium s-inline s-access-title a-text-normal").first().text()+":"+outprice.text());
				}
			}
			
			*/
			
			//System.out.println(e.first().text());
			//org.jsoup.nodes.Document doc = Jsoup.connect(URL).get();
			//Connection con = Jsoup.connect(URL);
			//con.userAgent(reguestAgent.httpString());
			//Elements e = doc.select(specialXpath);
			//System.out.println(doc);
			//return e.attr("content");
			return "";
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
	}
	private void schreiben(String g){
		FileWriter writer;
		File file = new File("FileWriterTest"+counter+++".txt");
	     try {
	       // new FileWriter(file ,true) - falls die Datei bereits existiert
	       // werden die Bytes an das Ende der Datei geschrieben
	       
	       // new FileWriter(file) - falls die Datei bereits existiert
	       // wird diese überschrieben
	       writer = new FileWriter(file ,false);
	       
	       // Text wird in den Stream geschrieben
	       writer.write(g);
	       writer.flush();
	       writer.close();
	     }catch (Exception e){
	    	 System.out.println(e);
	     }
	}
	public static String evaluateXPath(Document doc, String Xpath){
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr;
		System.err.println("evaluateXPath: "+Xpath); //-----------------------------------------------------DEBUGCODE
		try {
			expr = xpath.compile(Xpath);
			String erg = (String) expr.evaluate(doc,XPathConstants.STRING);
	        System.out.println(erg);
	        return erg;
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error by evaluate XPATH";
		}
		
	}
	private JSONObject responseToJsonObject(String Response){
		return  new JSONObject(Response.toString());
	}
	private String jsonToXMLString(JSONObject json){
		return XML.toString(json);
	}
	private Document ResponseToXMLConverter(String Response){
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(Response));
	        Document document =  builder.parse(is);
	        return document;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
        
	}

}
