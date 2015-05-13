package databaseConnector;

import java.io.File;

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

import configXML.ConfigXML;

public class AnalysisXML {
private  String filename = "analysis.xml";
private Document doc ;
public AnalysisXML(String filename){
	this.filename = filename;
	init();
}
private void init(){
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder;
	
	try {
		dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.newDocument();
		Element rootElement = doc.createElement("Results");
		doc.appendChild(rootElement);
	}catch (Exception e){
		
	}
}
	public AnalysisXML(){
		init();
	}
	public void addNewEntry(DatabaseRow row){
		Element request = doc.createElement("request");
		doc.getDocumentElement().appendChild(request);
		String temp = row.getPrice()+"";
		if(temp != null)
			request.setAttribute("price",temp );
		
		temp = row.getTime();
		if(temp != null)
			request.setAttribute("time", temp);
		temp = row.getRunCounter();
		if(temp != null)
			request.setAttribute("runCounter", temp);
		
		temp = row.getUid();
		if(temp != null)
			request.setAttribute("Uid", temp);
		
		Element name = doc.createElement("name");
		name.appendChild(doc.createTextNode(row.getName()));
		request.appendChild(name);
		
		
		Element URL = doc.createElement("url");
		URL.appendChild(doc.createTextNode(row.getUrl()));
		request.appendChild(URL);
		
		Element cssPath = doc.createElement("cssPath");
		cssPath.appendChild(doc.createTextNode(row.getCssPath()));
		request.appendChild(cssPath);
		
		Element useragent = doc.createElement("UserAgent");
		useragent.appendChild(doc.createTextNode(row.getUserAgent()));
		request.appendChild(useragent);
		
		Element referer = doc.createElement("referer");
		referer.appendChild(doc.createTextNode(row.getReferer()));
		request.appendChild(referer);
	}
	public void persits(){
		File file = new File(this.filename);
		TransformerFactory fac = TransformerFactory.newInstance();
		Transformer trans = null;
		try {
			trans = fac.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DOMSource source = new DOMSource(doc);
		StreamResult res = new StreamResult(file);
		try {
			trans.transform(source, res);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Document getDoc(){
		return this.doc;
	}
}
