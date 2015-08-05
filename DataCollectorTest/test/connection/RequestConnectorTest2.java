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
			expr = xpath.compile("/bookstore/book[3]/@category");
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
	
// Code coverage nach Abhandlung von Fall 01: 70,6 % 	
	
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
	
// Code coverage nach Abhandlung von Fall 02: 70,6 % 	
	
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
	
// Code coverage nach Abhandlung von Fall 03: 70,6 % 
	
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

// Code coverage nach Abhandlung von Fall 04: 33,3 % 
	
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
	
// Code coverage nach Abhandlung von Fall 05: 70,6 % 	

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
	
// Code coverage nach Abhandlung von Fall 06: 70,6 % 	
	
	@Test
	public void test_evaluateXPath_Fall07(){
		File fXmlFile = new File("./testfiles/experimenteeResults.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr;
	
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			expr = xpath.compile("");
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
	
// Code coverage nach Abhandlung von Fall 07: 31,4 % 	
	
	@Test
	public void test_evaluateXPath_Fall08(){
		File fXmlFile = new File("./testfiles/experimenteeResults.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr;
	
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			expr = xpath.compile("!?&%");
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
	
	// Code coverage nach Abhandlung von Fall 08: 31,4 % 	
	
}	
