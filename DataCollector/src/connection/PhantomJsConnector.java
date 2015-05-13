package connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Scanner;

import requestAgents.GenericAgent;
import requestAgents.RequestAgent;

public class PhantomJsConnector {

	public HashMap<String,String> doSwoodooRequest(String url, String referer, RequestAgent requestAgent){
		HashMap<String,String> toReturn = new HashMap<String, String>();
		ProcessBuilder builder = new ProcessBuilder( "D:\\phantomjs\\phantomjs.exe", "D:\\phantomjs\\getContent.js",url,referer,requestAgent.httpString(),requestAgent.getWidth(),requestAgent.getHeight(),"30000" );
		builder.directory( new File("c:/") );
		builder.redirectErrorStream(true);
		StringBuilder buffer = new StringBuilder();
		try {
			Process p;
			p = builder.start();
		/*	BufferedReader in = new BufferedReader(new 

					InputStreamReader(p.getErrorStream()));
					String line;
					while ((line = in.readLine()) != null) {
					   System.out.println(line);
						buffer.append(line);
					}
					p.waitFor();
					System.out.println("ok!");

					in.close();
					*/
			Scanner s = new Scanner( p.getInputStream() ).useDelimiter( "\\Z" );
			//Scanner s2 = new Scanner( p.getErrorStream() ).useDelimiter( "\\Z" );
			buffer.append(s.next());
			//buffer.append(s2.next());
			//System.out.println( s.next() );
			//System.out.println( s2.next() );
			s.close();
			//s2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.schreiben(buffer.toString());
		String[] splited = buffer.toString().split("Wählen");
		for(int i =1;i<splited.length;i++){
			String[] lineSplit = splited[i].split("\\r");
			String key = "";
			if(lineSplit.length >= 8){
				key = lineSplit[2].replace("\n","") +"_" +lineSplit[3].split(" ")[0].replace("\n","")+"_" +lineSplit[4].split(" ")[0].replace("\n","")+"_"+lineSplit[5].replace("\n","");//+"_"+lineSplit[8].replace("\n","");
			}
			String value = lineSplit[7];
			if(!value.contains("Weiter")){
				value = value.replaceAll("[\\D&&[^,]&&[^.]]", "");
				toReturn.put(key, value);
			}
		}
		return toReturn;
	}
	public HashMap<String,String> doTestRequest(String url, String referer, RequestAgent requestAgent){
		HashMap<String,String> toReturn = new HashMap<String, String>();
		ProcessBuilder builder = new ProcessBuilder( "D:\\phantomjs\\phantomjs.exe", "D:\\phantomjs\\getContent.js",url,referer,requestAgent.httpString(),requestAgent.getWidth(),requestAgent.getHeight() );
		builder.directory( new File("c:/") );
		builder.redirectErrorStream(true);
		StringBuilder buffer = new StringBuilder();
		try {
			Process p;
			p = builder.start();
		/*	BufferedReader in = new BufferedReader(new 

					InputStreamReader(p.getErrorStream()));
					String line;
					while ((line = in.readLine()) != null) {
					   System.out.println(line);
						buffer.append(line);
					}
					p.waitFor();
					System.out.println("ok!");

					in.close();
					*/
			Scanner s = new Scanner( p.getInputStream() ).useDelimiter( "\\Z" );
			//Scanner s2 = new Scanner( p.getErrorStream() ).useDelimiter( "\\Z" );
			buffer.append(s.next());
			//buffer.append(s2.next());
			//System.out.println( s.next() );
			//System.out.println( s2.next() );
			s.close();
			//s2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.schreiben(buffer.toString());
		System.out.println((buffer.toString()));
		return toReturn;
	}
	public static void main(String[] args){
		RequestAgent agent = new GenericAgent("IPhone4", "'Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_2_1 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8C148 Safari/6533.18.5'", RequestAgent.mobile,1920,1080);
		//RequestAgent agent = new GenericAgent("Mac", "'Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2049.0 Safari/537.36'", RequestAgent.normal,1920,1080);
		HashMap<String,String> result = new PhantomJsConnector().doTestRequest("https://www.otto.de/p/aeg-kuehl-gefrierkombination-santo-s55830cnx2-aplusplusplus-200-5-cm-hoch-nofrost-427389419/#variationId=427390413-M48", "http://www.google.de", agent);
		//HashMap<String,String> result = new PhantomJsConnector().doOttoMobile("AEG","https://www.otto.de/p/aeg-kuehl-gefrierkombination-santo-s55830cnx2-aplusplusplus-200-5-cm-hoch-nofrost-427389419/#variationId=427390413-M48", "http://www.google.de", agent);
		System.out.println(result);
	}
	private void schreiben(String g){
		FileWriter writer;
		File file = new File("PhantomjsAusgabe.txt");
	     try {
	       // new FileWriter(file ,true) - falls die Datei bereits existiert
	       // werden die Bytes an das Ende der Datei geschrieben
	       
	       // new FileWriter(file) - falls die Datei bereits existiert
	       // wird diese überschrieben
	       writer = new FileWriter(file ,true);
	       
	       // Text wird in den Stream geschrieben
	       writer.write(g);
	       writer.flush();
	       writer.close();
	     }catch (Exception e){
	    	 System.out.println(e);
	     }
	}
	public HashMap<String,String> doOttoMobile(String id,String url, String referer, RequestAgent requestAgent){
		HashMap<String,String> toReturn = new HashMap<String, String>();
		ProcessBuilder builder = new ProcessBuilder( "D:\\phantomjs\\phantomjs.exe", "D:\\phantomjs\\getContent.js",url,referer,requestAgent.httpString(),requestAgent.getWidth(),requestAgent.getHeight(),"3000" );
		builder.directory( new File("c:/") );
		builder.redirectErrorStream(true);
		StringBuilder buffer = new StringBuilder();
		try {
			Process p;
			p = builder.start();
		/*	BufferedReader in = new BufferedReader(new 

					InputStreamReader(p.getErrorStream()));
					String line;
					while ((line = in.readLine()) != null) {
					   System.out.println(line);
						buffer.append(line);
					}
					p.waitFor();
					System.out.println("ok!");

					in.close();
					*/
			Scanner s = new Scanner( p.getInputStream() ).useDelimiter( "\\Z" );
			//Scanner s2 = new Scanner( p.getErrorStream() ).useDelimiter( "\\Z" );
			buffer.append(s.next());
			//buffer.append(s2.next());
			//System.out.println( s.next() );
			//System.out.println( s2.next() );
			s.close();
			//s2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.schreiben(buffer.toString());
		//System.out.println(buffer.toString());
		String[] splited = buffer.toString().split("\\r");
		int i =0;
		for(i =0;i<splited.length;i++){
		//	System.out.println(i+":"+splited[i]);
			if(splited[i].contains("jetzt € ")){
			//	System.out.println("FAUND on index"+i);
				break;
			}
		}
		//System.out.println("COUNTER: "+i);
		if(i<splited.length){
			String value = splited[i].replaceAll("[\\D&&[^,]&&[^.]]", "");
			toReturn.put(id, value);
		}
		return toReturn;
	}
	
}
