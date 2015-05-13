package website;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class XPathValidator{

	public XPathValidator() {
		
	}
	public static double getValue(Document website, String xPath){
		
		return  Double.parseDouble(validate(website,xPath));
	}
	public static String getValueString(Document website, String xPath){
		return validate(website, xPath);
	}
	private static String validate(Document website, String XPath){
		XPathFactory xPathfactory = XPathFactory.newInstance();
		javax.xml.xpath.XPath xpathval = xPathfactory.newXPath();
		XPathExpression expr;
		try {
			expr = xpathval.compile(XPath);
			return (String) expr.evaluate(website, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}


	
	
}
