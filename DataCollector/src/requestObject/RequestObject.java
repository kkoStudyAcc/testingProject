package requestObject;

import java.util.HashMap;

import requestAgents.RequestAgent;


public interface RequestObject {
	static RequestObjectFactory factory = new RequestObjectFactory();
	void perform();
	double getPrice();
	String getURL();
	String getCssPath();
	String getName();
	String getReferer();
	RequestAgent getRequestAgent();
	String getExecutionTime();
	HashMap<String,Double> getAllPriceResults();
	boolean hasMulitblePrices();
}
