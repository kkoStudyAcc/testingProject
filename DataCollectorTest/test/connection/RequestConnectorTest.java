package connection;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import requestAgents.GenericAgent;
import requestAgents.RequestAgent;
import requestAgents.RequestAgentManager;

public class RequestConnectorTest {
	
	// create new requestConnector
	RequestConnector requestConnector = new RequestConnector();
	// Response should be a Hashmap
	HashMap<String,String> response = new HashMap<String,String>();
	
	// create GenericAgent
	String useragent ="Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:32.0) Gecko/20100101 Firefox/32.0";
	int kindOFDevice = RequestAgent.normal;
	GenericAgent agent = new GenericAgent("TestAgent", useragent, kindOFDevice, 1920, 1080);
	
	@Test
	public void testGetContentFromAmazon(){
		
		// send request to test method
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		//System.out.println(response);
		// is response empty?
		assertNotNull(response);
	}
	
	
	/* ------- Tests fuer Parameter: String ---------*/
	
	@Test(expected = IllegalArgumentException.class)
	public void testStringNull(){
		response = requestConnector.GetContentFromAmazon(null, "Test", agent);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testStringEmpty(){
		response = requestConnector.GetContentFromAmazon("", "Test", agent);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStringNotValidURL(){
		response = requestConnector.GetContentFromAmazon("Zeichenkette, keine URL", "Test", agent);
	}
	
	@Test
	public void testStringValidURL(){
		response = requestConnector.GetContentFromAmazon("http://google.de", "Test", agent);
		HashMap<String,String> expected = new HashMap<String,String>();
		assertEquals(expected, response);
	}
	@Test
	public void testStringValidAmazonURL(){
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		System.out.println(response);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));

	}
	
	/* ------- Tests fuer Parameter: referrer ---------*/
	
	
	
	
	
	/* ------- Tests fuer Parameter: requestAgent ---------*/
	
	
	
	
}
 