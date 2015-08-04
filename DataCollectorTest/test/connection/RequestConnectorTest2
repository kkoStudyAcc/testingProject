package connection;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class RequestConnectorTest2 {
//--------------test evaluateXPath--------------//

	@Test
	public void test_evaluateXPath_Fall01(){
		File fXmlFile = new File("./testfiles/experimenteeResults.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr;
	
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			expr = xpath.compile("/bookstore/book[3]@category");
			String result = (String) expr.evaluate(doc, XPathConstants.STRING);
			
			assertTrue("xPathExpression doesn't match with the correct result", result.equals("WEB"));
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void test_evaluateXPath_Fall02(){
		File fXmlFile = new File("./testfiles/experimenteeResults.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr;
	
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			expr = xpath.compile("/bookstore/book[4]/title");
			String result = (String) expr.evaluate(doc, XPathConstants.STRING);
			
			assertTrue("xPathExpression doesn't match with the correct result", result.equals("Learning XML"));
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void test_evaluateXPath_Fall03(){
		File fXmlFile = new File("./testfiles/experimenteeResults.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr;
	
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			expr = xpath.compile("/bookstore/book[3]/author[4]");
			String result = (String) expr.evaluate(doc, XPathConstants.STRING);
			
			assertTrue("xPathExpression doesn't match with the correct result", result.equals("James Linn"));
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void test_evaluateXPath_Fall04(){
		File fXmlFile = new File("./worngpath.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr;
	
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			expr = xpath.compile("/bookstore/book[4]/title");
			String result = (String) expr.evaluate(doc, XPathConstants.STRING);
			
			assertFalse("xPathExpression doesn't match with the correct result", result.equals("Learning XML"));
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void test_evaluateXPath_Fall05(){
		File fXmlFile = new File("./testfiles/experimenteeResults.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr;
	
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			expr = xpath.compile("/bookstore/book[5]/title");
			String result = (String) expr.evaluate(doc, XPathConstants.STRING);
			
			assertFalse("xPathExpression doesn't match with the correct result", result.equals("Harry Potter"));
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void test_evaluateXPath_Fall06(){
		File fXmlFile = new File("./testfiles/experimenteeResults.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr;
	
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			expr = xpath.compile("/bookstore/book[3]/author[4]");
			String result = (String) expr.evaluate(doc, XPathConstants.STRING);
			
			assertFalse("xPathExpression doesn't match with the correct result", result.equals("Sinnfrei"));
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}	
	}
	
}	
	
	/*
	 *  
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 	@Test
	public void test_evaluateXPath_Fall01(){
		File fXmlFile = new File("./testfiles/experimenteeResults.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr;
	
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			expr = xpath.compile("/bookstore/book[2]/title");
			//XPathExpression expr = "/bookstore/book[2]/title";
			String result = (String) expr.evaluate(doc, XPathConstants.STRING);
			
			assertTrue("xPathExpression doesn't solve with the correct result", result.equals("Harry Potter"));
			
			//assertEquals(result, "CHILDREN");
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	*
	*
	*
	*
	*
	*
	*
	


	@Test
	public void test_evaluateXPath_Fall2(){
		ConfigXML config = ConfigXML.loadConfigXML("./testfiles/rightFormatedFile.xml");
		assertNotNull(config);
	}
	

	
	@Test
	public void testloadConfigXML_Fall1(){
		ConfigXML config = ConfigXML.loadConfigXML("./testfiles/rightFormatedFile.xml");
		assertNotNull(config);
	}
	@Test
	public void testloadConfigXML_Fall2(){
		ConfigXML config = ConfigXML.loadConfigXML("./testfiles/IOException-NotThere.xml");
		assertNull(config);
	}
	
	@Test
	public void testloadConfigXML_Fall3(){
		ConfigXML config = ConfigXML.loadConfigXML("./testfiles/SAXException.xml");
		assertNull(config);
	}
	
	@Test
	public void testloadConfigXML_Fall8(){
		ConfigXML config = ConfigXML.loadConfigXML(null);
		assertNull(config);
	}
	@Test
	public void testloadConfigXML_Fall9(){
		ConfigXML config = ConfigXML.loadConfigXML("");
		assertNull(config);
	}
	@Test
	public void testloadConfigXML_Fall10(){
		ConfigXML config = ConfigXML.loadConfigXML(".");
		assertNull(config);
	}
	@Test
	public void testloadConfigXML_Fall11(){
		ConfigXML config = ConfigXML.loadConfigXML("./");
		assertNull(config);
	}
	@Test
	public void testloadConfigXML_Fall12(){
		ConfigXML config = ConfigXML.loadConfigXML("notExistingFile.xml");
		assertNull(config);
	}
	@Test
	public void testloadConfigXML_Fall13(){
		ConfigXML config = ConfigXML.loadConfigXML(".notExistingFile.xml");
		assertNull(config);
	}
	@Test
	public void testloadConfigXML_Fall14(){
		ConfigXML config = ConfigXML.loadConfigXML("./testfiles/notExistingFile.xml");
		assertNull(config);
	}
	@Test
	public void testloadConfigXML_Fall15(){
		ConfigXML config = ConfigXML.loadConfigXML("./testfiles/accessDenied.xml");
		assertNull(config);
	}
	
	
	 
}
*/
