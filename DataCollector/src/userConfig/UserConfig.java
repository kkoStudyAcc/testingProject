package userConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import resultXML.ResultXML;
import configXML.ConfigXML;

public class UserConfig {
private static final String ATTRIBUTE_NUMBER = "idnumber";
private static final String ATTRIBUTE_ID = "name";
public static String USER_CONFIG_XML_FILENAME = "userConfig.xml";
private String name = "";
private String idNumber = "";
private int runCounter =0;
Document doc ;
	public static UserConfig loadConfig(){
		File fXmlFile = new File(USER_CONFIG_XML_FILENAME);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		try {
			UserConfig user = new UserConfig();
			dBuilder = dbFactory.newDocumentBuilder();
			user.doc = dBuilder.parse(fXmlFile);
			
			//doc.getDocumentElement().setIdAttribute("Id", true);
			//doc.getElementsByTagName("name")
			Node name = user.doc.getDocumentElement().getFirstChild();
			Node idnumber = name.getNextSibling();
			user.name = name.getTextContent().trim();
			user.idNumber = idnumber.getTextContent().trim();
			user.runCounter = Integer.parseInt(user.doc.getDocumentElement().getAttribute("runCounter").trim());
			return user;
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
	private UserConfig(){
		
	}
	public String getName(){
		return this.name;
	}
	public String getIdNString(){
		return this.idNumber;
	}
	public String getNextRunCounter(){
		runCounter++;
		doc.getDocumentElement().setAttribute("runCounter",runCounter+"");
		schreiben(ResultXML.toXMLString(doc));
		return runCounter +"";
	}
	public static void main(String[] args){
		loadConfig();
	}
	private void schreiben(String g){
		FileWriter writer;
		File file = new File(USER_CONFIG_XML_FILENAME);
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
}
