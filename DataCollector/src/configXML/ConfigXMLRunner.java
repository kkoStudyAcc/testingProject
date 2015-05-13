package configXML;

import java.util.ArrayList;

import mail.GmailClient;



import requestObject.RequestObject;
import resultXML.ResultXML;

public class ConfigXMLRunner {

	ArrayList<RequestObject> requests ;
	ConfigXML con = null;
	public ConfigXMLRunner(String Path ){
		con = ConfigXML.loadConfigXML(Path);
		requests = con.getConfigRequest();
	}
	public ArrayList<String> getNames(){
		ArrayList<String>toReturn = new ArrayList<String>();
		for(RequestObject r : requests){
			toReturn.add(r.getURL());
		}
		return toReturn;
	}
	public double performRequestOnIndex(int index){
		RequestObject requ = requests.get(index);
		requ.perform();
		return requ.getPrice();
	}
	public void sendResults(){
		ResultXML xml = ResultXML.generateResultXML(this.requests);
		GmailClient.transferResults(xml);
	}
	public ArrayList<RequestObject> getRequestObjects(){
		return requests;
	}
	public ConfigXML getConfigXML(){
		if(this.con == null){
			System.err.println("no Config loaded");
		}
		return this.con;
	}
}
