package databaseConnector;


import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;
  private String premaredStatement1 = "select * from (select Count(Distinct(price)) AS DifferentPrices,name from badata where price != -1 AND name NOT LIKE 'average_%'"+ 
		  							"group by name) AS tab1 where tab1.DifferentPrices > 1; ";
  private String premaredStatement2 =  "Select root.runCounter, root.uid,root.price,root.time,root.name,root.url,root.cssPath,root.UserAgent,root.referer from (select Count(Distinct(price)) As DifferentPrices,runCounter,uid from badata where name = ? group by runCounter,uid) AS tab1 , badata As root where "+
  "tab1.runCounter = root.runCounter and tab1.uid = root.uid and tab1.DifferentPrices>1 and root.name = ? ";
  
 private String averageSelect = " select * from badata where  name RLIKE 'average_*' and price != -1";
 
 private String selectAllnamesWithMoreThanOneReferer =  "select * from (select name,Count(*) AS RefererCount from"
 		+ " (select Count(Distinct(price)) AS DifferentPrices,name ,referer from badata where price != -1  AND name NOT LIKE 'average_%' "
 		+ "group by name,referer)"
 		+ "AS tab1 "
 		+ "	group by name  )"
 		+ " AS tab2"
 		+ "	where tab2.RefererCount >1";
 
 private String selectRefererForGivenName = "select Distinct(referer) from "
 		+ "badata  where name LIKE ?";
 
 private String selectDataForGivenNameAndPrice = "select Distinct(price) from badata where name Like ?"
 		+ "and price != -1 "
 		+ "and referer Like (?)";
 
 private String selectFinalData = "select * from badata where name like ?"
 		+ " and referer = ?"
 		+ " and price = ?";
 
  private String preparedStatementForUserConfig = "select * from user";
  public ResultSet getUsers() throws Exception{
	  try {
	  Class.forName("com.mysql.jdbc.Driver");
      // setup the connection with the DB.
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost/testba?"
              + "user=root&password=root");
      PreparedStatement getUser = connect.prepareStatement(preparedStatementForUserConfig);
      
      return getUser.executeQuery();
	  } catch (Exception e) {
	      throw e;
	    } finally {
	    	
	    }
  }
  public void analyseReferer(AnalysisXML xml) throws Exception{
	  try {
	      // this will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // setup the connection with the DB.
	      connect = DriverManager
	          .getConnection("jdbc:mysql://localhost/testba?"
	              + "user=root&password=root");

	      // statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      ResultSet names = statement
	              .executeQuery(this.selectAllnamesWithMoreThanOneReferer);
	      PreparedStatement selectRefer = connect.prepareStatement(this.selectRefererForGivenName);
	      PreparedStatement selectPriceData = connect.prepareStatement(this.selectDataForGivenNameAndPrice);
	      PreparedStatement selectFinalData = connect.prepareStatement(this.selectFinalData);
	      while (names.next()) {
	    	  HashMap<String,ArrayList<Double> > prices = new HashMap<String,ArrayList<Double> >();
	    	  ArrayList<String> nameArray = new ArrayList<String>();
	    	  String name = names.getString("name");
	    	  selectRefer.setString(1, name);
	    	  ResultSet refers = selectRefer.executeQuery();
	    	  while(refers.next()){
	    		 String referer = refers.getString("referer");
	    		 selectPriceData.setString(1, name);
	    		 selectPriceData.setString(2, referer);
	    		 ResultSet resultSetprices = selectPriceData.executeQuery();
	    		 ArrayList<Double> temp = new ArrayList<Double>();
	    		 while(resultSetprices.next()){
	    			temp.add(resultSetprices.getDouble("price"));
	    		 }
	    		 prices.put(referer, temp); 
	    		 nameArray.add(referer);
	    	}
	    	ArrayList<Double> a1 = prices.get(nameArray.get(0));
	    	ArrayList<Double> a2 = prices.get(nameArray.get(1));
	    	HashMap<String,ArrayList<Double>> toSelectPrices = new HashMap<String, ArrayList<Double>>();
	    	for(double d1 : a1){
	    		toSelectPrices.put(nameArray.get(0), new ArrayList<Double>());
	    		if(!a2.contains(d1)){
	    			toSelectPrices.get(nameArray.get(0)).add(d1);
	    		}
	    	}
	    	for(double d1 : a2){
	    		toSelectPrices.put(nameArray.get(1), new ArrayList<Double>());
	    		if(!a1.contains(d1)){
	    			toSelectPrices.get(nameArray.get(1)).add(d1);
	    		}
	    	}
	    	for(String key : toSelectPrices.keySet()){
	    		for(double d1 : toSelectPrices.get(key)){
	    			selectFinalData.setString(1, name);
	    			selectFinalData.setString(2, key);
	    			selectFinalData.setString(3, String.valueOf(d1));
	    			ResultSet finalData = selectFinalData.executeQuery();
	    			  while(finalData.next()){
	    				  DatabaseRow row = new DatabaseRow();
	    	        	  row.setCssPath(finalData.getString("cssPath"));
	    	        	  row.setName(finalData.getString("name"));
	    	        	  row.setTime(finalData.getString("time"));
	    	        	  row.setPrice(finalData.getString("price"));
	    	        	  row.setReferer(finalData.getString("referer"));
	    	        	  row.setRunCounter(finalData.getString("runCounter"));
	    	        	  row.setUid(finalData.getString("uid"));
	    	        	  row.setUrl(finalData.getString("url"));
	    	        	  row.setUserAgent(finalData.getString("UserAgent"));
	    	        	  xml.addNewEntry(row);
	    			  }
	    		}
	    	}
	   }
	  } catch (Exception e) {
	      throw e;
	    } finally {
			resultSet.close();
			statement.close();
			connect.close();
	    }
  }
  public void readDataBase(AnalysisXML xml) throws Exception {
    try {
      // this will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      // setup the connection with the DB.
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost/testba?"
              + "user=root&password=root");

      // statements allow to issue SQL queries to the database
      statement = connect.createStatement();
      // resultSet gets the result of the SQL query
      resultSet = statement
          .executeQuery(this.premaredStatement1);
      PreparedStatement statement2 = connect.prepareStatement(premaredStatement2);
      while (resultSet.next()) {
          // it is possible to get the columns via name
          // also possible to get the columns via the column number
          // which starts at 1
          // e.g., resultSet.getSTring(2);
          String DifferentPrices = resultSet.getString("DifferentPrices");
          String name = resultSet.getString("name");
         // System.out.println("DifferentPrices: " + DifferentPrices);
        //  System.out.println("name: " + name);
          
          statement2.setString(1, name);
          statement2.setString(2, name);
          ResultSet resultset2 = statement2.executeQuery();
          while(resultset2.next()){
        	  DatabaseRow row = new DatabaseRow();
        	  row.setCssPath(resultset2.getString("cssPath"));
        	  row.setName(resultset2.getString("name"));
        	  row.setTime(resultset2.getString("time"));
        	  row.setPrice(resultset2.getString("price"));
        	  row.setReferer(resultset2.getString("referer"));
        	  row.setRunCounter(resultset2.getString("runCounter"));
        	  row.setUid(resultset2.getString("uid"));
        	  row.setUrl(resultset2.getString("url"));
        	  row.setUserAgent(resultset2.getString("UserAgent"));
        	  xml.addNewEntry(row);
        	  
          }
        }
      
      resultSet = statement
              .executeQuery(this.averageSelect);
      while (resultSet.next()) {
           	  DatabaseRow row = new DatabaseRow();
        	  row.setCssPath(resultSet.getString("cssPath"));
        	  String name = resultSet.getString("name");
        	  if(name.startsWith("average_Swoodoo_Stuttgart_Oslo")){
        		  name = "average_Swoodoo_Stuttgart_Oslo";
        	  }
        	  if(name.startsWith("average_Swoodoo_Stuttgart_Berlin")){
        		  name = "average_Swoodoo_Stuttgart_Berlin";
        	  }
        	  if(name.startsWith("average_AmazonSearchHeangematte")){
        		  name = "average_AmazonSearchHeangematte";
        	  }
        	  if(name.startsWith("average_AmazonSearch_Minions")){
        		  name = "average_AmazonSearch_Minions";
        	  }
        	  if(name.startsWith("average_Orbitz_Berlin")){
        		  name = "average_Orbitz_Berlin";
        	  }
        	  if(name.startsWith("average_Orbitz_Hamburg")){
        		  name = "average_Orbitz_Hamburg";
        	  }
        	  
        	  
        	  row.setName(name);
        	  row.setTime(resultSet.getString("time"));
        	  row.setPrice(resultSet.getString("price"));
        	  row.setReferer(resultSet.getString("referer"));
        	  row.setRunCounter(resultSet.getString("runCounter"));
        	  row.setUid(resultSet.getString("uid"));
        	  row.setUrl(resultSet.getString("url"));
        	  row.setUserAgent(resultSet.getString("UserAgent"));
        	  xml.addNewEntry(row);
        	  
       }
      
      
      // preparedStatements can use variables and are more efficient
     // preparedStatement = connect
      //    .prepareStatement("insert into  FEEDBACK.COMMENTS values (default, ?, ?, ?, ? , ?, ?)");
      // "myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
      // parameters start with 1
     // preparedStatement.setString(1, "Test");
     // preparedStatement.setString(2, "TestEmail");
     // preparedStatement.setString(3, "TestWebpage");
     // preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
     // preparedStatement.setString(5, "TestSummary");
     // preparedStatement.setString(6, "TestComment");
     // preparedStatement.executeUpdate();

   //   preparedStatement = connect
     //     .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
     // resultSet = preparedStatement.executeQuery();
     // writeResultSet(resultSet);

      // remove again the insert comment
    //  preparedStatement = connect
    //  .prepareStatement("delete from FEEDBACK.COMMENTS where myuser= ? ; ");
   //   preparedStatement.setString(1, "Test");
    //  preparedStatement.executeUpdate();
      
     // resultSet = statement
     // .executeQuery("select * from FEEDBACK.COMMENTS");
     // writeMetaData(resultSet);
      
    } catch (Exception e) {
      throw e;
    } finally {
		resultSet.close();
		statement.close();
		connect.close();
    }

  }

  private void writeMetaData(ResultSet resultSet) throws SQLException {
    // now get some metadata from the database
    System.out.println("The columns in the table are: ");
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // resultSet is initialised before the first data set
    while (resultSet.next()) {
      // it is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g., resultSet.getSTring(2);
      String runCounter = resultSet.getString("runCounter");
      String uid = resultSet.getString("uid");
      String price = resultSet.getString("price");
      String name = resultSet.getString("name");
      String UserAgent = resultSet.getString("UserAgent");
      System.out.println("runCounter: " + runCounter);
      System.out.println("uid: " + uid);
      System.out.println("price: " + price);
      System.out.println("name: " + name);
      System.out.println("UserAgent: " + UserAgent);
    }
  }

  
} 