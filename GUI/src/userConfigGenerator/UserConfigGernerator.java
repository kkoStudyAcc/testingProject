package userConfigGenerator;

import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import databaseConnector.MySQLAccess;

public class UserConfigGernerator {
public static void main(String[] args){
	MySQLAccess access = new MySQLAccess();
	ResultSet result = null;
	
	try {
		result = access.getUsers();
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		
		while(result.next()){
			String writeOut = "<?xml version='1.0' encoding='UTF-8'?><user Id='generate' runCounter='0'><name Id='name'>";
			String name = result.getString("name");
			writeOut += name+"</name><idNumber Id='idnumber'>";
			String myid = result.getString("myID");
			writeOut += myid+"</idNumber></user>";
			schreiben(myid+"userconfig.xml",writeOut);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
private static void schreiben(String filename,String g){
	FileWriter writer;
	File file = new File(filename);
     try {
       // new FileWriter(file ,true) - falls die Datei bereits existiert
       // werden die Bytes an das Ende der Datei geschrieben
       
       // new FileWriter(file) - falls die Datei bereits existiert
       // wird diese überschrieben
       writer = new FileWriter(file ,false);
       
       // Text wird in den Stream geschrieben
       writer.write(g);
       writer.flush();
       writer.close();
     }catch (Exception e){
    	 System.out.println(e);
     }
}
}
