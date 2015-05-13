package requestObject;

import java.util.ArrayList;

import requestAgents.RequestAgents;

public class RequestObjectForModel {
	String Website;
	String CSS;
	String CSSMobile;
	String referer;
	ArrayList<RequestAgents> agents = new ArrayList<RequestAgents>();
	public RequestObjectForModel(String website, String cSS,String cssMobile,String referer,ArrayList<RequestAgents> bla) {
		super();
		Website = website;
		CSS = cSS;
		this.CSSMobile = cssMobile;
		this.agents = bla;
		this.referer = referer;
	}
	public String getWebsite() {
		return Website;
	}
	public String getCSS() {
		return CSS;
	}
	public String getCssMobile(){
		return this.CSSMobile;
	}
	public String getReferr() {
		return this.referer;
	}
	public ArrayList<RequestAgents> getAgents() {
		return agents;
	}
	public String toString(){
		return Website;
	}
}
