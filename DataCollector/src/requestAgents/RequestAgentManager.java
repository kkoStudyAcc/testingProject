package requestAgents;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import configXML.ConfigXML;

public class RequestAgentManager {
private static final String XML_ELEMENT_USERAGENTS = "userAgentConfig";
private static final String XML_ELEMENT_USERAGENT = "useragent";
private static final String REQUEST_AGENT_CONFIG_XML_FILENAME = "requestAgentConfig.xml";
public static final String ATTRIBUTE_ISMOBILE = "isMobile";
public static final String ATTRIBUTE_ISTABLET = "isTablet";
public static final String ATTRIBUTE_HEIGHT = "height";
public static final String ATTRIBUTE_WIDTH = "width";
private ArrayList<RequestAgent> agents = new ArrayList<RequestAgent>();
private Document XMLDOC = null;

public RequestAgentManager(){
	Document requestAgentConfig = loadRequestManagerXML(REQUEST_AGENT_CONFIG_XML_FILENAME);
	NodeList list = requestAgentConfig.getElementsByTagName(XML_ELEMENT_USERAGENT);
	for(int i = 0;i<list.getLength();++i){
		Node n = list.item(i);
		String name = n.getAttributes().getNamedItem("id").getNodeValue();
		boolean isMobile = Boolean.getBoolean(n.getAttributes().getNamedItem(ATTRIBUTE_ISMOBILE).getNodeValue());
		Node isTabletNode = n.getAttributes().getNamedItem(ATTRIBUTE_ISTABLET);
		boolean isTablet = false;
		if(isTabletNode != null){
			isTablet = Boolean.getBoolean(n.getAttributes().getNamedItem(ATTRIBUTE_ISMOBILE).getNodeValue());
		}
		String httpstring = n.getTextContent();
		
		agents.add(new GenericAgent(name, httpstring,calculateKindOfDevice(isMobile, isTablet),1920,1080));
	}
}
public static int calculateKindOfDevice(boolean isMobile, boolean isTablet){
	if(isMobile && !isTablet){
		return RequestAgent.mobile;
	}
	if(isMobile && isTablet){
		return RequestAgent.tablet;
	}
	return RequestAgent.normal;
		
}
public NodeList getRequestAgents(){
	if(XMLDOC == null){
		XMLDOC = loadRequestManagerXML(REQUEST_AGENT_CONFIG_XML_FILENAME);
	}
	return XMLDOC.getElementsByTagName(XML_ELEMENT_USERAGENTS);
}
public ArrayList<RequestAgent> getAgents(){
	return this.agents;
}
private static Document loadRequestManagerXML(String Path){
	File fXmlFile = new File(Path);
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder;
	
	try {
		dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		return doc;
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
}
