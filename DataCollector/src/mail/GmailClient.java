package mail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.security.GeneralSecurityException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimePart;

import org.w3c.dom.Document;

import com.sun.mail.util.MailSSLSocketFactory;

import configXML.ConfigXML;
import resultXML.ResultXML;
import userConfig.UserConfig;


 

 
public class GmailClient {
 
    private static final String SEPERSTOR_ = "_";
	private static final String RESULTS_OF = "Results of ";
	private String userName;
    private String password;
    private String sendingHost;
    private int sendingPort;
    private String from;
    private String to;
    private String subject;
    private String text;
    private String receivingHost;
    private static String passwordDataCollector = "";
    private static String passwordResultCollector = "";
    
    private String newConfigXML = "";
//    private int receivingPort;
 
    private void setAccountDetails(String userName,String password){
 
        this.userName=userName;//sender's email can also use as User Name
        this.password=password;
 
    }
 
    private void sendGmail(String from, String to, String subject, String text){
 
         // This will send mail from -->sender@gmail.com to -->receiver@gmail.com
 
        this.from=from;
        this.to=to;
        this.subject=subject;
        this.text=text;
 
        // For a Gmail account--sending mails-- host and port shold be as follows
 
        this.sendingHost="smtp.gmail.com";
        this.sendingPort=465;
 
        Properties props = new Properties();
 
        props.put("mail.smtp.host", this.sendingHost);
        props.put("mail.smtp.port", String.valueOf(this.sendingPort));
        props.put("mail.smtp.user", this.userName);
        props.put("mail.smtp.password", this.password);
        props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.required", true);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
       
        /*MailSSLSocketFactory sf;
		try {
			sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
	        props.put("mail.smtp.ssl.socketFactory", sf);
	        props.put("mail.smtp.socketFactory.port", "465");
		} catch (GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        */
 
         Session session1 = Session.getDefaultInstance(props);
 
         Message simpleMessage = new MimeMessage(session1);
 
        //MIME stands for Multipurpose Internet Mail Extensions
 
        InternetAddress fromAddress = null;
        InternetAddress toAddress = null;
 
        try {
 
            fromAddress = new InternetAddress(this.from);
            toAddress = new InternetAddress(this.to);
 
        } catch (AddressException e) {
 
            e.printStackTrace();
 
                      System.err.println("Sending email to: " + to + " failed !!!");
 
        }
 
        try {
 
            simpleMessage.setFrom(fromAddress);
 
            simpleMessage.setRecipient(RecipientType.TO, toAddress);
            
 
                        // to add CC or BCC use
                        // simpleMessage.setRecipient(RecipientType.CC, new InternetAddress("CC_Recipient@any_mail.com"));
                        // simpleMessage.setRecipient(RecipientType.BCC, new InternetAddress("CBC_Recipient@any_mail.com"));
 
            simpleMessage.setSubject(this.subject);
 
            simpleMessage.setText(this.text);
 
        //sometimes Transport.send(simpleMessage); is used, but for gmail it's different
 
            Transport transport = session1.getTransport("smtps");
 
            transport.connect (this.sendingHost,sendingPort, this.userName, this.password);
 
            transport.sendMessage(simpleMessage, simpleMessage.getAllRecipients());
            transport.close();
            System.out.println("Mail sent successfully ...");
           
 
        } catch (MessagingException e) {
 
            e.printStackTrace();
            System.err.println("Sending email to: " + to + " failed !!!");
        }
 
    }
 
    private void readGmail(){
 
        /*this will print subject of all messages in the inbox of sender@gmail.com*/
 
        this.receivingHost="imap.gmail.com";//for imap protocol
 
        Properties props2=System.getProperties();
 
        props2.setProperty("mail.store.protocol", "imaps");
        // I used imaps protocol here
 
        Session session2=Session.getDefaultInstance(props2, null);
 
            try {
 
                    Store store=session2.getStore("imaps");
 
                    store.connect(this.receivingHost,this.userName, this.password);
 
                    Folder folder=store.getFolder("INBOX");//get inbox
 
                    folder.open(Folder.READ_ONLY);//open folder only to read
 
                    Message message[]=folder.getMessages();
 
                    for(int i=0;i<message.length;i++){
 
                        //print subjects of all mails in the inbox
 
                        System.out.println(message[i].getSubject());
                        
                        
                        //anything else you want
 
                    }
 
                    //close connections
 
                    folder.close(true);
 
                    store.close();
 
            } catch (Exception e) {
 
                    System.out.println(e.toString());
 
            }
 
    }
    private int getLatestConfig(int actuelConfig){
    	 
        /*this will print subject of all messages in the inbox of sender@gmail.com*/
 
        this.receivingHost="imap.gmail.com";//for imap protocol
 
        Properties props2=System.getProperties();
 
        props2.setProperty("mail.store.protocol", "imaps");
        // I used imaps protocol here
 
        Session session2=Session.getDefaultInstance(props2, null);
 
            try {
            		boolean higherconffound = false;
                    Store store=session2.getStore("imaps");
 
                    store.connect(this.receivingHost,this.userName, this.password);
 
                    Folder folder=store.getFolder("INBOX");//get inbox
 
                    folder.open(Folder.READ_ONLY);//open folder only to read
 
                    Message message[]=folder.getMessages();
                    
                    for(int i=0;i<message.length;i++){
 
                        //print subjects of all mails in the inbox
                    	String MessageSubject = message[i].getSubject();
                    	if(MessageSubject.startsWith("New Config v.")){
                    		System.out.println("Config Found: "+MessageSubject);
                    		String numberString = MessageSubject.replace("New Config v.", "");
                    		int number = -1;
                    		try{
	                    		number = Integer.parseInt(numberString);
	                    		if(number > actuelConfig){
	                    			higherconffound = true;
	                    			actuelConfig = number;
	                    			Object ob = message[i].getContent();
	                    			//if(ob instanceof Multipart){
	                    			//MimePart mp = (MimePart) message[i].getContent();
		                               
		                                System.out.println("SENT DATE:" + message[i].getSentDate());
		                                System.out.println("SUBJECT:" + message[i].getSubject());
		                                System.out.println("CONTENT:" + message[i].getContent().toString());
		                                newConfigXML = message[i].getContent().toString();
	                    			//}else{
	                    			//	System.err.println("Message is not object of mulipart");
	                    			//}
	                    			
	                    		}
                    		} catch (NumberFormatException e){
                    			System.err.println("Wrong format. after "+"New Config v."+ "must be the number");
                    			number = -1;
                    		}
                    		
                    		
                    	}
                    	
                        
                        
                        //anything else you want
 
                    }
 
                    //close connections
 
                    folder.close(true);
 
                    store.close();
                    return actuelConfig;
 
            } catch (Exception e) {
 
                    System.out.println(e.toString());
                    return actuelConfig;
 
            }
 
    }
    private void getResults(){
   	 
        /*this will print subject of all messages in the inbox of sender@gmail.com*/
 
        this.receivingHost="imap.gmail.com";//for imap protocol
 
        Properties props2=System.getProperties();
 
        props2.setProperty("mail.store.protocol", "imaps");
        // I used imaps protocol here
 
        Session session2=Session.getDefaultInstance(props2, null);
 
            try {
            		
                    Store store=session2.getStore("imaps");
                    
                    store.connect(this.receivingHost,this.userName, this.password);
 
                    Folder folder=store.getFolder("INBOX");//get inbox
 
                    folder.open(Folder.READ_WRITE);//open folder only to read
 
                    Message message[]=folder.getMessages();
                    String resultsXML = "";
                    String OS = System.getProperty("os.name");
                    boolean windows = false;
                    if(OS.contains("Windows")){
                    	windows = true;
                    }
                    for(int i=0;i<message.length;i++){
                    	if(!message[i].isSet(Flags.Flag.ANSWERED)){
	                        //print subjects of all mails in the inbox
	                    	String MessageSubject = message[i].getSubject();
	                    	if(MessageSubject.matches(RESULTS_OF+".+"+SEPERSTOR_+"\\d*")){
	                    		System.out.println("Result Found: "+MessageSubject);
	                            System.out.println("SENT DATE:" + message[i].getSentDate());
	                            System.out.println("SUBJECT:" + message[i].getSubject());
	                            System.out.println("CONTENT:" + message[i].getContent().toString());
	                            resultsXML = message[i].getContent().toString();
	                            String id = MessageSubject.split(SEPERSTOR_)[1];
	                            Date time = new Timestamp(new Date().getTime());
	                            String DateTime = new SimpleDateFormat("MM-dd-yyyy HH.mm.ss").format(time);
	                            File dropbox = null;
	                            if(windows){
	                            	// dropbox = new File("C:\\Users\\"+System.getProperty("user.name")+"\\Dropbox\\ResultsOfRequest\\"+id);	
	                            	dropbox = new File("D:\\Dropbox\\ResultsOfRequest\\"+id);	
	                            }else{
	                            	 dropbox = new File("\\home\\korbi\\Dropbox\\ResultsOfRequest\\"+id);
	                            }
	                            
	                            if(!dropbox.exists()){
	                            	System.out.println("create User dir");
	                            	if(!dropbox.mkdir()){
	                            		System.err.println("creating dir faild");
	                            	}
	                            }
	                          //  File resultFile = new File(dropbox.getAbsolutePath()+"\\"+DateTime+".xml");
	                           // FileOutputStream out = new FileOutputStream(resultFile);
	                            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dropbox.getAbsolutePath()+"\\"+DateTime+".xml"),"UTF-8"));
	                            
	                           
	                            
	                            
	                            System.out.println("Write file started");
	                            out.write(resultsXML);
	                            out.close();
	                            
	                            message[i].setFlag(Flag.ANSWERED, true);
	                            System.out.println("File persits");
	                            
	                    	}else{
	                    		System.out.println("REGEX pattern doesnt match: "+message[i].getSubject());
	                    	}
                    	}else{
                    		System.out.println("Flag answerd set: "+message[i].getSubject());
                    	}
	           
                    }
 
                    //close connections
 
                    folder.close(true);
 
                    store.close();
                   
 
            } catch (Exception e) {
 
                    System.out.println(e.toString());
                   
 
            }
 
    }
    public static void sendNewConfig(ConfigXML config){
    	String mailFrom=new String("DataCollectorForBaKK@gmail.com");
    	 
        //Sender must be a Gmail Account holder
 
        String mailTo=new String("DataCollectorForBaKK@gmail.com");
 
        //but here you can send to any type of mail account
 
        String senderPassword=new String(passwordDataCollector);
 
        String senderUserName=new String("DataCollectorForBaKK");
 
        //Mention your email subject and content
 
        String mailSubject=new String("New Config v."+config.getVersionNumber());
 
        String mailText=new String(config.toString());
 
        //Create a GmailClient object
 
        GmailClient newGmailClient=new GmailClient();
 
        //Setting up account details
 
        newGmailClient.setAccountDetails(senderUserName, senderPassword);
 
        //Send mail
 
        newGmailClient.sendGmail(mailFrom, mailTo, mailSubject, mailText);
    }
    
 
    public static void transferResults(ResultXML results) {
 
        String mailFrom=new String("DataCollectorForBaKK@gmail.com");
 
        //Sender must be a Gmail Account holder
 
        String mailTo=new String("ResultCollectorForBAKK@gmail.com");
 
        //but here you can send to any type of mail account
 
        String senderPassword=new String(passwordDataCollector);
 
        String senderUserName=new String("DataCollectorForBaKK");
 
        //Mention your email subject and content
        UserConfig conf = UserConfig.loadConfig();
        results.setIDAttribute(conf.getIdNString());
        results.setRunCounter(conf.getNextRunCounter());
        String mailSubject=new String(RESULTS_OF+conf.getName()+SEPERSTOR_+conf.getIdNString());
 
        String mailText=new String(results.getXMLString());
 
        //Create a GmailClient object
 
        GmailClient newGmailClient=new GmailClient();
 
        //Setting up account details
 
        newGmailClient.setAccountDetails(senderUserName, senderPassword);
 
        //Send mail
 
        newGmailClient.sendGmail(mailFrom, mailTo, mailSubject, mailText);
 
        //Receive mails
 
       // newGmailClient.readGmail();
 
    }
    public void collectResultData(){
    	String senderPassword=new String(passwordResultCollector);
        String senderUserName=new String("ResultCollectorForBAKK");
        this.setAccountDetails(senderUserName, senderPassword);
        this.getResults();
    }
    public boolean isThereANewConfig(int acutelConfig){
    	String senderPassword=new String(passwordDataCollector);
        String senderUserName=new String("DataCollectorForBaKK");
        this.setAccountDetails(senderUserName, senderPassword);
        int highestConfNumber = this.getLatestConfig(acutelConfig);
        return highestConfNumber>acutelConfig;
        	
        
    }
    public String getNewConfigXMLString(){
    	return this.newConfigXML;
    }
 
}