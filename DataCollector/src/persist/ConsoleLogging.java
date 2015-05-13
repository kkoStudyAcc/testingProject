package persist;

import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class ConsoleLogging implements IPersits {

	GregorianCalendar cal = new GregorianCalendar(); 
	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
	
	protected ConsoleLogging(){}
	
	
	
	public boolean setUpNewTest(String IP) {
		
		System.out.println("New Test Date will be processed "+df.format(cal.getTime())+ " Source:["+IP+"]");
		return true;
	}

	public boolean addRequestData(String URL, double price) {
		System.out.println("Request to URL["+URL+"] "+df.format(cal.getTime())+"  parsed price "+price);
		return true;
	}

	public boolean close() {
		System.out.println("Request finnished. Test closed");
		return true;
	}

}
