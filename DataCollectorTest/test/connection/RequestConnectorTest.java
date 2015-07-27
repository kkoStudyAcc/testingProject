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
	
	
	
	
	/* ------- Tests fuer Parameter: String ---------*/
	
	// ÄK 1
	@Test(expected = IllegalArgumentException.class)
	public void testStringNull(){
		response = requestConnector.GetContentFromAmazon(null, "Test", agent);
	}
	// ÄK 2
	@Test(expected = IllegalArgumentException.class)
	public void testStringEmpty(){
		response = requestConnector.GetContentFromAmazon("", "Test", agent);
	}
	// ÄK 3
	@Test(expected = IllegalArgumentException.class)
	public void testStringNotValidURL(){
		response = requestConnector.GetContentFromAmazon("Zeichenkette, keine URL", "Test", agent);
	}
	// ÄK 4
	@Test
	public void testStringValidURL(){
		response = requestConnector.GetContentFromAmazon("http://google.de", "Test", agent);
		HashMap<String,String> expected = new HashMap<String,String>();
		assertEquals(expected, response);
	}
	// ÄK 5
	@Test
	public void testStringValidAmazonURL(){
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));

	}
	
	
	
	
	
	
	
	/* ------- Tests fuer Parameter: referrer ---------*/
	
	// ÄK 1
	@Test(expected = IllegalArgumentException.class)
	public void testReferrerNull(){
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", null, agent);
	}
	
	// ÄK 2
	@Test
	public void testReferrerEmpty(){
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	
	// ÄK 3
	@Test
	public void testReferrerValidURL(){
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "http://google.de", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	
	// ÄK 4
	@Test
	public void testReferrerRandomString(){
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "!”§$%&&//(", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	
	
	
	
	
	
	
	
	/* ------- Tests fuer Parameter: requestAgent ---------*/
	
	/* RequestAgent: Parameter name */
	
	// ÄK 1
	@Test
	public void testRequestAgentNameNull(){
		GenericAgent agent = new GenericAgent(null, useragent, kindOFDevice, 1920, 1080);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	
	// ÄK 2
	@Test
	public void testRequestAgentNameEmpty(){
		GenericAgent agent = new GenericAgent("", useragent, kindOFDevice, 1920, 1080);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	
	// ÄK 3
	@Test
	public void testRequestAgentNameNotEmpty(){
		GenericAgent agent = new GenericAgent("Name des RequestAgents", useragent, kindOFDevice, 1920, 1080);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	

	/* RequestAgent: Parameter httpString */
	
	// ÄK 4
	@Test(expected = IllegalArgumentException.class)
	public void testRequestAgentHTTPStringNull(){
		GenericAgent agent = new GenericAgent("Name des RequestAgents", null, kindOFDevice, 1920, 1080);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
	}
	// ÄK 5
	public void testRequestAgentHTTPStringEmpty(){
		
	}
	// ÄK 6
	public void testRequestAgentHTTPStringRandom(){
		
	}
	// ÄK 7
	public void testRequestAgentHTTPStringKnownUserAgent(){
		
	}
	
	/* RequestAgent: Parameter kindOfDevice */
	
	// ÄK 8
	public void testRequestAgentKindOfDeviceNull(){
		
	}
	// ÄK 9
	public void testRequestAgentKindOfDeviceIntegerValid(){
		
	}
	// ÄK 10
	public void testRequestAgentKindOfDeviceIntegerTooSmall(){
		
	}
	// ÄK 11
	public void testRequestAgentKindOfDeviceIntegerTooBig(){
		
	}
	
	/* RequestAgent: Parameter width */
	
	// ÄK 12
	public void testRequestAgentWidthNull(){
		
	}
	// ÄK 13
	public void testRequestAgentWidthRandom(){
		
	}
	// ÄK 14
	public void testRequestAgentWidthKnownResolution(){
		
	}
	// ÄK 15
	public void testRequestAgentWidthMAXInteger(){
		
	}
	// ÄK 16
	public void testRequestAgentWidthMINInteger(){
		
	}
	
	/* RequestAgent: Parameter height */
	
	// ÄK 17
	public void testRequestAgentHeightNull(){
		
	}
	// ÄK 18
	public void testRequestAgentHeightRandom(){
		
	}
	// ÄK 19
	public void testRequestAgentHeightKnownResolution(){
		
	}
	// ÄK 20
	public void testRequestAgentHeightMAXInteger(){
		
	}
	// ÄK 21
	public void testRequestAgentHeightMINInteger(){
		
	}
	
	
	
	
}
 