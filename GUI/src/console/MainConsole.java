package console;

import java.util.ArrayList;

import requestObject.RequestObject;
import configXML.ConfigXML;
import configXML.ConfigXMLRunner;
import thread.RequestThread;
import updateConfig.ConfigUpdater;
import userConfig.UserConfig;
import mail.GmailClient;

public class MainConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainConsole main = new MainConsole();
		main.run(args);
	}
	private void run(String[] args){
		//check if there is a new config available
		//ConfigUpdater.update();
		//
		System.out.println(args);
		if(args.length == 2){
			ConfigXML.REQUEST_XML_FILENAME = args[0];
			UserConfig.USER_CONFIG_XML_FILENAME= args[1];
			System.out.println("ConfigXML: "+ConfigXML.REQUEST_XML_FILENAME);
			System.out.println("UserConfig: "+UserConfig.USER_CONFIG_XML_FILENAME);
		}
		
		System.out.println("*-----------LOAD CONFIG FILE-------------*");
		ConfigXMLRunner runner = new ConfigXMLRunner(ConfigXML.REQUEST_XML_FILENAME);
		System.out.println("*-----------Config FILE LOADED-----------*");
		System.out.println("*-----STARTING REQUESTING WEBSITES-------*");
		final long start = System.currentTimeMillis();
		/*ArrayList<Thread> threads = new ArrayList<Thread>();
		for(RequestObject req : runner.getRequestObjects()){
			Thread thread1 = new Thread(new RequestThread( "Thread-1",req), req.getName());
			threads.add(thread1);
			thread1.start();
			//requ.perform();
		}
		for(Thread r : threads){
			try {
				r.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		runner.sendResults();
		*/
		
		for(RequestObject req : runner.getRequestObjects()){
			System.out.println("*---------------WORKING ON---------------*");
			System.out.println("*"+req.getName()+"*");
			System.out.println("*"+req.getRequestAgent().getName()+"*");
			try{
				req.perform();
			}catch (Exception e){
				System.err.println(e);
			}
			if(req.hasMulitblePrices()){
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		System.out.println("*----"+req.getPrice()+"----*");
		System.out.println("*------------REQUEST DONE----------------*");
		
		}
		
		//System.out.println("*---------SEND RESULTS PER MAIL----------*");
		//runner.sendResults();
		//System.out.println("*---------SEND MAIL SUCCESSFULL----------*");
		final long timeEnd = System.currentTimeMillis();
		final long duration = (timeEnd - start);
		 long sekunden = duration / 1000;
		long minuten = sekunden / 60;
		final long restmili = duration % 1000;
		sekunden = sekunden % 60;
		System.out.println("Testzeit: "+minuten +"min "+sekunden+"sek "+restmili+"mili"); 
		
	}

}
