package databaseConnector;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.sql.Statement;

import resultXML.Result;
import resultXML.ResultXML;

public class Importer {
	 private Connection connect = null;
	 private String deleteStatement = "delete  from importofexcel";
	 private String insertIntoStatement =  "INSERT INTO badata (runCounter, uid,price,time,name,url,cssPath,UserAgent,referer) VALUES (?,?,?,?,?,?,?,?,?)";
	public static void main(String[] args) {
		Importer importer = new Importer();
		importer.importDataFromHDD("D:\\Dropbox\\ResultsOfRequest");
		importer.close();

	}
	public Importer(){
		 try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     // setup the connection with the DB.
	     try {
			connect = DriverManager
			     .getConnection("jdbc:mysql://localhost/testba?"
			         + "user=root&password=root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void close(){
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void importDataFromHDD(String Path){
		Statement statement;
		try {
			statement = connect.createStatement();
			statement.execute(deleteStatement);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		PreparedStatement insertIntoTable = null;
		
		File rootFolder = new File(Path);
		for(File f : rootFolder.listFiles()){
			if(!f.getName().equals("delete")){
				for(File xmlFile : f.listFiles()){
					ResultXML xml = ResultXML.generateResultXMLOutOFFile(xmlFile);
					for(Result result : xml.getResults()){
						System.out.println(result);
						try {
							try {
								insertIntoTable = connect.prepareStatement(insertIntoStatement);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							insertIntoTable.setString(1, xml.getRunCounter());
							insertIntoTable.setString(2, xml.getUID());
							insertIntoTable.setString(3, result.price);
							insertIntoTable.setString(4, result.time);
							insertIntoTable.setString(5, result.name);
							insertIntoTable.setString(6, result.url);
	
								insertIntoTable.setString(7, result.cssPath);
						
							insertIntoTable.setString(8, result.Useragent);
							insertIntoTable.setString(9, result.referer);
							insertIntoTable.execute();
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			}
		}
		try {
			insertIntoTable.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
