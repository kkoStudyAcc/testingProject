package requestAgents;


public class RequestAgentFactory {

	public int getCountOfAgents(){
		return 4;
	}
	
	/*
	 * 	static int IOS = 0;
	static int ANDROID = 1;
	static int MOZILLA = 2;
	static int SAFARI = 3;
	static int CHROME = 4;
	 * 
	 * */
	public String getNameOfAgentfromNumber(RequestAgents agent) throws Exception{
		switch(agent){
		case ANDROID:
			return "Android";
		case CHROME:
			return "Chrome";
		case IOS:
			return "IOS";
		case MOZILLA:
			return "Mozilla";
		case SAFARI:
			return "Safari";
		default:
			throw new Exception("number is not known");
		}
		
	}
	
	public RequestAgent createAgent(RequestAgents agent){
		switch(agent){
		case ANDROID:
		
		case CHROME:
			
		case IOS:
			
		case MOZILLA:
			
		case SAFARI:
			
		default:
			return null;
		}
	}
}
