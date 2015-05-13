package updateConfig;

import java.io.File;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import configXML.ConfigXML;
import mail.GmailClient;

public class ConfigUpdater {

	private static final String _OLD_VERSION = "_oldVersion_";

	public static void update(){
		GmailClient gmail = new GmailClient();	
		int acNumberOfConfig = ConfigXML.getLocalConfigNumber();
		if(gmail.isThereANewConfig(acNumberOfConfig)){
			String newConfigXMLString = gmail.getNewConfigXMLString();
			ConfigXML newconfigXML = ConfigXML.parse(newConfigXMLString);
			File old = new File(ConfigXML.REQUEST_XML_FILENAME);
			if(old.exists()){
				int maxversionNumber =0;
				int tempnumber = 0;
				
				for( String filename : old.getAbsoluteFile().getParentFile().list()){
					if(filename.contains(_OLD_VERSION)){
						String oldVersionNumber = filename.substring(filename.indexOf(_OLD_VERSION)+_OLD_VERSION.length());
						tempnumber = Integer.parseInt(oldVersionNumber);
						if(tempnumber > maxversionNumber){
							maxversionNumber = tempnumber;
						}
					}
				}
				System.out.println("Maximal Version Number with: "+maxversionNumber +" found");
				++maxversionNumber;
				old.renameTo(new File("request"+_OLD_VERSION+maxversionNumber));
				newconfigXML.persits(ConfigXML.REQUEST_XML_FILENAME);
			}
			//TODO: parse String create xml and read format it out to an file.
			System.out.println("-----------------------------------------------");
			System.out.println("--------------Here new Config------------------");
			System.out.println(newConfigXMLString);
			System.out.println("-----------------------------------------------");
		}
	}
}
