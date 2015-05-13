package databaseConnector;

public class DatabaseRow {
	public String getRunCounter() {
		return runCounter;
	}
	public void setRunCounter(String runCounter) {
		this.runCounter = runCounter;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCssPath() {
		return cssPath;
	}
	public void setCssPath(String cssPath) {
		if(cssPath == null)
			this.cssPath = "";
		else
			this.cssPath = cssPath;
	}
	public String getUserAgent() {
		return UserAgent;
	}
	public void setUserAgent(String userAgent) {
		UserAgent = userAgent;
	}
	public String getReferer() {
		return Referer;
	}
	public void setReferer(String referer) {
		Referer = referer;
	}
	String runCounter;
	String uid;
	String price;
	String time;
	String name;
	String url;
	String cssPath;
	String UserAgent;
	String Referer;
}
