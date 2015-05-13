package connection;

import java.util.Set;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.BrowserVersionFeatures;
import com.gargoylesoftware.htmlunit.PluginConfiguration;

public class GernericBrowser extends BrowserVersion{

	public GernericBrowser(String applicationName, String applicationVersion,
			String userAgent, float browserVersionNumeric) {
		super(applicationName, applicationVersion, userAgent, browserVersionNumeric);
		// TODO Auto-generated constructor stub
	}

	

	

	@Override
	public String getApplicationCodeName() {
		// TODO Auto-generated method stub
		return super.getApplicationCodeName();
	}

	@Override
	public String getApplicationMinorVersion() {
		// TODO Auto-generated method stub
		return super.getApplicationMinorVersion();
	}

	@Override
	public String getApplicationName() {
		// TODO Auto-generated method stub
		return super.getApplicationName();
	}

	@Override
	public String getApplicationVersion() {
		// TODO Auto-generated method stub
		return super.getApplicationVersion();
	}

	@Override
	public String getBrowserLanguage() {
		// TODO Auto-generated method stub
		return super.getBrowserLanguage();
	}

	@Override
	public float getBrowserVersionNumeric() {
		// TODO Auto-generated method stub
		return super.getBrowserVersionNumeric();
	}

	@Override
	public String getBuildId() {
		// TODO Auto-generated method stub
		return super.getBuildId();
	}

	@Override
	public String getCpuClass() {
		// TODO Auto-generated method stub
		return super.getCpuClass();
	}

	@Override
	public String getCssAcceptHeader() {
		// TODO Auto-generated method stub
		return super.getCssAcceptHeader();
	}

	@Override
	public String getHtmlAcceptHeader() {
		// TODO Auto-generated method stub
		return super.getHtmlAcceptHeader();
	}

	@Override
	public String getImgAcceptHeader() {
		// TODO Auto-generated method stub
		return super.getImgAcceptHeader();
	}

	@Override
	public String getNickname() {
		// TODO Auto-generated method stub
		return super.getNickname();
	}

	@Override
	public String getPlatform() {
		// TODO Auto-generated method stub
		return super.getPlatform();
	}

	@Override
	public Set<PluginConfiguration> getPlugins() {
		// TODO Auto-generated method stub
		return super.getPlugins();
	}

	@Override
	public String getScriptAcceptHeader() {
		// TODO Auto-generated method stub
		return super.getScriptAcceptHeader();
	}

	@Override
	public String getSystemLanguage() {
		// TODO Auto-generated method stub
		return super.getSystemLanguage();
	}

	@Override
	public String getUserAgent() {
		// TODO Auto-generated method stub
		return super.getUserAgent();
	}

	@Override
	public String getUserLanguage() {
		// TODO Auto-generated method stub
		return super.getUserLanguage();
	}

	@Override
	public String getVendor() {
		// TODO Auto-generated method stub
		return super.getVendor();
	}

	@Override
	public String getXmlHttpRequestAcceptHeader() {
		// TODO Auto-generated method stub
		return super.getXmlHttpRequestAcceptHeader();
	}

	@Override
	public boolean hasFeature(BrowserVersionFeatures property) {
		// TODO Auto-generated method stub
		return super.hasFeature(property);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean isOnLine() {
		// TODO Auto-generated method stub
		return super.isOnLine();
	}

	@Override
	public void setApplicationCodeName(String applicationCodeName) {
		// TODO Auto-generated method stub
		super.setApplicationCodeName(applicationCodeName);
	}

	@Override
	public void setApplicationMinorVersion(String applicationMinorVersion) {
		// TODO Auto-generated method stub
		super.setApplicationMinorVersion(applicationMinorVersion);
	}

	@Override
	public void setApplicationName(String applicationName) {
		// TODO Auto-generated method stub
		super.setApplicationName(applicationName);
	}

	@Override
	public void setApplicationVersion(String applicationVersion) {
		// TODO Auto-generated method stub
		super.setApplicationVersion(applicationVersion);
	}

	@Override
	public void setBrowserLanguage(String browserLanguage) {
		// TODO Auto-generated method stub
		super.setBrowserLanguage(browserLanguage);
	}

	@Override
	public void setBrowserVersion(float browserVersion) {
		// TODO Auto-generated method stub
		super.setBrowserVersion(browserVersion);
	}

	@Override
	public void setCpuClass(String cpuClass) {
		// TODO Auto-generated method stub
		super.setCpuClass(cpuClass);
	}

	@Override
	public void setCssAcceptHeader(String cssAcceptHeader) {
		// TODO Auto-generated method stub
		super.setCssAcceptHeader(cssAcceptHeader);
	}

	@Override
	public void setHtmlAcceptHeader(String htmlAcceptHeader) {
		// TODO Auto-generated method stub
		super.setHtmlAcceptHeader(htmlAcceptHeader);
	}

	@Override
	public void setImgAcceptHeader(String imgAcceptHeader) {
		// TODO Auto-generated method stub
		super.setImgAcceptHeader(imgAcceptHeader);
	}

	@Override
	public void setOnLine(boolean onLine) {
		// TODO Auto-generated method stub
		super.setOnLine(onLine);
	}

	@Override
	public void setPlatform(String platform) {
		// TODO Auto-generated method stub
		super.setPlatform(platform);
	}

	@Override
	public void setScriptAcceptHeader(String scriptAcceptHeader) {
		// TODO Auto-generated method stub
		super.setScriptAcceptHeader(scriptAcceptHeader);
	}

	@Override
	public void setSystemLanguage(String systemLanguage) {
		// TODO Auto-generated method stub
		super.setSystemLanguage(systemLanguage);
	}

	@Override
	public void setUserAgent(String userAgent) {
		// TODO Auto-generated method stub
		super.setUserAgent(userAgent);
	}

	@Override
	public void setUserLanguage(String userLanguage) {
		// TODO Auto-generated method stub
		super.setUserLanguage(userLanguage);
	}

	@Override
	public void setVendor(String vendor) {
		// TODO Auto-generated method stub
		super.setVendor(vendor);
	}

	@Override
	public void setXmlHttpRequestAcceptHeader(String xmlHttpRequestAcceptHeader) {
		// TODO Auto-generated method stub
		super.setXmlHttpRequestAcceptHeader(xmlHttpRequestAcceptHeader);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
