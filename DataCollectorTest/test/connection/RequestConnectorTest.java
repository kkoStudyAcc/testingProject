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
		GenericAgent agent = new GenericAgent("TestAgent", null, kindOFDevice, 1920, 1080);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
	}
	// ÄK 5
	@Test
	public void testRequestAgentHTTPStringEmpty(){
		GenericAgent agent = new GenericAgent("TestAgent", "", kindOFDevice, 1920, 1080);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	// ÄK 6
	@Test
	public void testRequestAgentHTTPStringRandom(){
		GenericAgent agent = new GenericAgent("TestAgent", "Beliebiger String", kindOFDevice, 1920, 1080);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	// ÄK 7
	@Test
	public void testRequestAgentHTTPStringKnownUserAgent(){
		GenericAgent agent = new GenericAgent("TestAgent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:32.0) Gecko/20100101 Firefox/32.0", kindOFDevice, 1920, 1080);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	
	/* RequestAgent: Parameter kindOfDevice */
	
	// ÄK 8
	@Test
	public void testRequestAgentKindOfDeviceIntegerValid(){
		GenericAgent agent = new GenericAgent("TestAgent", useragent, 0, 1920, 1080);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	// ÄK 9
	@Test
	public void testRequestAgentKindOfDeviceIntegerTooSmall(){
		GenericAgent agent = new GenericAgent("TestAgent", useragent, -1, 1920, 1080);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		HashMap<String,String> expected = new HashMap<String,String>();
		assertEquals(expected, response);
	}

	// ÄK 10
	@Test
	public void testRequestAgentKindOfDeviceIntegerTooBig(){
		GenericAgent agent = new GenericAgent("TestAgent", useragent, 4, 1920, 1080);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		HashMap<String,String> expected = new HashMap<String,String>();
		assertEquals(expected, response);
	}
	
	/* RequestAgent: Parameter width */
	
	// ÄK 11
	@Test
	public void testRequestAgentWidthRandom(){
		GenericAgent agent = new GenericAgent("TestAgent", useragent, kindOFDevice, 123456, 1080);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	// ÄK 12
	@Test
	public void testRequestAgentWidthKnownResolution(){
		GenericAgent agent = new GenericAgent("TestAgent", useragent, kindOFDevice, 800, 1080);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	// ÄK 13
	@Test
	public void testRequestAgentWidthMAXInteger(){
		GenericAgent agent = new GenericAgent("TestAgent", useragent, kindOFDevice, Integer.MAX_VALUE, 1080);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	// ÄK 14
	@Test
	public void testRequestAgentWidthMINInteger(){
		GenericAgent agent = new GenericAgent("TestAgent", useragent, kindOFDevice, Integer.MIN_VALUE, 1080);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	
	/* RequestAgent: Parameter height */
	
	// ÄK 15
	@Test
	public void testRequestAgentHeightRandom(){
		GenericAgent agent = new GenericAgent("TestAgent", useragent, kindOFDevice, 1920, 654321);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	// ÄK 16
	@Test
	public void testRequestAgentHeightKnownResolution(){
		GenericAgent agent = new GenericAgent("TestAgent", useragent, kindOFDevice, 1920, 600);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	// ÄK 17
	@Test
	public void testRequestAgentHeightMAXInteger(){
		GenericAgent agent = new GenericAgent("TestAgent", useragent, kindOFDevice, 1920, Integer.MAX_VALUE);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	// ÄK 18
	@Test
	public void testRequestAgentHeightMINInteger(){
		GenericAgent agent = new GenericAgent("TestAgent", useragent, kindOFDevice, 1920, Integer.MIN_VALUE);
		response = requestConnector.GetContentFromAmazon("http://sqat.eu-gb.mybluemix.net", "Test", agent);
		assertTrue(response.containsKey("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz") 
				&& response.get("BenQ GL2450H 61 cm (24 Zoll) LED Monitor (Full-HD, HDMI, VGA, 2ms Reaktionszeit) schwarz").equals("EUR 149,00"));
	}
	
	
	
	
}
 