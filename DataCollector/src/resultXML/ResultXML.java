package resultXML;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import configXML.ConfigXML;
import requestObject.RequestObject;

public class ResultXML {
	private Document resultXML=null;
public static ResultXML generateResultXML(ArrayList<RequestObject> reqList){
	ResultXML toReturn = new ResultXML();
	toReturn.resultXML = generateXMLObject();
	for( RequestObject req : reqList){
		addNewResult(toReturn.resultXML, req);
	}
	return toReturn;
}
private ResultXML(){}
private ResultXML(Document doc){
	resultXML = doc;
}
public static ResultXML generateResultXMLOutOFFile(File XMLFile){
	if(XMLFile.exists()){
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XMLFile.getAbsolutePath());
			return new ResultXML(doc);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	return null;
}

public String getUID(){
	return resultXML.getDocumentElement().getAttributes().getNamedItem("uid").getNodeValue();
}
public String getRunCounter(){
	if(resultXML.getDocumentElement().hasAttribute("runCounter"))
		return resultXML.getDocumentElement().getAttributes().getNamedItem("runCounter").getNodeValue();
	else
		return "0";
}
public ArrayList<Result> getResults(){
	ArrayList<Result> toReturn = new ArrayList<Result>();
	NodeList list =  resultXML.getDocumentElement().getElementsByTagName("request");
	for(int i =0;i<list.getLength();i++){
		Result r = new Result();
		Node rootnode = list.item(i);
		r.time = rootnode.getAttributes().getNamedItem("time").getNodeValue();
		String price = rootnode.getAttributes().getNamedItem("price").getNodeValue();
		if(price.equals("NaN"))
				r.price = "-1";
		else 
			r.price = price;
		Node workingNode = rootnode.getFirstChild();
		r.name = workingNode.getTextContent();
		workingNode = workingNode.getNextSibling();
		r.url = workingNode.getTextContent();
		workingNode = workingNode.getNextSibling();
		r.cssPath = workingNode.getTextContent();
		workingNode = workingNode.getNextSibling();
		r.Useragent = workingNode.getTextContent();
		workingNode = workingNode.getNextSibling();
		r.referer = workingNode.getTextContent();
		toReturn.add(r);
	}
	return toReturn;
}
public String getXMLString(){
	return toXMLString(this.resultXML);
}
public void setIDAttribute(String id){
	if(resultXML != null){
		resultXML.getDocumentElement().setAttribute("uid", id);
	}
}
public void setRunCounter(String counter){
	if(resultXML != null){
		resultXML.getDocumentElement().setAttribute("runCounter", counter);
	}
}



public static String toXMLString(Document doc) {
	//http://www.theserverside.com/discussions/thread.tss?thread_id=26060
	//File file = new File("test.xml");
	TransformerFactory tf = TransformerFactory.newInstance();
	Transformer transformer;
	try {
		transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		String output = writer.getBuffer().toString().replaceAll("\n|\r", "");

		return output;
	} catch (TransformerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "";
	}
	
	
	
	/*try
    {
       DOMSource domSource = new DOMSource(doc);
       StringWriter writer = new StringWriter();
       StreamResult result = new StreamResult(writer);
       TransformerFactory tf = TransformerFactory.newInstance();
       Transformer transformer = tf.newTransformer();
       transformer.transform(domSource, result);
       return writer.toString();
    }
    catch(TransformerException ex)
    {
       ex.printStackTrace();
       return null;
    }*/
}




private static void addNewResult(Document doc,RequestObject req){
	
	
	if(req.hasMulitblePrices()){
		
		HashMap<String,Double> priceResults = req.getAllPriceResults();
		for(Entry<String,Double> entry : priceResults.entrySet()){
			Element request = doc.createElement("request");
			doc.getDocumentElement().appendChild(request);
			String temp = entry.getValue()+"";
			if(temp != null)
				request.setAttribute("price",temp );
			
			temp = req.getExecutionTime();
			if(temp != null)
				request.setAttribute("time", temp);
			
			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(entry.getKey()));
			request.appendChild(name);
			
			Element URL = doc.createElement("url");
			URL.appendChild(doc.createTextNode(req.getURL()));
			request.appendChild(URL);
			
			Element cssPath = doc.createElement("cssPath");
			cssPath.appendChild(doc.createTextNode(""));
			request.appendChild(cssPath);
			
			Element useragent = doc.createElement("UserAgent");
			useragent.appendChild(doc.createTextNode(req.getRequestAgent().getName()));
			request.appendChild(useragent);
			
			Element referer = doc.createElement("referer");
			referer.appendChild(doc.createTextNode(req.getReferer()));
			request.appendChild(referer);
		}
		
	}else{
		Element request = doc.createElement("request");
		doc.getDocumentElement().appendChild(request);
		String temp = req.getPrice()+"";
		if(temp != null)
			request.setAttribute("price",temp );
		
		temp = req.getExecutionTime();
		if(temp != null)
			request.setAttribute("time", temp);
		
		Element name = doc.createElement("name");
		name.appendChild(doc.createTextNode(req.getName()));
		request.appendChild(name);
		
		
		Element URL = doc.createElement("url");
		URL.appendChild(doc.createTextNode(req.getURL()));
		request.appendChild(URL);
		
		Element cssPath = doc.createElement("cssPath");
		cssPath.appendChild(doc.createTextNode(req.getCssPath()));
		request.appendChild(cssPath);
		
		Element useragent = doc.createElement("UserAgent");
		useragent.appendChild(doc.createTextNode(req.getRequestAgent().getName()));
		request.appendChild(useragent);
		
		Element referer = doc.createElement("referer");
		referer.appendChild(doc.createTextNode(req.getReferer()));
		request.appendChild(referer);
	
	}

}
private static Document generateXMLObject(){
	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder;
	Document toReturn = null;
	try {
		docBuilder = docFactory.newDocumentBuilder();
		// root elements
		toReturn = docBuilder.newDocument();
		Element rootElement = toReturn.createElement("Results");
		toReturn.appendChild(rootElement);
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return toReturn;
}
}
