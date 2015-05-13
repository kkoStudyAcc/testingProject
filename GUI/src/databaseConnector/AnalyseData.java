package databaseConnector;

import resultXML.ResultXML;

public class AnalyseData {

	public static void main(String[] args) {
		AnalysisXML xml = new AnalysisXML();
		AnalysisXML refererXml = new AnalysisXML("referer.xml");
		MySQLAccess access = new MySQLAccess();
		try {
			access.readDataBase(xml);
			access.analyseReferer(refererXml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	System.out.println(ResultXML.toXMLString(xml.getDoc()));
		xml.persits();
		refererXml.persits();
		System.out.println("Finish");

	}

}
