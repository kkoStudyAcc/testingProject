package connection;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.Test;

import requestAgents.GenericAgent;
import requestAgents.RequestAgent;

// test GetContentFromAmazon method

public class RequestConnectorTest {
	@Test
	public void testGetContentFromAmazon(){
		
		// create new requestConnector
		RequestConnector requestConnector = new RequestConnector();
		// Response should be a Hashmap
		HashMap<String,String> response = new HashMap<String,String>();
		
		// create GenericAgent
		String useragent ="Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:32.0) Gecko/20100101 Firefox/32.0";
		int kindOFDevice = RequestAgent.normal;
		GenericAgent agent = new GenericAgent("bla",useragent , kindOFDevice,1920,1080);
		
		// send request to test method
		response = requestConnector.GetContentFromAmazon(null, "Test", agent);
		System.out.println(response);
		// is response empty?
		assertNotNull(response);
	}
}
 