package persist;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;



public class FileLogging implements IPersits {

	private Document doc = null ;
	private String rootNode = "RequestData";
	private String requestNode = "Request";
	private String timeAttribute = "time";
	private String urlAttribute= "url";
	private String ipAttribute = "ip";
	private String dateAttribute = "date";
	private String fileNameOfResult= null;
	
	GregorianCalendar cal = new GregorianCalendar(); 
	DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	DateFormat dfTime = new SimpleDateFormat("HH:mm:ss");
	
	protected FileLogging () {
		
	}
	public boolean setUpNewTest(String IP) {
		setUpNewXmlDocument(IP);
		return true;
	}

	public boolean addRequestData( String URL, double price) {
		addRequestToXML(URL, price);
		return true;
	}
	
	public boolean close() {
		String time = df.format(cal.getTime());
		time.replaceAll(".", "_");
		writeToHDD(time+"_DataCatch"+".xml");
		return true;
	}
	public void writeToHDD(String resultFileName){
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(resultFileName));
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
	 
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 
		
	}
	private void addRequestToXML(String URL,double price){
		if(doc == null){
			
		}
		Element e = createReqeustXML();
		addTimeAttribute(e);
		addURLAttribute(e, URL);
		addPrice(e, price);
		doc.getFirstChild().appendChild(e);
		
	}
	private void addPrice(Element workingElement, double price){
		workingElement.appendChild(doc.createTextNode(price+""));
	}
	private void addTimeAttribute(Element workingElement){
		workingElement.setAttribute(timeAttribute,""+ dfTime.format(cal.getTime()));
	}
	private void addDateAttribute(Element workingElement){
		workingElement.setAttribute(dateAttribute,""+ df.format(cal.getTime()));
	}
	private void addURLAttribute(Element workingElement, String url){
		workingElement.setAttribute(urlAttribute,url);
	}
	private Element createReqeustXML(){
		return doc.createElement(requestNode);
	}
	private void setUpNewXmlDocument(String IP){
		try
		{
		  DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		  DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		  //root elements
		  doc = docBuilder.newDocument();

		  Element rootElement = doc.createElement(rootNode);
		  doc.appendChild(rootElement);
		  rootElement.setAttribute(dateAttribute, df.format(cal.getTime()));
		  rootElement.setAttribute(ipAttribute, IP); 
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
