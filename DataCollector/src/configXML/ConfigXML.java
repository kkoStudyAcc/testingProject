package configXML;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage;

import requestAgents.GenericAgent;
import requestAgents.RequestAgent;
import requestAgents.RequestAgentManager;
import requestAgents.RequestAgents;
import requestObject.RequestObject;


public class ConfigXML {

private static final String ATTRIBUTE_ID_USER_AGENT = "id";
private static final String ATTRIBUTE_VERSIONNUMBER = "version";
public static String REQUEST_XML_FILENAME = "request.xml";
private static final String ATTRIBUTE_ID = ATTRIBUTE_ID_USER_AGENT;
private static final String ATTRIBUTE_SELECTION_SPECIFIC = "specific";
public static final String ATTRIBUTE_SELECTION_ALL = "all";
private static final String ATTRIBUTE_SELECTION = "selection";
private static final String ATTRIBUTE_SELECTION_NOMOBILE = "noMobile";
private static final String ATTRIBUTE_SELECTION_ONLYMOBILE = "onlyMobile";
private static final String USER_AGENTS = "userAgents";
public static final String CSS_PATH = "cssPath";
private static final String WEBSITE = "website";
public static final String CONFIG = "config";
private static final String REFERER = "referer";
private static final String XML_ELEMENT_USERAGENT = "useragent";
private static final String XML_ELEMENT_USERAGENTS = "userAgentConfig";
private static final int ALL = 0;
private static final int NOMOBILE = 1;
private static final int ONYLMOBILE = 2;
private static final String ATTRIBUTE_USEMOBILEWEBSITE = "useMobileWebsite";
private static final String ATTIBUTE_SPECIALIMPL = "specialImpl";
public static final String ATTIBUTE_SPECIALIMPL_AMAZON = "amazonSearch";
public static final String ATTIBUTE_SPECIALIMPL_ORBITZ = "Orbitz";
public static final Object ATTIBUTE_SPECIALIMPL_SWOODO = "Swoodoo";
public static final Object ATTIBUTE_SPECIALIMPL_MOBILEOTTO = "mobileOTTO";

private Document configXML;
private ArrayList<RequestAgent> agents = new ArrayList<RequestAgent>();
/*
 * <priceCollectorConfig version="3">
 * <useragents>
 * <useragent> blaaaakjksdkfjdksfasd</useragent>
 * </useragents>
 * <config id="Alternate">
 * <website>http://www.alternate.de/Crucial/DIMM-16-GB-DDR3-1600-Kit-Arbeitsspeicher/html/product/1008052?</website>
 * <cssPath>#buyProduct > div.productShort > p.price > span</cssPath>
 * <userAgents selection="all"/>
 * </config>
 * </priceCollectorConfig>
 * 
 * 
 * 
 */


public static ConfigXML loadConfigXML(String Path){
	File fXmlFile = new File(Path);
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder;
	
	try {
		dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		return new ConfigXML(doc);
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
public static ConfigXML parse(String XMLString){
	DocumentBuilder db = null;
	try {
		db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	} catch (ParserConfigurationException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	org.xml.sax.InputSource is = new InputSource();
    is.setCharacterStream(new StringReader(XMLString));
    try {
		Document doc = null;
		try {
			doc = db.parse(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ConfigXML(doc);
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return null;
}
public static int getLocalConfigNumber(){
	ConfigXML xml = loadConfigXML(REQUEST_XML_FILENAME);
	return xml.getVersionNumber();
}
public int getVersionNumber(){
	Element root = this.configXML.getDocumentElement();
	int number = Integer.parseInt(root.getAttribute(ATTRIBUTE_VERSIONNUMBER));
	return number;
}

public ArrayList<RequestObject> getConfigRequest(){
	ArrayList<RequestObject> toReturn = new ArrayList<RequestObject>();
	boolean isMulitpleRequestObject = false;
	NodeList nodeList =  configXML.getDocumentElement().getElementsByTagName(CONFIG);
	String specialimpl = null;
	for(int i =0;i<nodeList.getLength();++i){
		isMulitpleRequestObject = false;
		Node node = nodeList.item(i);
		String id = node.getAttributes().getNamedItem(ATTRIBUTE_ID).getNodeValue();
		//System.out.println("DEBUG:"+id);
		
		
		
		Node web = node.getFirstChild();
		String webValue = web.getTextContent();
	
		Node useMobileSiteModification = web.getAttributes().getNamedItem(ATTRIBUTE_USEMOBILEWEBSITE);
		boolean useMobileSite = false;
		if(useMobileSiteModification != null){
			useMobileSite = Boolean.parseBoolean(useMobileSiteModification.getNodeValue());
			//if(useMobileSite)
			//	webValue = webValue.replace("www", "m");
		}
		Node cssPath = null;
		ArrayList<String> cssPaths = null;
		if(null==node.getAttributes().getNamedItem(ATTIBUTE_SPECIALIMPL)){
			
			cssPath = web.getNextSibling(); 
			cssPaths = new ArrayList<String>();
			
			while(cssPath.getNodeName().equals(CSS_PATH)){
				cssPaths.add(cssPath.getTextContent());
				cssPath = cssPath.getNextSibling();
			}
			isMulitpleRequestObject = false;
		}else{
			specialimpl = node.getAttributes().getNamedItem(ATTIBUTE_SPECIALIMPL).getNodeValue();
			isMulitpleRequestObject = true;
			cssPath = web.getNextSibling();
		}
		//Node cssNormale = web.getNextSibling();
		//String cssValue = cssNormale.getTextContent();
		
		
		//Node cssMobile = cssNormale.getNextSibling();
		//String cssMobileValue = cssMobile.getTextContent();
		
		Node referer = cssPath;
		String ref = referer.getTextContent();
		
		Node useragent = referer.getNextSibling();
		String selection = useragent.getAttributes().getNamedItem(ATTRIBUTE_SELECTION).getNodeValue();
		int userAgentCondition = -1;
		
		if(selection.equals(ATTRIBUTE_SELECTION_ALL)){
			userAgentCondition = ALL;
		}else{
			if(selection.equals(ATTRIBUTE_SELECTION_NOMOBILE)){
				userAgentCondition = NOMOBILE;
			}else{
				if(selection.equals(ATTRIBUTE_SELECTION_ONLYMOBILE)){
					userAgentCondition = ONYLMOBILE;
				}
			}
		}
		
		switch (userAgentCondition){
		case ALL:
			for(RequestAgent regAgent : this.agents){
				if(useMobileSite&&(regAgent.whichKindOfDevice()==RequestAgent.mobile||regAgent.whichKindOfDevice()==RequestAgent.tablet))
				{
					String tempWebValue = webValue.replace("www", "m");
					toReturn.add(RequestObject.factory.createRequestObjecti(isMulitpleRequestObject,id,tempWebValue, cssPaths,specialimpl,ref, regAgent));
					//System.out.println("replaced: "+webValue+"for agent:"+regAgent.getName());
				}else{
					toReturn.add(RequestObject.factory.createRequestObjecti(isMulitpleRequestObject,id,webValue, cssPaths,specialimpl,ref, regAgent));
				}
			}
			break;
		case NOMOBILE:
			for(RequestAgent regAgent : this.agents){
				if(regAgent.whichKindOfDevice() == RequestAgent.normal){
					toReturn.add(RequestObject.factory.createRequestObjecti(isMulitpleRequestObject,id,webValue, cssPaths,specialimpl,ref, regAgent));
				}
			}
			break;
		case ONYLMOBILE:
			for(RequestAgent regAgent : this.agents){
				if(regAgent.whichKindOfDevice() == RequestAgent.mobile){
					if(useMobileSite)
						webValue = webValue.replace("www", "m");
					toReturn.add(RequestObject.factory.createRequestObjecti(isMulitpleRequestObject,id,webValue, cssPaths,specialimpl,ref, regAgent));
					}
				}
			break;
		}
		
		
			
			/*for(RequestAgents e : RequestAgents.values()){
				
				toReturn.add(RequestObject.factory.createRequestObjecti(id,webValue, cssValue, e));
			}*/
		}
	

	return toReturn;
}
private ConfigXML(Document doc){
	configXML = doc;
	NodeList nodeListOfAgents =  configXML.getDocumentElement().getElementsByTagName(XML_ELEMENT_USERAGENT);
	for(int k =0;k<nodeListOfAgents.getLength();++k){
		Node useragentOutOfList = nodeListOfAgents.item(k);
		String name = useragentOutOfList.getAttributes().getNamedItem(ATTRIBUTE_ID_USER_AGENT).getNodeValue();
		boolean ismobile = (useragentOutOfList.getAttributes().getNamedItem(RequestAgentManager.ATTRIBUTE_ISMOBILE).getNodeValue()).equals("true");
		boolean isTablet = (useragentOutOfList.getAttributes().getNamedItem(RequestAgentManager.ATTRIBUTE_ISTABLET).getNodeValue()).equals("true");
		int width = Integer.parseInt((useragentOutOfList.getAttributes().getNamedItem(RequestAgentManager.ATTRIBUTE_WIDTH).getNodeValue()));
		int height = Integer.parseInt((useragentOutOfList.getAttributes().getNamedItem(RequestAgentManager.ATTRIBUTE_HEIGHT).getNodeValue()));
		String httpString = useragentOutOfList.getTextContent();
		httpString = httpString.trim();
		this.agents.add(new GenericAgent(name, httpString,RequestAgentManager.calculateKindOfDevice(ismobile, isTablet),width,height));
	}
	
}
public ConfigXML(int version){
	init(version);
}
private void init(int version){
	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder;
	configXML = null;
	try {
		docBuilder = docFactory.newDocumentBuilder();
		// root elements
		configXML = docBuilder.newDocument();
		Element rootElement = configXML.createElement("priceCollectorConfig");
		rootElement.setAttribute(ATTRIBUTE_VERSIONNUMBER, version+"");
		configXML.appendChild(rootElement);
		RequestAgentManager man = new RequestAgentManager();
		NodeList nodesToCopy = man.getRequestAgents();
		for(int k =0;k<nodesToCopy.getLength();++k) {
			Node n = nodesToCopy.item(k);
		    Node newNode = configXML.importNode(n, true);
		    configXML.getDocumentElement().appendChild(newNode);
		}
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void addNewRequest(String id,String website, String csspathNormal,String csspathMobile,String referer, ArrayList<RequestAgents> array){
	if(array == null)
		addnewConfigEntry(id,website, csspathNormal,csspathMobile,referer, true);
	
}
public void persits(String path){
	File file = new File(path);
	TransformerFactory fac = TransformerFactory.newInstance();
	Transformer trans = null;
	try {
		trans = fac.newTransformer();
	} catch (TransformerConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	DOMSource source = new DOMSource(configXML);
	StreamResult res = new StreamResult(file);
	try {
		trans.transform(source, res);
	} catch (TransformerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}


private void addnewConfigEntry(String id,String website, String cssPathNormale, String cssPathMobile,String referer, boolean b) {
	Element entry = createXMLEntryStructure(id,true);
	Node web = entry.getFirstChild();
	web.appendChild(createTextNode(website));
	Node css = web.getNextSibling();
	css.appendChild(createTextNode(cssPathNormale));
	Node cssMobile = css.getNextSibling();
	cssMobile.appendChild(createTextNode(cssPathMobile));
	Node refererN = cssMobile.getNextSibling();
	refererN.appendChild(createTextNode(referer));
	
	configXML.getDocumentElement().appendChild(entry);
	
}
private Node createTextNode(String text){
	return configXML.createTextNode(text);
}

private Element createXMLEntryStructure(String name,Boolean setUserAgentAttribute){
	Element entry = configXML.createElement(CONFIG);
	entry.setAttribute(ATTRIBUTE_ID, name);
	Element webs = configXML.createElement(WEBSITE);
	Element css = configXML.createElement(CSS_PATH);
	Element cssMobile = configXML.createElement(CSS_PATH);
	Element referer = configXML.createElement(REFERER);
	Element userAgent = configXML.createElement(USER_AGENTS);
	if(setUserAgentAttribute){
		userAgent.setAttribute(ATTRIBUTE_SELECTION, ATTRIBUTE_SELECTION_ALL);
	}
	else{
		userAgent.setAttribute(ATTRIBUTE_SELECTION, ATTRIBUTE_SELECTION_SPECIFIC);
	}
	entry.appendChild(webs);
	entry.appendChild(css);
	entry.appendChild(cssMobile);
	entry.appendChild(referer);
	entry.appendChild(userAgent);

	return entry;
	
}
@Override
public String toString(){
	try
    {
       DOMSource domSource = new DOMSource(this.configXML);
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
    }
}
	
}
