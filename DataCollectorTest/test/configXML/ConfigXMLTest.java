package configXML;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigXMLTest {
//--------------test loadConfigXML 
	@Test
	public void testloadConfigXML_Fall1(){
		ConfigXML config = ConfigXML.loadConfigXML("./testfiles/rightFormatedFile.xml");
		assertNotNull(config);
	}
	@Test
	public void testloadConfigXML_Fall2(){
		ConfigXML config = ConfigXML.loadConfigXML("./testfiles/IOException-NotThere.xml");
		assertNull(config);
	}
	
	@Test
	public void testloadConfigXML_Fall3(){
		ConfigXML config = ConfigXML.loadConfigXML("./testfiles/SAXException.xml");
		assertNull(config);
	}
	
	@Test
	public void testloadConfigXML_Fall8(){
		ConfigXML config = ConfigXML.loadConfigXML(null);
		assertNull(config);
	}
	@Test
	public void testloadConfigXML_Fall9(){
		ConfigXML config = ConfigXML.loadConfigXML("");
		assertNull(config);
	}
	@Test
	public void testloadConfigXML_Fall10(){
		ConfigXML config = ConfigXML.loadConfigXML(".");
		assertNull(config);
	}
	@Test
	public void testloadConfigXML_Fall11(){
		ConfigXML config = ConfigXML.loadConfigXML("./");
		assertNull(config);
	}
	@Test
	public void testloadConfigXML_Fall12(){
		ConfigXML config = ConfigXML.loadConfigXML("notExistingFile.xml");
		assertNull(config);
	}
	@Test
	public void testloadConfigXML_Fall13(){
		ConfigXML config = ConfigXML.loadConfigXML(".notExistingFile.xml");
		assertNull(config);
	}
	@Test
	public void testloadConfigXML_Fall14(){
		ConfigXML config = ConfigXML.loadConfigXML("./testfiles/notExistingFile.xml");
		assertNull(config);
	}
	@Test
	public void testloadConfigXML_Fall15(){
		ConfigXML config = ConfigXML.loadConfigXML("./testfiles/accessDenied.xml");
		assertNull(config);
	}
	
	
	 
}
